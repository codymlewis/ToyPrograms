/* BSTreeDemo.cpp - Assignment 03
 * Author: Cody Lewis
 * Date: 10-10-2017
 * Last Modified: 02-11-2017
 * Description:
 * The demo file for the Binary Search tree
 */
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include "Student.h"
#include "BSTree.h"
using namespace std;
using namespace lewis_a03;
typedef Student contentType; //I thought this was good structure
void readFile(BSTree<contentType>*& bst){
//Reads the csv file and inputs it into the binary search tree
//Pre-conditions:  There is an initialised BSTree<Student> and there is the local file *.csv where star is specified
//Post-conditions: The contents of the *.csv are input in to the BSTree
  string fout;
  ifstream inData;
  inData.open("studentData.csv"); //csv's are a standard for data i/o a bit harder for this situation though
  inData >> fout; //Skips specification line
  while(!inData.eof()){
    inData >> fout; //specify for input into the Student
    if(fout=="") break;
    string name = fout.substr(0,fout.find(","));
    string grade = fout.substr(fout.find(",")+1);
    fout = name + " " + grade;  //formats for the cin
    stringstream ss(fout);      //further set up for cin
    ss >> *bst;
  }
  inData.close();
}
string formatSearch(string str){
//formats the sent in string to be suitable for the search
//Pre-conditions:  a string is sent in
//Post-conditions: a string with the first letter capital and the other letters
//                 lower case
  if(str.length()>0){
    str.at(0) = toupper(str.at(0));
    if(str.length()>=2){
      for (size_t i = 1; i < str.length(); i++) {
        str.at(i) = tolower(str.at(i));
      }
    }
  }
  return str;
}
int main() {
//The main thread, controls the flow of the program
  BSTree<contentType>* bst = new BSTree<contentType>();
  readFile(bst);                                //Step 1
  cout << *bst << endl;                          //Step 2
  cout << "FF: " << bst->search(50) << endl;     //Step 3
  cout << "Average: " << bst->average() << endl; //Step 4
  cout << endl;
  cout << "Deleting the students that failed" << endl;
  bst->remove(50);                               //Step 5
  cout << "Now the binary search tree gives" << endl;
  cout << *bst << endl;                          //Step 6:2
  cout << "FF: " << bst->search(50) << endl;     //Step 6:3
  cout << "Average: " << bst->average() << endl; //Step 6:4
  cout << "Enter a name to search for: ";
  string searchStr;
  cin >> searchStr;
  searchStr = formatSearch(searchStr);  //case insensitive
  bst->search(cout,searchStr);                   //Step 7
  cout << endl;
  bst->invert();
  cout << *bst << endl;
  delete bst;
  return 0;
}
