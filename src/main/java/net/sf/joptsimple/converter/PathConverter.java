package net.sf.joptsimple.converter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.joptsimple.ValueConversionException;
import net.sf.joptsimple.ValueConverter;

/**
 * Converts command line options to {@link Path} objects and checks the status of the underlying file.
 */
public class PathConverter implements ValueConverter<Path> {
    private final List<PathPredicate> predicates;

    private PathConverter(List<PathPredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public Path convert( String value ) {
        Path path = Paths.get( value );

        if ( predicates != null ) {
            for ( PathPredicate each : predicates ) {
                if ( !each.accept( path ) )
                    throw new ValueConversionException( message( each.getMessageKey(), path.toString() ) );
            }
        }

        return path;
    }

    @Override
    public Class<Path> valueType() {
        return Path.class;
    }

    @Override
    public String valuePattern() {
        return null;
    }

    private String message( String errorKey, String value ) {
        ResourceBundle bundle = ResourceBundle.getBundle( "net.sf.joptsimple.ExceptionMessages" );
        Object[] arguments = new Object[] { value, valuePattern() };
        String template = bundle.getString( PathConverter.class.getName() + "." + errorKey + ".message" );
        return new MessageFormat( template ).format( arguments );
    }

    public static PathConverter of( Collection<PathPredicate> predicates ) {
        return new PathConverter( List.copyOf( predicates ) );
    }

    public static PathConverter of( PathPredicate... predicates ) {
        return new PathConverter( List.of( predicates ) );
    }
}
