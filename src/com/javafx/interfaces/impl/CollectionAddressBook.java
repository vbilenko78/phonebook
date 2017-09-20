package com.javafx.interfaces.impl;

import com.javafx.interfaces.AddressBook;
import com.javafx.objects.Person;

import java.util.ArrayList;

public class CollectionAddressBook implements AddressBook {

    private ArrayList<Person> personList = new ArrayList();

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

    public ArrayList<Person> getPersonList() {
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
        personList.add(new Person("John Reeves", "504-903-9878", "jreevs@nomail.comd"));
        personList.add(new Person("John Reeves", "345-980-8888", "ssss@nomail.comd"));
        personList.add(new Person("John Smith", "123-444-7777", "newmail@nomail.comd"));
    }
}
