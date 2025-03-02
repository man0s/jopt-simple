package tests.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class OptionArgumentValueTypeTest {
    @Test
    public void convertsArgumentsToJavaValueTypes() {
        OptionParser parser = new OptionParser();
        parser.accepts( "flag" );
        parser.accepts( "count" ).withRequiredArg().ofType( Integer.class );
        parser.accepts( "level" ).withOptionalArg().ofType( Level.class );

        OptionSet options = parser.parse( "--count", "3", "--level", "DEBUG" );

        assertTrue( options.has( "count" ) );
        assertTrue( options.hasArgument( "count" ) );
        assertEquals( 3, options.valueOf( "count" ) );

        assertTrue( options.has( "level" ) );
        assertTrue( options.hasArgument( "level" ) );
        assertEquals( Level.DEBUG, options.valueOf( "level" ) );
    }
}
