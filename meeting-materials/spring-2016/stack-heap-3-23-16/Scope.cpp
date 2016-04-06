#include "stdio.h"
#include "stdlib.h"

/*
 * Sample object.
 */
class Account {
public:
	Account(char *p_name, int num) {
		mp_name = p_name;
		m_num = num;
		m_balance = 0;
	}

	void deposit(double amount) {
		m_balance += amount;
	}

	char *get_name() {
		return mp_name;
	}

private:
	char *mp_name;	
	double m_balance;
	int m_num;
};

void obj_pass(Account acc) {
	// Pass by value, slow
}

void ref_pass(Account *p_acc) {
	// Pass by reference, fast
}

void ref_pass_alt(Account &acc) {
	// Also a pass by reference
}

int *mkarray_stack(int length) {
	printf("%s\n", "Using stack...");
	int arr[length];
	for (int i = 0; i < length; i++)
		arr[i] = 100 + i;
	return arr;
}

int *mkarray_heap(int length) {
	printf("%s\n", "Using heap...");
	int *p_arr = new int[length];
	for (int i = 0; i < length; i++)
		p_arr[i] = 100 + i;
	return p_arr;
}

int main(int argc, char *argv[]) {
	if (argc < 2) {
		printf("Usage: Scope <0/1>\n\t0=stack\n\t1=heap\n");
		exit(1);
	}
	bool use_stack = !atoi(argv[1]);

	Account acc = Account((char *) "Matthew Rasa", 17); // Push Account obj to stack
	Account *p_acc = new Account((char *) "Matthew Rasa", 17); // Push Account obj to heap

	// Passing objects
	obj_pass(acc);
	ref_pass(&acc);
	ref_pass(p_acc);
	ref_pass_alt(acc);

	// Allocate array (on either stack or heap)
	int length = 10;
	int *p_accs = use_stack ? mkarray_stack(length) :
				mkarray_heap(length);
	for (int i = 0; i < length; i++)
		printf("%d\n", p_accs[i]);

	// Heap cleanup
	delete p_acc;
	if (!use_stack)
		delete[] p_accs;

	return 0;
}
