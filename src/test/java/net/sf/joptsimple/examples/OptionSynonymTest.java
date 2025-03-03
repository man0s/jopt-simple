package net.sf.joptsimple.examples;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class OptionSynonymTest {
    @Test
    public void supportsOptionSynonyms() {
        OptionParser parser = new OptionParser();
        List<String> synonyms = asList( "message", "blurb", "greeting" );
        parser.acceptsAll( synonyms ).withRequiredArg();
        String expectedMessage = "Hello";

        OptionSet options = parser.parse( "--message", expectedMessage );

        for ( String each : synonyms ) {
            assertTrue( options.has( each ), each );
            assertTrue( options.hasArgument( each ), each );
            assertEquals( expectedMessage, options.valueOf( each ), each );
            assertEquals( singletonList( expectedMessage ), options.valuesOf( each ), each );
        }
    }
}
