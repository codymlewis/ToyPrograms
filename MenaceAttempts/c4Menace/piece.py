# piece.py - c4Menace
# The connect 4 piece object code
# Author: Cody M. Lewis
# Date:   23-NOV-2017
# Mod.:   27-JAN-2018
class piece:
   # constructor
   def __init__(self):
      self.type = '0'
   
   # Mutators
   def setRed(self):
      self.type = 'R'
   
   def setBlue(self):
      self.type = 'B'
   
   def setEmpty(self):
      self.type = '0'

   # Queries
   def isRed(self):
      return self.type == 'R'

   def isBlue(self):
      return self.type == 'B'

   def isEmpty(self):
      return self.type == '0'

   def getType(self):
      return self.type
# test code
#x = piece()
#print(x.type)
#x.setRed()
#print(x.isRed())
