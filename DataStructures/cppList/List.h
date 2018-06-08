/**
 * A Quick List program I wrote, even figured out a simple iterator implementation
 * Author: Cody Lewis
 */
#ifndef LIST_h
#define LIST_h
#include "Node.h"
namespace lewis_ds {
  template<typename T>
    class List {
      private:
        Node<T> *head, *tail;
        bool isEmpty;
        class Iter { // A very simple iterator
          private:
            Node<T> *current;
          public:
            Iter(Node<T>* ptr) {
              current = ptr;
            }
            T next() { 
              current = hasNext() ? current->getNext() : 0; 
              if(current)
                return current->getData(); 
              else
                return 0;
            }
            bool hasNext() { return current->getNext() != 0; }
        };
      public:
        List() {
          head = 0;
          tail = 0;
          isEmpty = 1;
        };
        ~List() {
          if(!isEmpty) {
            while(head) {
              if(tail && tail->getPrev()) {
                Node<T> *cp = tail->getPrev();
                delete tail;
                tail = cp;
              } else {
                delete tail;
                tail = 0;
                head = 0;
              }
            }
          }
        };
        void addFirst(T data) {
          head = new Node<T>(data,0,0);
          tail = head;
          isEmpty = 0;
        };
        void push_front(T data) {
          if(isEmpty) {
            addFirst(data);
          } else {
            Node<T>* temp = new Node<T>(data,0,head);
            head->setPrev(temp);
            head = temp;
            temp = 0;
          }
        };
        void push_back(T data) {
          if(isEmpty) {
            addFirst(data);
          } else {
            Node<T>* temp = new Node<T>(data,tail,0);
            tail->setNext(temp);
            tail = temp;
            temp = 0;
          }
        };
        void pop_front() {
          if(head->getNext()) {
            Node<T>* temp = head;
            head = head->getNext();
            head->setPrev(0);
            delete temp;
            temp = 0;
          } else {
            delete head;
            head = 0;
            tail = 0;
            isEmpty = 1;
          }
        };
        void pop_back() {
          if(tail->getPrev()) {
            Node<T>* temp = tail;
            tail = tail->getPrev();
            tail->setNext(0);
            delete temp;
            temp = 0;
          } else {
            delete head;
            head = 0;
            tail = 0;
            isEmpty = 1;
          }
        };
        T front() { return head->getData(); };
        T back() { return tail->getData(); };
        Iter iter() {
          return Iter(head);
        };
    };
}
#endif
