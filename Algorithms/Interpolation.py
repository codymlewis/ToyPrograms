from math import ceil


def search(arr, val):
    '''
        An interpolation search
    '''
    index = int(ceil(val * (len(arr) - 1) / (arr[len(arr) - 1] - arr[0]))) - 1
    if(val == arr[index]):
        return index
    else:
        crement = 1 if val > arr[index] else -1
        while (((index > 0) and (index < len(arr))) or val != arr[index]):
            index += crement
        return index
