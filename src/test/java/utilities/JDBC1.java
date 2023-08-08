package utilities;

import java.sql.*;
import java.util.ArrayList;

public class JDBC1 {
    public static void main(String[] args) throws SQLException {
        // create connection:
        Connection cnn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/HR_production",
                "postgres",
                "Admin123"
        );

        // statement
        Statement stt = cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // read data from result set

      // ResultSet rs = stt.executeQuery("Select * from jobs");
       //rs.next();

        /**
         * print all countries from countries table
         */
       // printCountries(stt);
       // printAmountOfDepartmentByCountryId(stt);
        /**
         * write a query to get all emails in a result set
         * iterate through your rs and put all emails into ArrayList
         * iterate through arryList and change emails from @tutorial.org to mindtek.edu
         * print your list
         */
       // updateAllEmails(stt);
        updateAllEmails2(stt);
    }
    public static void printCountries(Statement stt) throws SQLException {
        ResultSet r = stt.executeQuery("SELECT country_name FROM countries");
        r.next();
        while (r.next()) System.out.println(r.getString("country_name"));
    }
    public static void printAmountOfDepartmentByCountryId(Statement stt) throws SQLException {
        ResultSet r = stt.executeQuery("SELECT l.country_id, count(d.department_name)\n" +
                "FROM departments d, locations l\n" +
                "WHERE d.location_id = l.location_id\n" +
                "GROUP BY l.country_id;");

        while (r.next()){
            System.out.println(r.getString("country_id")+" -> "+r.getString("count"));
        }
    }
    public static void updateAllEmails(Statement stt) throws SQLException {
        ResultSet rs = stt.executeQuery("SELECT email FROM employees");
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> changedEmails = new ArrayList<>();
        while (rs.next()){
            emails.add(rs.getString("email"));
        }
        for (String el: emails){
                changedEmails.add(el.substring(0,el.indexOf("@"))+"@mindtek.edu");
        }
        // put to db
        String updateQuery = "update employees set email = NEWEMAIL where employees.email = oldEmail";
        int i =0;
        for(String em: emails){
            updateQuery = "update employees set email = '" + changedEmails.get(i)+
                    "' where employees.email = '" + emails.get(i) +"'";
           // System.out.println(updateQuery);
            stt.executeUpdate(updateQuery);
            i++;
        }

    }
    public static void updateAllEmails2(Statement stt) throws SQLException {

    }
}
