package fr.epita.quiz.services.impl.jdbc;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionDAO {

    public QuestionDAO() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("""
        CREATE TABLE IF NOT EXISTS QUESTION( ID INTEGER auto_increment, title VARCHAR(500))
        """);
       preparedStatement.execute();
       connection.close();
    }

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Config.resolveConf("db.url"), Config.resolveConf("db.user"), Config.resolveConf("db.pwd"));
        return connection;
    }

    public void create(Question question) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO QUESTION (title) VALUES (?)");
        preparedStatement.setString(1, question.getTitle());
        preparedStatement.execute();
        connection.close();
    }

}
