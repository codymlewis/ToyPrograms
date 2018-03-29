# c4Perm.py - c4Menace
# code to find all of the permutation game states of connect 4
# this code takes way too long to run, ran for 24 hours, ended up only 0.004% complete
# Author: Cody M. Lewis
# Date:   05-JAN-2018
# Mod.:   02-FEB-2018
import board

field = board.board()

def permutate(turn):
   # permutation finding
#   print(field.toString())
   if(field.connectFour(0,0,'B') or field.connectFour(0,0,'R')):
#      print('Connect Four!')
      return turn
   if(turn%2 == 0):
      pType = 'R'
   else:
      pType = 'B'
   arr = []   
   for i in range(0,7):
      if(field.columnFull(i)):
         continue
      if(field.falling(0,i,pType)):
         mem.append(i)
         permutate(turn+1)
         mem.pop()
         field.undo()
      for j in range(0,7):
         if(field.columnFull(j)):
            continue
         else:
            arr.append(j)
#            arr.append(j)
#            arr.append(j)
   # file Output
   if(pType=='B'):
      memString = ''
      if(len(mem)>0):
         for i in range(0,len(mem)):
            memString = memString + str(mem[i])
      arrString = ''
      if(len(arr)>0):
         for i in range(0,len(arr)):
            arrString = arrString + str(arr.pop())
      f.write(memString+','+arrString+'\n') 
# program flow
f = open('connectFourPermutations.csv','w')
f.write('indexArr,arrVals\n')
mem = []
permutate(0)
f.close()
print("Output Success")
