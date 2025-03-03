package net.sf.joptsimple.examples;

import net.sf.joptsimple.OptionParser;

public class RequiredIfExample {
    public static void main( String[] args ) {
        OptionParser parser = new OptionParser();
        parser.accepts( "ftp" );
        parser.accepts( "username" ).requiredIf( "ftp" ).withRequiredArg();
        parser.accepts( "password" ).requiredIf( "ftp" ).withRequiredArg();

        parser.parse( "--ftp" );
    }
}
