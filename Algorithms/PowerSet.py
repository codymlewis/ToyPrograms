'''
Power set finding program
Author: Cody Lewis
Since: 2018-09-07
'''


def power_set(s, bin_stream=""):
    '''
    Recursive backtracking algorithm to find the power sets in
    a given set
    '''
    if len(bin_stream) == len(s):
        result = "{"
        for i in range(0, len(s)):
            if(bin_stream[i] == "1"):
                result += f'{s[i]}, '
        end = -2 if len(result) > 1 else len(result)
        print(result[:end] + "}")
    else:
        power_set(s, bin_stream + "1")
        power_set(s, bin_stream + "0")


if __name__ == "__main__":
    print("Input the your n:")
    n = int(input())
    s = [i for i in range(1, n + 1)]
    print(f'Finding the power sets of {s}')
    power_set(s)
