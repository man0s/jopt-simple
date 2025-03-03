package net.sf.joptsimple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 */
public class OptionSetAsMapTest extends AbstractOptionParserFixture {
    @Test
    public void gives() {
        OptionSpec<Void> a = parser.accepts( "a" );
        OptionSpec<String> b = parser.accepts( "b" ).withRequiredArg();
        OptionSpec<String> c = parser.accepts( "c" ).withOptionalArg();
        OptionSpec<String> d = parser.accepts( "d" ).withRequiredArg().defaultsTo( "1" );
        OptionSpec<String> e = parser.accepts( "e" ).withOptionalArg().defaultsTo( "2" );
        OptionSpec<String> f = parser.accepts( "f" ).withRequiredArg().defaultsTo( "3" );
        OptionSpec<String> g = parser.accepts( "g" ).withOptionalArg().defaultsTo( "4" );
        OptionSpec<Void> h = parser.accepts( "h" );

        OptionSet options = parser.parse( "-a", "-e", "-c", "5", "-d", "6", "-b", "4", "-d", "7", "-e", "8" );

        Map<OptionSpec<?>, List<?>> expected = Map.of(
            a, List.of(),
            b, List.of( "4" ),
            c, List.of( "5" ),
            d, List.of( "6", "7" ),
            e, List.of( "8" ),
            f, List.of( "3" ),
            g, List.of( "4" ),
            h, List.of() );

        assertEquals( expected, options.asMap() );
    }
}
