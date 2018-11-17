import sys
'''
Text searching program based on the Knuth-Morris-Pratt Algorithm.
'''
def shift(p):
    m = len(p)
    shift_arr = [0 for _ in range(m)]
    shift_arr[0] = 1
    i = 1
    j = 0
    while (i + j) < m:
        if p[i + j] == p[j]:
            shift_arr[i + j] = i
            j += 1
        else:
            if j == 0:
                shift_arr[i] = i + 1
            j_index = j - 1 if j > 0 else 0
            i += shift_arr[j_index]
            j = max(j - shift_arr[j_index], 0)
    return shift_arr


def search(pattern, text):
    m = len(pattern)
    n = len(text)
    shift_arr = shift(pattern)
    i = j = 0
    while (i + m) <= n:
        while text[i + j] == pattern[j]:
            j += 1
            if j >= m:
                return i
        j_index = j - 1 if j > 0 else 0
        i += shift_arr[j_index]
        j = max(shift_arr[j_index], 0)
    return -1


if __name__ == "__main__":
    args = sys.argv
    del(args[0])
    if len(args) < 2:
        print("Please input a pattern and file as an argument")
        print("Usage python KnuthMorrisPratt.py <pattern> <file>")
    else:
        P = args[0]
        T = ""
        with open(args[1]) as f:
            for line in f:
                T += line
        print(f"{P} appears in {args[1]} at char {search(P, T)}")
