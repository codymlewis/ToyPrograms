#!/usr/bin/env python3
'''
Solution to https://adventofcode.com/2018/day/1
Author: Cody Lewis
Date: 2018-12-01
'''


def memoize_changes():
    '''
    Create a table of the frequency changes.
    '''
    changes = []
    with open("Changes.txt") as f:
        for line in f:
            for element in line.split(" "):
                changes.append(int(element))
    return changes


def find_final_frequency(changes=memoize_changes()):
    '''
    Sum the changes together.
    '''
    final_frequency = 0
    for change in changes:
        final_frequency += change
    return final_frequency


def find_first_double(changes=memoize_changes()):
    '''
    Find a double of the sum cycle.
    '''
    running_frequency = 0
    occured_frequencies = {running_frequency : True}
    while True:
        for change in changes:
            running_frequency += change
            print(running_frequency)
            if occured_frequencies.get(running_frequency) != None:
                return running_frequency
            occured_frequencies.update({running_frequency : True})


if __name__ == "__main__":
    CHANGES = memoize_changes()
    print(f"The final frequency is {find_final_frequency(CHANGES)}")
    print(f"The first doubled frequency is {find_first_double(CHANGES)}")
