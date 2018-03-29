# engine.py - menace
# The AI engine for the menace program
# issue: consumes a lot of ram
# Author: Cody M. Lewis
# Date:   07-FEB-2018
# Mod.:   07-FEB-2018
import matchbox
class engine:
   def __init__(self,inFile):
      self.gameLine = dict()
      self.readFile(inFile)
      print(self.gameLine())

   def readFile(self,inFile):
      with open(inFile,'r') as f:
        finArr = []
        next(f)
        for line in f:
           index = line[:line.find(',')]
           arrString = line[line.find(',')+1:]
           
           if(len(arrString)>0):
              for i in range(0,int(len(arrString)/2)):
                 currNum = int(arrString[i*2:i*2+1]) * 3 + int(arrString[i*2+1:i*2+2])
                 finArr.append(currNum)
           self.gameLine[index] = matchbox.matchbox(finArr)
