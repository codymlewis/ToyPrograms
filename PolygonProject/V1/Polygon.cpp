/* Polygon.cpp - Assignment 1
 * Author: Cody Lewis - c3283349
 * Date: 10-MAR-2018
 * Last Modified: 24-MAR-2018
 * Description:
 * The implementation of the Polygon object
 */
#include <iostream>
#include <cmath>
#include <string>
#include <sstream>
#include "ComparePoly.h"
#include "Polygon.h"
#include "Point.h"
#include "Queue.h"
namespace lewis_a1 {
  // Constructors
  Polygon::Polygon(){
    points = new Queue<Point>(); 
  }
  Polygon::Polygon(const Queue<Point>& q){
    points = new Queue<Point>(q);
  }
  Polygon::Polygon(Polygon& p){
    points = new Queue<Point>(*p.getPoints());
  }
  // Destructors
  Polygon::~Polygon(){
    delete points;
  }
  // Mutators
  void Polygon::addPoint(Point& p){
    points->push(p);   
  }
  void Polygon::addPoint(Queue<Point>*& q){
    *points+=*q; // somehow copy to here
  }
  bool Polygon::addPoint(Polygon& p){
    Queue<Point>* q = p.getPoints();
    addPoint(q);
    return true;
  }
  // Queries
  double Polygon::distFromOrigin() const{
    double smallDist,a,b,c;
    bool firstPass = true;
    Queue<Point>* pointsCp = new Queue<Point>(*points);
    while(!pointsCp->isEmpty()){
      a = pointsCp->front().getX();
      b = pointsCp->front().getY();
      c = a*a + b*b;
      c = sqrt(c);
      if(firstPass){
        smallDist = c;
        firstPass = false;
      } else {
        if(c < smallDist){
          smallDist = c;
        }
      }
      pointsCp->pop();
    }
    delete pointsCp;
    return smallDist;
  }
  double Polygon::findArea() const{
  // A = (1/2)*ABS(Sigma(i=0,n-2)((x_i + x_i+1)*(y_i - y_i+1)))
    if(points->isEmpty()){
      return 0;
    } else {
      double area,summand;
      area = 0;
      Queue<Point>* pointsCp = new Queue<Point>(*points);
      Point curr,next,cp;
      cp = pointsCp->front();
      for(curr = pointsCp->front(); !pointsCp->isEmpty(); curr = pointsCp->front()){
        pointsCp->pop();
        if(pointsCp->isEmpty()){
          next = cp; // repeat the first point
        } else {
          next = pointsCp->front();
        }
        summand = (curr.getX() + next.getX()) * (curr.getY() - next.getY());
        if(summand < 0){ 
          summand*=-1; // Absolute value
        }
        area+=summand;
      }
      area*=0.5;
      delete pointsCp;
      return area;
    }
  }
  std::string Polygon::to52F(std::string fPoint) const{ // private
    if(fPoint.find(".") < fPoint.length()){
      if(fPoint.length() - fPoint.find(".") > 3){ // for .2f
        fPoint.erase(fPoint.find(".")+3);
      }
    }
    while(fPoint.length() < 5){ // for 5f
      fPoint = " " + fPoint;
    }
    return fPoint;  
  }
  bool Polygon::isEmpty() const{
    return points->isEmpty();
  }
  Queue<Point>*& Polygon::getPoints(){
    return points;
  }
  std::string Polygon::toString() const{
    std::stringstream ss;
    ss << "[";
    Queue<Point>* pointsCp = new Queue<Point>(*points);
    while(!pointsCp->isEmpty()){
      ss << pointsCp->front().toString() << ",";
      pointsCp->pop();
    }
    delete pointsCp;
    std::string str = ss.str();
    if(!points->isEmpty()){
      str.erase(str.length()-1); // remove trailing comma
    }
    str = str + "]:";
    ss.str(std::string()); // reset ss
    ss << findArea();
    std::string area = ss.str();
    area = to52F(area); // convert area to 5.2f
    str = str + area;
    return str;
  }
  // operator overloads
  bool operator>(const Polygon& lhs,const Polygon& rhs){
    double A = lhs.findArea();
    double minima = A*0.9995; // range is + or - 0.05% of the Area
    double maxima = A*1.0005;
    double A2 = rhs.findArea(); // stops redundant calculations
    if(A2 >= minima && A2 <= maxima){
      return (lhs.distFromOrigin() < rhs.distFromOrigin()); // true if this is closest to the origin
    } else {
      return (A > A2);
    }
  }
  bool operator<(const Polygon& lhs,const Polygon& rhs){
    return !(lhs>rhs);
  }
  std::istream& operator>>(std::istream& in,Polygon& p){
    Queue<Point>* newQ = new Queue<Point>();
    in >> *newQ;
    p.addPoint(newQ);
    delete newQ;
    return in;
  }
}
