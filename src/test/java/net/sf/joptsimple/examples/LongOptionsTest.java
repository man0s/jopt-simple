package net.sf.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class LongOptionsTest {
    @Test
    public void acceptsLongOptions() {
        OptionParser parser = new OptionParser();
        parser.accepts( "flag" );
        parser.accepts( "verbose" );

        OptionSet options = parser.parse( "--flag" );

        assertTrue( options.has( "flag" ) );
        assertFalse( options.has( "verbose" ) );
    }
}
