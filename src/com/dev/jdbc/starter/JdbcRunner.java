package com.dev.jdbc.starter;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {

    public static void main(String[] args) {
        String password = "Test";
        String username = "postgres";
        String url = "jdbc:postgresql://localhost:5432/demo";

        try {
            // Регистрируем драйвер
            Class.forName("org.postgresql.Driver");

            // Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection(url, username, password);

            // Создаем объект Statement для выполнения SQL-запросов
            Statement statement = connection.createStatement();

            // Выполняем SQL-запрос и получаем результат
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets");

            // Обрабатываем результат
            while (resultSet.next()) {
                // Получаем значения столбцов по их именам или индексам (индексация начинается с 1)
                String column1Value = resultSet.getString("ticket_no");
                String column2Value = resultSet.getString("book_ref");
                String column3Value = resultSet.getString("book_ref");
                String column4Value = resultSet.getString("passenger_name");
                String column5Value = resultSet.getString("contact_data");
                // Выводим значения в консоль
                System.out.println("Билет №: " + column1Value +
                        ", Бронь №: " + column2Value +
                        ", Пассажир ID: " + column3Value +
                        ", ФИО: " + column4Value +
                        ", Контакт: " + column5Value);
            }

            // Закрываем ресурсы
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
