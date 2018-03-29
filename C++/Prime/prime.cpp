/* prime.cpp
 * Author: Cody Lewis
 * Date: 1/07/2017
 * Description:
 * a program that finds the prime numbers up to a bound
 */
#include<iostream>
using namespace std;

int findPrime(int bound){
	//Description: finds and prints prime numbers up to a set bound
	//Pre-conditions: a bound must be sent in
	//Post-conditions: the prime numbers up to the bound are printed
	bool prime;
	cout << "2, " << endl;
	for(int counter=3; counter<bound; counter++){
		prime = true;
		for(int divisor=2; divisor<counter; divisor++){
			if((counter%divisor)==0){
				prime = false;
				break;
			}
		}
		if(prime==true){
			cout << counter << ", " << endl;
		}
	}
	return 1;
}
int main(){
	try{
		int bound;
		cout << "This program finds the prime numbers up to the bound you will input: ";
		cin >> bound;
		if(bound<2){
			throw 1;
		}
		findPrime(bound);
	} catch(int params) {
		switch(params){
			case 1: cout << "The number you entered cannot be calculated as prime" << endl;
		}
	}
	return 0;
}
