package be.brussel.datapersistence;

import be.brussel.model.*;
import be.brussel.service.ConnectionManager;
import be.brussel.service.ServiceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeersDAO {

    private ConnectionManager connectionManager = ServiceFactory.createConnectionManager();

    public List<Beer> getBeers() {

        List<Beer> beers= new ArrayList<>();
        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){

            String query = "SELECT * FROM Beers LEFT JOIN Brewers ON Beers.BrewerId = Brewers.Id " +
                    "LEFT JOIN Categories ON Beers.CategoryId = Categories.Id";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int brewerId = rs.getInt("Brewers.Id");
                String brewerName = rs.getString("Brewers.Name");
                String address = rs.getString("Address");
                int zipcode = rs.getInt("ZipCode");
                String city = rs.getString("City");
                int turnover = rs.getInt("Turnover");
                Brewer brewer = new Brewer(brewerId, brewerName, address, zipcode, city, turnover);

                int categoryId = rs.getInt("Categories.Id");
                String categoryName = rs.getString("Category");
                Category category = new Category(categoryId, categoryName);

                int beerId = rs.getInt("Beers.Id");
                String beerName = rs.getString("Name");
                float price = rs.getFloat("Price");
                int stock = rs.getInt("Stock");
                float alcohol = rs.getFloat("Alcohol");

                Beer beer = new Beer(beerId, beerName, brewer,category, price, stock, (int) alcohol );
                beers.add(beer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return beers;
    }


    public List<Beer> getBeers(int alcoholConsumed) {

        List<Beer> beers= new ArrayList<>();
        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){

            String query = "SELECT * FROM Beers LEFT JOIN Brewers ON Beers.BrewerId = Brewers.Id " +
                    "LEFT JOIN Categories ON Beers.CategoryId = Categories.Id";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int brewerId = rs.getInt("Brewers.Id");
                String brewerName = rs.getString("Brewers.Name");
                String address = rs.getString("Address");
                int zipcode = rs.getInt("ZipCode");
                String city = rs.getString("City");
                int turnover = rs.getInt("Turnover");
                Brewer brewer = new Brewer(brewerId, brewerName, address, zipcode, city, turnover);

                int categoryId = rs.getInt("Categories.Id");
                String categoryName = rs.getString("Category");
                Category category = new Category(categoryId, categoryName);

                int beerId = rs.getInt("Beers.Id");
                String beerName = rs.getString("Name");
                float price = rs.getFloat("Price");
                int stock = rs.getInt("Stock");
                float alcohol = rs.getFloat("Alcohol");

                if(beerId % (alcoholConsumed+1) == 2){
                    Beer beer = new Beer(beerId, beerName, brewer,category, price, stock, (int) alcohol );
                    beers.add(beer);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return beers;
    }
}

