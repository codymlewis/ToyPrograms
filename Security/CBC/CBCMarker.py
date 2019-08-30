#!/usr/bin/env python3

from Crypto.Cipher import AES  # pip install --user pycrypto
import argparse

'''
AES 128 in CBC mode

Author: Cody Lewis
Date: 2019-08-27
'''

def CBC(plaintext, IV, key, verbose, marking):
    marks = 0
    if key is not None:
        cipher = AES.new(key)
    blockin = bytes.fromhex(
        hex(int(plaintext[0:16].hex(), 16) ^ int(IV.hex(), 16))[2:].zfill(32)
    )
    if verbose or not marking:
        print(f"P1 XOR IV = {plaintext[0:16].hex()} XOR {IV.hex()} = {blockin.hex()}")
    if marking:
        answer = input("Input round 1 XOR or nil: ")
        if answer != "nil":
            marks += 1 if answer.zfill(32).lower() == blockin.hex().zfill(32) else 0.5
    if key is not None:
        blockout = cipher.encrypt(blockin)
        ciphertext = blockout
        if marking:
            answer = input("Input round 1 encrypt or nil: ")
            if answer != "nil":
                marks += 1 if answer.zfill(32).lower() == blockout.hex().zfill(32) else 0.5
        if verbose or not marking:
            print(f"C1 = {ciphertext.hex()}")
    else:
        ciphertext = blockin
    for i in range(1, 4):
        blockin = bytes.fromhex(
            hex(
                int(plaintext[i * 16:i * 16 + 16].hex(), 16) ^ \
                int(ciphertext[(i - 1) * 16:(i - 1) * 16 + 16].hex(), 16)
            )[2:].zfill(32)
        )
        if verbose or not marking:
            print(f"P{i + 1} XOR C{i} = {plaintext[i * 16:i * 16 + 16].hex()} XOR {ciphertext[(i - 1) * 16:(i - 1) * 16 + 16].hex()} = {blockin.hex()}")
        if marking:
            answer = input(f"Input round {i + 1} XOR or nil: ")
            if answer != "nil":
                marks += 1 if answer.zfill(32).lower() == blockin.hex().zfill(32) else 0.5
        if key is not None:
            blockout = cipher.encrypt(blockin)
            if verbose or not marking:
                print(f"C{i + 1} = {blockout.hex()}")
            if marking:
                answer = input(f"Input round {i + 1} encrypt or nil: ")
                if answer != "nil":
                    marks += 1 if answer.zfill(32).lower() == blockout.hex().zfill(32) else 0.5
        else:
            blockout = blockin
        ciphertext += blockout
    return marks


def hexinput(message, length):
    user_input = input(message)
    if user_input == "nil":
        return None
    return bytes.fromhex(user_input.zfill(length))

if __name__ == '__main__':
    PARSER = argparse.ArgumentParser(description='Perform AES CBC.')
    PARSER.add_argument('-v', '--verbose', dest='verbose', action='store_const',
                    const=True, default=False,
                    help='Make the program verbose')
    PARSER.add_argument('-m', '--mark', dest='mark', action='store_const',
                        const=True, default=False,
                        help='Operate program in marking mode')
    ARGS = PARSER.parse_args()

    PLAINTEXT = hexinput("Input the whole plaintext: ", 128)
    KEY = hexinput("Input the key or nil: ", 32)
    IV = hexinput("Input the IV: ", 32)
    print(f"{CBC(PLAINTEXT, IV, KEY, ARGS.verbose, ARGS.mark)} acheived")
