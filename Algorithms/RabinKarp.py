import sympy
import sys
'''
An implementation of the Rabin-Karp text searching algorithm.
'''
def search(pattern, text):
    '''
    Search for the pattern within the text.
    '''
    m = len(pattern)
    n = len(text)
    q = sympy.prime(m)  # Approximately m*lg(m)
    r = 2**(m - 1) % q
    f = [0]
    pfinger = 0
    for j in range(0, m):
        f[0] = (2 * f[0] + ord(text[j])) % q
        pfinger = (2 * pfinger + ord(pattern[j])) % q
    i = 0
    while (i + m) < n:
        if f[i] == pfinger:
            if text[i : (i + m)] == pattern:
                return i
        f.append((2 * (f[i] - r * ord(text[i])) + ord(text[i + m])) % q)
        i += 1
    return -1


if __name__ == "__main__":
    args = sys.argv
    del(args[0])
    if len(args) < 2:
        print("Please input a pattern and file as an argument")
        print("Usage python RabinKarp.py <pattern> <file>")
    else:
        P = args[0]
        T = ""
        with open(args[1]) as f:
            for line in f:
                T += line
        print(f"{P} appears in {args[1]} at char {search(P, T)}")
