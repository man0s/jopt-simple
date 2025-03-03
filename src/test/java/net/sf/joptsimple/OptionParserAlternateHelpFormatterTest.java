package net.sf.joptsimple;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionParserAlternateHelpFormatterTest extends AbstractOptionParserFixture {
    private StringWriter sink;
    private Map<String, ? extends OptionDescriptor> captured;

    @BeforeEach
    public void primeParser() throws Exception {
        captured = new HashMap<>();

        parser.accepts( "b", "boo" );

        parser.formatHelpWith( new HelpFormatter() {
            public String format( Map<String, ? extends OptionDescriptor> options ) {
                captured = options;
                return "some help you are";
            }
        } );

        sink = new StringWriter();

        parser.printHelpOn( sink );
    }

    @Test
    public void asksAlternateFormatterForHelpString() {
        assertEquals( "some help you are", sink.toString() );
    }

    @Test
    public void getsFedOptionDescriptorsForRecognizedOptions() {
        assertEquals( 2, captured.size() );
        Iterator<? extends Map.Entry<String,? extends OptionDescriptor>> iterator = captured.entrySet().iterator();
        Map.Entry<String, ? extends OptionDescriptor> first = iterator.next();
        assertEquals( "[arguments]", first.getKey() );
        Map.Entry<String, ? extends OptionDescriptor> second = iterator.next();
        assertEquals("b", second.getKey());
        OptionDescriptor descriptor = second.getValue();
        assertEquals( descriptor.options(), singletonList( "b" ) );
        assertEquals( "boo", descriptor.description() );
        assertFalse( descriptor.acceptsArguments() );
        assertFalse( descriptor.requiresArgument() );
        assertEquals( "", descriptor.argumentDescription() );
        assertEquals( "", descriptor.argumentTypeIndicator() );
        assertEquals( Collections.emptyList(), descriptor.defaultValues() );
    }
}
