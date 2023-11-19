import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            BST bst = new BST();
            AVL avl = new AVL();
            Scanner s = new Scanner(new File("titles.csv"));
            s.nextLine();
            while (s.hasNextLine()) {
                String[] infos = splitter(s.nextLine());
                boolean valid;
                if (infos[3].equals("SHOW")) {
                    valid = validateShow(infos);
                } else {
                    valid = validateMovie(infos, 9);
                }
                if (valid) {
                    ProgramaNetFlix pn = new ProgramaNetFlix(infos);
                    Node node = new Node(pn);
                    bst.insert(node);
                    avl.insert(node);
                }
            }
            System.out.println(avl.countNodes());
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

    private static boolean validateShow(String[] infos) {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].equals("")) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateMovie(String[] infos, int index) {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].equals("") && i != index) {
                return false;
            }
        }
        return true;
    }
}