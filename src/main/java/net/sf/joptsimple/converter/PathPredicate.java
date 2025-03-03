package net.sf.joptsimple.converter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * Enum for checking common conditions of files and directories.
 *
 * @see PathConverter
 */
public enum PathPredicate {
    FILE_EXISTING("file.existing", Files::isRegularFile),
    DIRECTORY_EXISTING("directory.existing", Files::isDirectory),
    NOT_EXISTING("file.not.existing", Files::notExists),
    FILE_OVERWRITABLE("file.overwritable", p -> Files.isRegularFile( p ) && Files.isWritable( p )),
    READABLE("file.readable", Files::isReadable),
    WRITABLE("file.writable", Files::isWritable);

    private final String messageKey;
    private final Predicate<Path> predicate;

    private PathPredicate(String messageKey, Predicate<Path> predicate) {
        this.messageKey = messageKey;
        this.predicate = predicate;
    }

    public final boolean accept( Path path ) {
        return predicate.test( path );
    }

    String getMessageKey() {
        return messageKey;
    }
}
