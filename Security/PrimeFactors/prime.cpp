/* prime.cpp
 * Author: Cody Lewis
 * Date: 1/07/2017
 * Updated: 25-APR-2018
 * Description:
 * a program that finds the prime numbers up to a bound
 * Can be passed with a number as a command line argument
 */
#include<cstdlib>
#include<iostream>
#include<sstream>
#include<list>
bool findPrime(int bound){
	//Description: finds and prints prime numbers up to a set bound
	//             O(n), bottom up approach
	//Pre-conditions: a bound must be sent in
	//Post-conditions: the prime numbers up to the bound are printed
	bool isPrime;
  std::list<int> primes = {2};
	for(int counter=3; counter<bound; counter++){
		isPrime = true;
    for(std::list<int>::iterator it = primes.begin(); it != primes.end(); it++){
      if(counter%*it == 0){
        isPrime = false;
        break;
      }
    }
		if(isPrime){
      primes.push_back(counter);
		}
	}
  std::cout << "The primes up to " << bound << " are ";
  for(std::list<int>::iterator it = primes.begin(); it != primes.end(); it++){
    std::cout << *it << ", ";
  }
  std::cout << std::endl;
	return true;
}
bool evaluate(int bound){
  // check that the input is valid
  if(bound<2){
    std::cout << "The bound " << bound << " is not valid, (less than 2)" << std::endl;
	}
	findPrime(bound);
	return true;
}
int main(int argc,char const *argv[]){
	int bound;
	if(argc == 1){
    std::cout << "This program finds the prime numbers up to the bound you will input: ";
    std::cin >> bound;
    evaluate(bound);
  } else {
    std::stringstream ss;
    ss.str(argv[1]); // only one argument is useful
    ss >> bound;
    evaluate(bound);
  }
	return 0;
}
