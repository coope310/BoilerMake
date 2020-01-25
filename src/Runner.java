import java.io.*;
import java.util.ArrayList;

public class Runner {

    public static File file;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        file = new File("file.txt");
        ArrayList<String> text = new ArrayList<>();
        text.add("this is the first line");
        text.add("this is the second line");
        text.add("this is the third line");

        write(text, file);
        ArrayList<String> readText = read(file);

        for(String s: readText) {
            System.out.println(s);
        }
    }

    public static void write(ArrayList<String> words, File file) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(file, true);
        PrintWriter pw = new PrintWriter(fos);
        for(String s: words) {
            pw.println(s);
        }
        pw.close();
    }

    public static ArrayList<String> read(File file) throws IOException {
        ArrayList<String> words = new ArrayList<String>();
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        String line;
        while ((line = bfr.readLine()) != null) {
            // process the line
            words.add(line);
        }
        bfr.close();
        return words;
    }
}
