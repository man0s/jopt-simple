package tests.joptsimple.examples;

import static java.util.Arrays.asList;

import java.io.File;

import joptsimple.OptionParser;
import joptsimple.converter.DateTimeConverter;

public class HelpScreenExample {
    public static void main( String[] args ) throws Exception {
        OptionParser parser = new OptionParser() {
            {
                accepts( "c" ).withRequiredArg().ofType( Integer.class )
                    .describedAs( "count" ).defaultsTo( 1 );
                accepts( "q" ).withOptionalArg().ofType( Double.class )
                    .describedAs( "quantity" );
                accepts( "d", "some date" ).withRequiredArg().required()
                    .withValuesConvertedBy( DateTimeConverter.of( "MM/dd/yy" ) );
                acceptsAll( asList( "v", "talkative", "chatty" ), "be more verbose" );
                accepts( "output-file" ).withOptionalArg().ofType( File.class )
                    .describedAs( "file" );
                acceptsAll( asList( "h", "?" ), "show help" ).forHelp();
                acceptsAll( asList( "cp", "classpath" ) ).withRequiredArg()
                    .describedAs( "path1" + File.pathSeparatorChar + "path2:..." )
                    .ofType( File.class )
                    .withValuesSeparatedBy( File.pathSeparatorChar );
            }
        };

        parser.printHelpOn( System.out );
    }
}
