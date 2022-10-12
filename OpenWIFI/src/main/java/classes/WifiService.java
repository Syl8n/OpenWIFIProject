package classes;

import lombok.val;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sqlite.core.DB;

import java.sql.*;

public class WifiService {
    private static final String DB_URL = "jdbc:sqlite:wifiTest.db";
    private static final String DB_CLASS = "org.sqlite.JDBC";

    private static String setRange(String str, long start, long end) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        sb.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8"));
        sb.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8"));
        return sb.toString();
    }

    public static long getList(String key, String docType, String category) throws IOException, ParseException {
        long start = 1;
        long end = 1;
        long maxEnd = 1;
        final long TEST_MAX = 10;
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode(key,"UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode(docType,"UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode(category,"UTF-8"));
        String urlStr = urlBuilder.toString();
        WifiConnect.createTable(DB_CLASS, DB_URL, "wifi_test");
        WifiConnect.truncateTable(DB_CLASS, DB_URL, "wifi_test");
        try {
            Class.forName(DB_CLASS);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection(DB_URL);
            System.out.println("DB connected");
        } catch (SQLException e){
            e.printStackTrace();
        }
        do {
            URL url = new URL(setRange(urlStr, start, end));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                System.out.println("Succeeded");
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                System.out.println("Failed");
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            System.out.println(sb.toString());
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(sb.toString());
            JSONObject wifiMeta = (JSONObject) jsonObject.get("TbPublicWifiInfo");

            if (end == 1) {
                maxEnd = TEST_MAX != 0 ? TEST_MAX : (long) wifiMeta.get("list_total_count");
            } else {
                JSONArray wifiArr = (JSONArray) wifiMeta.get("row");
                for (Object obj : wifiArr) {
                    Gson gson = new Gson();
                    String row = gson.toJson(obj);
                    Wifi wifi = gson.fromJson(row, Wifi.class);
                    insert(dbConn, wifi);
                }
            }
            start = end + 1;
            end = Math.min(start + 1000, maxEnd);
        }while(start <= maxEnd);
        try {
            if (dbConn != null && !dbConn.isClosed()) {
                dbConn.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return maxEnd;
    }

    public static void insert(Connection connection, Wifi wifi) {
        PreparedStatement ps = null;

        try {
            // Insert data
            String sql = " INSERT into WIFI_TEST (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
                    " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, " +
                    " X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, " +
                    " LAT, LNT, WORK_DTTM) " +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, wifi.getX_SWIFI_MGR_NO());
            ps.setString(2, wifi.getX_SWIFI_WRDOFC());
            ps.setString(3, wifi.getX_SWIFI_MAIN_NM());
            ps.setString(4, wifi.getX_SWIFI_ADRES1());
            ps.setString(5, wifi.getX_SWIFI_ADRES2());
            ps.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
            ps.setString(7, wifi.getX_SWIFI_INSTL_TY());
            ps.setString(8, wifi.getX_SWIFI_INSTL_MBY());
            ps.setString(9, wifi.getX_SWIFI_SVC_SE());
            ps.setString(10, wifi.getX_SWIFI_CMCWR());
            ps.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
            ps.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
            ps.setString(13, wifi.getX_SWIFI_REMARS3());
            ps.setFloat(14, Float.parseFloat(wifi.getLAT()));
            ps.setFloat(15, Float.parseFloat(wifi.getLNT()));
            ps.setString(16, wifi.getWORK_DTTM());

            int n = ps.executeUpdate();

            if(n > 0){
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void search(double lat, double lnt){

        try {
            Class.forName(DB_CLASS);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(DB_URL);

            String sql = " select * from WIFI_TEST ";
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while(rs.next()){
                String mgrNo = rs.getString("X_SWIFI_MGR_NO");

                System.out.println(mgrNo);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void logging(){

    }
}