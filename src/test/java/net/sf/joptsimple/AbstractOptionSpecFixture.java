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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public abstract class AbstractOptionSpecFixture<S extends OptionSpec<?>> {
    @Test
    public void equal() {
        assertEquals( createEqualOptionSpecInstance(), createEqualOptionSpecInstance() );
    }

    @Test
    public void notEqual() {
        assertNotEquals( createEqualOptionSpecInstance(), createNotEqualOptionSpecInstance() );
    }

    protected abstract S createEqualOptionSpecInstance();

    protected abstract S createNotEqualOptionSpecInstance();

    @Test
    public final void valuesWithNullOptionSet() {
        assertThrows( NullPointerException.class, () -> createEqualOptionSpecInstance().values( null ) );
    }

    @Test
    public final void valueWithNullOptionSet() {
        assertThrows( NullPointerException.class, () -> createNotEqualOptionSpecInstance().value( null ) );
    }

    @Test
    public final void valueOptionalWithNullOptionSet() {
        assertThrows( NullPointerException.class, () -> createNotEqualOptionSpecInstance().valueOptional( null ) );
    }
}
