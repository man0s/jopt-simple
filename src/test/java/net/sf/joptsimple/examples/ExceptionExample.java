package net.sf.joptsimple.examples;

import net.sf.joptsimple.OptionParser;

public class ExceptionExample {
    public static void main( String[] args ) {
        OptionParser parser = new OptionParser();

        parser.parse( "-x" );
    }
}
