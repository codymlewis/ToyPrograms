/* back2TopBtn.js
 * Author: Cody Lewis - c3283349
 * Date: 15-10-2017
 * Last Modified: 16-10-2017
 * Description: Defines the operations within the webpage
 */
function genBtn(){
//Generates the back to top button into the html
//Pre-conditions:  There is a tag with the id 'b2Top'
//Post-conditions: The focus is set to the textbox fNm
  var toTopBtn = document.getElementById("b2Top");
  toTopBtn.innerHTML="<input type='image' src='./images/upArr.png' onclick='toTop()' id='topBtn' title='' alt='Back to Top' value=''></input>";
}
window.onscroll = function() {scrolling()};
function scrolling() {
  if(document.body.scrollTop>20 || document.documentElement.scrollTop>20){
    document.getElementById("topBtn").style.display = "block";
  } else {
    document.getElementById("topBtn").style.display = "none";
  }
}
function toTop() {
  document.body.scrollTop = 0;  //chrome,safari,opera
  document.documentElement.scrollTop = 0; //Internet Explorer,firefox
}
