package com.javafx.interfaces;

import com.javafx.objects.Person;

public interface AddressBook {

    void add(Person person);

    void delete(Person person);

    void update(Person person);
}
