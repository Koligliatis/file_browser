import java.io.*;
import java.util.*;
import java.text.*;
// Read from /home/user/ the configuration file
// in a dictionary
public class Configuration {
    private Map<String,String> dictionary;
    private BufferedReader in;
    private Process p;

    public Configuration () {
        String user = "";

        dictionary = new HashMap<String,String>();
        try {
            p = Runtime.getRuntime().exec("whoami");
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            user = in.readLine();
            p.waitFor();
            p.destroy();
            in = new BufferedReader(new FileReader("/home/"+user+"/.ce325fb.config"));
            String line;
            while ((line = in.readLine()) != null) {
                String tokens [] = line.split("\\=");
                dictionary.put(tokens[0],tokens[1]);
            }
        } catch (IOException e) {
            System.out.println("File not found at "+"/home/"+user+"/.ce325fb.config");
        } catch(InterruptedException e) {
           Thread.currentThread().interrupt();
        }
    }

    public String getProgram (String extension) {
        return dictionary.get(extension);
    }
}
