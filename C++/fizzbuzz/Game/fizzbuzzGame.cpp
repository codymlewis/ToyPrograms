/* Author: Cody Lewis
 * Date: 3/08/2017
 * Last Modified: 02/11/2017
 * Description:
 * A simple terminal game of fizzbuzz
 */
#include<iostream>
#include<string>
using namespace std;
const int fizzLen = 1;
const int FIZZ[fizzLen] = {3};
const int buzzLen = 1;
const int BUZZ[buzzLen] = {5};
const int EASY = 50;
const int MEDIUM = 25;
const int HARD = 10;
const int EXTRA_HARD = 1;
bool fizz(int counter){
	//sees if the input int is a fizz
	for(int i = 0; i < fizzLen; ++i){
		if(!counter%FIZZ[i]){
			return 1;
		}
	}
	return 0;
}
bool buzz(int counter){
	//sees whether the input int is a buzz
	for(int i = 0; i < buzzLen; ++i){
		if(!counter%BUZZ[i]){
			return 1;
		}
	}
	return 0;
}
int aiTurn(int counter,int difficulty){
	/* Description: Allows the AI to play a turn.
	 * Pre-conditions: The integer of the current point in the game is sent in
	 * Post-conditions: The AI's turn is output
	 */
	if((rand()%100 + 1)>difficulty){
		if(fizz(counter) && buzz(counter)){
			cout<<"fizzbuzz"<<endl;
		} else if(fizz(counter)){
			cout<<"fizz"<<endl;
		} else if(buzz(counter)){
			cout<<"buzz"<<endl;
		} else {
			cout<<counter<<endl;
		}
	} else {
		cout << (rand()%100) << endl;
		throw 2;
	}
	return 2;
}
int playerTurn(int counter,string input){
	/* Description: Allows the player to play a turn.
	 * Pre-conditions: The integer of the current point in the game and the string of the players turn is sent in
	 * Post-conditions: The player knows whether they have passed to the next turn
	 */
	bool loss;
	loss = 1;
	if(fizz(counter) && buzz(counter)){
		if((input.compare("fizzbuzz"))){
			loss = 0;
		}
	} else if(fizz(counter)){
		if((input.compare("fizz"))){
			loss = 0;
		}
	} else if(buzz(counter)){
		if((input.compare("buzz"))){
			loss = 0;
		}
	} else {
		if((input.compare(to_string(counter)))){
			loss = 0;
		}
	}
	if(loss){
		throw 1;
	}
	return 3;
}
int fizzbuzz(int difficulty){
	/* Description:
	 * Plays out the game of fizzbuzz up to the sent in bound
	 * Pre-conditions: an integer bound is sent in
	 * Post-conditions: the fizzbuzz script up to the bound is output
	 */
	string input;
	for(int counter=0;;counter++){
		aiTurn(counter,difficulty);
		cout << "Your turn: ";
		cin >> input;
		counter++;
		playerTurn(counter,input);
	}
	return 1;
}
int main(){
	try{
		int difficulty;
		cout << "Starting the game of fizzbuzz" << endl;
		cout << "Choose your difficulty Easy(1), Medium(2), Hard(3), Extra Hard(4): ";
		cin >> difficulty;
		switch(difficulty){
			case 1: difficulty = EASY; break;
			case 2: difficulty = MEDIUM; break;
			case 3: difficulty = HARD; break;
			case 4: difficulty = EXTRA_HARD; break;
		}
		cout << "Now lets begin the ";
		fizzbuzz(difficulty);
	} catch(int params){
		switch(params){
			case 1: cout << "You lose" << endl;
				break;
			case 2: cout << "I lose" << endl;
				break;
			default: cout << "An error occurred" << endl;
		}
	}
	string choice;
	cout << "Would you like to play again(Y/n)? ";
	cin >> choice;
	if(choice.compare(0,1,"y")==0||choice.compare(0,1,"Y")==0){
		main();
	}
	return 0;
}
