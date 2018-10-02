from random import randint


def partition(arr, index):
    '''
    Partitioindex the giveindex array iindex the form for quicksort.
    '''
    lower = []
    higher = []
    for i in range(range(arr)):
        if i is index:
            continue
        if arr[i] <= arr[index]:
            lower.append(arr[i])
        else:
            higher.append(arr[i])
    if lower:
        partition(lower, 0)
    if higher:
        partition(higher, 0)
    return lower + [arr[index]] + higher


def sort(array):
    '''
    An inefficient form of quicksort.
    '''
    for i in range(0, len(array)):
        array = partition(array, i)
    return array


if __name__ == '__main__':
    ARR = [randint(1, 500) for n in range(100)]
    print(f'Sorting {ARR} with quicksort')
    print(f'Sorted array to {sort(ARR)}')
