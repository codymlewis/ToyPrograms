#!/usr/bin/env python3

import numpy as np

def sensitivity(conf_matrix):
    if np.shape(conf_matrix) == (2, 2):
        return conf_matrix[0][0] / (conf_matrix[0][0] + conf_matrix[1][0])
    result = 0
    for i in range(np.shape(conf_matrix)[1]):
        false_neg = 0
        for j in range(np.shape(conf_matrix)[0]):
            if i != j:
                false_neg += conf_matrix[j][i]
        if conf_matrix[i][i] != 0:
            result += conf_matrix[i][i] / (conf_matrix[i][i] + false_neg)
    if result == 0:
        return 0
    return result / np.shape(conf_matrix)[0]


def specificity(conf_matrix):
    if np.shape(conf_matrix) == (2, 2):
        return conf_matrix[1][1] / (conf_matrix[1][1] + conf_matrix[0][1])
    result = 0
    for i in range(np.shape(conf_matrix)[1]):
        true_neg = 0
        false_pos = 0
        for j in range(np.shape(conf_matrix)[0]):
            if i != j:
                false_pos += conf_matrix[i][j]
                true_neg += conf_matrix[j][j]
        if true_neg != 0:
            result += true_neg / (true_neg + false_pos)
    if result == 0:
        return 0
    return result / np.shape(conf_matrix)[0]


def accuracy(conf_matrix):
    corrects = 0
    total = 0
    for i in range(np.shape(conf_matrix)[1]):
        for j in range(np.shape(conf_matrix)[0]):
            if i == j:
                corrects += conf_matrix[i][j]
            total += conf_matrix[i][j]
    if corrects == 0:
        return 0
    return corrects / total


def precision(conf_matrix):
    if np.shape(conf_matrix) == (2, 2):
        return conf_matrix[0][0] / (conf_matrix[0][0] + conf_matrix[0][1])
    result = 0
    for i in range(np.shape(conf_matrix)[1]):
        false_pos = 0
        for j in range(np.shape(conf_matrix)[0]):
            if i != j:
                false_pos += conf_matrix[i][j]
        if conf_matrix[i][i] != 0:
            result += conf_matrix[i][i] / (conf_matrix[i][i] + false_pos)
    if result == 0:
        return 0
    return result / np.shape(conf_matrix)[0]


def f1score(conf_matrix):
    prec = precision(conf_matrix)
    recall = sensitivity(conf_matrix)
    if (prec + recall) == 0:
        return 0
    return (2 * prec * recall) / (prec + recall)


def correlation_coeff(conf_matrix):
    if np.shape(conf_matrix) == (2, 2):
        return (conf_matrix[0][0] * conf_matrix[1][1] -
                conf_matrix[1][0] * conf_matrix[0][1]) / np.sqrt(
                    (conf_matrix[0][0] + conf_matrix[0][1]) *
                    (conf_matrix[0][0] + conf_matrix[1][0]) *
                    (conf_matrix[1][1] + conf_matrix[0][1]) *
                    (conf_matrix[1][1] + conf_matrix[1][0])
                )
    result = 0
    for i in range(np.shape(conf_matrix)[0]):
        true_pos = 0
        true_neg = 0
        false_pos = 0
        false_neg = 0
        for j in range(np.shape(conf_matrix)[1]):
            if i == j:
                true_pos += conf_matrix[i][j]
            else:
                true_neg += conf_matrix[j][j]
                false_pos += conf_matrix[i][j]
                false_neg += conf_matrix[j][i]
        denom = np.sqrt(
            (true_pos + false_pos) * (true_pos + false_neg) *
            (true_neg + false_pos) * (true_neg + false_neg)
        )
        if denom != 0:
            result += (true_pos * true_neg + false_pos * false_neg) / denom
    if result == 0:
        return 0
    return result / np.shape(conf_matrix)[0]


def jstat(conf_matrix):
    return sensitivity(conf_matrix) + specificity(conf_matrix) - 1

if __name__ == '__main__':
    conf_matrix = [
        [237 + 27, 78 + 56, 39 + 6, 1 + 1, 8 + 0],
        [179 + 37, 406 + 331, 20 + 2, 165 + 38, 17 + 3],
        [96 + 22, 6 + 7, 256 + 60, 0 + 0, 19 + 2],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0]
    ]
    # conf_matrix = [
    #     [43 + 35 + 12, 0 + 5 + 6],
    #     [0 + 7 + 10, 40 + 34 + 2]
    # ]
    print(f"Sensitivity: {sensitivity(conf_matrix)}")
    print(f"Specificity: {specificity(conf_matrix)}")
    print(f"Accuracy: {accuracy(conf_matrix)}")
    print(f"Precision: {precision(conf_matrix)}")
    print(f"F1-Score: {f1score(conf_matrix)}")
    print(f"Matthews Correlation Coefficient: {correlation_coeff(conf_matrix)}")
    print(f"Youden's J Statistic: {jstat(conf_matrix)}")
