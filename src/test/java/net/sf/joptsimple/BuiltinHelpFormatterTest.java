package net.sf.joptsimple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.converter.DateTimeConverter;

public class BuiltinHelpFormatterTest {
    @Test // for issue #127
    public void gh127FormatHelpWithDateOptionAndPatternThatContainsDots() {
        OptionParser parser = new OptionParser();
        parser.accepts( "date" )
            .withRequiredArg()
            .withValuesConvertedBy( DateTimeConverter.of( "MM/dd/yy" ) );

        Map<String, AbstractOptionSpec<?>> specs = parser.recognizedOptions();

        BuiltinHelpFormatter builtinHelpFormatter = new BuiltinHelpFormatter();

        String actual = builtinHelpFormatter.format( specs );

        var expected =
            """
            Option             Description|
            ------             -----------|
            --date <MM/dd/yy>             |
            """;

        assertEquals( expected.replace( "|\n", "\n" ).replace( "\n", Strings.LINE_SEPARATOR ), actual );
    }
}
