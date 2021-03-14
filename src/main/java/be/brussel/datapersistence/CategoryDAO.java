package be.brussel.datapersistence;

import be.brussel.model.Category;
import be.brussel.service.ConnectionManager;
import be.brussel.service.ServiceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private ConnectionManager connectionManager = ServiceFactory.createConnectionManager();


    public List<Category> getCategories() {

        List<Category> categories = new ArrayList<>();
        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){

            String query = "SELECT * FROM Categories";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String categoryName = rs.getString("Category");

                Category category = new Category(id, categoryName);
                categories.add(category);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categories;
    }


    public Category createCategory(Category category){

        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){
            String query = String.format("INSERT INTO Categories (Category) VALUES('%s')", category.getCategoryName());
            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return getCategory(category);
    }

    public Category updateCategory(Category category) {

        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()) {
            String query = String.format("UPDATE Categories SET Category = '%s' WHERE Id = %s", category.getCategoryName(), category.getId());
            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return getCategory(category);
    }


    public boolean deleteCategory(Category category) {
        // use only for in app created Categories

        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){
            String query = String.format("DELETE FROM Categories WHERE Category LIKE '%s' AND Id = %s", category.getCategoryName(), category.getId());

            statement.execute(query);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    private Category getCategory(Category category){

        Connection connection = connectionManager.getConnection();
        Category categoryWithID = null;

        try (Statement statement = connection.createStatement()){
            String query = String.format("SELECT * FROM Categories WHERE Category LIKE '%s'", category.getCategoryName());
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            int id = rs.getInt("Id");

            categoryWithID = new Category(id, category.getCategoryName());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categoryWithID;
    }
}
