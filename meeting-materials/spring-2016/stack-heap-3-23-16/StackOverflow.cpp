#include "stdio.h"
#include "stdlib.h"

// Stack overflows from infinite function calls
int fibonacci(int value) {
	/* Forget to put in base case, never ends
	if (value == 1)
		return 0;
	if (value == 2)
		return 1;*/
	return fibonacci(value - 1) + fibonacci(value - 2);
}

int main(int argc, char *argv[]) {

	// Stack will overflow
	fibonacci(5);

	printf("%s\n", "Program never gets here!");
}
