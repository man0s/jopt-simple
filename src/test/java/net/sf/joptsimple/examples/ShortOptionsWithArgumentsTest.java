package net.sf.joptsimple.examples;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class ShortOptionsWithArgumentsTest {
    @Test
    public void allowsOptionsToAcceptArguments() {
        OptionParser parser = new OptionParser( "fc:q::" );

        OptionSet options = parser.parse( "-f", "-c", "foo", "-q" );

        assertTrue( options.has( "f" ) );

        assertTrue( options.has( "c" ) );
        assertTrue( options.hasArgument( "c" ) );
        assertEquals( "foo", options.valueOf( "c" ) );
        assertEquals( singletonList( "foo" ), options.valuesOf( "c" ) );

        assertTrue( options.has( "q" ) );
        assertFalse( options.hasArgument( "q" ) );
        assertNull( options.valueOf( "q" ) );
        assertEquals( emptyList(), options.valuesOf( "q" ) );
    }
}
