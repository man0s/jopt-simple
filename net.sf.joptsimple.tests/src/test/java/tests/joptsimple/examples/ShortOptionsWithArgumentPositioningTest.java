package tests.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ShortOptionsWithArgumentPositioningTest {
    @Test
    public void allowsDifferentFormsOfPairingArgumentWithOption() {
        OptionParser parser = new OptionParser( "a:b:c::" );

        OptionSet options = parser.parse( "-a", "foo", "-bbar", "-c=baz" );

        assertTrue( options.has( "a" ) );
        assertTrue( options.hasArgument( "a" ) );
        assertEquals( "foo", options.valueOf( "a" ) );

        assertTrue( options.has( "b" ) );
        assertTrue( options.hasArgument( "b" ) );
        assertEquals( "bar", options.valueOf( "b" ) );

        assertTrue( options.has( "c" ) );
        assertTrue( options.hasArgument( "c" ) );
        assertEquals( "baz", options.valueOf( "c" ) );
    }
}
