package net.sf.joptsimple.examples;

import static java.io.File.pathSeparator;
import static java.io.File.pathSeparatorChar;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;
import net.sf.joptsimple.OptionSpec;

public class MultipleDelimitedArgumentsTest {
    @Test
    public void supportsMultipleDelimitedArguments() {
        OptionParser parser = new OptionParser();
        OptionSpec<File> path = parser.accepts( "path" ).withRequiredArg().ofType( File.class )
            .withValuesSeparatedBy( pathSeparatorChar );

        OptionSet options = parser.parse(
            "--path",
            Stream.of( "/tmp", "/var", "/opt" ).collect( joining( pathSeparator ) ) );

        assertTrue( options.has( path ) );
        assertTrue( options.hasArgument( path ) );
        assertEquals(
            asList( new File( "/tmp" ), new File( "/var" ), new File( "/opt" ) ),
            options.valuesOf( path ) );
    }
}
