package org.test;

import org.test.example.singleton.Context;
import org.test.model.example.model.Address;
import org.test.model.example.model.Person;
import org.test.model.example.service.PersonService;
import org.test.model.example.service.PersonServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


public class App {

    public static void contxtDemo() throws SQLException {
        Context context = Context.getInstance();

        Connection connection = context.getConnection();
        try {
            System.out.println("Hello World!" + context);
        } finally {
            connection.close();
        }
    }


    public static void main(String[] args)  {
        try {
            PersonService personService = new PersonServiceImpl();
            for(int i=0; i<=10;i++) {
                Person person = new Person();
                Address address = new Address();
                System.out.println("["+ i + "]");
                address.setName("address" + i);
                person.setAddress(address);
                person.setName("Name" +i );
                person.setEmail("mmm"+i+"@ff.gg.tt");
                Long id = personService.addPerson(person);
                System.out.println(person);
                person = personService.getPerson(id);
                System.out.println(person);
            }
            System.out.println("Persons list ");
            int tempIndex =0;
            for (Person personItem: personService.getAllPerson()) {
                tempIndex ++;
                System.out.println("["+ tempIndex+ "]");
                System.out.println(personItem);
                personService.removePerson(personItem);
            }
        } finally {
            try {
                Context.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}