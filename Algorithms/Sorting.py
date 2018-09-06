from random import randint
def insertion_sort(arr):
    '''
        Insertion sort algorithm O(n^2)
    '''
    arr_cp = list()
    for item in arr:
        i = 0
        while (i < len(arr_cp)) and (item > arr_cp[i]):
            i += 1
        if i == len(arr_cp):
            arr_cp.append(item)
        else:
            arr_cp.insert(i, item)
    return arr_cp

def bubble_sort(arr):
    '''
        Bubble sort algorithm O(n^2)
    '''
    for i in range(len(arr)):
        cp = arr[i]
        del(arr[i])
        insert = False
        for j in range(len(arr)):
            if cp < arr[j]:
                arr.insert(j, cp)
                insert = True
                break
        if not insert:
            arr.append(cp)

def merge(arr, i, m, j):
    '''
        Merge operation
    '''
    p = i
    r = i
    q = m + 1
    cp = list(n for n in range(len(arr)))
    while (p <= m) and (q <= j):
        if arr[p] <= arr[q]:
            cp[r] = arr[p]
            p += 1
        else:
            cp[r] = arr[q]
            q += 1
        r += 1
    while p <= m:
        cp[r] = arr[p]
        p += 1
        r += 1
    while q <= j:
        cp[r] = arr[q]
        q += 1
        r += 1
    for r in range(i, j + 1):
        arr[r] = cp[r]

def sort(arr, i, j):
    '''
        Divide and conquer sort
    '''
    if i == j:
        return
    m = int((i + j) / 2)
    sort(arr, i, m)
    sort(arr, m + 1, j)
    merge(arr, i, m, j)

def merge_sort(arr):
    '''
        Merge sort algorithm O(nlgn)
    '''
    sort(arr, 0, len(arr) - 1)

if __name__ == "__main__":
    ARR = [randint(0, 500) for n in range(50)]
    print("Insertion sorting list: {}".format(ARR))
    ARR = insertion_sort(ARR)
    print("List sorted to: {}".format(ARR))
    ARR = [randint(0, 500) for n in range(50)]
    print("Merge sorting list: {}".format(ARR))
    merge_sort(ARR)
    print("List sorted to: {}".format(ARR))
    ARR = [randint(0, 500) for n in range(50)]
    print("Bubble sorting list: {}".format(ARR))
    bubble_sort(ARR)
    print("List sorted to: {}".format(ARR))