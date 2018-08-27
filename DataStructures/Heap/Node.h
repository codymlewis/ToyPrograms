#ifndef NODE_h
#define NODE_h
#include <stdlib.h>
/**
 * Node.h
 * The header for a heap node
 *
 * Author: Cody Lewis
 * Date: 2018-08-14
 */
enum bool { false = 0, true = 1 };
struct Node {
    struct Node* parent;
    struct Node* left;
    struct Node* right;
    void* data;
};

struct Node* new_node(void* data);
void destroy_node(struct Node* this);
enum bool set_data(struct Node* this, void* data);
enum bool set_parent(struct Node* this, struct Node* that);
enum bool set_left(struct Node* this, struct Node* that);
enum bool set_right(struct Node* this, struct Node* that);
void* get_data(struct Node* this);
struct Node* get_parent(struct Node* this);
struct Node* get_left(struct Node* this);
struct Node* get_right(struct Node* this);
#endif
