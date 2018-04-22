CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=Node.h Queue.h Point.cpp ComparePoly.h Polygon.cpp MyPolygons.cpp PolyDemo.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=PA1

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
