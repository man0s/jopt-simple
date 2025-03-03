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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sf.joptsimple.ValueConversionException;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class DateTimeConverterTest {

    private DateTimeConverter converter;

    @BeforeEach
    public void setUp() {
        converter = DateTimeConverter.of( "MM/dd/yyyy" );
    }

    @Test
    public void rejectsNullDateFormatter() {
        assertThrows( NullPointerException.class, () -> DateTimeConverter.of( null ) );
    }

    @Test
    public void shouldConvertValuesToDatesUsingADateFormat() {
        assertEquals( LocalDate.of( 2009, 1, 24 ), LocalDate.from( converter.convert( "01/24/2009" ) ) );
    }

    @Test
    public void rejectsNonParsableValues() {
        assertThrows( ValueConversionException.class, () -> converter.convert( "@(#*^" ) );
    }

    @Test
    public void rejectsValuesThatDoNotEntirelyMatch() {
        assertThrows( ValueConversionException.class, () -> converter.convert( "12/25/09 00:00:00" ) );
    }

    @Test
    public void shouldRaiseExceptionThatContainsDatePatternAndValue() {
        var exception = assertThrows( ValueConversionException.class,
            () -> converter.convert( "qwe" ) );
        assertTrue( exception.getMessage().contains( "qwe" ) );
        assertTrue( exception.getMessage().contains( "MM/dd/yyyy" ) );
    }

    @Test
    public void shouldAnswerCorrectValueType() {
        assertSame( TemporalAccessor.class, converter.valueType() );
    }
}
