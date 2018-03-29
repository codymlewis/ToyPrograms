# piece.py - menace
# The noughts and crosses piece object code
# Author: Cody M. Lewis
# Date:   05-FEB-2018
# Mod.:   05-FEB-2018
class piece:
   # constructor
   def __init__(self):
      self.type = '0'
   
   # Mutators
   def setNought(self):
      self.type = 'O'
   
   def setCross(self):
      self.type = 'X'
   
   def setEmpty(self):
      self.type = '0'

   # Queries
   def isNought(self):
      return self.type == 'O'

   def isCross(self):
      return self.type == 'X'

   def isEmpty(self):
      return self.type == '0'

   def getType(self):
      return self.type
