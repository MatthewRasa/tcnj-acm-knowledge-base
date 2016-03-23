CC=g++ -std=gnu++11
CFLAGS=

all: Stack StackOverflow Scope Heap

Stack: Stack.cpp
	$(CC) $(CFLAGS) -c Stack.cpp -o Stack.o
	$(CC) $(CFLAGS) Stack.o -o Stack

StackOverflow: StackOverflow.cpp
	$(CC) $(CFLAGS) -c StackOverflow.cpp -o StackOverflow.o
	$(CC) $(CFLAGS) StackOverflow.o -o StackOverflow

Scope: Scope.cpp
	$(CC) $(CFLAGS) -c Scope.cpp -o Scope.o
	$(CC) $(CFLAGS) Scope.o -o Scope

Heap: Heap.cpp
	$(CC) $(CFLAGS) -c Heap.cpp -o Heap.o
	$(CC) $(CFLAGS) Heap.o -o Heap

clean:
	rm *.o Stack StackOverflow Scope Heap
