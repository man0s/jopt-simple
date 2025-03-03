package net.sf.joptsimple.examples;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class UnrecognizedOptionsAllowedTest {
    @Test
    public void acceptsLongOptions() {
        OptionParser parser = new OptionParser();
        parser.allowsUnrecognizedOptions();
        parser.accepts( "f" );

        OptionSet options = parser.parse( "-f", "-d" );

        assertTrue( options.has( "f" ) );
        assertFalse( options.has( "d" ) );
        assertEquals( singletonList( "-d" ), options.nonOptionArguments() );
    }
}
