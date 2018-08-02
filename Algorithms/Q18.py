# Program created to solve the problem:
# Locker  doors:
# There  are  n  lockers  in  a  hallway  numbered  sequentially from  1  to  n.
# Initially, all the locker doors are closed. You make n passes by the lockers, each time
# starting with locker #1. On the ith pass, i =1, 2, ..., n, you toggle the door of every ith
# locker: if the door is closed, you open it, if it is open, you close it. For example, after
# the first pass every door is open; on the second pass you only toggle the even-numbered
# lockers (#2, #4, ...) so that after the second pass the even doors are closed and the odd
# ones are opened; the third time through you close the door of locker #3 (opened from
# the  first  pass),  open  the  door  of  locker #6  (closed  from  the  second  pass),  and  so  on.
# After the last pass, whichlocker doors are open and which are closed? How many of them are open?

import sys
from math import sqrt, floor
class Door(): # used to store the doors information (state, position)
    def __init__(self, position):
        self.__state = 0
        self.__position = position

    def toggle(self):
        self.__state = (self.__state + 1) % 2

    def get_state(self):
        return self.__state

    def to_string(self):
        string = "{}".format(self.__position)
        string += ": closed, " if self.__state is 0 else ": open, "
        return string

def solve(n): # solves the door problem
    doors = [None]
    for i in range(1, n + 1):
        doors.append(Door(i))
    for i in range(1, len(doors)):
        for j in range(1, len(doors)):
            if (j % i) is 0:
                doors[j].toggle()
    # string = ""
    sigma = 0
    for i in range(1, len(doors)):
        # string += doors[i].to_string()
        sigma += doors[i].get_state()
    return "Door " + doors[N].to_string()[:-2] + "\nTotal Open: {}".format(sigma)
    # return string[:-2] + "\nTotal Open: {}".format(sigma)

def efficient_solve(n): # performs equations based on trends found in solve
    used_n = sqrt(n)
    total = floor(used_n)
    state = "open" if used_n == total else "closed"
    return "Door {}: {}\nTotal Open: {}".format(n, state, total)

if __name__ == "__main__":
    if len(sys.argv) is 2:
        N = int(sys.argv[1])
        print("Solving for {} doors".format(N))
    else:
        N = int(input("Input the number of doors you want to solve for: "))
    print("Started solving problem")
    print(efficient_solve(N))
