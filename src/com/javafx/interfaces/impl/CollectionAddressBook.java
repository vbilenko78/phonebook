package com.javafx.interfaces.impl;

import com.javafx.interfaces.AddressBook;
import com.javafx.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    @Override
    public void update(Person person) {
        //not implemented for collections
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void print() {
        int number = 0;
        System.out.println();
        for (Person person : personList) {
            number++;
            System.out.println(number + ") name = " + person.getName() + "; phone = " + person.getPhone() + "; e-mail = " + person.getEmail());
        }
    }

    public void fillTestData() {
        personList.add(new Person("John Reeves", "504-903-9878", "jreevs@nomail.com"));
        personList.add(new Person("Bob Feofanov", "345-980-8888", "ssss@nomail.com"));
        personList.add(new Person("John Smith", "123-444-5678", "newmail@nomail.com"));
        personList.add(new Person("Bob Johnson", "123-444-3432", "newyork@nomail.com"));
        personList.add(new Person("Ivan Ivanov", "123-444-4444", "ivan@nomail.com"));
        personList.add(new Person("Ben Hickopp", "123-444-4444", "ben@nomail.com"));
        personList.add(new Person("Dan Hoffman", "123-444-4444", "dan@nomail.com"));
        personList.add(new Person("Mike Gorbachev", "123-444-4444", "mike@nomail.com"));
        personList.add(new Person("Ludmila Baranova", "123-444-4444", "ldml@nomail.com"));
        personList.add(new Person("Mark Klimov", "123-444-4444", "marik@nomail.com"));
    }
}
