package net.sf.joptsimple.examples;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.OptionException;
import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.OptionSet;

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
