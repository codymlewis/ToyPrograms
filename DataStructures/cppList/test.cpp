#include <iostream>
#include "Queue.h"
int main(){
  lewis_ds::Queue<int>* q = new lewis_ds::Queue<int>();
  int tmp;
  std::cout << "Input a number for the Queue: ";
  std::cin >> tmp;
  q->enqueue(tmp);
  std::cout << "The first entry in the list is "<< q->peek() << std::endl;
  q->dequeue();
  delete q;
  return 0;
}
