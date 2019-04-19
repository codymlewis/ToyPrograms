# board.py - c4Menace
# code to define the connect 4 board
# Author: Cody M. Lewis
# Date:   24-NOV-2017
# Mod.:   27-JAN-2018
import piece
class board:
   # constructor
   def __init__(self):
      self.columns  = 7
      self.rows     = 6
      self.boardArr = [] # this matrix will be notated row then column
      for k in range(0,self.rows):
         self.boardArr.append([])
      for i in range(0,self.rows):
         for j in range(0,self.columns):
            self.boardArr[i].append(piece.piece())
      self.memory = [] # a list (used as stack) which stores the moves played

   # Queries
   def getRows(self):
      return self.rows

   def getColumns(self):
      return self.columns

   # toString: outputs the board as a nice to read string
   def toString(self):
      x = ""
      for i in range(0,self.rows):
         for j in range(0,self.columns):
            if(self.boardArr[i][j].isEmpty()):
               x = x + "| "
            else:
               x = x + "|" + self.boardArr[i][j].getType()
         x = x + "|"
         x = x + "\n"
      return x

   def columnFull(self,column):
       if(self.boardArr[0][column].isEmpty()):
          return False
       else:
          return True

   # Mutators
   def falling(self,row,column,pType):
      if(row == (self.rows-1) and self.boardArr[row][column].isEmpty()):
         self.setPiece(row,column,pType)
         return True
      elif(row == self.rows-1):
         return False
      else:
         if(self.boardArr[row+1][column].isEmpty()):
            return self.falling(row+1,column,pType)
         else:
            self.setPiece(row,column,pType)
            return True

   def setPiece(self,row,column,pType):
      if(pType == 'R'):
         self.boardArr[row][column].setRed()
      else:
         self.boardArr[row][column].setBlue()
      self.memory.append(column)

   def undo(self):
      # refer to memory variable to undo the most recent move
      column = self.memory.pop()
      row = 0
      if(self.boardArr[row][column].isEmpty()):
         while self.boardArr[row][column].isEmpty():
            row = row+1
      self.boardArr[row][column].setEmpty()
   #Connection Functions

   # The controller
   def connectFour(self,row,column,pType):
      if(self.boardArr[row][column].getType() == pType):
         if((row+1 < self.rows) and (self.boardArr[row+1][column].getType() == pType)):
            end = self.connectDown(row+1,column,pType,2) # 2 pieces are already shown to be matching
            if(end):
               return end
         if((column+1 < self.columns) and (self.boardArr[row][column+1].getType() == pType)):
            end = self.connectRight(row,column+1,pType,2)
            if(end):
               return end
         if((row+1 < self.rows) and (column+1 < self.columns) and (self.boardArr[row+1][column+1].getType() == pType)):
            end = self.connectDiaRight(row+1,column+1,pType,2)
            if(end):
               return end
         if((row+1 < self.rows) and (column-1 > -1) and (self.boardArr[row+1][column-1].getType() == pType)):
            end = self.connectDiaLeft(row+1,column-1,pType,2)
            if(end):
               return end
      if(column+1 < self.columns):
         return self.connectFour(row,column+1,pType)
      elif(row+1 < self.rows):
         return self.connectFour(row+1,0,pType)
      else:
         return False

   # direction checks
   def connectDown(self,row,column,pType,count):
      if(count == 4):
         return True
      if((row+1 < self.rows) and self.boardArr[row+1][column].getType() == pType):
          if(count+1 == 4):
             return True
          return self.connectDown(row+1,column,pType,count+1)
      else:
         return False

   def connectRight(self,row,column,pType,count):
      if(count == 4):
         return True
      if((column+1 < self.columns) and self.boardArr[row][column+1].getType() == pType):
         if(count+1 == 4):
            return True
         return self.connectRight(row,column+1,pType,count+1)
      else:
         return False

   def connectDiaRight(self,row,column,pType,count): # diagonal checks are broken somewhere, sometimes counts 3 as a connect four
      if(count == 4):
         return True
      if((row+1 < self.rows) and (column+1 < self.columns) and self.boardArr[row+1][column+1].getType() == pType):
         if(count+1 == 4):
            return True
         return self.connectDiaRight(row+1,column+1,pType,count+1)
      else:
         return False

   def connectDiaLeft(self,row,column,pType,count): # maybe try up
      if(count == 4):
         return True
      if((row+1 < self.rows) and (column-1 >= 0) and self.boardArr[row+1][column-1].getType() == pType):
         if(count+1 == 4):
            return True
         return self.connectDiaLeft(row+1,column-1,pType,count+1)
      else:
         return False
