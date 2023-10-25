#include <stdio.h>

int main(){
    int n;
    scanf("%d", &n);
    for(int i = 0; i < n; i++){
        char[50] s;
        int j, x;
        scanf("%s", &s);
        scanf("%d", &x);
        while(s[j] != '\0'){
            int z = ((int)s[j] + z) % 90;
            if(z < 65){
                z += 65;
            }
            j++;
            printf("%c\n", z);
        }
    }
    return 0;
}