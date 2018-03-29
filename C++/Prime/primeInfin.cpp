/* prime.cpp
 * Author: Cody Lewis
 * Date: 1/07/2017
 * Description:
 * a program that finds the prime numbers infinitely
 */
#include<iostream>
using namespace std;

integer findPrime(){
	//Description: finds and prints prime numbers up to a set bound
	//Pre-conditions: none
	//Post-conditions: the prime numbers are printed infinitely
	bool prime;
	cout << "2, " << endl;
	for(integer counter=3; ; counter++){
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
	cout << "This program finds the prime numbers infinitely: ";
	findPrime();
	return 0;
}
