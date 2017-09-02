/**
 * LinkedListDemo.cpp - Assignment01
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 2017-08-16
 * Last modified: 2017-08-31
 * Description:
 * Demo Program for the LinkedList
 */
 #include<iostream>
 #include<string>    //Provides the substring method
 #include<sstream>   //Allows for strings to be streamed as cin
 #include "LinkedList.h"
 using namespace std;
 using namespace lewis_a01_LinkedList;
 void lineIn(LinkedList& l,string& line){
   //Breaks up the input line into individual word for the Nodes of the list
   //The same letter entered multiple times causes only one instance of that
   //letter to be in the list. (only single letters though)
   //Pre-conditions: A LinkedList and string is sent in
   //Post-conditions: The LinkedList now contains the line as individual words
   if(line.find(" ")<line.length()){
      unsigned int pos = 0;   //Unsigned is used as length is unsigned, due to -ve length not existing
      while(line.find(" ")<line.length()){
         if(line.find(" ")>1) //Checks wierd spacing and removes it
            stringstream(line) >> l;
         pos = line.find(" ")+1;
         line = line.substr(pos);
      }
   }
   stringstream(line) >> l;
 };
 void FlexStringDemo(LinkedList l){
    //Calls the reverse method from LinkedList then outputs the new list
    l.reverse();
    cout << l << endl;
 };
 int main() {
   string input;
   LinkedList inputOne;
   LinkedList inputTwo;
   cout << "Enter a sentence for the list: ";
   getline(cin,input);
   lineIn(inputOne,input);
   cout << "The list is now " << inputOne << endl;
   cout << "The list is now: \n" << inputOne << endl;
   cout << "The list currently contains " << inputOne.length() << " words." << endl;
   cout << "\"SENG1120\" is in the list " << inputOne.count("SENG1120") << " times." << endl;
   cout << "Now, enter a sentence for the second list: ";
   getline(cin,input);
   lineIn(inputTwo,input);
   cout << "The second list contains: " << inputTwo << endl;
   cout << "Merging the two lists together" << endl;
   inputOne += inputTwo;
   cout << "The first list now contains: " << inputOne << endl;
   cout << "This list is " << inputOne.length() << " words long." << endl;
   input = "is";
   cout << "It contains the word \"is\" " << inputOne.count(input) << " times." << endl;
   cout << "Enter a word you want to remove from the second list: ";
   cin >> input;
   inputTwo.remove(input);
   cout << "The lists now read: \nList 1: " << inputOne << "\nList 2: " << inputTwo << endl;
   cout << "Now reversing the first list: " << endl;
   FlexStringDemo(inputOne);
   return 0;
 };
