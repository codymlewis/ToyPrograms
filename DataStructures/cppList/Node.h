#ifndef NODE_h
#define NODE_h
namespace lewis_ds{
  template<typename T>
    class Node{
      private:
        Node<T> *prev,*next;
        T data;
      public:
        Node(){
          prev = 0;
          next = 0;
        };
        Node(T newData,Node<T> *newPrev,Node<T> *newNext){
          data = newData;
          prev = newPrev;
          next = newNext;
        };
        ~Node(){
          prev = 0;
          next = 0;
        };
        void setNext(Node<T> *newNext){
          next = newNext;
        };
        void setPrev(Node<T> *newPrev){
          prev = newPrev;
        };
        Node<T>* getPrev(){ return prev; };
        Node<T>* getNext(){ return next; };
        T getData(){ return data; };
    };
}
#endif
