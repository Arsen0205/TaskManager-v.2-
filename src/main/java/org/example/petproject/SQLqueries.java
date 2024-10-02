package org.example.petproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLqueries{

    public static boolean login(String number, String password){
        String selectSQL = "SELECT * FROM users WHERE phone = ?";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){

            preparedStatement.setString(1, number);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                if (PasswordUtils.checkPassword(password, resultSet.getString("password"))){
                    System.out.println("Здравствуйте, " + resultSet.getString("name"));
                    CurrentUser.setUserId(resultSet.getInt("user_id"));
                    return true;
                }else{
                    System.out.println("Неверный пароль или логин");
                    return false;
                }
            }else{
                System.out.println("Пользователь не найден");
                return false;
            }
        }catch (SQLException e){
            System.out.println("Ошибка базы данных при входе" + e.getMessage());
            return false;
        }
    }

    public static boolean register(String name, String number, String password){
        String insertUserSQL = "INSERT INTO users (name, phone, password) VALUES (?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, number);
            preparedStatement.setString(3, password);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0){
                System.out.println("Вы успешно зарегистрировались");
                return true;
            }else{
                System.out.println("Ошибка при регистрации пользователя");
                return false;
            }
        }catch (SQLException e){
            System.out.println("Ошибка базы дданых при регистрации" + e.getMessage());
            return false;
        }
    }


    public static boolean addTask(String title, String description){
        String insertSQL = "INSERT INTO tasks (user_id, title, description) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
            preparedStatement.setInt(1, CurrentUser.getUserId());
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0){
                System.out.println("Задача успешно добавлена!");
                return true;
            }else{
                System.out.println("Ошибка при добавлении задачи!");
                return false;
            }
        }catch (SQLException e){
            System.out.println("Ошибка базы дданых при добавлении задачи: " + e.getMessage());
            return false;
        }
    }

    public static List<Task> loadTasks(int userId) throws Exception {
        List<Task> tasks = new ArrayList<>();

        // Открытие соединения с базой данных
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM tasks WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            // Добавление всех задач в список
            while (resultSet.next()) {
                int taskId = resultSet.getInt("task_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                tasks.add(new Task(taskId, title, description));
            }
        }
        return tasks;
    }

    public static void perfomTask(int taskId){
        String deletSQL = "DELETE FROM tasks WHERE task_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deletSQL)) {
            preparedStatement.setInt(1, taskId);

            preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println("Ошибка базы данных при удалении задачи: " + e.getMessage());
        }
    }
}
