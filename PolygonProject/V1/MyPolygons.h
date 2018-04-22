/* MyPolygons.h - Assignment 1
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 11-MAR-2018
 * Last Modified: 16-MAR-2018
 * Description:
 * An object containing a Circular Doubly linked list
 */
#ifndef MYPOLYGONS_h
#define MYPOLYGONS_h
#include <iostream>
#include <cstdlib>
#include <string>
#include "Node.h"
#include "Polygon.h"
namespace lewis_a1{
  class MyPolygons{
    private:
      // Members
      std::size_t length;
      Node<Polygon>* sentinal;
      Node<Polygon>* current;
      // First item adding method
      void firstAdd(Polygon& p);
    public:
      // Constructors
   
      // Default Constructor
      // Pre-conditions:  None
      // Post-conditions: A MyPolygons object is instantiated
      MyPolygons();

      // Copy Constructor
      // Pre-conditions:  A MyPolygons object is sent in
      // Post-conditions: A sort deep copy of MyPolygons object is instantiated
      MyPolygons(MyPolygons& mp);

      // Destructor
      // Pre-conditions:  this MyPolygons object is instantiated
      // Post-conditions: this object is destructed
      ~MyPolygons();
      // Mutators

      // Prepends items to the list
      // Pre-conditions:  The Polygon data is sent in
      // Post-conditions: The data is a part of the list
      void prepend(Polygon& p);
      void prepend(Node<Polygon>*& np);

      // Appends items to the list
      // Pre-conditions:  Polygon data is sent in
      // Post-conditions: the data is a part of the list
      void append(Polygon& p);
      void append(Node<Polygon>*& np);

      // Inserts items into the list
      // Pre-conditions:  Polygon data is sent in
      // Post-conditions: the data is now a part of the list
      bool insert(Polygon& in);
      bool insert(Node<Polygon>*& np);

      // Reset current to point to head
      // Pre-conditions:  this object is instantiated
      // Post-conditions: current points to head
      void resetCurr();

      // Removes the item from the head of the list
      // Pre-conditions:  length > 0
      // Post-conditions: the former head of the list is removed
      void pop();

      // Queries
    
      // Take the item from the head of the list
      // Pre-conditions:  There is a head of the list
      // Post-conditions: The item from the head of the list is returned
      const Polygon& front() const;
      Node<Polygon>* getSentinal() const;

      // to string method
      // Pre-conditions:  This list is instantiated
      // Post-conditions: A string is formed from the contents of the list
      const std::string toString() const;
  };
  // Operator overloads

  // Input operator
  // Pre-conditions:  The list is instantiated
  // Post-conditions: A Polygon is added to the list
  std::istream& operator>>(std::istream& in,MyPolygons& mp);
}
#endif
