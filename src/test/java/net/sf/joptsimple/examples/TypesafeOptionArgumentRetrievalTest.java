package net.sf.joptsimple.examples;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;
import net.sf.joptsimple.OptionSpec;

public class TypesafeOptionArgumentRetrievalTest {
    @Test
    public void allowsTypesafeRetrievalOfOptionArguments() {
        OptionParser parser = new OptionParser();
        OptionSpec<Integer> count = parser.accepts( "count" ).withRequiredArg().ofType( Integer.class );
        OptionSpec<File> outputDir = parser.accepts( "output-dir" ).withOptionalArg().ofType( File.class );
        OptionSpec<Void> verbose = parser.accepts( "verbose" );
        OptionSpec<File> files = parser.nonOptions().ofType( File.class );

        OptionSet options = parser.parse( "--count", "3", "--output-dir", "/tmp", "--verbose", "a.txt", "b.txt" );

        assertTrue( options.has( verbose ) );

        assertTrue( options.has( count ) );
        assertTrue( options.hasArgument( count ) );
        Integer expectedCount = 3;
        assertEquals( expectedCount, options.valueOf( count ) );
        assertEquals( expectedCount, count.value( options ) );
        assertEquals( singletonList( expectedCount ), options.valuesOf( count ) );
        assertEquals( singletonList( expectedCount ), count.values( options ) );
        assertEquals( asList( new File( "a.txt" ), new File( "b.txt" ) ), options.valuesOf( files ) );

        assertTrue( options.has( outputDir ) );
        assertTrue( options.hasArgument( outputDir ) );
        File expectedFile = new File( "/tmp" );
        assertEquals( expectedFile, options.valueOf( outputDir ) );
        assertEquals( expectedFile, outputDir.value( options ) );
        assertEquals( singletonList( expectedFile ), options.valuesOf( outputDir ) );
        assertEquals( singletonList( expectedFile ), outputDir.values( options ) );
        assertEquals( asList( new File( "a.txt" ), new File( "b.txt" ) ), files.values( options ) );
    }
}
