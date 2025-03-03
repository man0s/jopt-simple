package net.sf.joptsimple.examples;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

public class PosixlyCorrectTest {
    @Test
    public void supportsPosixlyCorrectBehavior() {
        OptionParser parser = new OptionParser( "i:j::k" );
        String[] arguments = { "-ibar", "-i", "junk", "xyz", "-jixnay", "foo", "-k", "blah", "--", "bah" };

        OptionSet options = parser.parse( arguments );

        assertTrue( options.has( "i" ) );
        assertTrue( options.has( "j" ) );
        assertTrue( options.has( "k" ) );
        assertEquals( asList( "bar", "junk" ), options.valuesOf( "i" ) );
        assertEquals( singletonList( "ixnay" ), options.valuesOf( "j" ) );
        assertEquals( asList( "xyz", "foo", "blah", "bah" ), options.nonOptionArguments() );

        parser.posixlyCorrect( true );
        options = parser.parse( arguments );

        assertTrue( options.has( "i" ) );
        assertFalse( options.has( "j" ) );
        assertFalse( options.has( "k" ) );
        assertEquals( asList( "bar", "junk" ), options.valuesOf( "i" ) );
        assertEquals( emptyList(), options.valuesOf( "j" ) );
        assertEquals( asList( "xyz", "-jixnay", "foo", "-k", "blah", "--", "bah" ), options.nonOptionArguments() );
    }
}
