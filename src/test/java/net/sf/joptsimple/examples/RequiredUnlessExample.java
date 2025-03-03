package net.sf.joptsimple.examples;

import net.sf.joptsimple.OptionParser;

public class RequiredUnlessExample {
    public static void main( String[] args ) {
        OptionParser parser = new OptionParser();
        parser.accepts( "anonymous" );
        parser.accepts( "username" ).requiredUnless( "anonymous" ).withRequiredArg();
        parser.accepts( "password" ).requiredUnless( "anonymous" ).withRequiredArg();

        parser.parse( "--anonymous" );
    }
}
