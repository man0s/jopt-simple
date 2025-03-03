package tests.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ShortOptionsClusteringWithArgumentTest {
    @Test
    public void allowsClusteringShortOptionsThatAcceptArguments() {
        OptionParser parser = new OptionParser();
        parser.accepts( "a" );
        parser.accepts( "B" );
        parser.accepts( "c" ).withRequiredArg();

        OptionSet options = parser.parse( "-aBcfoo" );

        assertTrue( options.has( "a" ) );
        assertTrue( options.has( "B" ) );
        assertTrue( options.has( "c" ) );
        assertEquals( "foo", options.valueOf( "c" ) );
    }
}
