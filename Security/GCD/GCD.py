#!/usr/bin/python

def CalculateGCD(i,j,oldi=None,oldj=None):

    if i == j:
        print("Error: Numbers are the same; {}, {}".format(i,j))
    else:
        if i > j:
            prefix = int(i / j)
            remainder = i % j
            greater = i
            lesser = j
        else:
            prefix = int(i / j)
            remainder = j % i
            greater = j
            lesser = i

        print("{}={}({})+{}".format(greater,prefix,lesser,remainder))
        if oldi is None:
            oldi = greater
            oldj = lesser
        if remainder is 0:
            print("Initial inputs; {},{}".format(oldi,oldj))
            print("Found a solution; lowest GCD = {}".format(lesser))
        else:
            CalculateGCD(lesser,remainder,oldi,oldj)

if __name__ == "__main__":
    try:
        i = input("Enter first number:")
        j = input("Enter second number:")
        CalculateGCD(int(i),int(j))
    except Exception:
        print("An error occurred: {}".format(Exception))
