package net.fratzlow.jdk;

import net.fratzlow.jdk.data.Person;
import net.fratzlow.jdk.data.PersonComparator;
import net.fratzlow.jdk.data.Sex;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;


public class PersonTreeSetTest {

    @Test
    public void testEqualsOneCriteria() {
        NavigableSet<Person> set = new TreeSet<Person>();
        Person frank_1 = newPerson("frank", "ratzlow", 38, toDate("1975-08-22"), Sex.FEMALE);
        Person frank_2 = newPerson("frank", "ratzlow", 38, toDate("1975-08-22"), Sex.MALE);
        Person leti_1 = newPerson("leti", "fernandez", 24, toDate("1989-08-05"), Sex.FEMALE);

        set.add(frank_1);
        set.add(frank_2);
        set.add(leti_1);

        PersonComparator oneCriteria = new PersonComparator(true) {
            @Override
            public int compare(Person o1, Person o2) {
                return compare( o1.sex, o2.sex );
            }
        };
        Person from = new Person(null, null, null, null, Sex.FEMALE, oneCriteria);
        Person to = new Person(null, null, null, null, Sex.FEMALE, oneCriteria);

        NavigableSet<Person> persons = set.subSet(
                from, true,
                to, true
        );

        Assert.assertEquals(2, persons.size());
        Assert.assertTrue(persons.contains(frank_1));
        Assert.assertTrue(persons.contains(leti_1));
    }


    //------------------------------------------------------------------------------------------------------------------
    // inner helper methods
    //------------------------------------------------------------------------------------------------------------------
    private Person newPerson( String firstname, String lastname, Integer age, Date date, Sex sex ) {
        return new Person(firstname, lastname, age, date, sex );
    }

    private Date toDate( String date ) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse( date );
        } catch (ParseException e) {
            throw new RuntimeException( e );
        }
    }
}
