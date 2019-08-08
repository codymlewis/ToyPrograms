#!/usr/bin/env python3

import pandas as pd
import numpy as np
from tabulate import tabulate

'''
Find the distances between the elections

Author: Cody Lewis
Date: 2019-08-07
'''


def get_data():
    return pd.read_csv("table.txt", sep=" ")


def hamming(data):
    '''
    Give a distance matrix using Hamming calculations on the election data.
    '''
    distances = np.zeros((len(data), len(data)))
    for i, row_i in data.iterrows():
        for j, row_j in data.iterrows():
            cur_dist = 0
            for index in data:
                if index not in ["Year", "Target"]:
                    if row_i[index] != row_j[index]:
                        cur_dist += 1
            distances[i][j] = cur_dist
    return distances


def jaccard(data):
    '''
    Give a distance matrix using Jaccard calculations on the election data.
    '''
    distances = np.zeros((len(data), len(data)))
    for i, row_i in data.iterrows():
        for j, row_j in data.iterrows():
            one_matchs = 0
            zero_matchs = 0
            bits = 0
            for index in data:
                if index not in ["Year", "Target"]:
                    bits += 1
                    if row_i[index] == row_j[index]:
                        if row_i[index] == 1:
                            one_matchs += 1
                        else:
                            zero_matchs += 1
            if one_matchs == 0:
                distances[i][j] = 0
            else:
                distances[i][j] = one_matchs / (bits - zero_matchs)
    return distances


if __name__ == '__main__':
    ELECTION_DATA = get_data()
    print("The Hamming distance is:")
    HAMMING = hamming(ELECTION_DATA)
    print(tabulate(HAMMING))
    np.savetxt("hamming.csv", HAMMING, fmt="%d", delimiter=",")
    print()
    JACCARD = jaccard(ELECTION_DATA)
    print(tabulate(JACCARD))
    np.savetxt("jaccard.csv", JACCARD, fmt="%5.3f", delimiter=",")
