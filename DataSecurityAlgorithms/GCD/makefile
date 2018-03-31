CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=gcd.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=gcd

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
