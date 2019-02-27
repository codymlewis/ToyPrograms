#!/usr/bin/env python3

'''
Author: Cody Lewis
Date: 2019-02-26

Task: Write a Sigmoid program capable of calculating the sigmoid function for
one variable and the sigmoid function for two variables.
Use matplotlib to plot these functions for reasonable values of x and y.
Visualise the effect of modifying the slope parameters.
Be sure to utilise numpy functions in your code.
'''
import numpy as np
from matplotlib import cm
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

def find_2d_sigmoid(x_values):
    '''
    Give the y axis of the standard sigmoid for a given x axis
    '''
    return 1 / (1 + np.exp(-x_values))

def find_3d_sigmoid(x_values, y_values):
    '''
    Give the z axis of the standard sigmoid for a given x and y axis
    '''
    return 1 / (1 + np.exp(-x_values - y_values))


if __name__ == '__main__':
    X = np.arange(-6, 6, 0.2)
    plt.plot(X, find_2d_sigmoid(X))
    plt.title("2D Sigmoid function")
    plt.xlabel("x")
    plt.ylabel("y")
    plt.show()

    Y = np.arange(-6, 6, 0.2)
    X, Y = np.meshgrid(X, Y)
    FIG = plt.figure()
    AX = FIG.add_subplot(111, projection='3d')
    SURF = AX.plot_surface(
        X,
        Y,
        find_3d_sigmoid(X, Y),
        rstride=1,
        cstride=1,
        cmap=cm.hsv,
        linewidth=0,
        antialiased=False
    )
    FIG.colorbar(SURF, shrink=0.5, aspect=5)
    plt.title("3D Sigmoid function")
    plt.show()
