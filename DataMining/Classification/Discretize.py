import numpy as np

'''
Discretize data

Author: Cody Lewis
Date: 2019-09-22
'''

class DecisionTree:
    '''A decision tree class'''
    def __init__(self):
        self.root = Node()

    def add(self, value, label):
        '''Add a node to the tree'''
        self.root.add(value, label)

    def traverse(self, value):
        '''Traverse the tree to find a label for the value'''
        return self.root.traverse(value)

class Node:
    '''A node of a decsion tree'''
    def __init__(self, value=None, label=None):
        self.value = value
        self.label = label
        self.left = None
        self.right = None

    def set_value(self, value):
        '''Set the value of this node'''
        self.value = value

    def add(self, value, label):
        '''Add a node'''
        if self.value is None:
            self.value = value
            self.label = label
        else:
            if self.eval(value):
                self.add_left(value, label)
            else:
                self.add_right(value, label)

    def add_left(self, value, label):
        '''Add a node the the left of this'''
        if self.left is None:
            self.left = Node(value, label)
        else:
            self.left.add(value, label)

    def add_right(self, value, label):
        '''Add a node to the right of this'''
        if self.right is None:
            self.right = Node(value, label)
        else:
            self.right.add(value, label)

    def traverse(self, value):
        '''Traverse the node, to find the label for the value'''
        if self.eval(value):
            return self.traverse_left(value)
        return self.traverse_right(value)

    def traverse_left(self, value):
        '''Traverse to the left of this'''
        if self.left is None:
            return self.label
        return self.left.traverse(value)

    def traverse_right(self, value):
        '''Traverse to the right of this'''
        if self.right is None:
            return self.label
        return self.right.traverse(value)

    def eval(self, value):
        '''Find whether the value is less than or equal to this'''
        return value <= self.value

def id3(data, labels, classes):
    '''Discretize the data using the id3 algorithm'''
    result = []
    for data_row in data:
        row = data_row
        dtree = DecisionTree()
        cur_labels = labels
        for _ in classes:
            cut_point = find_cut_point(data_row, cur_labels, classes)
            dtree.add(cut_point, int(np.round(np.mean(cur_labels[data_row <= cut_point]))))
            cur_labels = cur_labels[data_row > cut_point]
            data_row = data_row[data_row > cut_point]
            if len(data_row) == 0:
                break
        result.append([dtree.traverse(value) for value in row])
    return np.array(result)


def entropy(labels, classes):
    '''Find the entropy of the set with classes'''
    result = 0
    for c in classes:
        cur_prop = proportion(labels, c)
        if cur_prop != 0:
            result += cur_prop * np.log2(cur_prop)
    return -result

def proportion(labels, goal_class):
    '''Find the proportion of labels with the goal class'''
    has_goal_class = 0
    for label in labels:
        if label == goal_class:
            has_goal_class += 1
    return has_goal_class / len(labels)

def find_cut_point(data, labels, classes):
    '''Linearly search for the optimal cut point for the set'''
    sorted_data = np.sort(data)
    if len(sorted_data) > 1:
        entropies = dict()
        for i in range(len(sorted_data) - 1):
            midpoint = (sorted_data[i] + sorted_data[i + 1]) / 2
            s1 = data[data <= midpoint]
            s2 = data[data > midpoint]
            cur_entropy = (len(s1) / len(data)) * entropy(labels[data <= midpoint], classes) + \
                (len(s2) / len(data)) * entropy(labels[data > midpoint], classes)
            entropies[cur_entropy] = midpoint
        return entropies[min(entropies)]
    return sorted_data[0]
