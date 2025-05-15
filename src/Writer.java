import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer
{
    private static BufferedWriter writer;               // Static writer so all threads can write to the same file

    public Writer(String fileName) throws IOException
    {
        writer = new BufferedWriter(new FileWriter(fileName, true));
    }

    static synchronized public void writeLine(String line) throws IOException           //Synchronized to avoid multiple threads trying to write at the same time
    {
        writer.write(line);
        writer.newLine();
        writer.flush();                             // makes sure it writes and cleans storage for next one
    }

    public void newLine() throws IOException            //to make just a new line in the file
    {
        writer.newLine();
        writer.flush();
    }

    public void close() throws IOException
    {
        if (writer != null) {
            writer.close();
        }
    }
}
