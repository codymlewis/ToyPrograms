#!/usr/bin/env python3

'''
A simple CFB moded block cipher.

Author: Cody Lewis
Date: 2019-08-16
'''

def encipher(text, key):
    '''The cipher block.'''
    return bin(text ^ key)[2:]

def xor(a, b):
    '''XOR 2 binary strings.'''
    result = ""
    for i, j in zip(a, b):
        if i == j:
            result += "0"
        else:
            result += "1"
    return result

def encrypt(plaintext, key, IV, k):
    '''Encrypt using CFB mode with self-syncronous shift.'''
    cur_cipher = ""
    ciphertext = ""
    shift = bin(IV)[2:]
    for i in range(0, len(plaintext), k):
        cur_cipher = xor(encipher(int(shift, 2), key)[:k], plaintext[i:i + k])
        ciphertext += cur_cipher
        shift = bin(((int(shift, 2) << k) + int(cur_cipher, 2)) % (2**16))[2:]
    return process_output(ciphertext)

def decrypt(ciphertext, key, IV, k):
    '''Decrypt using CFB mode with self-syncronous shift.'''
    plaintext = ""
    shift = bin(IV)[2:]
    for i in range(0, len(ciphertext), k):
        plaintext += xor(encipher(int(shift, 2), key)[:k], ciphertext[i:i + k])
        shift = bin(
            ((int(shift, 2) << k) + int(ciphertext[i:i + k], 2)) % (2**16)
        )[2:]
    return process_output(plaintext)


def process_input(text):
    '''Make the input suitable for the encrypt/decrypt.'''
    bin_text = bin(int(text, 16))[2:]
    return bin_text.zfill(len(bin_text) + ((16 - len(bin_text)) % 16))

def process_output(text):
    '''Make the output suitable for printing.'''
    return hex(int(text, 2))[2:]

if __name__ == '__main__':
    IV = input("Input your IV: ")
    OG_IV = IV
    IV = int(IV[:4], 16)
    KEY = input("Input your key: ")
    OG_KEY = KEY
    KEY = int(KEY[:4], 16)
    PLAINTEXT = input("Input your plaintext: ")
    K = int(input("Input your shift bits: "))
    print()
    print(f"IV:\t\t{OG_IV}")
    print(f"Key:\t\t{OG_KEY}")
    CIPHERTEXT = encrypt(process_input(PLAINTEXT), KEY, IV, K)
    print(f"Ciphertext:\t{CIPHERTEXT}")
    print(f"Plaintext:\t{decrypt(process_input(CIPHERTEXT), KEY, IV, K)}")
