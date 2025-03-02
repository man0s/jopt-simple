package tests.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class RequiredOptionsTest {
    @Test
    public void allowsSpecificationOfRequiredOptions() {
        OptionParser parser = new OptionParser() {
            {
                accepts( "userid" ).withRequiredArg().required();
                accepts( "password" ).withRequiredArg().required();
            }
        };

        assertThrows( OptionException.class, () -> parser.parse( "--userid", "bob" ) );
    }

    @Test
    public void aHelpOptionMeansRequiredOptionsNeedNotBePresent() {
        OptionParser parser = new OptionParser() {
            {
                accepts( "userid" ).withRequiredArg().required();
                accepts( "password" ).withRequiredArg().required();
                accepts( "help" ).forHelp();
            }
        };

        OptionSet options = parser.parse( "--help" );
        assertTrue( options.has( "help" ) );
    }

    @Test
    public void missingHelpOptionMeansRequiredOptionsMustBePresent() {
        OptionParser parser = new OptionParser() {
            {
                accepts( "userid" ).withRequiredArg().required();
                accepts( "password" ).withRequiredArg().required();
                accepts( "help" ).forHelp();
            }
        };

        assertThrows( OptionException.class, () -> parser.parse( "" ) );
    }
}
