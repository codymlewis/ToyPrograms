# game.py - c4Menace
# code for the game flow of connect four
# Author: Cody M. Lewis
# Date:   4-JAN-2018
# Mod.:   5-JAN-2018
from random import randint # for generating random numbers
import board # my connect four python library 

field = board.board()   # the board object


while(True):
   print(" 1 2 3 4 5 6 7\n" + field.toString())
   columnStr = input("Your turn, please type the column number to drop your piece at: ")

   column = int(columnStr) - 1

   if(not field.columnFull(column) and field.falling(0,column,'B')):
      print(field.toString())
   else:
      print("Your move did not work, please try again")

   if(field.connectFour(0,0,'B')):
      print("You win!")
      break
   print(" 1 2 3 4 5 6 7\n" + field.toString())
   columnStr = input("Your turn, please type the column number to drop your piece at: ")

   column = int(columnStr) - 1

   if(not field.columnFull(column) and field.falling(0,column,'R')):
      print(field.toString())
   else:
      print("Your move did not work, please try again")
#   column = randint(0,6)

#   print("The CPU plays",(column+1))

#   if(not field.columnFull(column) and field.falling(0,column,'R')):
#      print(field.toString())

   if(field.connectFour(0,0,'R')):
      print("CPU wins!")
      break

   
