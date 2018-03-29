/* Node.hpp - Assignment 1
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 08-MAR-2018
 * Last Modified: 08-MAR-2018
 * Description:
 * The implementation of the Node object
 */
#include <iostream>
namespace lewis_a1 {
  // Constructors
  template<typename T>
    Node<T>::Node(){
      prev = NULL;
      next = NULL;
    }
  template<typename T>
    Node<T>::Node(const T& newData){
      data = newData;
    }

  // Destructor
  template<typename T>
    Node<T>::~Node(){
      prev = NULL;
      next = NULL;
    }

  // Mutators
  template<typename T>
    void Node<T>::setData(const T& newData){
      data = newData;
    }
  template<typename T>
    void Node<T>::setPrev(Node<T>* newPrev){
      prev = newPrev;
    }
  template<typename T>
    void Node<T>::setNext(Node<T>* newNext){
      next = newNext;
    }
  template<typename T>
    T& Node<T>::modData(){
      return data;
    }

  // Queries
  template<typename T>
    const T& Node<T>::getData() const{
      return data;
    }
  template<typename T>
    Node<T>* Node<T>::getPrev() const{
      return prev;
    }
  template<typename T>
    Node<T>* Node<T>::getNext() const{
      return next;
    }
  // Operator overloads
  template<typename T>
    std::istream& operator>>(std::istream& in, Node<T>& n){
      T newData; 
      in >> newData;
      n.setData(newData);
      return in;
    }
}
