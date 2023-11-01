import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        float m[][] = new float[12][12], soma = 0;
        char x = s.next().charAt(0);
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                m[i][j] = s.nextFloat();
                if(j - i > 0){
                    soma += m[i][j];
                }
            }
        }
        if(x == 'M'){
            soma /= 66;
        }
        String f = String.format("%.1f", soma);
        float g = Float.parseFloat(f);
        System.out.println(f);
    }
}