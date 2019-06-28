#!/usr/bin/env python3

import argparse
import turtle
import tkinter

from PIL import Image

'''
Draw a square fractal using turtle

Author: Cody Lewis
Date: 2019-04-19
'''


SPEED = 0


class ThisTurtle(turtle.Turtle):
    '''
    Set up the turtle that draws a specific fractal.
    '''
    def __init__(self, goal):
        super(ThisTurtle, self).__init__()
        self.color("green")
        self.speed(SPEED)
        self.goal = goal
        self.forward_dist = 3
        self.hideturtle()

    def draw(self):
        '''Draw the fractal, first generation defines general shape'''
        omega(self, goal=self.goal)
        for _ in range(11):
            self.right(30)
            omega(self, goal=self.goal)


def omega(cur_turtle, level=1, goal=0):
    '''Recursively draw proceeding generations'''
    if level == goal:
        cur_turtle.forward(cur_turtle.forward_dist)
    else:
        omega(cur_turtle, level + 1, goal)
        cur_turtle.right(90)
        omega(cur_turtle, level + 1, goal)
        cur_turtle.left(90)
        omega(cur_turtle, level + 1, goal)
        cur_turtle.left(90)
        omega(cur_turtle, level + 1, goal)
        cur_turtle.right(90)
        omega(cur_turtle, level + 1, goal)
        # cur_turtle.left(90)
        # omega(cur_turtle, level + 1, goal)
        # cur_turtle.left(90)
        # omega(cur_turtle, level + 1, goal)
        omega(cur_turtle, level + 1, goal)


if __name__ == '__main__':
    PARSER = argparse.ArgumentParser(description="Draw a square fractal using turtle.")
    PARSER.add_argument(dest="goal", metavar="GOAL", action="store", nargs="?", type=int, default=1,
                        help="The number of generations of the fractal")

    ARGS = PARSER.parse_args()
    GOAL = ARGS.goal
    if GOAL < 1:
        GOAL = 1
    print(f"Drawing a fractal with {GOAL} generation{(lambda: 's' if GOAL > 1 else '')()}")
    TURTLE = ThisTurtle(GOAL)
    TURTLE.draw()
    print("Saving fractal...")
    TURTLE.getscreen().getcanvas().postscript(file='fractal.ps')
    IMG = Image.open("fractal.ps")
    IMG.save("fractal.png")
    try:
        turtle.exitonclick()
        turtle.mainloop()
    except turtle.Terminator:
        print("bye.")
