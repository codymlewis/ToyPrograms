/**
 * Node.cpp - Assignment01
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 2017-08-16
 * Last modified: 2017-08-24
 * Description:
 * Defines the Nodes of the LinkedList
 */
#include<iostream>
#include "Node.h"
using namespace std;
namespace lewis_a01_Node{
  Node::Node(){
    prvPtr = NULL;  //Initially the pointers must be set
    nxtPtr = NULL;  //as NULL
  };
  Node::Node(const item& itm){
    content = itm;
    prvPtr = NULL;
    nxtPtr = NULL;
  };
  void Node::setContent(const item& cntnt){
    content = cntnt;
  };
  void Node::setPrev(Node* prev){
    prvPtr = prev;
  };
  void Node::setNext(Node* next){
    nxtPtr = next;
  };
  Node::item Node::getContent() const{
    return content;
  };
  Node* Node::getPrev() const{
    return prvPtr;
  };
  Node* Node::getNext() const{
    return nxtPtr;
  };
}
