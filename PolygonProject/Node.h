/* Node.h - Assignment 1
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 08-MAR-2018
 * Last Modified: 18-MAR-2018
 * Description:
 * The header file for the Node object
 */
#ifndef NODE_h
#define NODE_h
#include<iostream>
namespace lewis_a1 {
  template<typename T>
  class Node {
    private:
      // Member variables
      T data;
      Node<T>* prev;
      Node<T>* next;
    public:
      // Constuctors
      
      // Default Constructor
      // Pre-conditions:  None
      // Post-conditions: this Node is constructed
      Node();

      // Input Constuctors
      // Pre-conditions:  Template type T object is sent in
      // Post-conditions: this Node is constructed with the object set as data
      Node(const T& newData);

      // Destructor
      // Pre-conditions:  this Node is instantiated
      // Post-conditions: this Node is destructed
      ~Node();

      // Mutators
      
      // Input method
      // Pre-conditions:  Template type T object is sent in
      // Post-conditions: data member variable hold the sent in object
      void setData(const T& newData);

      // Pointer setting methods
      // Pre-conditions:  A Node<T> Pointer is sent in
      // Post-conditions: The respective pointer is set to the one in the argument
      void setPrev(Node<T>* newPrev);
      void setNext(Node<T>* newNext);

      // Data mutation
      // Pre-conditions:  None
      // Post-conditions: A reference to the data is returned
      T& modData();

      // Queries
      
      // Query data
      // Pre-conditions:  None
      // Post-conditions: A reference to the data is returned
      const T& getData() const;

      // Get pointer
      // Pre-conditions:  None
      // Post-conditions: The respective pointer is returned
      Node<T>* getPrev() const;
      Node<T>* getNext() const;
  };
  // Operator overloads
  template<typename T>
  std::istream& operator>>(std::istream& in,Node<T>& n);
}
#include "Node.hpp"
#endif
