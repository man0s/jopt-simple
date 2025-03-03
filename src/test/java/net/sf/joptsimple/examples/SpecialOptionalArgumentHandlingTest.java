package net.sf.joptsimple.examples;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class SpecialOptionalArgumentHandlingTest {
    @Test
    public void handlesNegativeNumberOptionalArguments() {
        OptionParser parser = new OptionParser();
        parser.accepts( "a" ).withOptionalArg().ofType( Integer.class );
        parser.accepts( "2" );

        OptionSet options = parser.parse( "-a", "-2" );

        assertTrue( options.has( "a" ) );
        assertFalse( options.has( "2" ) );
        assertEquals( singletonList( -2 ), options.valuesOf( "a" ) );

        options = parser.parse( "-2", "-a" );

        assertTrue( options.has( "a" ) );
        assertTrue( options.has( "2" ) );
        assertEquals( emptyList(), options.valuesOf( "a" ) );
    }
}
