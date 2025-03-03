# JOpt Simple

JOpt Simple is a Java library for parsing command line options, such as those you might pass to an invocation of `javac`.

In the interest of striving for simplicity, as closely as possible JOpt Simple attempts to honor the command line option syntaxes of POSIX `getopt()` and GNU  `getopt_long()`. It also aims to make option parser configuration and retrieval of options and their arguments simple and expressive, without being overly clever.

Here are some libraries that perform the same duties as JOpt Simple:

* JArgs
* Jakarta Commons CLI
* TE-Code has a command line parsing library.
* argparser
* Java port of GNU getopt
* Args4J
* JSAP
* CLAJR
* CmdLn
* JewelCli
* JCommando
* parse-cmd
* JCommander
* plume-lib Options
* picocli

I hope you'll agree that JOpt Simple tops them all in ease of use and cleanliness of code (although I admire JewelCli quite a bit).

I'd love to hear your constructive feedback, especially suggestions for improvements to the library or the site! If your project is using JOpt Simple, do let me know.

## Projects Using JOpt Simple

Here are some we know of or have known of:

* OpenJDK. From Mark Reinhold: "I thought you might be interested to know that we're using your jopt-simple library in the open-source Java Development Kit. Thanks for writing such a nice little library! It's far cleaner than any of the other alternatives out there."
* Gradle
* NATBraille
* Minecraft
