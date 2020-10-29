package io.github.liambloom.softwareEngineering.chapter7;

import java.io.*;

/*
 * This would keep track of the characters that were printed,
 * and allow them to be cleared using the backspace ('\b')
 * character. It would replace System.out by doing 
 * `System.setOut(new PrintStream(new ClearableOutput()))`.
 * I don't know if it would be able to monitor things printed
 * to System.err and System.in. You can print to stdout without
 * System.out using https://stackoverflow.com/a/13172932. One 
 * other important note is https://stackoverflow.com/a/36250626
 * (difference between output stream types).
 * 
 * Also, it it doesn't monitor System.in and System.err, I could
 * use another class that combines them (Or maybe just modify
 * this class to do so). Or maybe have a central private thing 
 * that keeps track of all with OutputStream and InputStream
 * classes to be used for those three. Or maybe use the 
 * OutputStream and InputStream ones internally, set 
 * System.in/out/err behind the scenes.
 * 
 * Use a public class that works for input and output (I wish
 * java supported multiple inheritance), and use default (non-
 * public classes), one which extends InputStream, and one
 * that extends OutputStream, and the public class's constructor
 * would set System.in/out/err to instances of the default 
 * classes
 * 
 * Another thing: https://stackoverflow.com/a/23419451 - There
 * is no way in java to check if stdout and stdin are ttys. You
 * can use another language (such as C), and use JNI to use that
 * in java, but there is not pure java solution. There is, however,
 * the System.conosle() method, which returns a Console object
 * that uses uses the terminal if both stdin AND stdout are ttys,
 * and null otherwise
 * 
 * If I use System.console(), I will get a PrintWriter, and to access
 * the raw output stream `out`, I would need to extend it, because it
 * is protected
 */

// I used an external library to interact between Reader/Writer classes
// and Input/OutputStream classes. Usually I would just try and do it
// myself, but I have no clue how to do encoding. Also, since this isn't
// for a required thing anyway, I think it's fine.
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.commons.io.output.WriterOutputStream;

public class ClearableOutput {
    /*protected static final InputStream in = new ReaderInputStream(new Reader() {
        private static final Reader inner;

        public void close() {
            inner.close();
        }

        public int read(char[] buf, int off, int len) {
            inner.read(buf, off, len);
        }
    });
    private static final Reader innerIn;
    protected static final OutputStream out = new WriterOutputStream();
    private static final Writer innerOut;

    private static final boolean initError;

    static {
        Console c = System.console();
        if (c == null) {
            initError = true;
            innerOut = null;
            innerIn = null;
        }
        else {
            initError = false;
            innerOut = c.writer();
            innerIn = c.reader();
        }
    }

    public static void init() {

    }

    public ClearableOutput() {
        this(FileDescriptor.out);
    }

    public ClearableOutput(OutputStream out) {
        this.out = out;
    }*/
}
