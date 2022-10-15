package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 4기 서진우

public class WifiConnect {
    public static void truncateTable(String dbClass, String dbUrl, String tbName) {
        try {
            Class.forName(dbClass);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection(dbUrl);

            // Delete data
            String sql = " DELETE from " + tbName;
            ps = connection.prepareStatement(sql);

            int n = ps.executeUpdate();
            if(n > 0){
                System.out.println("메인테이블 삭제 성공");
            } else {
                System.out.println("메인테이블 실패");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
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

    public static void createTable(String dbClass, String dbUrl, String tbName) {

        try {
            Class.forName(dbClass);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(dbUrl);

            // Create table
            String sql = " CREATE TABLE IF NOT EXISTS " + tbName + " ( " +
                    " X_SWIFI_MGR_NO        TEXT, " +
                    " X_SWIFI_WRDOFC        TEXT, " +
                    " X_SWIFI_MAIN_NM       TEXT, " +
                    " X_SWIFI_ADRES1        TEXT, " +
                    " X_SWIFI_ADRES2        TEXT, " +
                    " X_SWIFI_INSTL_FLOOR   TEXT, " +
                    " X_SWIFI_INSTL_TY      TEXT, " +
                    " X_SWIFI_INSTL_MBY     TEXT, " +
                    " X_SWIFI_SVC_SE        TEXT, " +
                    " X_SWIFI_CMCWR         TEXT, " +
                    " X_SWIFI_CNSTC_YEAR    TEXT, " +
                    " X_SWIFI_INOUT_DOOR    TEXT, " +
                    " X_SWIFI_REMARS3       TEXT, " +
                    " LAT                   TEXT, " +
                    " LNT                   TEXT, " +
                    " WORK_DTTM             TEXT, " +
                    " PRIMARY KEY(X_SWIFI_MGR_NO)) ";
            ps = connection.prepareStatement(sql);

            int n = ps.executeUpdate();
            if(n > 0){
                System.out.println("메인테이블 생성 성공");
            } else {
                System.out.println("메인테이블 생성 실패");
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

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void createLogTable(String dbClass, String dbUrl, String tbName) {

        try {
            Class.forName(dbClass);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(dbUrl);

            // Create table
            String sql = " CREATE TABLE IF NOT EXISTS " + tbName + " ( " +
                    " ID    INTEGER, " +
                    " LAT   REAL, " +
                    " LNT   REAL, " +
                    " DTTM  TEXT, " +
                    " PRIMARY KEY(ID AUTOINCREMENT)) ";
            ps = connection.prepareStatement(sql);

            int n = ps.executeUpdate();
            if(n > 0){
                System.out.println("로그테이블 생성 성공");
            } else {
                System.out.println("로그테이블 생성 실패");
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

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
