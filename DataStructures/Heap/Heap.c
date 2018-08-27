#include "Heap.h"
struct Heap* new_heap() {
    struct Heap* this;
    this = malloc(sizeof(struct Heap));
    this->root = NULL;
    this->last_node = NULL;
    this->size = 0;
    this->lowest_val = NULL;
    return this;
}
void destroy_heap(struct Heap* this) {
    destroy_node(this->root);
    free(this);
}
int* largest(struct Heap* this) {
    if(this->root) {
        return (int*) this->root->data;
    }
    return NULL;
}
struct Node* siftdown(struct Heap* this, struct Node* current) {
    enum bool first_pass = true;
    while(current->left) {
        struct Node* child = current->left;
        if(current->right && (int*) current->right->data > (int*) current->left->data) {
            child = current->right;
            current->right = current->left;
            current->left = child;
        }
        if((int*) child->data > (int*) current->data) {
            void* temp = child->data;
            child->data = current->data;
            current->data = temp;
        } else {
            break;
        }
        current = child;
        if(first_pass) {
            this->root = current->parent;
            first_pass = false;
        }
    }
    return current;
}
void delete(struct Heap* this) {
    if(this->root) {
        this->root->data = this->lowest_val;
        struct Node* del = siftdown(this, this->root);
        destroy_node(del);
        this->size = this->size - 1;
    }
}
void insert(struct Heap* this, void* val) {
    if(!this->lowest_val) {
        this->lowest_val = val;
    } else {
        if(this->lowest_val > (int*) val) {
            this->lowest_val = val;
        }
    }
    struct Node* in_node = new_node(val);
    struct Node* current = this->last_node;
    if(this->root) {
        if(current->parent) {
            if(!current->parent->left) { /* if left is empty */
                current->parent->left = in_node;
            } else {
                current->parent->right = in_node;
            }
            in_node->parent = current->parent;
            current = in_node;
            while((int*) current->data > (int*) current->parent->data) {
                void* temp = current->parent->data;
                current->parent->data = current->data;
                current->data = temp;
            }
            this->last_node = in_node; /* set new last_node */
        } else {
            if(!current->left) {
                current->left = in_node;
            } else {
                current->right = in_node;
            }
        }
    } else {
        this->root = in_node;
        this->last_node = this->root;
    }
}
