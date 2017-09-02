/**
 * Node.h - Assignment01
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 2017-08-16
 * Last modified: 2017-08-23
 * Description:
 * header file for the Nodes of the LinkedList
 */
#ifndef CODY_NODE_h
#define  CODY_NODE_h
#include<iostream>
using namespace std;
namespace lewis_a01_Node{
  class Node{
    public:
      typedef string item;  //Type Definition of the item stored in the Node
      // Default Constructor
      Node();

      // Constructor for the Nodes
      // Pre-conditions: An item objects and two Node pointers are sent in
      // Post-conditions: A Node is initialized
      Node(const item& itm);

      // Setting method for the content of the Node
      // Pre-conditions: An item object item is sent in
      // Post-conditions: The Node now contains that item
      void setContent(const item& cntnt);

      // Setting method for the pointer to the Node before this one
      // Pre-conditions: A Node pointer is sent in
      // Post-conditions: This Node now contains that pointer
      void setPrev(Node* prev);

      // Setting method for the pointer to the Node after this one
      // Preostream& std::operator<<(ostream& out,const account &a)-conditions: A Node pointer is sent in
      // Post-conditions: This Node now contains that pointer
      void setNext(Node* next);

      // Getter method for the item object contained in the Node
      // Pre-conditions: The Node is initialized with an item object in it
      // Post-conditions: The item object is returned
      item getContent() const;

      // Getter method for the Node pointer before this one
      // Pre-conditions: The Nodes are initialized with this one pointer to the one before it
      // Post-conditions: The Node pointer is returned
      Node* getPrev() const;

      // Getter method for the Node pointer before this one
      // Pre-conditions: The Nodes are initialized with this one pointer to the one after it
      // Post-conditions: The Node pointer is returned
      Node* getNext() const;
    private:
      Node* prvPtr;
      Node* nxtPtr;
      item content;

  };
}
#endif
