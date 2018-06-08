/**
 * A test program, currently tests the list
 */
#include <iostream>
#include "Queue.h"
int main() {
  lewis_ds::List<int>* q = new lewis_ds::List<int>();
  int tmp;
  std::cout << "Input a number for the Queue: ";
  std::cin >> tmp;
  q->push_back(tmp);
  std::cout << "The first entry in the list is "<< q->front() << std::endl;
  for(int i = 0; i < 50; i++) {
    q->push_back(i);
  }
  std::cout << q->iter().next() << std::endl;
//  q->dequeue();
  delete q;
  return 0;
}
