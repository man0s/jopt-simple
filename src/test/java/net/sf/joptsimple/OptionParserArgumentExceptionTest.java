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

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class OptionParserArgumentExceptionTest extends AbstractOptionParserFixture {
    @Test
    public void createWithNullOptionSpec() {
        assertThrows( NullPointerException.class, () -> new OptionParser( null ) );
    }

    @Test
    public void parseNull() {
        assertThrows( NullPointerException.class, () -> parser.parse( (String[]) null ) );
    }

    @Test
    public void nullOptionToAccepts() {
        assertThrows( NullPointerException.class, () -> parser.accepts( null ) );
    }

    @Test
    public void nullOptionToAcceptsWithDescription() {
        assertThrows( NullPointerException.class, () -> parser.accepts( null, "a weird option" ) );
    }

    @Test
    public void nullOptionListToAcceptsAll() {
        assertThrows( NullPointerException.class, () -> parser.acceptsAll( null ) );
    }

    @Test
    public void emptyOptionListToAcceptsAll() {
        assertThrows( IllegalArgumentException.class, () -> parser.acceptsAll( emptyList() ) );
    }

    @Test
    public void optionListContainingNullToAcceptsAll() {
        assertThrows( NullPointerException.class, () -> parser.acceptsAll( singletonList( null ) ) );
    }

    @Test
    public void nullOptionListToAcceptsAllWithDescription() {
        assertThrows( NullPointerException.class, () -> parser.acceptsAll( null, "some weird options" ) );
    }

    @Test
    public void optionListContainingNullToAcceptsAllWithDescription() {
        assertThrows( NullPointerException.class, () -> parser.acceptsAll( singletonList( null ), "some weird options" ) );
    }
}
