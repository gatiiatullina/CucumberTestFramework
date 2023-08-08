package utilities;

import java.sql.*;
import java.util.ArrayList;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        // create connection:
        Connection cnn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/HR_production",
                "postgres",
                "Admin123"
        );

        // statement
        Statement stt = cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stt.execute("Select*from jobs");

        // read data from result set
        ResultSet rs = stt.executeQuery("Select * from jobs");
        rs.next();// to move pointer to the next row
       // rs.next();

        System.out.println(rs.getString("job_title"));
        System.out.println(rs.getInt(1));
        while(rs.next()) {
            System.out.println(rs.getString("job_title"));
            System.out.println(rs.getString("min_salary"));
        }

        /**
         *  print fullname of parent is a parent of fullname of child
         *          Penelope Gietz is a parent of Jack Gietz
          */

//        ResultSet employee = stt.executeQuery("SELECT CONCAT(e.first_name,' ',e.last_name) AS parent_name,\n" +
//                "CONCAT(d.first_name,' ',d.last_name) AS child_name\n" +
//                "FROM employees e\n" +
//                "INNER JOIN dependents d ON e.employee_id=d.employee_id;");
//        while (employee.next()) System.out.println(employee.getString("parent_name") +
//        " is a parent of "+ employee.getString("child_name"));

        /**
         * find out if there any emails not ending with @sgltutorialorg
         * 1. query to get all emails and put them intro rs
         * 2. put those to ArrayList
         * 3. find out if there any emails not ending with @sgltutorialorg
         * 4. if there is such email then print "Found different email"
         * 5. if all emails end with @sgltutorialorg then print "All emails are same"
         */
        findDifferentEmail(cnn,stt);

    }
    private static void findDifferentEmail(Connection cnn, Statement stt) throws SQLException {
        ResultSet res = stt.executeQuery("SELECT email FROM employees");
       // while (res.next()) System.out.println(res.getString("email"));
        ArrayList<String> emailList = new ArrayList<>();
        while (res.next()) emailList.add(res.getString("email"));
        boolean isWrongEmail = false;
        for (String e: emailList){
            if(!e.endsWith("sqltutorial.org")) {
                System.out.println("Wrong email: " + e);
                isWrongEmail = true;
            }
        }
        if(isWrongEmail) System.out.println("Found different email");
        else System.out.println("All emails are the same");
    }
}
