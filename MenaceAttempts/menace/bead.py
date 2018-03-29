# bead.py - menace
# Bead object for the menace matchboxes
# Author: Cody M. Lewis
# Date:   09-JAN-2018
# Mod.:   09-JAN-2018
class bead:
   # Constructor
   def __init__(self,newNum):
      self.num = int(newNum)

   # Query
   def getNum(self):
      return self.num
