/**
 * LinkedList.h - Assignment01
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 2017-08-16
 * Last modified: 2017-08-30
 * Description:
 * The header file for a LinkedList Program
 */
#ifndef CODY_LINKEDLIST_h
#define CODY_LINKEDLIST_h
#include<iostream>
#include "Node.h"
using namespace std;
using namespace lewis_a01_Node;
namespace lewis_a01_LinkedList{
class LinkedList{
  public:
    typedef Node::item item;  // Defines the type of item stored in the list, based on the type of item in Node
    //Default constructor
    //Pre-conditions: None
    //Post-conditions: A LinkedList is created
    LinkedList();

    //Constructor taking one instance of an item
    //Pre-conditions: An object of type item is sent in
    //Post-conditions: A LinkedList containing that item is created
    LinkedList(const item& frst);

    //Destructor for LinkedList
    //Pre-conditions: There is an initialized LinkedList
    //Post-conditions: The LinkedList is dereferenced
    ~LinkedList();  //In this program LinkedList is constructed in the stack
                    //So the Destructor will be called automatically when
                    //it goes out of scope

    //Adding functions
    //Pre-conditions: An object of type item is sent in
    //Post-conditions: The sent in object is added to the LinkedList
    void add(const item& add);

    // Getter methods for the pointers
    // Pre-conditions: The pointers are initialized
    // Post-conditions: The specified pointer is returned
    Node* current() const;
    Node* head() const;
    Node* tail() const;

    //Give the length of LinkedList
    //Pre-conditions: A LinkedList is constructed
    //Post-conditions: Returns the length of LinkedList as an int
    int length() const;

    //Gives the amount of times a specified item appears in the LinkedList
    //Pre-conditions: An of object of type item is sent in
    //Post-conditions: The count of  the item in the LinkedList is returned
    int count(const item& find) const;

    //Reverses the order of the Nodes in the list
    //Pre-conditions: The LinkedList is initialized
    //Post-conditions: The Nodes are in reverse order
    void reverse();  //I thought this would be a fun challenge

    //Removes the specified word from the LinkedList
    //Pre-conditions: An object of type item is sent in
    //Post-conditions: All occurances of the item are removed from the LinkedList
    void remove(const item& rm);

    //Concatonation: Performs a union between two LinkedLists
    //Pre-conditions: Two constructed LinkedLists are sent in
    //Post-conditions: The argument LinkedList is merged into the left hand LinkedList
    void operator +=(const LinkedList &l2);
  private:
    Node* head_ptr;
    Node* tail_ptr;
    Node* current_ptr;
    Node* add_ptr;
    int numberOfItems;
    bool firstAdd;

    //Takes care of removal of Nodes
    //Pre-conditions: A Node pointer is sent in
    //Post-conditions: The pointer is dereferenced
    void rm(Node*& rm_ptr);
    void removal(Node* temp_ptr);
    void removeLast(Node*& temp_ptr);
    void removeHead();
    void removeTail();
    void removeCurrent(Node*& temp_ptr);

    // Movement methods for current_ptr
    // Pre-conditions: There is more than one place that current_ptr can move to
    // Post-conditions: current_ptr now points to a new Node
    void forward();
    void back();

  };
  //Input
  //Pre-conditions: An object of type item is sent into the LinkedList
  //Post-conditions: The item is added to the LinkedList
  istream& operator>>(istream& in,LinkedList& l);

  //Output
  //Pre-conditions: A LinkedList is constructed
  //Post-conditions: The contents of the LinkedList are Output
  ostream& operator<<(ostream& out,const LinkedList& l);
};
#endif
