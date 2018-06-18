# An O(log(n)) factorial definition
def fact(lower, upper):
    if upper % 2 is 0:
        upper_bound_add = 2
    else:
        upper_bound_add = 1
    if lower == upper:
        return upper
    elif upper == lower + 1:
        return lower * upper
    elif upper == lower + 2:
        return lower * (lower + 1) * upper
    return fact(lower, lower + int((upper - lower + 1) / 2)) * fact(upper + upper_bound_add - int((upper - lower + 1) / 2), upper)

def factorial(number):
    return fact(1, number)

if __name__ == "__main__":
    INPUT_NUM = int(input("Enter the number you want to find the factorial of: "))
    print("{}! = {}".format(INPUT_NUM, factorial(INPUT_NUM)))
