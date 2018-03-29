/* MyPolygons.cpp - Assignment 1
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 12-MAR-2018
 * Last Modified: 24-MAR-2018
 * Description:
 * The implementation of the MyPolygons object
 */
#include <iostream>
#include <sstream>
#include <string>
#include "Node.h"
#include "Queue.h"
#include "Polygon.h"
#include "MyPolygons.h"
namespace lewis_a1{
  // Constructors
  MyPolygons::MyPolygons(){
    length = 0;
    sentinal = NULL;
    current = sentinal;
  }
  MyPolygons::MyPolygons(MyPolygons& mp){
  // take the Polygons from mp then sort them
    length = 0;
    sentinal = NULL;
    current = sentinal;
    Node<Polygon>* temp = mp.getSentinal();
    if(temp){ // if not empty
      append(temp->modData());
      current = sentinal; // assurance
      if((temp->getNext() != temp) && (temp->getNext()->getNext() == temp)){ // if length == 2
        temp = temp->getNext();
        if(temp->getData() > sentinal->getData()){
          append(temp->modData());
        } else {
          prepend(temp->modData());
        }
      } else {
        temp = temp->getNext();
        while(temp != mp.getSentinal()){ // stops case of one Node copying multiple times
          if(temp && current){
            if(temp->getData() > current->getData()){ // this line caused errors in cygwin specifically rhs
              while((current->getNext() != sentinal) && (temp->getData() > current->getNext()->getData())){
                current = current->getNext(); // sorting
              }
              current = current->getNext();
              insert(temp->modData());
            } else {
              prepend(temp->modData());
            }
          }
          if(temp)
            temp = temp->getNext();
        }
      }
      current = sentinal;
    }
  }
  // Destructor
  MyPolygons::~MyPolygons(){
    if(length>1){
      Node<Polygon>* temp = sentinal->getNext();
      while(temp != sentinal){
        Node<Polygon>* rm = temp;
        temp = temp->getNext();
        delete rm;
        length--;
      }
      delete temp;
    } else if(sentinal) {
      delete sentinal;
    }
  }
  // Mutators
  void MyPolygons::firstAdd(Polygon& p){ // private
    sentinal = new Node<Polygon>();
    sentinal->modData().addPoint(p);
    sentinal->setPrev(sentinal);
    sentinal->setNext(sentinal);
    current = sentinal;
  }
  void MyPolygons::prepend(Polygon& p){
  // current is the new head in the list
    if(length == 0){
      firstAdd(p);
    } else {
      Node<Polygon>* newNode = new Node<Polygon>();
      newNode->modData().addPoint(p);
      newNode->setPrev(sentinal->getPrev());
      newNode->setNext(sentinal);
      sentinal->getPrev()->setNext(newNode);
      sentinal->setPrev(newNode);
      sentinal = newNode;
      current = sentinal;
      newNode = NULL;
    }
    length++;
  }
  void MyPolygons::prepend(Node<Polygon>*& np){
    if(length == 0){
      sentinal = np;
      sentinal->setPrev(sentinal);
      sentinal->setNext(sentinal);
    } else {
      np->setNext(sentinal);
      np->setPrev(sentinal->getPrev());
      sentinal->getPrev()->setNext(np);
      sentinal->setPrev(np);
      sentinal = np;
    }
    current = sentinal;
    np = NULL;
    length++;
  }
  void MyPolygons::append(Polygon& p){
  // current stays the head of the list
    if(length == 0){
      firstAdd(p);
    } else {
      current = sentinal;
      Node<Polygon>* newNode = new Node<Polygon>();
      newNode->modData().addPoint(p);
      newNode->setPrev(sentinal);
      newNode->setNext(sentinal->getNext());
      sentinal->getNext()->setPrev(newNode);
      sentinal->setNext(newNode);
      newNode = NULL;
    }
    length++;
  }
  void MyPolygons::append(Node<Polygon>*& np){
    if(length == 0){
      sentinal = np;
      sentinal->setPrev(sentinal);
      sentinal->setNext(sentinal);
    } else {
      np->setPrev(sentinal);
      np->setNext(sentinal->getNext());
      sentinal->getNext()->setPrev(np);
      sentinal->setNext(np);
    }
    current = sentinal;
    np = NULL;
    length++;
  }
  bool MyPolygons::insert(Polygon& in){
  // insert before current
    if(length == 0){
      firstAdd(in);
    } else {
      Node<Polygon>* newNode = new Node<Polygon>();
      newNode->modData().addPoint(in);
      current->getPrev()->setNext(newNode);
      newNode->setNext(current);
      newNode->setPrev(current->getPrev());
      current->setPrev(newNode);
      newNode = NULL;
    }
    length++;
    return true;
  }
  bool MyPolygons::insert(Node<Polygon>*& np){
    if(length == 0){
      sentinal = np;
      sentinal->setPrev(sentinal);
      sentinal->setNext(sentinal);
      current = sentinal;
    } else {
      current->getPrev()->setNext(np);
      np->setNext(current);
      np->setPrev(current->getPrev());
      current->setPrev(np);
    }
    length++;
    return true;
  }
  void MyPolygons::resetCurr(){
  // reset current to point at the head of the list
    current = sentinal;
  }
  void MyPolygons::pop(){
  // taking an item from the head of the list
    if(length>1){
      if(current == sentinal){
        current = sentinal->getNext();
      }
      Node<Polygon>* rm = sentinal;
      sentinal = sentinal->getNext();
      sentinal->setPrev(rm->getPrev());
      rm->getPrev()->setNext(sentinal);
      delete rm;
      rm = NULL;
      length--;
    } else if(length==1){
      delete sentinal;
      sentinal = NULL;
      current = sentinal;
      length = 0;
    }
  }
  // Queries
  const Polygon& MyPolygons::front() const{
    return sentinal->getData();
  }
  Node<Polygon>* MyPolygons::getSentinal() const{
    return sentinal;
  }
  const std::string MyPolygons::toString() const{
    std::string str = "";
    if(length > 0){
      Node<Polygon>* temp = sentinal->getNext();
      while(temp != sentinal){
        str = str + temp->getData().toString() + ",";
        temp = temp->getNext();
      }
      int index = str.find(",");
      if(index > -1){
        str.erase(str.length()-1);
        str = "{" + temp->getData().toString() + ", " + str + "}";
      } else {
        str = "{" + temp->getData().toString() + str + "}";
      }
    } else {
      str = "{}";
    }
    return str;
  }
  // Operator overload
  std::istream& operator>>(std::istream& in,MyPolygons& mp){
    std::string input = "";
    getline(in,input);
    while(input.find("P") < input.length()){ // while there are more Polygon inputs
      std::stringstream ss(input);
      std::string str;
      ss >> str;
      if(str != "P") return in; // check that the input is a Polygon
      int vertices;
      ss >> vertices; // vertice number input
      Queue<Point>* q = new Queue<Point>();
      for(int i = 0; i < vertices; i++){
        ss >> *q; // input each point
      } 
      Node<Polygon>* np = new Node<Polygon>();
      np->modData().addPoint(q); // stops Destructor call of Polygon
      mp.append(np);
      delete q;
      q = NULL;
      if(input.find_last_of("P") > 0)
        input = input.substr(input.find("P",1));
      else
        break;
    }
    return in;
  }
}
