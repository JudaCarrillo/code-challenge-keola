package com.codechallenge.keola.api.infrastructure.persistence;

import com.codechallenge.keola.api.domain.customer.Customer;
import com.codechallenge.keola.api.domain.customer.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final String url = "jdbc:postgresql://127.0.0.1:5432/live";
    private final String user = "root";
    private final String password = "example";

    @Override
    public boolean save(Customer customer) {
        String sql = "INSERT INTO Customers (name, lastname, email, password_hash, phone) VALUES (?, ?, ?, ?, ?)";
        Logger logger = Logger.getLogger(getClass().getName());

        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getLastname());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword_hash());
            statement.setString(5, customer.getPhone());
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving customer: " + customer.getEmail(), e.toString());
            return false;
        }
    }

    @Override
    public Customer findById(String id) {
        String sql = "SELECT name, lastname, email, phone FROM Customers WHERE customerId = ?";
        Customer customer;
        Logger logger = Logger.getLogger(getClass().getName());


        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            customer = new Customer();
            customer.setName(resultSet.getString("name"));
            customer.setLastname(resultSet.getString("lastname"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPhone(resultSet.getString("phone"));

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding customer: " + id, e.toString());
            return null;
        }

        return customer;
    }


    @Override
    public List<Customer> findAll() {
        String sql = "SELECT name,  lastname, email, phone FROM Customers";
        Logger logger = Logger.getLogger(getClass().getName());
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all customers", e.toString());
        }

        return customers;
    }

    @Override
    public boolean update(Customer customer, String id) {
        String sql = "UPDATE Customers SET name = ?, lastname = ?, email = ?, phone = ? WHERE customerId = ?";
        Logger logger = Logger.getLogger(getClass().getName());

        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getLastname());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating customer: " + id, e.toString());
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM Customers WHERE customerId = ?";
        Logger logger = Logger.getLogger(getClass().getName());

        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting customer: " + id, e.toString());
            return false;
        }
    }
}