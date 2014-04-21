package net.fratzlow.jdk.data;

import java.util.Comparator;
import java.util.Date;

public class Person implements Comparable<Person> {
    public String firstname;
    public String lastname;
    public Integer age;
    public Date date;
    public Sex sex;
    PersonComparator comparator = PersonComparator.DEFAULT;

    //
    // constructors
    //

    public Person(String firstname, String lastname, Integer age, Date date, Sex sex) {
        this( firstname, lastname, age, date, sex, PersonComparator.DEFAULT);
    }

    public Person(String firstname, String lastname, Integer age, Date date, Sex sex, PersonComparator comparator) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.date = date;
        this.sex = sex;
        this.comparator = comparator;
    }


    //
    // API overrides
    //

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", age=").append(age);
        sb.append(", date=").append(date);
        sb.append(", sex=").append(sex);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Person o) {
       // comparators used for queries always take precedence
       Comparator<Person> comp = comparator.isQuery() ? comparator : o.comparator;

       return comp.compare(this, o);
    }
}
