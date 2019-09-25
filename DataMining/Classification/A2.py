#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import argparse
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

import Graph
import Perceptron
import Discretize
import FeatureSets

'''
Comp3340 Assignment 2

Author: Cody Lewis
Date: 2019-09-08
'''


def get_data(filename):
    return pd.read_csv(filename)


def euc_dist(p, q):
    '''Find the Euclidean distance between the points p and q.'''
    return np.sqrt(np.sum(np.power(np.subtract(p, q), 2)))


def strip_class(data):
    '''Remove the class/id columns and convert the data into a numpy tensor.'''
    columns = data.columns.difference(['CLASS'])
    result = np.zeros(data[columns].shape)
    for i, row in data[columns].iterrows():
        for j, col in enumerate(row.to_numpy()):
            result[i][j] = col
    return result, data['CLASS']


def find_dist_matrix(data, dist):
    '''
    Give a distance matrix using a distance equation of the data.
    '''
    distances = np.zeros((len(data), len(data)))

    for i, row_i in enumerate(data):
        for j, row_j in enumerate(data):
            distances[i][j] = dist(row_i, row_j)

    return distances

def find_beta_skeleton(beta, data, dist_matrix):
    '''
    Find a beta skeleton by looking at all of the possible triples formed in
    the distance matrix
    '''
    graph = Graph.Graph(np.shape(dist_matrix)[0])
    for i in range(np.shape(dist_matrix)[0]):
        for j in set(range(np.shape(dist_matrix)[0])) - {i}:
            add_ij = True
            point_a = (1 - beta / 2) * data[i] + data[j] * beta / 2
            point_b = data[i] * beta / 2 + (1 - beta / 2) * data[j]
            radius = (beta * dist_matrix[i][j]) / 2
            for k in set(range(len(dist_matrix))) - {i, j}:
                # find dist from pa pb
                if max(euc_dist(point_a, data[k]), euc_dist(point_b, data[k])) < radius:
                    add_ij = False
                    break
            # add i, j to graph
            if add_ij:
                graph.connect(i, j, dist_matrix[i][j])
    return graph

def enumerate_classes(classes):
    '''Assign arbitrary values to the classes'''
    equivalences = dict()
    result = []
    for c in classes:
        if equivalences.get(c) is None:
            equivalences[c] = len(equivalences)
        result.append(equivalences[c])
    return np.array(result), equivalences


def eval_classifer(classifier, data_fn, feature_sets, equiv, rs_prefix, verbose=False):
    '''Create a confusion matrix on the data for the given classifier'''
    data = get_data(data_fn)
    data, classes = strip_class(data)
    data = FeatureSets.reduce_data(data, feature_sets)
    confusion_matrix = [[0 for _ in range(len(equiv))] for _ in range(len(equiv))]
    for c, row in zip(equiv.keys(), confusion_matrix):
        row.insert(0, c)
    for i, t in zip(data, classes):
        output = classifier.classify(i)
        confusion_matrix[output][equiv[t] + 1] += 1
        class_enum = equiv[t]
    if verbose:
        print()
        print("The classfier gives the following confusion matrix for the data:")
    conf_df = pd.DataFrame(data=confusion_matrix, columns=["class"] + list(equiv.keys()))
    if verbose:
        print(conf_df)
    filename = f"{rs_prefix}-confusion_matrix.tex"
    with open(filename, "w") as f:
        f.write(conf_df.to_latex())
    if verbose:
        print(f"Confusion matrix written to {filename}")
        print()


if __name__ == '__main__':
    PARSER = argparse.ArgumentParser(description="COMP3340 Assignment 2")
    PARSER.add_argument("-f", "--feature", dest="feature", action="store_const",
                        default=False, const=True,
                        help="Perform feature wise analysis.")
    PARSER.add_argument("-b", "--beta", dest="beta", action="store_const",
                        default=False, const=True,
                        help="Construct a beta skeleton")
    PARSER.add_argument("-p", "--param", dest="param", type=int, action="store",
                        default=1,
                        help="Hyper parameter to use for the graph")
    PARSER.add_argument("-ns", "--node-size", dest="node_size", type=float,
                        action="store", default=1,
                        help="Size of nodes in the plotted graph")
    PARSER.add_argument("-c", "--classify", dest="classify", action="store_const",
                        default=False, const=True,
                        help="Apply a classifier to the data")
    PARSER.add_argument("--train-data", dest="train", type=str, action="store",
                        default="data/alzheimers_train.csv",
                        help="Training data filename.")
    PARSER.add_argument("--test-data", dest="test", type=str, action="store",
                        default="data/alzheimers_test.csv",
                        help="Testing data filename.")
    PARSER.add_argument("--mci-data", dest="mci", type=str, action="store",
                        default=None,
                        help="MCI data filename.")
    ARGS = PARSER.parse_args()
    TRAIN_DATA = get_data(ARGS.train)
    INPUT_DATA, CLASSES = strip_class(TRAIN_DATA)
    if ARGS.feature:
        INPUT_DATA = INPUT_DATA.transpose()
    DIST_MATRIX = find_dist_matrix(INPUT_DATA, euc_dist)
    # beta 1 for Gabriel, beta 2 for RNG
    if ARGS.beta:
        print(f"Finding {ARGS.param}-Skeleton...")
        find_beta_skeleton(ARGS.param, INPUT_DATA, DIST_MATRIX).create_diagram(
            f"{'feature' if ARGS.feature else 'sample'}-wise-{ARGS.param}-skeleton",
            node_size=ARGS.node_size,
            verbose=True
        )
    if ARGS.classify:
        CLASSES, EQUIV = enumerate_classes(CLASSES)
        print("Discretizing data...")
        DISCRETIZED_DATA = Discretize.id3(
            INPUT_DATA.transpose(),
            CLASSES,
            list(EQUIV.values())
        ).transpose()
        print("Finding a reduced feature set...")
        FEATURE_SETS = FeatureSets.ils_feature_set(DISCRETIZED_DATA, CLASSES)
        print()
        print(f"Reduced feature set: {FEATURE_SETS}, length: {len(FEATURE_SETS)}")
        PERCEPTRON = Perceptron.Perceptron(len(FEATURE_SETS), ARGS.param, len(EQUIV))
        print("Learning...")
        MINIMUM_INPUT_DATA = FeatureSets.reduce_data(INPUT_DATA, FEATURE_SETS)
        ERR, ERRS = PERCEPTRON.learn(0.01, MINIMUM_INPUT_DATA, CLASSES, verbose=True)
        plt.plot(ERRS)
        plt.title("Error Curve of the Perceptron")
        plt.xlabel("Epochs")
        plt.ylabel("Error")
        FILENAME = "error_curve.png"
        plt.savefig("error_curve.png")
        print(f"Error curve written to {FILENAME}")
        eval_classifer(PERCEPTRON, ARGS.train, FEATURE_SETS, EQUIV, "train", verbose=True)
        eval_classifer(PERCEPTRON, ARGS.test, FEATURE_SETS, EQUIV, "test", verbose=True)
        if ARGS.mci is not None:
            eval_classifer(PERCEPTRON, ARGS.mci, FEATURE_SETS, EQUIV, "mci", verbose=True)
