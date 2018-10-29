import * as FS from "fs";
/**
 * SevenSegWords.ts
 * Find all the words that ouput properly on a seven segment display
 *
 * Author: Cody Lewis
 * Since: 2018-10-10
 */
main();

// The main thread
function main(): void {
    console.log(`The longest words that can be displayed nicely on a` +
        ` seven segment display are:`);
    const longestWords: string[] = findLongestWords();
    for(let word of longestWords) {
        console.log(word);
    }
}

// find the longest word that can be properly displayed on a seven segment display
function findLongestWords(): string[] {
    const words: string = FS.readFileSync("words.txt").toString(); // word list from https://github.com/dwyl/english-words
    let wordsList: string[] = words.split("\n");
    wordsList = mergeSort(wordsList);
    const badLetters: RegExp = /[gkmqvwxz]/;
    let longestWordLength: number = wordsList[wordsList.length - 1].length;
    let longestAcceptableWords: string[] = [];
    for(let i: number = wordsList.length - 1; i > 0; --i) {
        if(!wordsList[i].match(badLetters)) {
            if(longestAcceptableWords.length == 0 || wordsList[i].length == longestAcceptableWords[0].length) {
                longestAcceptableWords[longestAcceptableWords.length] = wordsList[i];
            }
        }
    }
    return longestAcceptableWords;
}

// Merge sort driver function
function mergeSort(arr: string[]): string[] {
    return merge(arr, 0, arr.length - 1);
}

// Merge sort recurrence function
function merge(arr: string[], start: number, end : number): string[] {
    if(start == end) { // if the current inspected array is of length 1
        return [arr[start]];
    }
    let midpoint: number = Math.floor((end - start) / 2) + start; // middle index
    let mergedArr: string[] = merge(arr, start, midpoint).concat(merge(arr, midpoint + 1, end));
    midpoint = Math.ceil(mergedArr.length / 2); // reassign to midpoint of merged array
    let endMarker: number = midpoint;
    let startMarker: number = 0;
    let result: string[] = [];
    let resultIndex: number = 0;
    // merge sort comparing and placing operations
    while(startMarker < midpoint && endMarker < mergedArr.length) {
        if(mergedArr[startMarker].length < mergedArr[endMarker].length) {
            result[resultIndex] = mergedArr[startMarker];
            resultIndex++;
            startMarker++;
        } else {
            result[resultIndex] = mergedArr[endMarker];
            resultIndex++;
            endMarker++;
        }
    }
    // Add in the leftovers
    while(startMarker < midpoint) {
        result[resultIndex] = mergedArr[startMarker];
        resultIndex++;
        startMarker++;
    }
    while(endMarker < mergedArr.length) {
        result[resultIndex] = mergedArr[endMarker];
        resultIndex++;
        endMarker++;
    }
    return result;
}
