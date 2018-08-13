# a chinese remainder theorum program
# Author: Cody Lewis
import math
def chinese_rem(c, n, e): # cx mod n = e
    factor_list = factors(n)
    x = 0
    t = len(factor_list)
    for i in range(0, t):
        d = factor_list[i]
        x += ((n/d) * find_y(n, d) * find_x(c, d, e)) % n
    return int(x % n)

def factors(n): # find the factors of the number n
    factor_list = []
    for i in range(2, math.ceil(math.sqrt(n))):
        if (n % i) == 0:
            factor_list.append(i)
            factor_list.append(int(n / i))
    return factor_list

def find_x(c, d, e): # cx mod d = e
    for x in range(1, d):
        if (c*x % d) == e:
            return x
    return 0

def find_y(n, d): # (n/d)y mod d = 1
    for y in range(1, d):
        if int((n/d)*y % d) == 1:
            return y
    return 0
if __name__ == "__main__":
    c = input("Input the constant of x: ")
    n = input("Input the modulus: ")
    e = input("Input the rhs of the equation: ")
    print("The inverse is: {}".format(chinese_rem(int(c), int(n), int(e))))
