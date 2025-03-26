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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class EmptyOptionSetTest {
    private OptionSet empty;

    @BeforeEach
    public void setUp() {
        empty = new OptionParser().parse();
    }

    @Test
    public void valueOf() {
        assertNull( empty.valueOf( "a" ) );
    }

    @Test
    public void valueOfOptional() {
        assertEquals( Optional.empty(), empty.valueOfOptional( "a" ) );
    }

    @Test
    public void valuesOf() {
        assertEquals( emptyList(), empty.valuesOf( "a" ) );
    }

    @Test
    public void hasArgument() {
        assertFalse( empty.hasArgument( "a" ) );
    }

    @Test
    public void hasOptions() {
        assertFalse( empty.hasOptions() );
    }

    @Test
    public void valueOfWithNullString() {
        assertThrows( NullPointerException.class, () -> empty.valueOf( (String) null ) );
    }

    @Test
    public void valueOfOptionalWithNullString() {
        assertThrows( NullPointerException.class, () -> empty.valueOfOptional( (String) null ) );
    }

    @Test
    public void valueOfOptionalWithNullOptionSpec() {
        assertThrows( NullPointerException.class, () -> empty.valueOfOptional( (OptionSpec<?>) null ) );
    }

    @Test
    public void valueOfWithNullOptionSpec() {
        assertThrows( NullPointerException.class, () -> empty.valueOf( (OptionSpec<?>) null ) );
    }

    @Test
    public void valuesOfWithNullString() {
        assertThrows( NullPointerException.class, () -> empty.valuesOf( (String) null ) );
    }

    @Test
    public void valuesOfWithNullOptionSpec() {
        assertThrows( NullPointerException.class, () -> empty.valuesOf( (OptionSpec<?>) null ) );
    }
}
