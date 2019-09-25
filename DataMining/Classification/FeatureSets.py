#!/usr/bin/env python3

import random
import numpy as np

'''
Find the feature sets of supervised data

Author: Cody Lewis
Data: 2019-09-05
'''

def ils_feature_set(data, classes):
    '''Use an iterative local search to find the feature set.'''
    features = np.shape(data)[1]
    start_str = perturbation("0" * features, [], random.randint(0, features))
    return ils(start_str, data, classes)


def ils(bin_str, data, classes):
    '''Perform the iterative local search on the data starting from bin_str.'''
    str_best = bin_str
    str_best, best_fitness = local_search(str_best, data, classes)
    search_history = [str_best]
    while best_fitness < np.shape(data)[0] and \
            len(search_history) < 2**np.shape(data)[1]:
        print(f"\rBest fitness: {best_fitness}", end="")
        str_new = perturbation(str_best, search_history, 4)
        str_new, fitness_new = local_search(str_new, data, classes)
        search_history.append(str_new)
        if fitness_new > best_fitness:
            str_best = str_new
            best_fitness = fitness_new
    return get_feature_set(str_best)


def local_search(str_test, data, classes):
    '''Perform a local search for the fittest value.'''
    best_str = str_test
    best_fitness = fitness(str_test, data, classes)
    for i, a in enumerate(str_test):
        new_str = str_test[:i] + ("0" if a == "1" else "1") + str_test[i + 1:]
        new_fitness = fitness(new_str, data, classes)
        if new_fitness > best_fitness:
            best_str = new_str
            best_fitness = new_fitness
    return best_str, best_fitness


def perturbation(str_best, search_history, level):
    '''Find a perturbation of the input string'''
    str_new = str_best
    for i in random.sample([x for x in range(len(str_best))], level):
        str_new = str_new[:i] + ("1" if str_best[i] == "0" else "0") + str_new[i + 1:]
    if str_new not in search_history:
        return str_new
    return perturbation(str_best, search_history, level)


def fitness(feature_set_bin, data, classes):
    '''Find the fitness of the given feature set.'''
    feature_set = get_feature_set(feature_set_bin)
    explainers = dict()
    fitness_val = 0
    for i, row in enumerate(data):
        cur_fs = ""
        for col in feature_set:
            cur_fs += str(int(row[col]))
        if explainers.get(cur_fs) is not None and explainers[cur_fs] != classes[i]:
            fitness_val -= 1
        else:
            fitness_val += 1
        explainers[cur_fs] = classes[i]
    return fitness_val


def get_feature_set(feature_set_bin):
    '''Get feature set indices given a binary string'''
    feature_set = []
    for i, a in enumerate(feature_set_bin):
        if a == "1":
            feature_set.append(i)
    return feature_set

def reduce_data(data, feature_set):
    '''Reduce the data into the features from the set'''
    result = []
    for row in data:
        result.append(row[feature_set])
    return np.array(result)
