/**
 * A Queue implemented by inheritance
 * Author: Cody Lewis
 */
#ifndef QUEUE_h
#define QUEUE_h
#include "List.h"
namespace lewis_ds {
  template<typename T>
    class Queue: private List<T> {
      private:
      public:
        Queue() {
          List<T>();
        };
        ~Queue() {
          List<T>::~List();
        };
        void enqueue(T data) {
          List<T>::push_back(data);
        };
        void dequeue() {
          List<T>::pop_front();
        };
        T peek() {
          return List<T>::front();
        };
    };
}
#endif
