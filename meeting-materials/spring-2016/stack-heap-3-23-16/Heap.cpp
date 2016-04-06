#include "stdio.h"
#include "stdlib.h"

/*
 * Sample object.
 */
class Account {
public:
	
	/*
	 * Constructor, create new account.
	 */
	Account(char *p_name, int num) {
		mp_checking_acc = new Account(num + 200);
		mp_name = p_name;
		m_num = num;
		m_balance = 0;
	}

	/*
	 * Destructor, almost always prefix with virtual.
	 */
	virtual ~Account() {
		delete mp_checking_acc;
		if (mp_name != NULL)
			printf("Deleted checking account %d\n", m_num);
	}

	void deposit(double amount) {
		m_balance += amount;
	}

	char *get_name() {
		return mp_name;
	}

	int get_num() {
		return m_num;
	}

private:
	Account *mp_checking_acc;
	char *mp_name;	
	double m_balance;
	int m_num;

	/*
	 * Private constructor, used for checking accounts.
	 */
	Account(int num) {
		mp_checking_acc = NULL;
		mp_name = NULL;
		m_num = num;
		m_balance = 0;
	}
};

Account *mkacc_bad() {
	// Let's break this into parts:
	// 	Account *p_acc = new Account("John Doe", 5);

	// Create new Account pointer on the local function stack
	Account *p_acc;

	// Create a new Account object on the heap and set p_acc to point to it
	p_acc = new Account((char *) "John Doe", 5);

	// return p_acc; Whoops, forgot to return anything!
	// The local function stack now pops p_acc, but of course can't touch
	// the new account object since its on the heap. Without a pointer to
	// the newly allocated memory, a leak occurs!
}

Account *mkacc_good() {
	Account *p_acc;
	p_acc = new Account((char *) "John Doe", 5);

	return p_acc;
	// Now p_acc is still popped, since it was local to the function,
	// but its value is copied out via the return.
}

int main(int argc, char *argv[]) {
	if (argc < 2) {
		printf("Usage: Heap <0/1>\n\t0=bad_alloc\n\t1=good_alloc\n");
		exit(1);
	}
	bool bad = !atoi(argv[1]);

	Account *p_acc;
	p_acc = bad ? mkacc_bad() : mkacc_good();
	if (p_acc == NULL) {
		printf("Pointer is NULL!\n");
	} else {
		int acc_num;
		printf("\tAccount Name: %s\n\tAccount Number: %d\n\n", p_acc->get_name(), acc_num = p_acc->get_num());	
		delete p_acc;
		printf("Deleted account %d\n", acc_num);
	}


	
	return 0;
}
