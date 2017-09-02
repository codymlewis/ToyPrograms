/**
 * LinkedList.cpp - Assignment01
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 2017-08-16
 * Last modified: 2017-09-02
 * Description:
 * The LinkedList Definition of the object
 */
 #include<iostream>
 #include "LinkedList.h"
 #include "Node.h"
 using namespace std;
 using namespace lewis_a01_Node;
 namespace lewis_a01_LinkedList{

   //Constructors
   LinkedList::LinkedList(){
      head_ptr = new Node();  //Sets up the pointers
      tail_ptr = head_ptr;
      current_ptr = tail_ptr;
      add_ptr = NULL;
      numberOfItems = 0;   //While there is an empty Node, no items are truly contained in the list yet
      firstAdd = true;  //Makes certain that the head Node is not empty
   };
   LinkedList::LinkedList(const item& frst){
      head_ptr = new Node(frst);
      tail_ptr = head_ptr;
      current_ptr = tail_ptr;
      add_ptr = NULL;
      numberOfItems = 1;
      firstAdd = false; //The first Node already contains information
   };
   //Destructor
   LinkedList::~LinkedList(){
      while(head_ptr->getNext()!=NULL){
         removeHead();
      }
      rm(head_ptr);
   };
   //Adding methods
   void LinkedList::add(const item& add){
      if(firstAdd){
         head_ptr->setContent(add);
         firstAdd = false;
      } else {
         current_ptr = tail_ptr;
         add_ptr = new Node(add);
         add_ptr->setPrev(current_ptr);
         current_ptr->setNext(add_ptr);
         forward();  //Moves the current_ptr ahead one Node
         tail_ptr = current_ptr;
      }
      numberOfItems++;
   };
   //Pointer methods
   Node* LinkedList::current() const{
      return current_ptr;
   };
   Node* LinkedList::head() const{
      return head_ptr;
   };
   Node* LinkedList::tail() const{
      return tail_ptr;
   };
   void LinkedList::forward(){
      current_ptr = current_ptr->getNext();
   };
   void LinkedList::back(){
      current_ptr = current_ptr->getPrev();
   };
   //Query Methods
   int LinkedList::length() const{
      return numberOfItems;
   };
   int LinkedList::count(const item& find) const{
      int counter = 0;
      Node* temp_ptr = head_ptr;
      if(length()>0){
         while(temp_ptr->getNext()!=NULL){
            if(temp_ptr->getContent()==find)
               counter++;
            temp_ptr = temp_ptr->getNext();
         }
         if(temp_ptr->getContent()==find)
            counter++;
      }
      temp_ptr = NULL;
      return counter;
   };
   //Removal Methods
   void LinkedList::remove(const item& itm){
      Node* temp_ptr = head_ptr;
      if(length()>1){
         while(temp_ptr->getNext()!=NULL){
            if(temp_ptr->getContent()==itm)
               removal(temp_ptr);   //Sends to the removal controller
            temp_ptr = temp_ptr->getNext();
         };
         if(temp_ptr->getContent()==itm)
            removal(temp_ptr);
      } else if(length()==1 && temp_ptr->getContent()==itm){
         firstAdd = true;
         numberOfItems--;
      }
      temp_ptr = NULL;
   };
   void LinkedList::removal(Node* temp_ptr){
      if(head_ptr == tail_ptr && temp_ptr == head_ptr){
         removeLast(temp_ptr);
      }else if(temp_ptr == head_ptr){
         removeHead();
      } else if(temp_ptr == tail_ptr){
         removeTail();
      } else {
         removeCurrent(temp_ptr);
      }
   };
   void LinkedList::removeLast(Node*& temp_ptr){
      rm(temp_ptr);
      head_ptr = new Node(); //Sets the pointers back to their initial state
      tail_ptr = head_ptr;
      current_ptr = tail_ptr;
      firstAdd = true;
   };
   void LinkedList::removeHead(){
      Node* temp_ptr = head_ptr;
      head_ptr = head_ptr->getNext();
      head_ptr->setPrev(NULL);
      rm(temp_ptr);
   };
   void LinkedList::removeTail(){
      Node* temp_ptr = tail_ptr;
      tail_ptr = tail_ptr->getPrev();
      tail_ptr->setNext(NULL);
      rm(temp_ptr);
   };
   void LinkedList::removeCurrent(Node*& temp_ptr){
      temp_ptr->getPrev()->setNext(temp_ptr->getNext());
      temp_ptr->getNext()->setPrev(temp_ptr->getPrev());
      Node* rm_ptr = temp_ptr;
      temp_ptr = temp_ptr->getNext();
      rm(rm_ptr);
   };
   void LinkedList::rm(Node*& rm_ptr){
      delete rm_ptr;
      rm_ptr = NULL;
      numberOfItems--;
   };
   void LinkedList::reverse(){
      if(length()>1){   //Nothing need to be done to reverse one Node
         Node* temp_ptr = head_ptr;
         temp_ptr->setPrev(temp_ptr->getNext());   //Sets up the head pointer to be
         temp_ptr->setNext(NULL);                  //like the tail
         temp_ptr = temp_ptr->getPrev();  //Moves to what was formally the previous Node
         while(temp_ptr->getNext()!=NULL && length()!=2){
            Node* sTemp_ptr = temp_ptr->getNext(); //So the pointer to the next Node does not get erased
            temp_ptr->setNext(temp_ptr->getPrev());   //The reversal statements
            temp_ptr->setPrev(sTemp_ptr);
            temp_ptr = sTemp_ptr; //sets the pointer to the former next
         }
         temp_ptr->setNext(temp_ptr->getPrev());
         temp_ptr->setPrev(NULL);
         tail_ptr = head_ptr;
         head_ptr = temp_ptr;
         temp_ptr = NULL;
      }
   };
   //Operator Overloads
   void LinkedList::operator +=(const LinkedList &l2){
      if(l2.length()>0){
         Node* temp_ptr = l2.head();
         while(temp_ptr->getNext()!= NULL){  //Increments, deep copying the Nodes
            item temp;
            temp = temp_ptr->getContent();
            add(temp);
            temp_ptr = temp_ptr->getNext();
         };
         item temp;
         temp = temp_ptr->getContent();
         add(temp);
      }
   };
   istream& operator>>(istream& in,LinkedList& l){
      LinkedList::item add;   //The list is generalised
      in >> (LinkedList::item&)add;
      l.add(add);
      return in;
   };
   ostream& operator<<(ostream& out,const LinkedList& l){
      if(l.length()>0){
         Node* temp_ptr;
         temp_ptr = l.head(); //Needed so the list can be constant
         while(temp_ptr->getNext()!= NULL){
            out << temp_ptr->getContent() << " ";
            temp_ptr = temp_ptr->getNext();
         };
         out << temp_ptr->getContent() << endl;
         temp_ptr = NULL;
      } else {
         out << "" << endl;
      }
      return out;
   };
};
