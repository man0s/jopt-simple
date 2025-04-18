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

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class ShortOptionsRequiredArgumentTest extends AbstractOptionParserFixture {
    @BeforeEach
    public final void initializeParser() {
        parser.accepts( "d" ).withRequiredArg();
        parser.accepts( "e" );
        parser.accepts( "f" );
        parser.accepts( "infile" ).withOptionalArg();
    }

    @Test
    public void argumentNotPresent() {
        var exception = assertThrows( OptionException.class, () -> parser.parse( "-d" ) );
        assertTrue( exception.options().contains( "d" ) );
    }

    @Test
    public void withArgument() {
        OptionSet options = parser.parse( "-d", "foo" );

        assertOptionDetected( options, "d" );
        assertEquals( "foo", options.valueOf( "d" ) );
        assertEquals( Optional.of( "foo" ), options.valueOfOptional( "d" ) );
        assertEquals( emptyList(), options.nonOptionArguments() );
    }

    @Test
    public void clusteredOptionsWithLastOneAcceptingAnArgumentButMissing() {        
        assertThrows( OptionException.class, () -> parser.parse( "-fed" ) );
    }
    
    @Test
    public void clusteredOptionsWithLastOneAcceptingAnArgument() {
        OptionSet options = parser.parse( "-fed", "foo" );
        
        assertOptionDetected( options, "d" );
        assertOptionDetected( options, "f" );
        assertOptionDetected( options, "e" );
        assertEquals( "foo", options.valueOf( "d" ) );
        assertEquals( Optional.of( "foo" ), options.valueOfOptional( "d" ) );
    }
    
    @Test
    public void clusteredOptionsWithOneAcceptingAnArgument() {
        OptionSet options = parser.parse( "-fde" );
        
        assertOptionDetected( options, "f" );
        assertOptionDetected( options, "d" );
        assertOptionNotDetected( options, "e" );
        
        assertEquals( "e", options.valueOf( "d" ) );
        assertEquals( Optional.of( "e" ), options.valueOfOptional( "d" ) );
    }

    @Test
    public void argumentNotPresentFollowedByAnotherOption() {
        OptionSet options = parser.parse( "-d", "--infile" );

        assertOptionDetected( options, "d" );
        assertOptionNotDetected( options, "infile" );
        assertEquals( "--infile", options.valueOf( "d" ) );
        assertEquals( Optional.of( "--infile" ), options.valueOfOptional( "d" ) );
        assertEquals( emptyList(), options.nonOptionArguments() );
    }

    @Test
    public void appearingMultipleTimes() {
        OptionSet options = parser.parse( "-d", "foo", "-d", "bar" );

        assertOptionDetected( options, "d" );
        assertEquals( asList( "foo", "bar" ), options.valuesOf( "d" ) );
        assertEquals( emptyList(), options.nonOptionArguments() );
    }

    @Test
    public void inSameToken() {
        OptionSet options = parser.parse( "-dfoo" );

        assertOptionDetected( options, "d" );
        assertEquals( singletonList( "foo" ), options.valuesOf( "d" ) );
        assertEquals( emptyList(), options.nonOptionArguments() );
    }

    @Test
    public void whenEndOfOptionsMarkerIsInPlaceOfRequiredArgument() {
        OptionSet options = parser.parse( "-d", "--", "foo", "bar" );

        assertOptionDetected( options, "d" );
        assertEquals( singletonList( "--" ), options.valuesOf( "d" ) );
        assertEquals( asList( "foo", "bar" ), options.nonOptionArguments() );
    }
}
