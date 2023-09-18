#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void g3x(float p1, float p2, int* array){

}

int main(){
    FILE *f;
    int max = 20;
    f = fopen("backup.in.txt", "r");
    char x[max];
    fgets(x, max, f);
    for(int i = 0; i < atoi(x); i++){
        char y[max];
        printf("%s", y);
        fgets(y, max, f);
        char* token1 = strtok(y, " ");
        char* token2 = strtok(NULL, " ");
        float p1 = atoi(token1)/2;
        float p2 = p1;
        int array[max];
        for(int j = 0; j < atoi(token2);j++){
            int z = atoi(fgets(y, max, f));
            array[j] = z;
        }

    }
    fclose(f);
    return 0;
}