/* Polygon.h - Assignment 1
 * Author: Cody Lewis - c3283349
 * Date: 07-MAR-2018
 * Last Modified: 19-MAR-2018
 * Description:
 * The header for the polygon class
 */

#ifndef POLYGON_h
#define POLYGON_h
#include <iostream>
#include "ComparePoly.h"
#include "Point.h"
#include "Queue.h"
#include "Node.h"
namespace lewis_a1{
 class Polygon: public ComparePoly{
    private:
      // Member variables
      Queue<Point>* points; // Queue used as points are added dynamically and
                            // only accessed sequentially
      // Method to allow for 5.2f output into the toString method
      // Pre-conditions:  A string is sent in
      // Post-conditions: The string is formatted to 5.2f standards
      std::string to52F(std::string fPoint) const;

    public:
      // Constructors
      
      // Default Constructor
      // Pre-conditions:  None
      // Post-conditions: This object is instantiated
      Polygon();

      // Queue copy constructor
      // Pre-conditions:  None
      // Post-conditions: The Queue of points is added into this object
      Polygon(const Queue<Point>& q);

      // Copy constructor
      // Pre-conditions:  None
      // Post-conditions: The argument Polygon is deep copied into this
      Polygon(Polygon& p);

      // Destructor
      // Pre-conditions:  this object is instantiated
      // Post-conditions: this object is destructed
      ~Polygon();

      // Mutators
      
      // Add Point
      // Pre-conditions: A Point(s) is/are sent in
      // Post-conditions: The Point(s) is/are added
      void addPoint(Point& p); 
      void addPoint(Queue<Point>*& q);
      bool addPoint(Polygon& p);
      
      // Queries
      
      // Finds the distance of closest vertex from the origin
      // Pre-conditions:  None
      // Post-conditions: The distance is returned
      double distFromOrigin() const;

      // Finds the area of the Polygon
      // Pre-conditions:  Node
      // Post-conditions: The area is returned
      double findArea() const; 

      // Emptiness Query
      // Pre-conditions:  None
      // Post-conditions: returns true if empty else returns false
      bool isEmpty() const;

      // Points copy Query
      // Pre-conditions:  None
      // Post-conditions: The Queue of Points is returned
      Queue<Point>*& getPoints();

      // To String method
      // Pre-conditions:  None
      // Post-conditions: A string of the points is returned
      std::string toString() const;

  };
  // Operator overloads

  // > operator
  // Pre-conditions:  rhs and lhs are instantiated
  // Post-conditions: returns true if rhs has a bigger area than lhs
  //                  if the areas are equal then returns true if rhs
  //                  is closer to the origin, else retruns false
  bool operator>(const Polygon& rhs,const Polygon& lhs);

  // Input operator
  // Pre-conditions:  p is instantiated
  // Post-conditions: A point is added to the Polygon
  std::istream& operator>>(std::istream& in,Polygon& p);
}
#endif
