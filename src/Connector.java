import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Connector {
    public static void main(String[] args) throws SQLException {
        getCapitan();
        passengersToNewYork();
//        String dbUrl= "jdbc:mysql://localhost:3306/gateocean";
//        String username = "domal";
//        String password = "";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection(dbUrl, username, password);
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from capitan");
//
//            System.out.println("SQL: Dselect * from capitan");
//            System.out.println("passenger_id |" + " " + "first_name |" + " " + "last_name |" + " " + "date_of_birth |" + " " + "phone_number |" + " " + "license_number");
//            while (resultSet.next()){
//
//                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " "
//                        +resultSet.getString(3) + " " + resultSet.getDate(4) + " " + resultSet.getString(5) + " "
//                        + resultSet.getString(6));
//            }
//            connection.close();
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
    }

    public static Connection connect(){
        String dbUrl= "jdbc:mysql://localhost:3306/gateocean";
        String username = "domal";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected");
            return connection;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<String> getCapitan() throws SQLException {
        try{
        Connection connection = connect();
            assert connection != null;
            PreparedStatement statementCapitan = connection.prepareStatement("select * from capitan");

        ResultSet result = statementCapitan.executeQuery();
        ArrayList<String> capitanList = new ArrayList<String>();

            System.out.println("SQL: select * from capitan");
            System.out.println("capitan_id |" + " " + "first_name |" + " " + "last_name |" + " " + "date_of_birth |" + " " + "phone_number |" + " " + "license_number");
        while (result.next()) {
            System.out.println(result.getInt(1) + " " + result.getString(2) + " "
                    + result.getString(3) + " " + result.getDate(4) + " " + result.getString(5) + " "
                    + result.getString(6));
            capitanList.add(result.getString(1));
        }
        System.out.println("Capitan list above" + "\n");
//        return capitanList;
    } catch (Exception e){System.out.println(e);}
        return null;
    }

    public static ArrayList<String> passengersToNewYork() throws SQLException{
        try{
        Connection connection = connect();
        assert  connection != null;
        PreparedStatement statementNewYork = connection.prepareStatement("select p.first_name, p.last_name, p.date_of_birth, p.phone_number\n" +
                "from passenger p, cruise c, booking b\n" +
                "where p.passenger_id = b.passenger_id\n" +
                "and b.cruise_id = c.cruise_id\n" +
                "and c.end_destination = \"New York\"\n" +
                "order by (p.first_name) ASC;");

        ResultSet result = statementNewYork.executeQuery();
        ArrayList<String> newYorkList = new ArrayList<String>();

        System.out.println("SQL: select p.first_name, p.last_name, p.date_of_birth, p.phone_number\n" +
                "from passenger p, cruise c, booking b\n" +
                "where p.passenger_id = b.passenger_id\n" +
                "and b.cruise_id = c.cruise_id\n" +
                "and c.end_destination = \"New York\"\n" +
                "order by (p.first_name) ASC;" + "\n");
        System.out.println("first_name |" + " " + "last_name |" + " " + "date_of_birth |" + " " + "phone_number |");

        while(result.next()){
            System.out.println(result.getString(1) + " " + result.getString(2) + " "
                    + result.getDate(3) + " " + result.getString(4));

        }
        System.out.println("List above");
    }catch (Exception e){System.out.println(e);}
        return null;
    }
}

