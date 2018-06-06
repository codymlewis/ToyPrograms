# One time pad encryption/decryption program 
# Only supports lower case messages with no spaces
# Author: Cody Lewis
# Version: 2.0
# Since: 22-APR-2018
def encrypt(message):
    import random
    cipher = ""
    key = []
    for i in range(0,len(message)):
        key.append(random.randint(0,25))
        cipher = cipher + chr(((ord(message[i]) - 97 + key[i]) % 26) + 97) # C = M + K
    return cipher,key

def decrypt(cipher, key):
    message = ""
    for i in range(0,len(key)):
        message = message + chr(((ord(cipher[i]) - 97 - key[i]) % 26) + 97) # M = C - K = M + K - K
    return message

if __name__ == "__main__":
    import sys
    import json
    if len(sys.argv) is not 1:
        try:
            args = sys.argv
            del args[0]
            for i in range(0, len(args)):
                arg = args[i]
                if arg[0] == "-": # a flag identifier
                    flags = {"encrypt" : False, "decrypt" : False, "readFile" : False, "file":"", "file1":""}
                    for j in range(1, len(arg)):
                        if arg[j] == "e":
                            flags["encrypt"] = True
                        elif arg[j] == "d":
                            flags["decrypt"] = True
                        elif arg[j] == "f":
                            flags["readFile"] = True
                    if flags["readFile"]:
                        if(flags["encrypt"] or flags["decrypt"]):
                            flags["file"] = args[i+1]
                            i += 1
                            if flags["decrypt"]:
                                flags["file1"] = args[i+1]
                                i += 1
                        else:
                            print("Nothing to do with the file")
                            break
                    if flags["readFile"]:
                        if flags["encrypt"]:
                            message = ""
                            with open(flags["file"], "r") as f:
                                for line in f:
                                    message = message + line
                            cipher, key = encrypt(message)
                            print("Encrypted message")
                            with open(flags["file"][0:flags["file"].find(".")]+"-cipher.txt", "w") as f:
                                f.write(cipher)
                                print("Wrote cipher to {}".format(f.name))
                            with open(flags["file"][0:flags["file"].find(".")]+"-cipher-key.json", "w") as f:
                                json.dump(key,f)
                                print("Dumped key to {}".format(f.name))
                        elif(flags["decrypt"]):
                            cipher = ""
                            with open(flags["file"],"r") as f:
                                for line in f:
                                    cipher = cipher + line
                            key = []
                            with open(flags["file1"],"r") as f:
                                key = json.load(f)
                            message = decrypt(cipher,key)
                            print("Decrypted message")
                            with open(flags["file"][0:flags["file"].find(".")]+"-message.txt","w") as f:
                                f.write(message)
                                print("Wrote message to {}".format(f.name))
                    else:
                        if(flags["encrypt"]):
                            message = args[i+1]
                            i+=1
                            cipher,key = encrypt(message)
                            print("Cipher:{}\nKey:{}".format(cipher,key))
                        elif(flags["decrypt"]):
                            cipher = args[i+1]
                            keyStr = args[i+2]
                            keyStr = keyStr[1:len(keyStr)-1]
                            key = keyStr.split(",")
                            for k in range(0,len(key)):
                                key[k] = int(key[k])
                            i+=2
                            message = decrypt(cipher,key)
                            print("Message:{}".format(message))
        except(Exception):
            print("Invalid arguments, need a message for encryption or a cipher and key for decryption.\nYou may need to remove the spaces for the key.")
    else:
        print("Not enough arguments")
