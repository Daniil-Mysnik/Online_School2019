package net.thumbtack.school.capitalOfCountries.service;


import net.thumbtack.school.capitalOfCountries.jdbc.JdbcUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbsService {

    public static void createDB() throws SQLException {
        JdbcUtils.createConnection();
        ScriptRunner runner = new ScriptRunner(JdbcUtils.getConnection());
        try {
            runner.setAutoCommit(true);
            runner.setSendFullScript(false);
            runner.runScript(new FileReader(new File("sql/capital.sql")));
        } catch (FileNotFoundException e) {
            throw new SQLException();
        }
    }

    public static void deleteCapitals() throws SQLException {
        String deleteQuery = "delete from capitals";
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.executeUpdate();
        }
    }
}
