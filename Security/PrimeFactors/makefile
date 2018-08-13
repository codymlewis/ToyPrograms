CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=prime.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=prime

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
