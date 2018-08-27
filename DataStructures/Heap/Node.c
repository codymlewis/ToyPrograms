#include <string.h>
#include "Node.h"
/**
 * Node.c
 * A Node struct for a heap
 *
 * Author: Cody Lewis
 * Date: 2018-08-14
 */
/* Construct a new node */
struct Node* new_node(void* data) {
    struct Node* this;
    this = malloc(sizeof(struct Node));
    this->right = NULL;
    this->left = NULL;
    this->parent = NULL;
    this->data = data;
    return this;
}
void destroy_node(struct Node* this) {
    if(this) {
        if(this->left) {
            destroy_node(this->left);
        }
        if(this->right) {
            destroy_node(this->right);
        }
        free(this);
    }
}
/* Mutators */
enum bool set_data(struct Node* this, void* data) {
    this->data = data;
    return true;
}
enum bool set_parent(struct Node* this, struct Node* that) {
    this->parent = that;
    return true;
}
enum bool set_left(struct Node* this, struct Node* that) {
    this->left = that;
    return true;
}
enum bool set_right(struct Node* this, struct Node* that) {
    this->right = that;
    return true;
}
/* Queries */
void* get_data(struct Node* this) {
    return this->data;
}
struct Node* get_parent(struct Node* this) {
    return this->parent;
}
struct Node* get_left(struct Node* this) {
    return this->left;
}
struct Node* get_right(struct Node* this) {
    return this->right;
}
