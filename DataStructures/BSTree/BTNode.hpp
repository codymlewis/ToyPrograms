/* BTNode.hpp - Assignment 3
 * Author: Cody Lewis - c3283349
 * Date: 10-10-2017
 * Last Modified: 31-10-2017
 * Description:
 * The implementation for the binary tree node
 */
// #include <iostream>
// #include <cstdlib>
// #include "BTNode.h"
namespace lewis_a03{
  //Constructor
  template<typename value_type>
  BTNode<value_type>::BTNode(){
    lChild = NULL;  //The pointers are instantiated as NULL
    rChild = NULL;
    parent = NULL;
  }
  //Destructor
  template<typename value_type>
  BTNode<value_type>::~BTNode(){
    lChild = NULL;  //Gets rid of dangling pointers
    rChild = NULL;
    parent = NULL;
  }
  //Mutators
  template<typename value_type>
  void BTNode<value_type>::setData(const value_type& newData){
    data = newData;
  }
  template<typename value_type>
  void BTNode<value_type>::setParent(BTNode<value_type>* par){
    parent = par;
  }
  template<typename value_type>
  void BTNode<value_type>::setLeft(BTNode<value_type>* newLChild){
    lChild = newLChild;
  }
  template<typename value_type>
  void BTNode<value_type>::setRight(BTNode<value_type>* newRChild){
    rChild = newRChild;
  }
  template <typename value_type>
  void BTNode<value_type>::inherit(BTNode<value_type>* del){
    parent = del->getParent();
    if(parent){
      if(data < parent->getData()){
        parent->setLeft(this);
      } else {
        parent->setRight(this);
      }
    }
    delete del;
    del = NULL;
  }
  //Queries
  template<typename value_type>
  value_type BTNode<value_type>::getData() const{
    return data;
  }
  template<typename value_type>
  BTNode<value_type>* BTNode<value_type>::getParent() const{
    return parent;
  }
  template<typename value_type>
  BTNode<value_type>* BTNode<value_type>::getLeft() const{
    return lChild;
  }
  template<typename value_type>
  BTNode<value_type>* BTNode<value_type>::getRight() const{
    return rChild;
  }
  //Operator overloads
  template <typename value_type>
  std::istream& operator>>(std::istream& in,BTNode<value_type>& btn){
    value_type addData;
    in >> addData;
    btn.setData(addData);
    return in;
  }
  template <typename value_type>
  std::ostream& operator<<(std::ostream& out,const BTNode<value_type>& btn){
    if(NULL!=btn.getLeft())           //Inorder - (Left)root(Right)
      out << *(btn.getLeft()) << " "; //recursively prints the children
    out << btn.getData() << " ";
    if(NULL!=btn.getRight())
      out << *(btn.getRight()) << " ";
    return out;
  }
}
