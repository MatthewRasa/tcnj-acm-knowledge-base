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
private:
	char *mp_name;	
	double m_balance;
	int m_num;
};

/*
 * Push param to stack, reserve empty space for return value.
 */
int func(int param) {
	int a = param; // Push a to stack
	float b = 4.5; // Push b to stack
	double c = 5.5; // Push c to stack

	return 17; // Before exiting, store return value in reserved space
} // On exit, pop all variables pushed to stack in this function

/*
 * Begin stack.
 */
int main(int argc, char *argv[]) {
	int a = 5; // Push a to stack
	float b = 4.5; // Push b to stack
	double c = 5.5; // Push c to stack

	char *strings[3] {
		(char *) "Push me to stack",
		(char *) "Me next",
		(char *) "Then me"
	};

	func(a); // Add function call to stack

	Account acc = Account((char *) "Matthew Rasa", 17); // Add object to stack

	return 0;
} // On exit, pop all variables pushed to stack
