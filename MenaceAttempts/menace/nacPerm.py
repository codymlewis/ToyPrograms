# c4Perm.py - c4Menace
# code to find all of the permutation game states of noughts and crosses
# Author: Cody M. Lewis
# Date:   05-JAN-2018
# Mod.:   02-FEB-2018
import board

field = board.board()

def permutate(turn):
   # permutation finding
#   print(field.toString())
   if(field.linkThree(0,0,'X') or field.linkThree(0,0,'O')):
      print('Three Linked!')
      return turn
   if(turn%2 == 0):
      pType = 'X'
   else:
      pType = 'O'
   arr = []   
   for i in range(0,3):
      for j in range(0,3):
         if(field.slotFull(i,j)):
            continue
         if(field.play(i,j,pType)):
            mem.append(i)
            mem.append(j)
            permutate(turn+1)
            mem.pop()
            mem.pop()
            field.undo() # issue
      for m in range(0,3):
         for n in range(0,3):
            if(field.slotFull(m,n)):
               continue
            arr.append(m)
            arr.append(n)
   # file Output
   if(pType=='O'):
      memString = ''
      if(len(mem)>0):
         for i in range(0,len(mem)):
            memString = memString + str(mem[i])
      arrString = ''
      if(len(arr)>0):
         for i in range(0,len(arr)):
            arrString = arrString + str(arr[i])
      f.write(memString+','+arrString+'\n') 
      print(field.toString())
# program flow
f = open('noughtsAndCrossesPermutations.csv','w')
f.write('indexArr,arrVals\n')
mem = []
permutate(0)
f.close()
print("Output Success")
