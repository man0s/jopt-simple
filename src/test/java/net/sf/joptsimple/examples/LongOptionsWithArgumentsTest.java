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

public class LongOptionsWithArgumentsTest {
    @Test
    public void supportsLongOptionsWithArgumentsAndAbbreviations() {
        OptionParser parser = new OptionParser();
        parser.accepts( "flag" );
        parser.accepts( "count" ).withRequiredArg();
        parser.accepts( "level" ).withOptionalArg();

        OptionSet options = parser.parse( "-flag", "--co", "3", "--lev" );

        assertTrue( options.has( "flag" ) );

        assertTrue( options.has( "count" ) );
        assertTrue( options.hasArgument( "count" ) );
        assertEquals( "3", options.valueOf( "count" ) );
        assertEquals( singletonList( "3" ), options.valuesOf( "count" ) );

        assertTrue( options.has( "level" ) );
        assertFalse( options.hasArgument( "level" ) );
        assertNull( options.valueOf( "level" ) );
        assertEquals( emptyList(), options.valuesOf( "level" ) );
    }

    @Test
    public void supportsLongOptionsWithEmptyArguments() {
        OptionParser parser = new OptionParser();
        parser.accepts( "verbose" );
        parser.accepts( "brief" );
        parser.accepts( "add" );
        parser.accepts( "append" );
        parser.accepts( "delete" ).withRequiredArg();
        parser.accepts( "create" ).withRequiredArg();
        parser.accepts( "file" ).withRequiredArg();

        OptionSet options = parser.parse( "--delete", "", "--add" );

        assertTrue( options.has( "delete" ) );
        assertTrue( options.hasArgument( "delete" ) );
        assertEquals( "", options.valueOf( "delete" ) );

        assertTrue( options.has( "add" ) );

        options = parser.parse( "--delete=", "--add" );

        assertTrue( options.has( "delete" ) );
        assertTrue( options.hasArgument( "delete" ) );
        assertEquals( "", options.valueOf( "delete" ) );

        assertTrue( options.has( "add" ) );
    }
}
