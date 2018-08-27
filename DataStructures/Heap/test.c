#include <stdio.h>
#include "Heap.h"
int main() {
    struct Heap* h = new_heap();
    insert(h, (void*) 5);
    insert(h, (void*) 3);
    insert(h, (void*) 6);
    destroy_heap(h);
    return 0;
}
