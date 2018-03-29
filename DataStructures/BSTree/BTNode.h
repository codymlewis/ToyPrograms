/* BTNode.h - Assignment 3
 * Author: Cody Lewis - c3283349
 * Date: 10-10-2017
 * Last Modified: 31-10-2017
 * Description:
 * The header for the binary tree node
 */
#ifndef BTNODE_h
#define BTNODE_h
#include <iostream>
#include <cstdlib>
namespace lewis_a03{
  template<typename value_type>
  class BTNode{
  public:
    //Constructor
    //Pre-conditions:  None
    //Post-conditions: The BTNode object is instantiated
    BTNode();

    //Destructor
    //Pre-conditions:  this BTNode is instantiated
    //Post-conditions: The member variables are NULLed
    ~BTNode();

    //Mutators

    //Set the contents
    //Pre-conditions:  There is a value_type sent in
    //Post-conditions: The reference to the value_type is sent into the node
    void setData(const value_type& newData);

    //Set the parent node
    //Pre-conditions:  There is another node instantiated
    //Post-conditions: this node now points to the sent in node
    void setParent(BTNode<value_type>* par);

    //Set the left node
    //Pre-conditions:  There is another node instantiated
    //Post-conditions: this node now points to the sent in node
    void setLeft(BTNode<value_type>* newLChild);

    //set the right node
    //Pre-conditions:  There is another node instantiated
    //Post-conditions: this node now points to the sent in node
    void setRight(BTNode<value_type>* newRChild);

    //This takes a child node, attaches it to the parent of del then deletes del
    //Pre-conditions:  another node is instantiated an points to an instantiated parent
    //Post-conditions: this node now points to the parent of del and del is deleted
    void inherit(BTNode<value_type>* del);
    //Queries

    //Gets the contents of the node
    //Pre-conditions:  this node is instantiated
    //Post-conditions: The contents of this node are returned
    value_type getData() const;

    //Gets the pointer to the parent of this node
    //Pre-conditions:  this node is instantiated
    //Post-conditions: The parent pointer is returned
    BTNode<value_type>* getParent() const;

    //Gets the pointer to the left of this node
    //Pre-conditions:  this node is instantiated
    //Post-conditions: The left pointer is returned
    BTNode<value_type>* getLeft() const;

    //Gets the pointer to the right of this node
    //Pre-conditions:  this node is instantiated
    //Post-conditions: The right pointer is returned
    BTNode<value_type>* getRight() const;
  private:
    //Member variables
    value_type data;
    BTNode<value_type>* lChild;
    BTNode<value_type>* rChild;
    BTNode<value_type>* parent;
  };
  //operator overloads

  //input operator
  //Pre-conditions:  The node is instantiated
  //Post-conditions: The input data is input into the node
  template <typename value_type>
  std::istream& operator>>(std::istream& in,BTNode<value_type>& btn);

  //output operator
  //Pre-conditions:  The node is instantiated
  //Post-conditions: The contents of the node it's children are output
  template <typename value_type>
  std::ostream& operator<<(std::ostream& out,const BTNode<value_type>& btn);
}
#include "BTNode.hpp"
#endif
