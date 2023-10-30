import java.util.Scanner;

class Jetiqui{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String array[] = new String[3];
        int n = s.nextInt();
        s.nextLine();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 3; j++){
                String x = s.next();
                if(x.length() > 15){
                    System.exit(0);
                }
                array[j] = x;
            }
            int indexes[] = new int[2], count = 0;
            for(int k = 0; k < array[2].length(); k++){
                char is_under = array[2].charAt(k);
                if(is_under == '_'){
                    indexes[count] = k;
                    count++;
                }
            }
            char l1 = array[0].charAt(indexes[0]), l2 = array[0].charAt(indexes[1]), l3 = array[1].charAt(indexes[0]), l4 = array[1].charAt(indexes[1]);
            if(l2 == l3 || l1 == l4){
                System.out.println("Y");
            }
            else{
                System.out.println("N");
            }
        }
    }
}