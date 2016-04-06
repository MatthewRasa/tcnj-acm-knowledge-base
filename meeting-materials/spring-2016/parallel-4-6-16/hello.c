#include <stdio.h>
#include "omp.h"

int main() {
#pragma omp parallel num_threads(16)
    {
        int t_num = omp_get_thread_num();
        printf("hello(%d) ", t_num);
        printf("world(%d)\n", t_num);
    }
}
