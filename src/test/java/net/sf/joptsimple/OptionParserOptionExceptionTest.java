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

package net.sf.joptsimple;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class OptionParserOptionExceptionTest extends AbstractOptionParserFixture {
    @Test
    public void unrecognizedOption() {
        var exception = assertThrows( OptionException.class, () -> parser.parse( "-a" ) );
        assertTrue( exception.options().contains( "a" ) );
    }

    @Test
    public void illegalOptionCharacter() {
        var exception = assertThrows( OptionException.class, () -> parser.accepts( "%" ) );
        assertTrue( exception.options().contains( "%" ) );
    }

    @Test
    public void asteriskIsIllegalOptionCharacter() {
        var exception = assertThrows( OptionException.class, () -> parser.accepts( "*" ) );
        assertTrue( exception.options().contains( "*" ) );
    }

    @Test
    public void tooManyHyphens() {
        parser.accepts( "b" );

        var exception = assertThrows( OptionException.class, () -> parser.parse( "---b" ) );
        assertTrue( exception.options().contains( "-b" ) );
    }

    @Test
    public void valueOfWhenMultiples() {
        parser.accepts( "e" ).withRequiredArg();
        OptionSet options = parser.parse( "-e", "foo", "-e", "bar" );

        var exception = assertThrows( OptionException.class, () -> options.valueOf( "e" ) );
        assertTrue( exception.options().contains( "e" ) );
    }

    @Test
    public void valueOfOptionalWhenMultiples() {
        parser.accepts( "e" ).withRequiredArg();
        OptionSet options = parser.parse( "-e", "foo", "-e", "bar" );

        var exception = assertThrows( OptionException.class, () -> options.valueOfOptional( "e" ) );
        assertTrue( exception.options().contains( "e" ) );
    }
}
