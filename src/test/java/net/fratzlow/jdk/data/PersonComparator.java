package net.fratzlow.jdk.data;

import java.util.Comparator;

/**
 * Created by frank on 08/02/14.
 */
public class PersonComparator implements Comparator<Person> {

    public static PersonComparator DEFAULT = new PersonComparator();
    private boolean query = false;

    //
    // constructors
    //

    public PersonComparator() {
        this.query = false;
    }

    public PersonComparator(boolean query) {
        this.query = query;
    }

    //
    // API
    //

    @Override
    public int compare(Person o1, Person o2) {
        if ( o1 == null || o2 == null) throw new IllegalArgumentException("No comparison of null elements allowed!");

        int result = compare(o1.firstname, o2.firstname);
        result     = result == 0 ? compare(o1.lastname, o2.lastname) : result;
        result     = result == 0 ? compare(o1.age, o2.age) : result;
        result     = result == 0 ? compare(o1.date, o2.date) : result;
        result     = result == 0 ? compare(o1.sex, o2.sex) : result;

        return result;
    }

    protected <T extends Comparable<T>> int compare( T inner, T outer ) {
        int result;

        if ( outer == null && inner == null ) {
            result = 0;
        } else if ( outer == null ) {
            result = 1;
        } else if ( inner == null) {
            result = -1;
        } else {
            result = inner.compareTo(outer);
        }

        return result;
    }

    /**
     * @return false ... standard comparator
     */
    public boolean isQuery() { return query; }
}
