/* Queue.hpp - Assignment 1
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 08-MAR-2018
 * Last Modified: 24-MAR-2018
 * Description:
 * The implementation of the Queue
 */
// Constructor
namespace lewis_a1 {
  template<typename T>
    Queue<T>::Queue(){
      head = new Node<T>();
      tail = head;
      length = 0;
    }
  // Copy Constructor
  template<typename T>
    Queue<T>::Queue(const Queue& q){
      length = 0;
      head = NULL;
      tail = head;
      if(!operator+=(q)){
        Queue();
      }
    }
  // Destructor
  template<typename T>
    Queue<T>::~Queue(){
      if(length > 1){
        Node<T>* temp = head;
        while(length>1){
          Node<T>* delTemp = temp;
          temp = temp->getNext();
          delete delTemp;
          length--;
        }
        delete temp;
      } else { // for case of 'empty' Queue
        delete head;
      }
    }
  // Mutators
  template<typename T>
    void Queue<T>::push(T& newData){
      if(length == 0){
        head->setData(newData);
      } else {
        Node<T>* newNode = new Node<T>(newData);
        newNode->setPrev(tail);
        tail->setNext(newNode);
        tail = newNode;
        newNode = NULL;
      }
      length++;
    }
  template<typename T>
    void Queue<T>::push(Node<T>* &newData){
      if(length == 0){
        head->setData(newData->getData());
        delete newData;
      } else {
        newData->setPrev(tail);
        tail->setNext(newData);
        tail = newData;
      }
      newData = NULL;
      length++;
    }
  template<typename T>
    void Queue<T>::pop(){
      if(length > 1){
        Node<T>* temp = head->getNext();
        temp->setPrev(NULL);
        head->setNext(NULL);
        delete head;
        head = temp;
        temp = NULL;
      } else if(length == 1) { // reset Queue
        delete head;
        head = new Node<T>();
        tail = head;
      } else{
        return;
      }
      length--;
    }
  // Queries
  template<typename T>
    const T& Queue<T>::front() const{
      return head->getData();
    }
  template<typename T>
    Node<T>* Queue<T>::frontNode() const{
      return head;
    }
  template<typename T>
    const bool Queue<T>::isEmpty() const{
      return length==0;
    }
  template<typename T>
    std::size_t Queue<T>::getLength() const{
      return length;
    }
  template<typename T>
    bool Queue<T>::operator+=(const Queue& q){
      if(!q.isEmpty()){
        Node<T>* temp = q.frontNode();
        Node<T>* newNode;
        for(std::size_t i = 0; i < q.getLength(); i++){
          newNode = new Node<T>(temp->getData());
          if(length == 0){
            delete head;
            head = newNode;
            tail = head;
            length++;
          } else {
            tail->setNext(newNode);
            newNode->setPrev(newNode);
            tail = newNode;
            length++;
          }
          temp = temp->getNext();
        }
        return true;
      }
      return false;
    }
  template<typename T>
    std::istream& operator>>(std::istream& in,Queue<T>& q){
      Node<T>* newData = new Node<T>();
      in >> *newData;
      q.push(newData);
      newData = NULL;
      return in;
    }
}
