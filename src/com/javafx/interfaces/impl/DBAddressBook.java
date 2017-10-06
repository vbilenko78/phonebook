package com.javafx.interfaces.impl;

import com.javafx.db.SQLiteConnection;
import com.javafx.interfaces.AddressBook;
import com.javafx.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddressBook implements AddressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public DBAddressBook() {
        personList = findAll(); // загрузка всех данных
    }


    @Override
    public boolean add(Person person) {
        try (Connection con = SQLiteConnection.getConnection(); PreparedStatement statement = con.prepareStatement("INSERT INTO person(name, phone, email) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getPhone());
            statement.setString(3, person.getEmail());

            int result = statement.executeUpdate();
            if (result > 0) {
                int id = statement.getGeneratedKeys().getInt(1);// получить сгенерированный id вставленной записи
                person.setId(id);
                personList.add(person);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(Person person) {

        try (Connection con = SQLiteConnection.getConnection(); Statement statement = con.createStatement();) {
            int result = statement.executeUpdate("delete from person where id=" + person.getId());

            if (result > 0) {
                personList.remove(person);
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public ObservableList<Person> findAll() {
        personList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM person");) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setPhone(rs.getString("phone"));
                person.setEmail((rs.getString("email")));
                personList.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personList;
    }

    @Override
    public boolean update(Person person) {
        try (Connection con = SQLiteConnection.getConnection(); PreparedStatement statement = con.prepareStatement("UPDATE person SET name=?, phone=?, email=? WHERE id=?")) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getPhone());
            statement.setString(3, person.getEmail());
            statement.setInt(4, person.getId());

            int result = statement.executeUpdate();
            if (result > 0) {
                // обновление в коллекции происходит автоматически, после нажатия ОК в окне редактирования
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public ObservableList<Person> find(String text) {

        personList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM person WHERE name LIKE ? OR phone LIKE ? OR email LIKE ?")) {

            String searchStr = "%" + text + "%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);
            statement.setString(3, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setPhone(rs.getString("phone"));
                person.setEmail(rs.getString("email"));
                personList.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personList;
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }
}
