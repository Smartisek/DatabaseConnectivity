import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Connector {
    public static void main(String[] args) {
        String dbUrl= "jdbc:mysql://localhost:3306/gateocean";
        String username = "domal";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(dbUrl, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from capitan");

            System.out.println("SQL: Dselect * from capitan");
            System.out.println("passenger_id |" + " " + "first_name |" + " " + "last_name |" + " " + "date_of_birth |" + " " + "phone_number |" + " " + "license_number");
            while (resultSet.next()){

                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " "
                        +resultSet.getString(3) + " " + resultSet.getDate(4) + " " + resultSet.getString(5) + " "
                        + resultSet.getString(6));
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
