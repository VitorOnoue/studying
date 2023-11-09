import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        try {
            File f = new File("titles.csv");
            Scanner s = new Scanner(f);
            String infos[] = s.nextLine().split(",");
            while (s.hasNextLine()) {
                String row[] = s.nextLine().split(",");
                // inserir na avl
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}