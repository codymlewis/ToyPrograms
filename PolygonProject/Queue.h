/* Queue.h - Assignment 1
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 08-MAR-2018
 * Last Modified: 18-MAR-2018
 * Description:
 * The header for the Queue object
 */
#ifndef QUEUE_h
#define QUEUE_h
#include <cstdlib>
#include <iostream>
#include "Node.h"
namespace lewis_a1 {
  template<typename T>
    class Queue {
      private:
        // Member variables
        Node<T>* head;
        Node<T>* tail;
        std::size_t length;
      public:
        // Constructor
        // Pre-conditions:  None
        // Post-conditions: this object is constructed
        Queue();

        // Copy Constructor
        // Pre-conditions:  A Queue is sent in
        // Post-conditions: this object is constructed as a deep copy of the Queue
        Queue(const Queue& q);

        // Destructor
        // Pre-conditions:  this Queue is instantiated
        // Post-conditions: this Queue is destructed
        ~Queue();

        // Mutators
        
        // Add to the tail of the Queue
        // Pre-conditions:  A suitable object is sent in
        // Post-conditions: That object is stored at the end of the Queue
        void push(T& newData);
        void push(Node<T>* &newData);

        // Remove from the head of the Queue
        // Pre-conditions:  None
        // Post-conditions: The head of the Queue is removed
        void pop();

        // Queries
        
        // Empty query
        // Pre-conditions:  None
        // Post-conditions: returns true if empty else returns false
        const bool isEmpty() const;

        // Length query
        // Pre-conditions:  None
        // Post-conditions: returns the length of the Queue
        std::size_t getLength() const;

        // Front query
        // Pre-conditions:  None
        // Post-conditions: returns the head of the Queue
        const T& front() const;
        Node<T>* frontNode() const;
        
        // Operator overloads
        
        // Unary addition
        // Pre-conditions:  A Queue is sent in
        // Post-conditions: The Nodes from the argument Queue are added to this
        bool operator+=(const Queue& q); 
    };
    
    // Input operator
    // Pre-conditions:  An istream and a Queue are sent in
    // Post-conditions: The input is added to the Queue
    template<typename T>
      std::istream& operator>>(std::istream& in,Queue<T>& q);
}
#include "Queue.hpp"
#endif
