/* Student.cpp - Assignment 3
 * Author: Cody Lewis - 3283349
 * Date: 09-10-2017
 * Last Modified: 30-10-2017
 * Description:
 * Implementation file for the Student class
 */
#include <iostream>
#include "Student.h"
using namespace std;
namespace lewis_a03{
  //Default Constructor
  Student::Student(){
    name = "";
    grade = 0;
  };
  //Assignment constructor
  Student::Student(string newName,int newGrade){
    name = newName;
    grade = newGrade;
  };
  //Destructor
  Student::~Student(){
    name = "";  //It's kind of redundant, but allows for code completion
    grade = 0;
  }
  //Mutators
  void Student::setName(string newName){
    name = newName;
  };
  void Student::setGrade(int newGrade){
    grade = newGrade;
  };
  //Queries
  Student::nameType Student::getName() const{
    return name;
  };
  Student::gradeType Student::getGrade()const{
    return grade;
  };
  //Operator overloads
  Student::operator double(){
    return (double)grade; //If a double is assigned the Student then it will receive the grade
  }
  //compares the names
  bool operator<(Student lhs,Student rhs){
    return (lhs.getName()<rhs.getName());
  }
  //compares the grade
  bool operator<(Student::gradeType lhs,Student rhs){
    return (lhs<rhs.getGrade());
  }
  //compares the names
  bool operator<(Student::nameType lhs,Student rhs){
    return (lhs<rhs.getName());
  }
  //compares the grade
  bool operator<(Student lhs,Student::gradeType rhs){
    return (lhs.getGrade()<rhs);
  }
  //compares the names
  bool operator>(Student lhs,Student rhs){
    return (lhs.getName()>rhs.getName());
  }
  //compares the grade
  bool operator>(Student::gradeType lhs,Student rhs){
    return (lhs>rhs.getGrade());
  }
  //compares the names
  bool operator>(Student::nameType lhs,Student rhs){
    return (lhs>rhs.getName());
  }
  //compares the grade
  bool operator>(Student lhs,Student::gradeType rhs){
    return (lhs.getGrade()>rhs);
  }
  //compares the names
  bool operator==(Student lhs,Student rhs){
    return (lhs.getName()==rhs.getName());
  }
  //compares the names
  bool operator==(Student::nameType lhs,Student rhs){
    return (lhs==rhs.getName());
  }
  //compares the names
  bool operator==(Student lhs,Student::nameType rhs){
    return (lhs.getName()==rhs);
  }
  //Input operator
  istream& operator>>(istream& in,Student& stu){
    Student::nameType newName;
    Student::gradeType newGrade;
    in >> newName;
    stu.setName(newName);
    in >> newGrade;
    stu.setGrade(newGrade);
    return in;
  }
  //Output operator
  ostream& operator<<(ostream& out,const Student& stu){
    out << "(" << stu.getName() << ", " << stu.getGrade() << ")";
    return out;
  }
}
