#!/usr/bin/env python3

from Crypto.Cipher import AES  # pip install --user pycrypto
import sys

'''
AES 128 in CBC mode

Author: Cody Lewis
Date: 2019-08-27
'''

def CBC(plaintext, IV, key, verbose=False):
    marks = 0
    cipher = AES.new(key)
    blockin = bytes.fromhex(
        hex(int(plaintext[0:16].hex(), 16) ^ int(IV.hex(), 16))[2:]
    )
    if verbose:
        print(f"P1 XOR IV = {plaintext[0:16].hex()} XOR {IV.hex()} = {blockin.hex()}")
    answer = input("Input round 1 XOR or nil: ")
    if answer != "nil":
        marks += 1 if answer.zfill(32) == blockin.hex().zfill(32) else 0.5
    blockout = cipher.encrypt(blockin)
    ciphertext = blockout
    answer = input("Input round 1 encrypt or nil: ")
    if answer != "nil":
        marks += 1 if answer.zfill(32) == blockout.hex().zfill(32) else 0.5
    if verbose:
        print(f"C1 = {ciphertext.hex()}")
    for i in range(1, 4):
        blockin = bytes.fromhex(
            hex(
                int(plaintext[i * 16:i * 16 + 16].hex(), 16) ^ \
                int(ciphertext[(i - 1) * 16:(i - 1) * 16 + 16].hex(), 16)
            )[2:].zfill(32)
        )
        if verbose:
            print(f"P{i + 1} XOR C{i} = {plaintext[i * 16:i * 16 + 16].hex()} XOR {ciphertext[(i - 1) * 16:(i - 1) * 16 + 16].hex()} = {blockin.hex()}")
        answer = input(f"Input round {i + 1} XOR or nil: ")
        if answer != "nil":
            marks += 1 if answer.zfill(32) == blockin.hex().zfill(32) else 0.5
        blockout = cipher.encrypt(blockin)
        if verbose:
            print(f"C{i + 1} = {blockout.hex()}")
        answer = input(f"Input round {i + 1} encrypt or nil: ")
        if answer != "nil":
            marks += 1 if answer.zfill(32) == blockout.hex().zfill(32) else 0.5
        ciphertext += blockout
    return marks


def hexinput(message, length):
    return bytes.fromhex(input(message).zfill(length))

if __name__ == '__main__':
    PLAINTEXT = hexinput("Input the whole plaintext: ", 128)
    KEY = hexinput("Input the key: ", 32)
    IV = hexinput("Input the IV: ", 32)
    print(f"{CBC(PLAINTEXT, IV, KEY, len(sys.argv) > 1)} acheived")
