package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        DB db = new DB();
        /**
         * run insert query
         */
//        String query = "insert into countries values ('NK','North Korea',3)";
//        db.runInsertQuery(query);


        /**
         * run delete query
         */
//        String query = "delete from countries where country_id = 'BR'";
//        db.runDeleteQuery(query);

        /**
         * run update query
         */
//        String query = "Update employees Set email = 'test@gmail.com' where employee_id=100";
//        db.runUpdateQuery(query);
/**
 * run select query
 */
       // String query = "Select * from dependents";
       // ResultSet rs = db.executeSelectQuery(query);

       // ResultSet rs = db.runSelectQuery("*","employees");

     // while (rs.next()) System.out.println(rs.getString("first_name")+ " "+ rs.getString("last_name"));
     //   List<Map<String,Object>> list = db.getTableForQuery(query);
     //  System.out.println(list);

        /**
         * Second method of run select query
         */
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("first_name");
        columnNames.add("last_name");
        columnNames.add("phone_number");

        ResultSet rs = db.runSelectQuery(columnNames, "employees");
        while (rs.next()) System.out.println(rs.getString("first_name") + " " +
                rs.getString("phone_number"));
        db.close();
    }
}
