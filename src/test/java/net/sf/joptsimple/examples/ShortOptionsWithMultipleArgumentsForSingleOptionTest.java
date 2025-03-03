package net.sf.joptsimple.examples;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionException;
import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class ShortOptionsWithMultipleArgumentsForSingleOptionTest {

    @Test
    public void allowsMultipleValuesForAnOption() {
        OptionParser parser = new OptionParser( "a:" );

        OptionSet options = parser.parse( "-a", "foo", "-abar", "-a=baz" );

        assertTrue( options.has( "a" ) );
        assertTrue( options.hasArgument( "a" ) );
        assertEquals( asList( "foo", "bar", "baz" ), options.valuesOf( "a" ) );

        assertThrows( OptionException.class, () -> options.valueOf( "a" ) );

    }
}
