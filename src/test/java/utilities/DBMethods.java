package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DBMethods {
    ResultSet runSelectQuery(String query) throws SQLException;
    void runUpdateQuery(String query) throws SQLException;
    void runInsertQuery(String query) throws SQLException;
    void runDeleteQuery(String query) throws SQLException;
}
