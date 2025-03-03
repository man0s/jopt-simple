package net.sf.joptsimple.converter;

import static net.sf.joptsimple.converter.PathPredicate.DIRECTORY_EXISTING;
import static net.sf.joptsimple.converter.PathPredicate.FILE_OVERWRITABLE;
import static net.sf.joptsimple.converter.PathPredicate.NOT_EXISTING;
import static net.sf.joptsimple.converter.PathPredicate.READABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.sf.joptsimple.ValueConversionException;

public class PathConverterTest {
    @Test
    public void convertsValuesToPaths() throws Exception {
        Path path = Files.createTempFile( "prefix", null );
        path.toFile().deleteOnExit();

        String pathName = path.toString();

        assertEquals( path, PathConverter.of( new PathPredicate[] {} ).convert( pathName ) );
        assertEquals( path, PathConverter.of( List.of() ).convert( pathName ) );
    }

    @Test
    public void answersCorrectValueType() {
        assertEquals( Path.class, PathConverter.of().valueType() );
    }

    @Test
    public void testReadableAndOverwritableFile() throws Exception {
        Path path = Files.createTempFile( "prefix", null );
        path.toFile().deleteOnExit();

        String pathName = path.toString();

        assertTrue( Files.isReadable( PathConverter.of( READABLE ).convert( pathName ) ) );
        assertTrue( Files.exists( PathConverter.of( READABLE ).convert( pathName ) ) );
        assertTrue( Files.isWritable( PathConverter.of( READABLE ).convert( pathName ) ) );
        assertTrue( Files.isWritable( PathConverter.of( FILE_OVERWRITABLE ).convert( pathName ) ) );
    }

    @Test
    public void testNotExisting() throws Exception {
        Path path = Files.createTempFile( "prefix", null );

        Files.deleteIfExists( path );

        assertFalse( Files.exists( PathConverter.of( NOT_EXISTING ).convert( path.toString() ) ) );
    }

    @Test
    public void testNotReadable() throws Exception {
        Path path = Files.createTempFile( "prefix", null );
        String pathName = path.toString();
        Files.deleteIfExists( path );

        var exception =
            assertThrows( ValueConversionException.class, () -> PathConverter.of( READABLE ).convert( pathName ) );
        assertTrue( exception.getMessage().contains( "File [" + pathName ) );
    }

    @Test
    public void testDirectoryExisting() throws Exception {
        Path path = Files.createTempDirectory( "prefix" );
        path.toFile().deleteOnExit();

        String pathName = path.toString();

        assertTrue( Files.isDirectory( PathConverter.of( DIRECTORY_EXISTING ).convert( pathName ) ) );
    }

    @Test
    public void testDirectoryNotOverwritable() throws Exception {
        Path path = Files.createTempDirectory( "prefix" );
        path.toFile().deleteOnExit();

        String pathName = path.toString();

        var exception = assertThrows( ValueConversionException.class,
            () -> PathConverter.of( FILE_OVERWRITABLE ).convert( pathName ) );
        assertTrue( exception.getMessage().contains( "File [" + pathName ) );
    }

    @Test
    public void testNotExistingNotOverwritable() throws Exception {
        Path path = Files.createTempDirectory( "prefix" );
        String pathName = path.toString();
        Files.deleteIfExists( path );

        var exception = assertThrows( ValueConversionException.class,
            () -> PathConverter.of( FILE_OVERWRITABLE ).convert( pathName ) );
        assertTrue( exception.getMessage().contains( "File [" + pathName ) );
    }
}
