package tests.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ShortOptionsTest {
    @Test
    public void supportsShortOptions() {
        OptionParser parser = new OptionParser( "aB?*." );

        OptionSet options = parser.parse( "-a", "-B", "-?" );

        assertTrue( options.has( "a" ) );
        assertTrue( options.has( "B" ) );
        assertTrue( options.has( "?" ) );
        assertFalse( options.has( "." ) );
    }
}
