# matchbox.py - menace
# The matchbox object for menace
# Author: Cody M. Lewis
# Date:   09-JAN-2018
# Mod.:   11-JAN-2018
from random import randint
import bead
class matchbox:
   def __init__(self,arr):
      self.beads = []
      for i in range(0,len(arr)):
         if(arr[i] == -1):
            continue
         else:
            self.beads.append(bead.bead(arr[i]))

   def length(self):
      return len(self.beads)

   def takeBead(self):
      if(self.length() < 1):
         return False
      else:
         self.memory = randint(0,self.length()-1) 
         return self.beads[self.memory].getNum()

   def removeBead(self):
      del self.beads[self.memory]

   def addBead(self):
      beadNum = self.beads[self.memory].getNum()
      self.beads.append(bead.bead(beadNum))
