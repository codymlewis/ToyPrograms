CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=Node.cpp LinkedList.cpp LinkedListDemo.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=a1

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
