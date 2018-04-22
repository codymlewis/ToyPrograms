/* Point.h - Assignment 1
 * Author: Cody Lewis - c3283349
 * Date: 06-MAR-2018
 * Last Modified: 06-MAR-2018
 * Description:
 * The header for the point class
 */
#ifndef POINT_h
#define POINT_h
#include <iostream>
#include <string>
namespace lewis_a1{
  class Point{
    private:
      // Member variables
      double x;
      double y;
      // Method used to get 4.2f output for the toString method
      // Pre-conditions:  A string is sent in
      // Post-conditions: A string in format 4.2f is returned
      const std::string to42F(std::string fPoint) const;
    public:
      // Constructors
      
      // Default Constructor
      // Pre-conditions:  None
      // Post-conditions: The point is instantiated
      Point();

      // Argument Constructor
      // Pre-conditions:  Two doubles are sent in
      // Post-conditions: The Point is instantiated with the doubles set
      //                  as the x and y values
      Point(const double newX,const double newY);
      // Mutators

      // Set x
      // Pre-conditions:  A double is sent in
      // Post-conditions: x is set to that value
      void setX(const double newX);

      // Set y
      // Pre-conditions:  A double is sent in
      // Post-conditions: y is set to that value
      void setY(const double newY);
      // Queries
      
      // Get x
      // Pre-conditions:  None
      // Post-conditions: x is returned
      const double getX() const;

      // Get y
      // Pre-conditions:  None
      // Post-conditions: y is returned
      const double getY() const;

      // To string method
      // Pre-conditions:  None
      // Post-conditions: A string of (x,y) is output
      const std::string toString() const;
  };
  // Operator overloads
  
  // Output
  // Pre-conditions:  p is instantiated
  // Post-conditions: The x and y values are input into p
  std::istream& operator>>(std::istream& in, Point& p);
}
#endif
