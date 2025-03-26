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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class PosixlyCorrectOptionParserTest {

    private static Stream<OptionParser> parseWithPosixlyCorrect() {
        return Stream.of(
            new OptionParser() { {
                posixlyCorrect( true );
                accepts( "i" ).withRequiredArg();
                accepts( "j" ).withOptionalArg();
                accepts( "k" );
            } },
            new OptionParser( "+i:j::k" ) );
    }

    @ParameterizedTest
    @MethodSource
    public void parseWithPosixlyCorrect(OptionParser parser) {
        OptionSet options =
            parser.parse( "-ibar", "-i", "junk", "xyz", "-jixnay", "foo", "-k", "blah", "--", "yermom" );

        assertTrue( options.has( "i" ), "i?" );
        assertFalse( options.has( "j" ), "j?" );
        assertFalse( options.has( "k" ), "k?" );
        assertEquals( asList( "bar", "junk" ), options.valuesOf( "i" ), "args of i?" );
        assertEquals(
            asList( "xyz", "-jixnay", "foo", "-k", "blah", "--", "yermom" ),
            options.nonOptionArguments(),
            "non-option args?" );
    }
}
