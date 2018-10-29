"use strict";
exports.__esModule = true;
var FS = require("fs");
/**
 * SevenSegWords.ts
 * Find all the words that ouput properly on a seven segment display
 *
 * Author: Cody Lewis
 * Since: 2018-10-10
 */
main();
// The main thread
function main() {
    console.log("The longest words that can be displayed nicely on a" +
        " seven segment display are:");
    var longestWords = findLongestWords();
    for (var _i = 0, longestWords_1 = longestWords; _i < longestWords_1.length; _i++) {
        var word = longestWords_1[_i];
        console.log(word);
    }
}
// find the longest word that can be properly displayed on a seven segment display
function findLongestWords() {
    var words = FS.readFileSync("words.txt").toString(); // word list from https://github.com/dwyl/english-words
    var wordsList = words.split("\n");
    wordsList = mergeSort(wordsList);
    var badLetters = /[gkmqvwxz]/;
    var longestWordLength = wordsList[wordsList.length - 1].length;
    var longestAcceptableWords = [];
    for (var i = wordsList.length - 1; i > 0; --i) {
        if (!wordsList[i].match(badLetters)) {
            if (longestAcceptableWords.length == 0 || wordsList[i].length == longestAcceptableWords[0].length) {
                longestAcceptableWords[longestAcceptableWords.length] = wordsList[i];
            }
        }
    }
    return longestAcceptableWords;
}
// Merge sort driver function
function mergeSort(arr) {
    return merge(arr, 0, arr.length - 1);
}
// Merge sort recurrence function
function merge(arr, start, end) {
    if (start == end) { // if the current inspected array is of length 1
        return [arr[start]];
    }
    var midpoint = Math.floor((end - start) / 2) + start; // middle index
    var mergedArr = merge(arr, start, midpoint).concat(merge(arr, midpoint + 1, end));
    midpoint = Math.ceil(mergedArr.length / 2); // reassign to midpoint of merged array
    var endMarker = midpoint;
    var startMarker = 0;
    var result = [];
    var resultIndex = 0;
    // merge sort comparing and placing operations
    while (startMarker < midpoint && endMarker < mergedArr.length) {
        if (mergedArr[startMarker].length < mergedArr[endMarker].length) {
            result[resultIndex] = mergedArr[startMarker];
            resultIndex++;
            startMarker++;
        }
        else {
            result[resultIndex] = mergedArr[endMarker];
            resultIndex++;
            endMarker++;
        }
    }
    // Add in the leftovers
    while (startMarker < midpoint) {
        result[resultIndex] = mergedArr[startMarker];
        resultIndex++;
        startMarker++;
    }
    while (endMarker < mergedArr.length) {
        result[resultIndex] = mergedArr[endMarker];
        resultIndex++;
        endMarker++;
    }
    return result;
}
