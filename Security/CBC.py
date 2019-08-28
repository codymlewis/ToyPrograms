#!/usr/bin/env python3

from Crypto.Cipher import AES  # pip install --user pycrypto

'''
AES 128 in CBC mode

Author: Cody Lewis
Date: 2019-08-27
'''

def CBC(plaintext, IV, key):
    cipher = AES.new(key)
    blockin = bytes.fromhex(
        hex(int(plaintext[0:16].hex(), 16) ^ int(IV.hex(), 16))[2:]
    )
    print(f"P1 XOR IV = {plaintext[0:16].hex()} XOR {IV.hex()} = {blockin.hex()}")
    ciphertext = cipher.encrypt(blockin)
    print(f"C1 = {ciphertext.hex()}")
    for i in range(1, 4):
        blockin = bytes.fromhex(
            hex(
                int(plaintext[i * 16:i * 16 + 16].hex(), 16) ^ \
                int(ciphertext[(i - 1) * 16:(i - 1) * 16 + 16].hex(), 16)
            )[2:].zfill(32)
        )
        print(f"P{i + 1} XOR C{i} = {plaintext[i * 16:i * 16 + 16].hex()} XOR {ciphertext[(i - 1) * 16:(i - 1) * 16 + 16].hex()} = {blockin.hex()}")
        blockout = cipher.encrypt(blockin)
        print(f"C{i + 1} = {blockout.hex()}")
        ciphertext += blockout


def hexinput(message):
    return bytes.fromhex(input(message))

if __name__ == '__main__':
    PLAINTEXT = hexinput("Input the whole plaintext: ")
    KEY = hexinput("Input the key: ")
    IV = hexinput("Input the IV: ")
    CBC(PLAINTEXT, IV, KEY)
