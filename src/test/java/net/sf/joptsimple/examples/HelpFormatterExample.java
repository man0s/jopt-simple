package net.sf.joptsimple.examples;

import static java.util.Arrays.asList;

import java.io.File;
import java.util.HashSet;
import java.util.Map;

import net.sf.joptsimple.HelpFormatter;
import net.sf.joptsimple.OptionDescriptor;
import net.sf.joptsimple.OptionParser;
import net.sf.joptsimple.converter.DateTimeConverter;

public class HelpFormatterExample {
    private static class MyFormatter implements HelpFormatter {
        public String format( Map<String, ? extends OptionDescriptor> options ) {
            StringBuilder buffer = new StringBuilder();
            for ( OptionDescriptor each : new HashSet<>( options.values() ) ) {
                buffer.append( lineFor( each ) );
            }
            return buffer.toString();
        }

        private String lineFor( OptionDescriptor descriptor ) {
            if ( descriptor.representsNonOptions() ) {
                return descriptor.argumentDescription() + '(' + descriptor.argumentTypeIndicator() + "): "
                        + descriptor.description() + System.getProperty( "line.separator" );
            }

            StringBuilder line = new StringBuilder( descriptor.options().toString() );
            line.append( ": description = " ).append( descriptor.description() );
            line.append( ", required = " ).append( descriptor.isRequired() );
            line.append( ", accepts arguments = " ).append( descriptor.acceptsArguments() );
            line.append( ", requires argument = " ).append( descriptor.requiresArgument() );
            line.append( ", argument description = " ).append( descriptor.argumentDescription() );
            line.append( ", argument type indicator = " ).append( descriptor.argumentTypeIndicator() );
            line.append( ", default values = " ).append( descriptor.defaultValues() );
            line.append( System.getProperty( "line.separator" ) );
            return line.toString();
        }
    }

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
                nonOptions( "files to chew on" ).ofType( File.class ).describedAs( "input files" );
            }
        };

        parser.formatHelpWith( new MyFormatter() );
        parser.printHelpOn( System.out );
    }
}
