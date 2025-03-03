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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sf.joptsimple.ValueConversionException;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class RegexConverterTest {
    private RegexConverter abc;

    @BeforeEach
    public void setUp() {
        abc = RegexConverter.of( "abc", 0 );
    }

    @Test
    public void shouldAttemptToMatchValueAgainstARegex() {
        assertEquals( "abc", abc.convert( "abc" ) );
    }

    @Test
    public void rejectsValueThatDoesNotMatchRegex() {
        assertThrows( ValueConversionException.class, () -> abc.convert( "abcd" ) );
    }

    @Test
    public void raisesExceptionContainingValueAndPattern() {
        var exception =
            assertThrows( ValueConversionException.class, () -> RegexConverter.of( "\\d+", 0 ).convert( "asdf" ) );
        assertTrue( exception.getMessage().contains( "\\d+" ) );
        assertTrue( exception.getMessage().contains( "asdf" ) );
    }

    @Test
    public void shouldOfferConvenienceMethodForCreatingMatcherWithNoFlags() {
        assertEquals( "sourceforge.net", RegexConverter.of( "\\w+\\.\\w+" ).convert( "sourceforge.net" ) );
    }

    @Test
    public void shouldAnswerCorrectValueType() {
        assertEquals( String.class, abc.valueType() );
    }

    @Test
    public void shouldGiveCorrectValuePattern() {
        assertEquals( "abc", abc.valuePattern() );
    }
}
