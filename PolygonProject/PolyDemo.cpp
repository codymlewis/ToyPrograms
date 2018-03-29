/* PolyDemo.cpp - Assignment 1
 * Author Cody Lewis - c3283349
 * Date: 13-MAR-2018
 * Last Modified: 21-MAR-2018
 * Description:
 * The main thread of the Assignment
 */
#include <iostream>
#include <fstream>
#include <sstream>
#include "MyPolygons.h"
#include "Polygon.h"
void readFile(lewis_a1::MyPolygons* &mp,std::string file){
// Function to read the specified file and input it into MyPolygons
  std::string fout;
  std::ifstream inData;
  inData.open(file.c_str());
  while(!inData.eof()){
    std::getline(inData,fout);
    if(fout == "") continue;
    std::stringstream ss(fout); 
    ss >> *mp;
  }
  inData.close();
}
void sortList(lewis_a1::MyPolygons*& mp){
// Creates a sorted copy of the Polygon list and outputs it
  lewis_a1::MyPolygons* sortedMp = new lewis_a1::MyPolygons(*mp);
  std::cout << "The sorted list is:\n" << sortedMp->toString() << std::endl;
  delete sortedMp;
}
int main(int argc,char const *argv[]){
// Main thread, defining the flow of the program
  lewis_a1::MyPolygons* mp = new lewis_a1::MyPolygons();
  if(argc > 1){ // case of running program with arguement
    std::string file = argv[1];
    std::cout << "Reading file " << file << " and inputting the data into the program" << std::endl;
    readFile(mp,file);
    std::cout << "The input list is:\n" << mp->toString() << std::endl;
    sortList(mp);
  } else { // without arguement
    // Interface for when there is no arguement
    bool noExit = true; // exit condition
    std::cout << "Started PolyDemo" << std::endl;
    while(noExit){
      std::cout << std::endl << "Please choose an option:" << std::endl;
      std::cout << "Add polygons(1) View polygons(2) View Sorted Polygons(3) Exit(9)" << std::endl;
      std::string input;
      getline(std::cin,input); // using this allows for separation of choice and adding Polygons
      std::stringstream ss(input);
      int choice;
      ss >> choice;
      switch(choice){
        case 1: std::cout << "Input your polygons: ";
                std::cin >> *mp;
                break;
        case 2: std::cout << mp->toString() << std::endl;
                break;
        case 3: sortList(mp);
                break;
        case 9: noExit = false;
                break;
        default:std::cout << "That input was not valid, please try again" << std::endl; 
                break;
      }
    }
  }
  delete mp;
  return 0;
}
