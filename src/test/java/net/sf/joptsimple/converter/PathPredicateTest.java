package net.sf.joptsimple.converter;

import static net.sf.joptsimple.converter.PathPredicate.DIRECTORY_EXISTING;
import static net.sf.joptsimple.converter.PathPredicate.FILE_EXISTING;
import static net.sf.joptsimple.converter.PathPredicate.FILE_OVERWRITABLE;
import static net.sf.joptsimple.converter.PathPredicate.NOT_EXISTING;
import static net.sf.joptsimple.converter.PathPredicate.READABLE;
import static net.sf.joptsimple.converter.PathPredicate.WRITABLE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class PathPredicateTest {
    @Test
    public void readableFile() throws Exception {
        Path path = Files.createTempFile( "prefix", null );

        path.toFile().deleteOnExit();

        assertTrue( READABLE.accept( path ) );
        assertFalse( DIRECTORY_EXISTING.accept( path ) );
        assertTrue( FILE_EXISTING.accept( path ) );
        assertTrue( FILE_OVERWRITABLE.accept( path ) );
        assertTrue( WRITABLE.accept( path ) );
        assertFalse( NOT_EXISTING.accept( path ) );
    }

    @Test
    public void nonExisting() throws Exception {
        Path path = Files.createTempFile( "prefix", null );

        Files.deleteIfExists( path );

        assertFalse( READABLE.accept( path ) );
        assertFalse( DIRECTORY_EXISTING.accept( path ) );
        assertFalse( FILE_EXISTING.accept( path ) );
        assertFalse( FILE_OVERWRITABLE.accept( path ) );
        assertTrue( NOT_EXISTING.accept( path ) );
        assertFalse( WRITABLE.accept( path ) );
    }

    @Test
    public void directory() throws Exception {
        Path path = Files.createTempDirectory( "prefix" );

        path.toFile().deleteOnExit();

        assertTrue( READABLE.accept( path ) );
        assertTrue( DIRECTORY_EXISTING.accept( path ) );
        assertFalse( FILE_EXISTING.accept( path ) );
        assertFalse( FILE_OVERWRITABLE.accept( path ) );
        assertFalse( NOT_EXISTING.accept( path ) );
        assertTrue( WRITABLE.accept( path ) );
    }
}
