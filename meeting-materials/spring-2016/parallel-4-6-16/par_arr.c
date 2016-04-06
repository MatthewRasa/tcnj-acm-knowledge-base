#include <stdio.h>
#include "omp.h"

int main() {

    long int glob_sum = 0;
    int size = 100000;
    int array[size];

    for (int i = 0; i < size; i++) {
        array[i] = 2;
    }

#pragma omp parallel
{
    int num_threads = omp_get_num_threads();
    int t_num = omp_get_thread_num();
    long int loc_sum = 0;
    for (int i = t_num; i < size; i += num_threads) {
        //printf("Thread %d getting array element %d\n", t_num, i, array[i]);
        loc_sum += array[i];
    }
    //printf("Local sum of thread %d: %d\n", t_num, loc_sum);
    #pragma omp critical
    {
        glob_sum += loc_sum;
    }
}

    printf("Sum: %ld\n", glob_sum);
}
