#ifndef HEAP_h
#define HEAP_h
#include <stdlib.h>
#include "Node.h"
/**
 * Heap.h
 * Node based heap implementation
 *
 * Author: Cody Lewis
 */
struct Heap {
    struct Node* root;
    struct Node* last_node;
    unsigned int size;
    int* lowest_val;
};

struct Heap* new_heap();
void destroy_heap(struct Heap* this);
int* largest(struct Heap* this);
struct Node* siftdown(struct Heap* this, struct Node* current);
void delete(struct Heap* this);
void insert(struct Heap* this, void* val);
#endif
