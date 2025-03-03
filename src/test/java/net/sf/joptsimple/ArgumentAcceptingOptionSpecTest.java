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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class ArgumentAcceptingOptionSpecTest {
    @Test
    public void requiredArgOfNullType() {
        assertThrows( NullPointerException.class, () -> new OptionParser().accepts( "a" ).withRequiredArg().ofType( null ));
    }

    @Test
    public void optionalArgOfNullType() {
    	assertThrows( NullPointerException.class, () -> new OptionParser().accepts( "verbose" ).withOptionalArg().ofType( null ));
    }

    @Test
    public void requiredArgOfNonValueType() {
    	assertThrows( IllegalArgumentException.class, () -> new OptionParser().accepts( "threshold" ).withRequiredArg().ofType( Object.class ));
    }

    @Test
    public void optionalArgOfNonValueType() {
    	assertThrows( IllegalArgumentException.class, () -> new OptionParser().accepts( "max" ).withOptionalArg().ofType( Object.class ));
    }

    @Test
    public void requiredArgOfValueTypeBasedOnValueOf() {
        new OptionParser().accepts( "threshold" ).withRequiredArg().ofType( ValueOfHaver.class );
    }

    @Test
    public void optionalArgOfValueTypeBasedOnValueOf() {
        new OptionParser().accepts( "abc" ).withOptionalArg().ofType( ValueOfHaver.class );
    }

    @Test
    public void requiredArgOfValueTypeBasedOnCtor() {
    	new OptionParser().accepts( "threshold" ).withRequiredArg().ofType( Ctor.class );
    }

    @Test
    public void optionalArgOfValueTypeBasedOnCtor() {
        OptionParser parser = new OptionParser();
        ArgumentAcceptingOptionSpec<String> spec = parser.accepts( "abc" ).withOptionalArg();

        ArgumentAcceptingOptionSpec<Ctor> typed = spec.ofType( Ctor.class );
        assertEquals( "foo", parser.parse( "--abc", "foo" ).valueOf( typed ).getS() );
    }

    @Test
    public void rejectsUnicodeZeroAsCharValueSeparatorForRequiredArgument() {
    	assertThrows( IllegalArgumentException.class, () -> new OptionParser().accepts( "a" ).withRequiredArg().withValuesSeparatedBy( '\u0000' ));
    }

    @Test
    public void rejectsUnicodeZeroAsCharValueSeparatorForOptionalArgument() {
    	assertThrows( IllegalArgumentException.class, () -> new OptionParser().accepts( "b" ).withOptionalArg().withValuesSeparatedBy( '\u0000' ));
    }

    @Test
    public void rejectsUnicodeZeroInStringValueSeparatorForRequiredArgument() {
    	assertThrows( IllegalArgumentException.class, () -> new OptionParser().accepts( "c" ).withRequiredArg().withValuesSeparatedBy( "::\u0000::" ));
    }

    @Test
    public void rejectsUnicodeZeroInStringValueSeparatorForOptionalArgument() {
    	assertThrows( IllegalArgumentException.class, () -> new OptionParser().accepts( "d" ).withOptionalArg().withValuesSeparatedBy( "::::\u0000" ));
    }

    @Test
    public void rejectsNullConverter() {
    	assertThrows( NullPointerException.class, () -> new OptionParser().accepts( "c" ).withRequiredArg().withValuesConvertedBy( null ));
    }

    @Test
    public void rejectsNullDefaultValue() {
    	assertThrows( NullPointerException.class, () -> new OptionParser().accepts( "d" ).withRequiredArg().ofType( Integer.class ).defaultsTo( (Integer) null ));
    }

    @Test
    public void rejectsNullDefaultValueRemainder() {
    	assertThrows( NullPointerException.class, () -> new OptionParser().accepts( "d" ).withRequiredArg().ofType( Integer.class ).defaultsTo( 2, (Integer[]) null ));
    }

    @Test
    public void rejectsNullInDefaultValueRemainder() {
    	assertThrows( NullPointerException.class, () -> new OptionParser().accepts( "d" ).withRequiredArg().ofType( Integer.class ).defaultsTo( 2, 3, null ));
    }
}
