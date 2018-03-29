/* feedback.js
 * Author: Cody Lewis - c3283349
 * Date: 13-10-2017
 * Last Modified: 20-10-2017
 * Description: Defines the operations within the webpage
 */

 //UI Functions
 window.onload = function(){  //The operation that occurs when the window loads
 //Focuses on the first text box in the form
 //Pre-conditions:  The window is loaded
 //Post-conditions: The focus is set to the textbox fNm
   document.getElementById("name").focus();
   var btns = document.getElementById("buttons"); //This stops submission of the form when there is no Javascript
   btns.innerHTML="<input type='submit' value='Submit your feedback'/> <input type='reset' value='Reset your answers'/>";
 }
 function selectAllFind() {
 //Marks all checkboxes of find as checked
 //Pre-conditions:  slctBtn is pressed
 //Post-conditions: The checkboxes named find are selected
   var find = window.document.feedbackForm.find;
   for (var i = 0; i < find.length; i++) {
     find[i].checked = true;
   }
 }
 function showVal(id,newVal) {
 //Places the argument value into the html tag specified with the argument id
 //Pre-conditions:  An event calls the method
 //Post-conditions: The specified tag now contains the argument value
   document.getElementById(id).innerHTML = newVal;
 }
//Check and Error Functions
function checkDetails(){
//Checks the details input into the form by calling the various check functions
//Pre-conditions:  the form is submitted
//Post-conditions: a boolean value is returned which determines whether the form is completely returned
  refreshErr();
  if(checkName()&checkDob()&checkAge()&checkEmail()&checkWeb()&
      checkPhone()&checkFind()&checkAdd()&checkComp())	//single ampersand to evaluate everything
    return true;
  return false;
}
function refreshErr(){
//Resets the errors displayed in the form
//Pre-conditions:  the checkDetails is called
//Post-conditions: the error spans are emptied
  var err = document.getElementsByClassName("error");
  for (var i = 0; i < err.length; i++)
    err[i].innerHTML="";
  showVal("satisfied",6); //resets the scale number
}
function checkName() {
//Checks the name submission of the form
//Pre-conditions:  the checkDetails is called
//Post-conditions: the name field is validified
  var fName = window.document.feedbackForm.fNm.value;
  var lName = window.document.feedbackForm.lNm.value;
  if(isEmpty(fName) || isEmpty(lName)){
    document.getElementById("nmErr").innerHTML="You need to fill in your name <br />"; //While inputing these was repititous
    return false;                                                                      //the specificity was advantagous
  }

  if(!(checkWordWSpace(fName) && checkWordWSpace(lName))){
    document.getElementById("nmErr").innerHTML="The name you input was incorrect, it should only contain letters <br />";
    return false;
  }
  return true;
}
function isEmpty(input) {
//Checks whether the input is empty
//Pre-conditions:  A variable is sent in
//Post-conditions: if it is empty then true is returned else false is returned
  if(input.length==0)
    return true;
  return false;
}
function checkWord(word){
//Checks that a string only contains letters
//Pre-conditions:  a variable is instantiated and sent in
//Post-conditions: The validity of the word is returned as a boolean
  for (var i = 0; i < word.length; i++) {
    var isChar = checkChar(word.charAt(i));
    if(!isChar)
      return false;
  }
  return true;
}
function checkWordWSpace(word){
//Check word with spaces
//Pre-conditions:  a variable is sent in
//Post-conditions: The validity of the variable as a word is evaluated
  for (var i = 0; i < word.length; i++) {
    var isChar = checkChar(word.charAt(i)) || word.charCodeAt(i)==32;
    if(!isChar)
      return false;
  }
  return true;
}
function checkChar(isChar) {
//Checks that a single character string is a letter
//Pre-conditions:  a single character variable is sent in
//Post-conditions: if it is a letter true is returned, else false is returned
  var code = isChar.charCodeAt(0);
  if((code > 64 && code < 91) || (code > 96 && code < 123))	//Checks with the ascii codes
    return true;
  return false;
}
function checkNo(number) {
//Checks that a string consists only of numbers
//Pre-conditions:  a variable is sent in
//Post-conditions: the variable is evaluated
  for (var i = 0; i < number.length; i++)
    if(!isNum(number.charAt(i)))
      return false;
  return true;
}
function isNum(num){
//Checks that a single character is a number
//Pre-conditions:  a variable is sent in
//Post-conditions: the variable is evaluated
  var code = num.charCodeAt(0);
  if(code > 47 && code < 58)
    return true;
  return false;
}
function checkDob() {
//Checks whether the input is a valid date
//Pre-conditions:  the checkDetails is called
//Post-conditions: the DoB field is validified
  var dob = window.document.feedbackForm.dob.value;
  if(!isEmpty(dob)){
    if(!isDate(dob)){
      document.getElementById("dobErr").innerHTML="The date of birth you input was incorrect, it should be a valid date (day/month/year) <br />";
      return false;
    }
  }
  return true;
}
function isDate(date) {
//Evaluates whether a variable is a date
//Pre-conditions:  A variable is sent in
//Post-conditions: returns true if it is a date, otherwise it returns false
  if(date.length==8){
    var day   = date.substring(0,2);
    var month = date.substring(2,4);
    var year  = date.substring(4,8);
  } else if (date.length==10) {
    var day   = date.substring(0,2);
    var month = date.substring(3,5);
    var year  = date.substring(6,10);
  } else {
    return false;
  }
  return isRealDate(day,month,year);
}
function isRealDate(day,month,year){
//Evaluates whether a real date has been placed in
//Pre-conditions:  Three variables are sent in
//Post-conditions: They are evaluated as to whether they are a valid date
  if(checkNo(day) && checkNo(month) && checkNo(year)){
    day   = parseInt(day);
    month = parseInt(month);
    year  = parseInt(year);
    if(day < 1){
      return false;
    }
    var dt = new Date();
    var curYear = dt.getYear()+1900;	//Sets to the current year
    if(year > (curYear-120) && year < curYear){	//120 is a bit bigger than the age of the current oldest person
      if(month > 0 && month < 13){
        if(((month%2)==1 && (month!=9 && month!=11)) || month==8 || month==10 || month==12){
          if(day<32){
            return true;
          }
        } else if(month==4 || month==6 || month==9 || month==11){
          if(day<31){
            return true;
          }
        } else {
          if((year%4)==0){
            if(day<30){
              return true;
            }
          } else {
            if(day<29){
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
function checkAge() {
//Checks whether the input is a valid age (not still at default)
//Pre-conditions:  the checkDetails is called
//Post-conditions: the age field is validified
  var age = window.document.feedbackForm.age.value;
  if(age=="nil"){
    document.getElementById("ageErr").innerHTML="You need to choose something here <br />";
    return false;
  }
  return true;
}
function checkEmail() {
//Checks whether the email is valid
//Pre-conditions:  the checkDetails is called
//Post-conditions: the email field is validified
  var email = window.document.feedbackForm.eml.value;
  if(!isEmpty(email)){
    var userN = email.substring(0,email.indexOf("@"));
    if(checkWebAdd(userN)){
      var dom = email.substring((email.indexOf("@")+1),email.indexOf("."));
      if(checkWord(dom)){
        return true;
      }
    }
    document.getElementById("emlErr").innerHTML="The email you entered is not valid, please enter a valid address (eg. someone@example.com) <br />";
    return false;
  }
  return true;
}
function checkWeb() {
//Checks whether the website address entered is valid
//Pre-conditions:  the checkDetails is called
//Post-conditions: the website field is validified
  var web = window.document.feedbackForm.web.value;
  if(!isEmpty(web)){
    var protocol = web.substring(0,web.lastIndexOf("/")+1);
    if(protocol=="http://" || protocol=="https://"){
      var www = web.substring((web.lastIndexOf("/")+1),web.indexOf("."));
      if(www=="www"){
        var web = web.substring(web.indexOf(".")+1);
        if(isWebsite(web))
            return true;
      }
    } else if (web.substring(0,web.indexOf(".")) == "www") {
      web = web.substring(web.indexOf(".")+1);
      if(isWebsite(web))
        return true;
    } else {
      if(isWebsite(web))
        return true;
    }
    document.getElementById("webErr").innerHTML="The website address you entered is not valid, please enter a valid address (eg. example.com) <br />";
    return false;
  }
  return true;
}
function isWebsite(web) {
//Checks whether the web address after the www. is valid
//Pre-conditions:  A web address is sent in
//Post-conditions: The web address is validated
  var webAddress = web.substring(0,web.lastIndexOf("."));
  if(checkWebAdd(webAddress)){
    var domExt = web.substring(web.lastIndexOf(".")+1);
    if(checkWord(domExt)){
      return true;
    }
  }
  return false;
}
function checkWebAdd(address){
//Finds whether the address sent in follows web address standards
//(only includes UTF-8, although other unicode formats are possible)
//Pre-conditions:  A variable is sent in
//Post-conditions: The variable is evaluated as to whether it only contains numbers and letters
  address.trim();	//removes white space
  for (var i = 0; i < address.length; i++) {
    if(!isLetOrNum(address.charAt(i))){
      return false;
    }
  }
  return true;

}
function isLetOrNum(char) {
//Checks whether a character is a letter or number
//Pre-conditions:  a variable is sent in
//Post-conditions: it is evaluated as to whether it is a letter or number
  var notP = !checkChar(char);
  var notQ = !isNum(char);
  if(!(notP&&notQ) && (notP||notQ)) //XOR
    return true;
  return false;
}
function checkPhone() {
//Checks whether the phone number entered is valid
//Pre-conditions:  the checkDetails is called
//Post-conditions: the phone number field is validified
  var phone = window.document.feedbackForm.phnNo.value;
  if(!isEmpty(phone)){
    if(phone.length!=10 || !checkNo(phone)){
      document.getElementById("pnErr").innerHTML="The phone number you entered is not valid, please enter a valid number (eg. 0000000000) <br />";
      return false;
    }
  }
  return true;
}
function checkFind() {
//Evaluates whether at least one checkbox is checked
//Pre-conditions:  checkDetails was called
//Post-conditions: The find checkboxes are evaluated
  var find = window.document.feedbackForm.find;
  for (var i = 0; i < find.length; i++)
    if(find[i].checked)
      return true;
  document.getElementById("findErr").innerHTML="You need to choose something here <br />";
  return false;
}
function checkAdd() {
//Evaluates whether a radio button has been chosen
//Pre-conditions:  checkDetails was called
//Post-conditions: The add radio buttons are evaluated
  var add = window.document.feedbackForm.add;
  for (var i = 0; i < add.length; i++)
    if(add[i].checked)
      return true;
  document.getElementById("addErr").innerHTML="You need to choose something here <br />";
  return false;
}
function checkComp() {
//Checks whether the textbox for knowledge of the company is filled in
//Pre-conditions:  checkDetails was called
//Post-conditions: The textbox is validated
  var comp = window.document.feedbackForm.companyKnow.value;
  if(isEmpty(comp)){
    document.getElementById("compErr").innerHTML="You need to fill in this field <br />"; //While inputing these was repititous
    return false;                                                                      //the specificity was advantagous
  }
  if(!checkWordWSpace(comp)){
    document.getElementById("compErr").innerHTML="Your input was incorrect, it should only contain letters <br />";
    return false;
  }
  return true;
}
