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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import net.sf.joptsimple.ValueConversionException;
import net.sf.joptsimple.ValueConverter;
import net.sf.joptsimple.internal.Messages;

/**
 * Converts values to {@link java.net.InetAddress} using {@link InetAddress#getByName(String) getByName}.
 *
 * @author <a href="mailto:r@ymund.de">Raymund F\u00FCl\u00F6p</a>
 */
public final class InetAddressConverter implements ValueConverter<InetAddress> {
    private static final InetAddressConverter INSTANCE = new InetAddressConverter();

    private InetAddressConverter() {
    }

    @Override
    public InetAddress convert( String value ) {
        try {
            return InetAddress.getByName( value );
        } catch ( UnknownHostException e ) {
            throw new ValueConversionException( message( value ) );
        }
    }

    @Override
    public String revert( InetAddress value ) {
        return value.getHostName();
    }

    @Override
    public Class<InetAddress> valueType() {
        return InetAddress.class;
    }

    @Override
    public String valuePattern() {
        return null;
    }

    private String message( String value ) {
        return Messages.message(
            Locale.getDefault(),
            "net.sf.joptsimple.ExceptionMessages",
            InetAddressConverter.class,
            "message",
            value );
    }

    public static InetAddressConverter of() {
        return INSTANCE;
    }
}
