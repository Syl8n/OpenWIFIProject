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

public class WifiService {

    private static String setRange(String str, int start, int end) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        sb.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8"));
        sb.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8"));
        return sb.toString();
    }

    public static void getList(String key, String docType, String category) throws IOException, ParseException {
        int start = 1;
        int end = 1;
        int maxEnd = 1;
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode(key,"UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode(docType,"UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode(category,"UTF-8"));
        String urlStr = urlBuilder.toString();
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
            System.out.println(rd);
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
                maxEnd = (int) wifiMeta.get("list_total_count");
            } else {
                JSONArray wifiArr = (JSONArray) wifiMeta.get("row");
                for (Object obj : wifiArr) {
                    Gson gson = new Gson();
                    String row = gson.toJson(obj);
                    Wifi wifi = gson.fromJson(row, Wifi.class);
                    insert(wifi);
                }
            }
            start = end + 1;
            end = Math.min(start + 1000, maxEnd);
        }while(start <= maxEnd);
    }

    public static void insert(Wifi wifi){

    }

    public static void search(double lat, double lnt){

    }

    public static void logging(){

    }
}