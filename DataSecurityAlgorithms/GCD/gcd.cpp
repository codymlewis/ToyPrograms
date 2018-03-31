/* gcd.cpp
 * Author: Cody Lewis
 * Date: 29-MAR-2018
 * Description:
 * Finds gcd's of two numbers
 * takes argument '--gcd=p,q' where p and q are the numbers
 */
#include <iostream>
#include <cmath>
#include <string>
#include <sstream>
int gcd(int p,int q){
// Pre-condition: p must be bigger than q
  int divisor = std::floor(p/q);
  int remainder = p - q*divisor;
  if(remainder == 0){
    return 0;
  } else {
    int nextGcd = gcd(q,remainder);
    if(nextGcd == 0){
      return remainder;
    } else {
      return nextGcd;
    }
  }
}
int eulerFun(int p,int q){
// if q is bigger swap with q's place
  if(q > p){
    return gcd(q,p);
  } else {
    return gcd(p,q);
  }
}
int main(int argc,char const *argv[]){
  if(argc == 1){
    std::cout << "Input your first number: ";
    int p;
    std::cin >> p;
    std::cout << "Input your second number: ";
    int q;
    std::cin >> q;
    std::cout << "The greatest common divisor of " << p << " and " << q << " is: " << eulerFun(p,q) << std::endl;
  } else {
    for(int i = 1; i < argc; i++){
      std::string arg = argv[i];
      if(arg.find("--gcd=") < arg.length()){
        std::string str = arg.substr(arg.find("=")+1,arg.length()-arg.find(",")+1);
        if(str.find(",") < str.length()){
          str.erase(str.find(",")); // removes comma stuff for the p value 
        }
        str = str + " " + arg.substr(arg.find(",")+1); // when q is bigger, a comma trails p
        std::stringstream ss;
        int p,q;
        ss.str(str);
        ss >> p >> q;
        std::cout << "The greatest common divisor of " << p << " and " << q << " is: " << eulerFun(p,q) << std::endl;
      }
    }
  }
}
