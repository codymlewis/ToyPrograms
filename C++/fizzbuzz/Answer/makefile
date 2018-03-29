CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=fizzbuzz.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=fba

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
