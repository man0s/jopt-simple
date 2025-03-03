package net.sf.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;
import net.sf.joptsimple.OptionSpec;

public class ExportOptionsTest {
    private static Properties asProperties( OptionSet options, String prefix ) {
        Properties properties = new Properties();
        for ( Entry<OptionSpec<?>, List<?>> entry : options.asMap().entrySet()) {
            OptionSpec<?> spec = entry.getKey();
            properties.setProperty(
                asPropertyKey( prefix, spec ),
                asPropertyValue( entry.getValue(), options.has( spec ) ) );
        }
        return properties;
    }

    private static String asPropertyKey( String prefix, OptionSpec<?> spec ) {
        List<String> flags = spec.options();
        for (String flag : flags)
            if (1 < flag.length())
                return null == prefix ? flag : (prefix + '.' + flag);
        throw new IllegalArgumentException( "No usable non-short flag: " + flags );
    }

    private static String asPropertyValue( List<?> values, boolean present ) {
        // Simple flags have no values; treat presence/absence as true/false
        return values.isEmpty()
                ? String.valueOf( present )
                : values.stream().map( Object::toString ).collect( Collectors.joining( "," ) );
    }

    @Test
    public void allowsExportOfOptions() {
        Properties expected = new Properties();
        expected.setProperty( "rice.count", "3" );
        // Cannot check path as string directly - Windows flips the leading slash
        expected.setProperty( "rice.output-dir", new File( "/tmp" ).toString() );
        expected.setProperty( "rice.fun", "false" );
        expected.setProperty( "rice.verbose", "true" );

        OptionParser parser = new OptionParser();
        parser.accepts( "count" ).withRequiredArg().ofType( Integer.class );
        parser.accepts( "output-dir" ).withOptionalArg().ofType( File.class );
        parser.accepts( "verbose" );
        parser.accepts( "fun" );
        parser.nonOptions().ofType( File.class );

        OptionSet options = parser.parse( "--count", "3", "--output-dir", "/tmp", "--verbose", "a.txt", "b.txt" );

        assertEquals( expected, asProperties( options, "rice" ) );
    }
}
