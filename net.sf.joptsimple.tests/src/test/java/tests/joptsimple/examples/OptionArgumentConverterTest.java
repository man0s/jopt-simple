package tests.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.converter.DateTimeConverter;
import joptsimple.converter.RegexConverter;

public class OptionArgumentConverterTest {
    @Test
    public void usesConvertersOnOptionArgumentsWhenTold() {
        OptionParser parser = new OptionParser();
        var birthdate = parser
            .accepts( "birthdate" )
            .withRequiredArg()
            .withValuesConvertedBy( DateTimeConverter.of( "MM/dd/yy" ) );
        parser.accepts( "ssn" )
            .withRequiredArg()
            .withValuesConvertedBy( RegexConverter.of( "\\d{3}-\\d{2}-\\d{4}" ) );

        OptionSet options = parser.parse( "--birthdate", "02/24/05", "--ssn", "123-45-6789" );

        assertEquals( LocalDate.of( 2005, 2, 24 ), LocalDate.from( options.valueOf( birthdate ) ) );
        assertEquals( "123-45-6789", options.valueOf( "ssn" ) );
    }
}
