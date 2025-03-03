package net.sf.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class AlternativeLongOptionsTest {
    @Test
    public void handlesAlternativeLongOptions() {
        OptionParser parser = new OptionParser( "W;" );
        parser.recognizeAlternativeLongOptions( true );  // same effect as above
        parser.accepts( "level" ).withRequiredArg();

        OptionSet options = parser.parse( "-W", "level=5" );

        assertTrue( options.has( "level" ) );
        assertTrue( options.hasArgument( "level" ) );
        assertEquals( "5", options.valueOf( "level" ) );
    }
}
