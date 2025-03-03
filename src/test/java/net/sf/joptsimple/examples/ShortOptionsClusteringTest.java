package net.sf.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class ShortOptionsClusteringTest {
    @Test
    public void allowsClusteringShortOptions() {
        OptionParser parser = new OptionParser( "aBcd" );

        OptionSet options = parser.parse( "-cdBa" );

        assertTrue( options.has( "a" ) );
        assertTrue( options.has( "B" ) );
        assertTrue( options.has( "c" ) );
        assertTrue( options.has( "d" ) );
    }
}
