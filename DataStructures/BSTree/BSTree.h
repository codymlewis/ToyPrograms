/* BSTree.h - Assignment 3
 * Author: Cody Lewis - c3283349
 * Date: 16-10-2017
 * Last Modified: 31-10-2017
 * Description:
 * The header for a binary search tree
 */
#ifndef BSTREE_h
#define BSTREE_h
#include <iostream>
#include "BTNode.h"
namespace lewis_a03{
  template <typename value_type>
  class BSTree{
  public:
    //Constructor
    //Pre-conditions:  None
    //Post-conditions: The BSTree object is initialised
    BSTree();

    //Destructor
    //Pre-conditions:  This tree is initialised
    //Post-conditions: The pointers created by it are deleted
    ~BSTree();

    //Mutators

    //The wrapper Function for add
    //Pre-conditions:  this tree is initialised, and a BTNode is sent in
    //Post-conditions: The data pointer is sent into the add private Function
    bool add(BTNode<value_type>* &data);

    //Query Functions

    //Output the tree
    //Pre-conditions:  An ostream& is sent into this tree
    //Post-conditions: The ostream& of the contents of the tree is returned
    std::ostream& outputTree(std::ostream& out) const;

    //Shows if the tree is empty
    //Pre-conditions:  this tree is instantiated
    //Post-conditions: The root of the tree is returned
    bool isEmpty() const;

    //Shows the size of the tree
    //Pre-conditions:  this tree is instantiated
    //Post-conditions: The number of vertices in this tree is returned
    std::size_t size() const;

    //searchs this tree for the node matching the specified string
    //Pre-conditions:  An ostream and string are sent in
    //Post-conditions: if there is a match the output of data returns into the ostream
    std::ostream& search(std::ostream& out,std::string name);

    //Searchs this tree for for nodes lower than the specified int
    //Pre-conditions:  this tree is instantiated
    //Post-conditions: returns the number of nodes that are < the int
    std::size_t search(int grade);

    //Returns the average of the number component of the node
    //Pre-conditions:  this tree is instantiated
    //Post-conditions: the value of this.sum() divided by this.size is returned
    double average();

    //Removes the nodes that are < the specified int
    //Pre-conditions:  this tree is initialised and an int is sent in
    //Post-conditions: the node matching the criteria are deleted
    void remove(int find);

    void invert();
  private:
    //Mutators

    //recursively deletes the nodes from the tree
    //Pre-conditions:  this tree is initialised
    //Post-conditions: all the nodes in the tree are deleted
    void destroy(BTNode<value_type>* node);

    //Adds the node pointer to the tree
    //Pre-conditions:  this tree is initialised and a BTNode pointer is sent in
    //Post-conditions: The node is correctly added to the tree
    bool add(BTNode<value_type>* &data,BTNode<value_type>* branch);

    //Sets the root pointer
    //Pre-conditions:  this tree is initialised and contains no nodes
    //Post-conditions: the arguement node is set to the root pointer
    bool setRoot(BTNode<value_type>* &data);

    //Removal Methods

    //The main removal controller
    //Pre-conditions:  An int condition and an initialised BTNode is sent in
    //Post-conditions: The nodes that fall below that condition are removed
    void remove(int find,BTNode<value_type>* node);

    //Checks the position of the node in the tree, and sends it to the suitable
    //removal method
    //Pre-conditions:  An initialised node is sent in
    //Post-conditions: The node is sent to the suitable removal method
    void remove(BTNode<value_type>* &node);

    //Removes a leaf node
    //Pre-conditions:  The sent in node is a leaf to this tree
    //Post-conditions: The node is removed
    void removeLeaf(BTNode<value_type>* &node);

    //Removes a double subtree root
    //Pre-conditions:  The sent in node is a root to 2 subtrees
    //Post-conditions: The root is replaced with the suitable node from the
    //								 subtrees
    void removeSubRoot(BTNode<value_type>* &node);

    //Finds and returns the suitable node for the replacement of the subroot
    //Pre-conditions:  The sent in node is a root to 2 subtrees
    //Post-conditions: The suitable replacement node is returned
    BTNode<value_type>* replace(BTNode<value_type>* node);

    //removes a left subtree root
    //Pre-conditions:  The sent in node points to a subtree to it's left
    //Post-conditions: The root node is correctly removed
    void removeSubLeft(BTNode<value_type>* &node);

    //removes a right subtree root
    //Pre-conditions:  The sent in node points to a subtree to it's right
    //Post-conditions: The root node is correctly removed
    void removeSubRight(BTNode<value_type>* &node);

    //Wrapper function for node removal
    //Pre-conditions:  An initialised node is sent in
    //Post-conditions: The node is deleted and dangling pointers are NULLed
    void rm(BTNode<value_type>*& node);

    //Queries

    //Finds the sum of the number contents of the nodes
    //Pre-conditions:  this tree is initialised
    //Post-conditions: the double sum of the number components of the nodes is returned
    double sum(BTNode<value_type>* node);


    //Search methods

    //String search
    //Pre-conditions:  this tree is initialised and a string is sent in
    //Post-conditions: The matching node's contents are return, if no match then NULL is implicitley returned
    value_type search(BTNode<value_type>* node,std::string name);

    //int search
    //Pre-conditions:  this tree is initialised and an int is sent in
    //Post-conditions: the sum of the number nodes below the int is returned
    std::size_t search(BTNode<value_type>* node,int grade);

    void invert(BTNode<value_type>* node);

    //Member variables
    BTNode<value_type>* root;
    bool firstAdd;
    std::size_t vertices;

  };
  //Operator overloads

  //Input operator
  //Pre-conditions:  An istream& and an initialised BSTree is sent in
  //Post-conditions: The istream& is sent in to new BTNode object, that node is
  //                 then sent in to the BSTree's add method
  template <typename value_type>
  std::istream& operator>>(std::istream& in,BSTree<value_type>& bst);

  //Output operator
  //Pre-conditions:  An ostream& and BSTree is sent in
  //Post-conditions: The contents of the BSTree is output through the stream
  template <typename value_type>
  std::ostream& operator<<(std::ostream& out,const BSTree<value_type>& bst);

  //Comparison method
  //A bit random here, but cygwin would not compile it in a separate file
  //Pre-conditions:  Two value_type are sent in
  //Post-conditions: an int representing the relation of size
  //                 between lhs and rhs is returned
  template<typename value_type>
  int compare(value_type lhs,value_type rhs);
}
#include "BSTree.hpp"
#endif
