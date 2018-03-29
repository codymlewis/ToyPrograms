CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=Student.cpp BSTree.h BTNode.h BSTreeDemo.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=a3

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
