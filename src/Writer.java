import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer
{
    private static BufferedWriter writer;

    public Writer(String fileName) throws IOException
    {
        writer = new BufferedWriter(new FileWriter(fileName, true));
    }

    static synchronized public void writeLine(String line) throws IOException
    {
        writer.write(line);
        writer.newLine();
        writer.flush();                             // makes sure it writes and cleans storage for next one
    }

    public void newLine() throws IOException
    {
        writer.newLine();
        writer.flush();                             // makes sure it writes and cleans storage for next one
    }

    public void close() throws IOException
    {
        if (writer != null) {
            writer.close();
        }
    }
}
