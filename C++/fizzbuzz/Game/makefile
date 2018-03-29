CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=fizzbuzzGame.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=fbg

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
