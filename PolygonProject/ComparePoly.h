/* ComparePoly.h - Assignment 1
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 11-MAR-2018
 * Last Modified: 11-MAR-2018
 * Description:
 * The interface for the polygon object
 */
#ifndef COMPAREPOLY_h
#define COMPAREPOLY_h
#include <string>
#include "Point.h"
namespace lewis_a1 {
  class ComparePoly{
    public:
      virtual ~ComparePoly(){}; // empty destructor for proper cleanup
      // Mutator
      virtual void addPoint(Point& p) = 0;
      // Queries
      virtual double distFromOrigin() const = 0;
      virtual double findArea() const = 0;
      virtual std::string toString() const = 0;
  };
}
#endif
