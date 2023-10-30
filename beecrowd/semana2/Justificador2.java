import java.util.Scanner;

class Justificador2 {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        if(n < 1 || n > 100){
            System.exit(1);
        }
        s.nextLine();
        String str[] = new String[n], row;
        for(int i = 0; i < n; i++){
            row = s.nextLine().trim().replaceAll(" +", " ");
            if(row.length() == 0){
                System.exit(0);
            }
            str[i] = row;
        }
        int maior = 0;
        for(int i = 0; i < str.length; i++){
            if(str[i].length() > maior){
                maior = str[i].length();
            }
        }
        for(int i = 0; i < str.length; i++){
            int qtd = maior - str[i].length();
            String spaces = "";
            for(int j = 0; j < qtd; j++){
                spaces += " ";
            }
            str[i] = spaces + str[i];
            System.out.println(str[i]);
        }
        s.close();
    }
}
