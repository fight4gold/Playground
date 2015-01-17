package net.fratzlow.jdk;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2014-11-02
 */
public class Java8Test {

    @Test
    public void test2_2() throws IOException {
        Path path = Paths.get(this.getClass().getResource("/war_and_peace.txt").getPath());
        Stream<String> lines = Files.lines(path);
        long start = System.currentTimeMillis();
        long wordCount = lines.map(s -> s.split("\\s+")).flatMap(sa -> Stream.<String>of(sa) )
                //.peek(s -> System.out.println("\tbefore => " + s))
                //.filter( s -> s.length() > 5)
                //.limit(5)
                //.forEach( s -> System.out.println("after => " + s));
                .count();
        System.out.println("Word count = " + wordCount + " -> after (ms):" + (System.currentTimeMillis()-start));
    }


    @Test
    public void testIntStreamCreation2_4() {
        int[] ar = { 1,4,9,16 };
        int[] intAr = IntStream.of(ar).toArray();
        Assert.assertArrayEquals( ar, intAr );
    }

    @Test
    public void test2_5() {
        
    }

    @Test
    public void testListIntoMapCollection() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        List<Tuple> tuples = map.entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        Map<String, String> map2 = tuples.stream().collect(Collectors.toMap(Tuple::getKey, Tuple::getValue));
        Assert.assertEquals(map, map2);
    }

    class Tuple {
        String key, value;

        Tuple(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
