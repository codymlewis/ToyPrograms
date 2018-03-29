/* Point.cpp - Assignment 1
 * Author: Cody Lewis - c3283349
 * Date: 06-MAR-2018
 * Last Modified: 06-MAR-2018
 * Description:
 * The implementation for the point class
 */
#include <iostream>
#include <sstream>
#include <string>
#include "Point.h"
namespace lewis_a1{
  // Constructors
  Point::Point(){
    x = 0;
    y = 0;
  }
  Point::Point(const double newX,const double newY){
    x = newX;
    y = newY;
  }
  // Mutators
  void Point::setX(const double newX){
    x = newX;
  }
  void Point::setY(const double newY){
    y = newY;
  }
  // Queries
  const double Point::getX() const{
    return x;
  }
  const double Point::getY() const{
    return y;
  }
  const std::string Point::toString() const{ // output as 4.2f
    std::stringstream ss;
    ss << x;
    std::string xVal = ss.str();
    ss.str(std::string()); // clears the string stream
    xVal = to42F(xVal);
    ss << y;
    std::string yVal = ss.str();
    yVal = to42F(yVal);
    std::string str = "(" + xVal + "," + yVal + ")";
    return str; 
  }
  const std::string Point::to42F(std::string fPoint) const{ // private
    if(fPoint.find(".") < fPoint.length()){ // check that there is a decimal
      if(fPoint.length() - fPoint.find(".") > 3){ // truncate
        fPoint.erase(fPoint.find(".")+3);
      }
    }
    while(fPoint.length() < 4){ // give space format
      fPoint = " " + fPoint;
    }
    return fPoint;  
  }
  // Operator overlaods
  std::istream& operator>>(std::istream& in,Point& p){
    double newX,newY;
    in >> newX >> newY;
    p.setX(newX);
    p.setY(newY);
    return in;
  }
  std::ostream& operator<<(std::ostream& out,Point& p){
    out <<  "(" << p.getX() << "," << p.getY() << ")";
    return out;
  }
}
