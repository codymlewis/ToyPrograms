# game.py - c4Menace
# code for the game flow of connect four
# Author: Cody M. Lewis
# Date:   4-FEB-2018
# Mod.:   7-FEB-2018
from random import randint # for generating random numbers
import board # my connect four python library 

field = board.board()   # the board object


while(True):
   print(" 1 2 3\n" + field.toString())
   rowStr    = input("row number: ")
   columnStr = input("Your turn, please type the column number to drop your piece at: ")

   column = int(columnStr) - 1
   row    = int(rowStr) - 1

   if(not field.slotFull(row,column) and field.play(row,column,'X')):
      print(field.toString())
   else:
      print("Your move did not work, please try again")
   if(field.linkThree(0,0,'X')):
      print("You win!")
      break

   column = randint(0,2)
   row    = randint(0,2)

   print("The CPU plays",(row+1),(column+1))

   if(not field.slotFull(row,column) and field.play(row,column,'O')):
      print(field.toString())

   if(field.linkThree(0,0,'O')):
      print("CPU wins!")
      break

   
