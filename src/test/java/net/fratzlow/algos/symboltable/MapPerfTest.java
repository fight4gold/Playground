package net.fratzlow.algos.symboltable;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Test client to exercise symbol table implementations.
 *
 * @author ratzlow@gmail.com
 * @since 2015-08-15
 */
public class MapPerfTest {

    int minCharSize = 4;
//    wordCount("ilias.txt");
  //  wordCount("war_and_peace.txt");

    @Test
    public void testTokenizeText() throws URISyntaxException, IOException {
        int runs = 200;
        List<String> durations = new ArrayList<>();
        String fileName = "tale_of_two_cities.txt";

        for (int i=0; i < runs; i++) {
            int lineNumber = i+1;
            durations.add("" + lineNumber + " " +
                    wordCount(
                        new MapDelegator<>(new HashMap<>()),
                        fileName)
            );
        }

        Files.write(Paths.get("./out/" + fileName + ".dat"), durations );
    }

    private long wordCount(SymbolTable<String, Long> st, String classPath) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getResource("/" + classPath).toURI());
        try( Stream<String> words = Files.lines(path)
                                            .flatMap(line -> Stream.of(line.split("\\W")))) {
            long start = System.nanoTime();
            words.filter( word -> word.length() >= minCharSize )
                    .forEach( word -> {
                        long v = 0;
                        if (st.contains(word)) {
                            v = st.get(word) + 1;
                        }
                        st.put( word, v );
                    });

            return (System.nanoTime() - start) / 1_000_000;
        }
    }
}
