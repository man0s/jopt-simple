package net.sf.joptsimple.examples;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class SignallingEndOfOptionsTest {
    @Test
    public void doubleHyphenSignalsEndOfOptions() {
        OptionParser parser = new OptionParser( "ab:c::de:f::" );

        OptionSet options = parser.parse( "-a", "-b=foo", "-c=bar", "--", "-d", "-e", "baz", "-f", "biz" );

        assertTrue( options.has( "a" ) );
        assertFalse( options.hasArgument( "a" ) );
        assertTrue( options.has( "b" ) );
        assertTrue( options.hasArgument( "b" ) );
        assertEquals( singletonList( "foo" ), options.valuesOf( "b" ) );
        assertTrue( options.has( "c" ) );
        assertTrue( options.hasArgument( "c" ) );
        assertEquals( singletonList( "bar" ), options.valuesOf( "c" ) );
        assertFalse( options.has( "d" ) );
        assertFalse( options.has( "e" ) );
        assertFalse( options.has( "f" ) );
        assertEquals( asList( "-d", "-e", "baz", "-f", "biz" ), options.nonOptionArguments() );
    }
}
