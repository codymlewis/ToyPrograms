/* decToRat.cpp - decToRat
 * Author: Cody Lewis
 * Date:   19-FEB-2018
 * Description:
 * Converts an input decimal into a fraction
 * successfully finds non reccurring fractions
 */
#include<iostream>
#include<string>
std::string convert(double input){
  std::string output = "";
  // find the mixed fraction
  if(input > 1){ 
    int i;
    for(i = 0; input > 1; input--){
      i++;
    }
    output = output + std::to_string(i) + " and ";
  }
  // find a suitable numerator and denomonator
  int denomonator;
  double inputCp = input;
  for(denomonator = 1; inputCp>0; denomonator*=10){
    inputCp*=10;
    if(inputCp > 1){
      for(; inputCp >= 1; inputCp--);
    }
  }
  int numerator = input*denomonator;
  // factorize the fraction
  int gcf = 0; // the greatest common factor
  for(int i = 1; i<=numerator; i++){
    if(denomonator % i == 0 && numerator % i == 0){
      gcf = i;
    }
  }
  if(gcf != 0){
    denomonator/=gcf;
    numerator/=gcf;
  } else {
  }
  output = output + std::to_string(numerator) + "/" + std::to_string(denomonator);
  return output;
}
int main(){
  double input;
  std::cout << "Input your fraction: ";
  std::cin >> input;
  std::cout << convert(input) << std::endl;
  return 0;
}
