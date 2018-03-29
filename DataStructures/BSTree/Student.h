/* Student.h - Assignment 3
 * Author: Cody Lewis - 3283349
 * Date: 09-10-2017
 * Last Modified: 30-10-2017
 * Description:
 * Header file for the Student class
 */
#ifndef STUDENT_h
#define STUDENT_h
#include <iostream>
namespace lewis_a03{
  class Student{
  public:
    //Type definitions
    typedef std::string nameType;
    typedef int gradeType;

    //*structors

    //Constructor
    //Pre-conditions:  None
    //Post-conditions: A student object is instantiated
    Student();

    //Value assignment Constructor
    //Pre-conditions: A nameType and gradeType value is sent in
    //Pre-conditions: A student object is instantiated and assigned those values
    Student(nameType newName,gradeType newGrade);

    //Destructor
    //Pre-conditions:  The is a Student object
    //Post-conditions: The values in the Student object are reset
    ~Student();

    //Mutator methods

    //Set the name
    //Pre-conditions:  A nameType is sent in
    //Post-conditions: This Student object is assigned the name
    void setName(nameType newName);

    //Set the grade
    //Pre-conditions:  A gradeType is sent in
    //Post-conditions: This Student object is assigned the grade
    void setGrade(gradeType newGrade);

    //Query methods

    //Gets the name
    //Pre-conditions:  This Student is initialised
    //Post-conditions: This Student's name is returned
    nameType getName() const;

    //Gets the grade
    //Pre-conditions:  This Student is initialised
    //Post-conditions: This Student's grade is returned
    gradeType getGrade() const;

    //Conversion operator
    //Pre-conditions:  This Student is initialised
    //Post-conditions: Returns the grade as a double
    operator double();
  private:
    //Member variables
    nameType name;
    gradeType grade;
  };
  //Operator overloads

  //Less than
  //Pre-conditions:  Both variable are initialised
  //Post-conditions: returns true if lhs is less than rhs, else returns false
  bool operator<(Student lhs,Student rhs);
  bool operator<(Student::gradeType lhs,Student rhs);
  bool operator<(Student::nameType lhs,Student rhs);
  bool operator<(Student lhs,Student::gradeType rhs);

  //Greater than
  //Pre-conditions:  Both variables are initialised
  //Post-conditions: returns true if lhs is Greater than rhs, else returns false
  bool operator>(Student lhs,Student rhs);
  bool operator>(Student::gradeType lhs,Student rhs);
  bool operator>(Student::nameType lhs,Student rhs);
  bool operator>(Student lhs,Student::gradeType rhs);

  //Congruence
  //Pre-conditions:  Both variable are initialised
  //Post-conditions: returns true if lhs conguent to rhs, else returns false
  bool operator==(Student lhs,Student rhs);
  bool operator==(Student::nameType lhs,Student rhs);
  bool operator==(Student lhs,Student::nameType rhs);

  //IO
  //Pre-conditions:  The Student object is initialised
  //Post-conditions: The Student recieves input/sends output respectively
  std::istream& operator>>(std::istream& in, Student& stu);
  std::ostream& operator<<(std::ostream& out,const Student& stu);
}
#endif
