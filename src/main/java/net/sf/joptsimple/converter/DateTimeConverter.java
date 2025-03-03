/*
 The MIT License

 Copyright (c) 2004-2021 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package net.sf.joptsimple.converter;

import java.text.ParsePosition;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Objects;

import net.sf.joptsimple.ValueConversionException;
import net.sf.joptsimple.ValueConverter;
import net.sf.joptsimple.internal.Messages;

/**
 * Converts values to {@link TemporalAccessor}s using a {@link DateTimeFormatter} object.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public final class DateTimeConverter implements ValueConverter<TemporalAccessor> {
    private final DateTimeFormatter formatter;
    private final String visualPattern;

    private DateTimeConverter(DateTimeFormatter formatter, String visualPattern) {
        this.formatter = Objects.requireNonNull( formatter, "formatter must not be null." );
        this.visualPattern = Objects.requireNonNull( visualPattern, "visualPattern must not be null." );
    }

    @Override
    public TemporalAccessor convert( String value ) {
        ParsePosition position = new ParsePosition( 0 );

        try {
            TemporalAccessor date = formatter.parse( value, position );
            if ( position.getIndex() != value.length() )
                throw new ValueConversionException( message( value ) );
            return date;
        } catch ( DateTimeParseException exception ) {
            throw new ValueConversionException( message( value ) );
        }
    }

    @Override
    public String revert( TemporalAccessor value ) {
        return formatter.format( value );
    }

    @Override
    public Class<TemporalAccessor> valueType() {
        return TemporalAccessor.class;
    }

    @Override
    public String valuePattern() {
        return visualPattern;
    }

    private String message( String value ) {
        String key;
        Object[] arguments;

        key = "with.pattern.message";
        arguments = new Object[] { value, visualPattern };

        return Messages.message(
            Locale.getDefault(),
            "net.sf.joptsimple.ExceptionMessages",
            DateTimeConverter.class,
            key,
            arguments );
    }

    /**
     * Creates a converter that uses the given formatter.
     *
     * @param formatter     the formatter to use
     * @param visualPattern the pattern to use when displaying messages
     * @throws NullPointerException if {@code formatter} or {@code visualPattern} are {@code null}
     */
    public static DateTimeConverter of( DateTimeFormatter formatter, String visualPattern ) {
        return new DateTimeConverter( formatter, visualPattern );
    }

    /**
     * Creates a converter that uses the given date/time pattern.
     *
     * @param pattern the pattern to use
     * @throws NullPointerException if {@code pattern} is {@code null}
     */
    public static DateTimeConverter of( String pattern ) {
        Objects.requireNonNull( pattern );
        return new DateTimeConverter( DateTimeFormatter.ofPattern( pattern ), pattern );
    }
}
