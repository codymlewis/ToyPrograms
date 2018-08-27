CC=cc
CFLAGS=-c -g -Wall
LDFLAGS=
SOURCES=Node.c Heap.c test.c
OBJECTS=$(SOURCES:.c=.o)
EXECUTABLE=test

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.c
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
