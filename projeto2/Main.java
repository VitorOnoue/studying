import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main{
    public static void main(String[] args) {
        try {
            AVL avl = new AVL();
            Scanner s = new Scanner(new File("titles.csv"));
            s.nextLine();
            int i = 0;
            while(i < 5){
                i++;
                String[] infos = splitter(s.nextLine());
                ProgramaNetFlix pn = new ProgramaNetFlix(infos);    
                Node node = new Node(pn);
                avl.insert(node);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static String[] splitter(String row) {
        String[] fields = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replaceAll("^\"|\"$", "");
        }
        return fields;
    }
}