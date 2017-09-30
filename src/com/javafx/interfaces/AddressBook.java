package com.javafx.interfaces;

import com.javafx.objects.Person;
import javafx.collections.ObservableList;

public interface AddressBook {

    boolean add(Person person);

    boolean update(Person person);

    boolean delete(Person person);

    ObservableList<Person> findAll();

    ObservableList<Person> find(String text);

}
