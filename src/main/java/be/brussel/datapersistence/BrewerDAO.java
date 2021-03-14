package be.brussel.datapersistence;

import be.brussel.model.Brewer;
import be.brussel.service.ConnectionManager;
import be.brussel.service.ServiceFactory;
import be.brussel.service.data.Valuta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrewerDAO {

    private ConnectionManager connectionManager = ServiceFactory.createConnectionManager();

    public List<Brewer> getBrewers() {

        List<Brewer> brewers= new ArrayList<>();
        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){
            String query = "SELECT * FROM Brewers";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int zipcode = rs.getInt("ZipCode");
                String city = rs.getString("City");
                int turnover = rs.getInt("Turnover");

                Brewer brewer = new Brewer(id, name, address, zipcode, city, turnover);
                brewers.add(brewer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return brewers;
    }



    public List<Brewer> getBrewers(Valuta valuta) {

        List<Brewer> brewers= new ArrayList<>();
        Connection connection = connectionManager.getConnection();

        try(Statement statement = connection.createStatement())  {

            String query = "SELECT * FROM Brewers";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int zipcode = rs.getInt("ZipCode");
                String city = rs.getString("City");
                int turnover = rs.getInt("Turnover");

                Brewer brewer = new Brewer(id, name, address, zipcode, city, (int) (turnover*valuta.getConversionRate()));
                brewers.add(brewer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return brewers;
    }


    public List<Brewer> getBrewers(String nameFilter) {
        // note to frontend: this method seems not implemented in view

        List<Brewer> brewers= new ArrayList<>();
        Connection connection = connectionManager.getConnection();

        String query = "SELECT * FROM Brewers WHERE Name LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, nameFilter);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int zipcode = rs.getInt("ZipCode");
                String city = rs.getString("City");
                int turnover = rs.getInt("Turnover");

                Brewer brewer = new Brewer(id, name, address, zipcode, city, turnover);
                brewers.add(brewer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return brewers;
    }

    public List<Brewer> getBrewers(String nameFilter, Valuta valuta) {
        // note to frontend: this method seems not implemented in view

        List<Brewer> brewers= new ArrayList<>();
        Connection connection = connectionManager.getConnection();

        String query = "SELECT * FROM Brewers WHERE Name LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query))  {

            statement.setString(1, nameFilter);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int zipcode = rs.getInt("ZipCode");
                String city = rs.getString("City");
                int turnover = rs.getInt("Turnover");

                Brewer brewer = new Brewer(id, name, address, zipcode, city, (int) (turnover*valuta.getConversionRate()));
                brewers.add(brewer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return brewers;
    }


    public Brewer createBrewer(Brewer brewer) {

        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()) {

            String query = String.format("INSERT INTO Brewers (Name, Address, ZipCode, City, Turnover) VALUES('%s', '%s', %s, '%s', %s)",
                    brewer.getName(), brewer.getAddress(), brewer.getZipcode(), brewer.getCity(), brewer.getTurnover());

            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getBrewer(brewer);
    }


    public Brewer updateBrewer(Brewer brewer)  {

        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){

            String query = String.format("UPDATE Brewers SET Name = '%s',Address = '%s', ZipCode = %s, City = '%s', Turnover = %s WHERE Id = %s",
                    brewer.getName(), brewer.getAddress(), brewer.getZipcode(), brewer.getCity(), brewer.getTurnover(), brewer.getId());

            statement.execute(query);

        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return getBrewer(brewer);
    }

    public boolean deleteBrewerById(Integer id) {
        // note to frontend: delete button seems not implemented in view
        // use only for in app created Categories

        Connection connection = connectionManager.getConnection();

        try (Statement statement = connection.createStatement()){

            String query = String.format("DELETE FROM Brewers WHERE Id = %s", id);

            statement.execute(query);

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    private Brewer getBrewer(Brewer brewer){

        Connection connection = connectionManager.getConnection();
        Brewer  brewerWithID = null;

        try (Statement statement = connection.createStatement()){

            String query = String.format("SELECT * FROM Brewers WHERE Name LIKE '%s'", brewer.getName());
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            int id = rs.getInt("Id");

            brewerWithID = new Brewer(id, brewer.getName(), brewer.getAddress(), brewer.getZipcode(), brewer.getCity(), brewer.getTurnover());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return brewerWithID;
    }
}
