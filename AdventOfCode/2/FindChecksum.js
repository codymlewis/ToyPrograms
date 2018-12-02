const fs = require('fs');
/**
Solution to https://adventofcode.com/2018/day/2
Author: Cody Lewis
Date: 2018-12-02
*/

main(process.argv.slice(2));

function main(args) { // Go through the 2 parts of the challenge
    if(args < 1) {
        console.log("You must specify a file to read.")
    } else {
        ids = fs.readFileSync(args[0]);
        ids = ids.toString().split("\n");
        console.log(`The final checksum is ${part1(ids)}`);
        console.log(`The chared id of the right boxes is ${part2(ids)}`);
    }
}

function part1(ids) {
    // Find a checksum based on occurances of the same 2 letters and 3 letters
    twoLetterOccurances = 0;
    threeLetterOccurances = 0;
    ids.forEach((id) => {
        letterCount = [];
        var twoLetterHasHappened = false;
        var threeLetterHasHappened = false;
        for(var i = 0; i < id.length; ++i) {
            var change = false;
            letterCount[id.charAt(i)] = letterCount[id.charAt(i)] === undefined ? 1 : letterCount[id.charAt(i)] + 1;
        }
        for(var letter in letterCount) {
            var count = letterCount[letter];
            if(!twoLetterHasHappened && count == 2) {
                twoLetterOccurances++;
                twoLetterHasHappened = true;
            } else if(!threeLetterHasHappened && count == 3) {
                threeLetterOccurances++;
                threeLetterHasHappened = true;
            }
        }
    });
    return twoLetterOccurances * threeLetterOccurances;
}

function part2(ids) {
    // Find the shared letters between the pair of ids which match at everything but 1 letter
    ids = ids.sort();
    for(var i = 0; i < ids.length; ++i) {
        // Have a shift register type setup to check the shared letters
        if(i > 0) {
            wordDiff = findWordDiff(ids[i - 1], ids[i]);
            if(wordDiff !== undefined) {
                return wordDiff;
            }
        }
        if(i < (ids.length - 1)) {
            wordDiff = findWordDiff(ids[i], ids[i + 1]);
            if(wordDiff !== undefined) {
                return wordDiff;
            }
        }
    }
}

function findWordDiff(word1, word2) {
    // Return the shared letters between the 2 words iff they share all but
    // 1 letter, otherwise return undefined
    dist = 0;
    finalWord = "";
    for(var j = 0; j < word1.length; ++j) {
        if(word1.charAt(j) != word2.charAt(j)) {
            dist++;
            if(dist > 1) {
                return undefined;
            }
        } else {
            finalWord += word1.charAt(j);
        }
    }
    if(dist == 1) {
        return finalWord;
    }
    return undefined;
}
