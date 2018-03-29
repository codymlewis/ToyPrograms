/* fizzbuzz.cpp
 * Author: Cody Lewis
 * Date: 3/08/2017
 * Description:
 * Plays out the script of the game of fizzbuzz up to the bound
 */
#include<iostream>
#include<array>
using namespace std;
typedef int no;
array<no,1> FIZZ = {3};
array<no,1> BUZZ = {5};
bool divisible(no number,array<no,1> arr){
	for(int count; count<arr.size(); count++)
		if((number%arr[count])==0)
			return true;
	return false;
}
int fizzbuzz(int bound){
	/* Description:
	 * Plays out the game of fizzbuzz up to the sent in bound
	 * Pre-conditions: an integer bound is sent in
	 * Post-conditions: the fizzbuzz script up to the bound is output
	 */
	bound++;
	for(no counter=0;counter<bound;counter++){
		if(divisible(counter,FIZZ) && divisible(counter,BUZZ)){
			cout<<"fizzbuzz"<<endl;
		} else if(divisible(counter,FIZZ)){
			cout<<"fizz"<<endl;
		} else if(divisible(counter,BUZZ)){
			cout<<"buzz"<<endl;
		} else {
			cout<<counter<<endl;
		}
	}
	return 1;
}

int main(){
	fizzbuzz(100);
	return 0;
}
