#ifndef LIST_h
#define LIST_h
#include "Node.h"
namespace lewis_ds{
  template<typename T>
    class List: private Node<T>{
      private:
        Node<T> *head,*tail,*current;
        bool isEmpty;
      public:
        List(){
          head = 0;
          tail = 0;
          current = 0;
          isEmpty = 1;
        };
        ~List(){
          if(!isEmpty){
            while(head){
              if(tail->getPrev()){
                current = tail->getPrev();
                delete tail;
                tail = current;
              } else {
                delete tail;
              }
            }
          }
        };
        void addFirst(T data){
          head = new Node<T>(data,0,0);
          tail = head;
          current = head;
          isEmpty = 0;
        };
        void push_front(T data){
          if(isEmpty){
            addFirst(data);
          } else {
            Node<T>* temp = new Node<T>(data,0,head);
            head->setPrev(temp);
            head = temp;
            temp = 0;
          }
        };
        void push_back(T data){
          if(isEmpty){
            addFirst(data);
          } else {
            Node<T>* temp = new Node<T>(data,tail,0);
            tail->setNext(temp);
            tail = temp;
            temp = 0;
          }
        };
        void pop_front(){
          if(head->getNext()){
            if(current == head){
              current = current->getNext();
            }
            Node<T>* temp = head;
            head = head->getNext();
            head->setPrev(0);
            delete temp;
            temp = 0;
          } else {
            delete head;
            head = 0;
            tail = 0;
            current = 0;
            isEmpty = 1;
          }
        };
        void pop_back(){
          if(tail->getPrev()){
            if(current == tail){
              current = current->getPrev();
            }
            Node<T>* temp = tail;
            tail = tail->getPrev();
            tail->setNext(0);
            delete temp;
            temp = 0;
          } else {
            delete head;
            head = 0;
            tail = 0;
            current = 0;
            isEmpty = 1;
          }
        };
        T front(){ return head->getData(); };
        T back(){ return tail->getData(); };
    };
}
#endif
