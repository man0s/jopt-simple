package net.sf.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static net.sf.joptsimple.examples.Level.INFO;
import static net.sf.joptsimple.examples.Level.WARNING;

import java.io.File;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionException;
import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;
import net.sf.joptsimple.OptionSpec;

public class DefaultValuesForOptionArgumentsTest {

    @Test
    public void allowsSpecificationOfDefaultValues() throws Exception {
        File tempDir = new File( System.getProperty( "java.io.tmpdir" ) );
        File tempFile = File.createTempFile( "aFile", ".txt" );
        OptionParser parser = new OptionParser();
        OptionSpec<File> infile =
            parser.accepts( "infile" ).withRequiredArg().ofType( File.class ).defaultsTo( tempFile );
        OptionSpec<File> outdir =
            parser.accepts( "outdir" ).withRequiredArg().ofType( File.class ).defaultsTo( tempDir );
        OptionSpec<Integer> bufferSize =
            parser.accepts( "buffer-size" ).withOptionalArg().ofType( Integer.class ).defaultsTo( 4096 );
        OptionSpec<Level> level =
            parser.accepts( "level" ).withOptionalArg().ofType( Level.class ).defaultsTo( INFO );
        OptionSpec<Integer> count =
            parser.accepts( "count" ).withOptionalArg().ofType( Integer.class ).defaultsTo( 10 );

        OptionSet options = parser.parse( "--level", "WARNING", "--count", "--infile", "/etc/passwd" );

        assertEquals( new File( "/etc/passwd" ), infile.value( options ) );
        assertTrue( options.has( infile ) );
        assertTrue( options.hasArgument( infile ) );
        assertEquals( tempDir, outdir.value( options ) );
        assertFalse( options.has( outdir ) );
        assertFalse( options.hasArgument( outdir ) );
        assertEquals( Integer.valueOf( 4096 ), bufferSize.value( options ) );
        assertFalse( options.has( bufferSize ) );
        assertFalse( options.hasArgument( bufferSize ) );
        assertEquals( WARNING, level.value( options ) );
        assertTrue( options.has( level ) );
        assertTrue( options.hasArgument( level ) );
        assertEquals( Integer.valueOf( 10 ), count.value( options ) );
        assertTrue( options.has( count ) );
        assertFalse( options.hasArgument( count ) );

        assertThrows( OptionException.class, () -> parser.parse( "--outdir" ) );
    }
}
