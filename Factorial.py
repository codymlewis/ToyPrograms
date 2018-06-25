# An O(log(n)) factorial definition
def fact(lower, upper):
    if (upper - lower) % 2 is 0:
        upper_bound_add = 1 # so the same number is not counted twice
    else:
        upper_bound_add = 0
    if lower == upper:
        return upper
    elif upper == lower + 1:
        return lower * upper
    elif upper == lower + 2:
        return lower * fact(lower + 1, upper)
    return fact(lower, lower + int((upper - lower) / 2)) * fact((upper - int((upper - lower) / 2) + upper_bound_add), upper)

def factorial(number):
    if number is 0:
        return 1 # special case
    elif number < 0:
        return 0 # case of invalid input
    return fact(1, number)

if __name__ == "__main__":
    INPUT_NUM = int(input("Enter the number you want to find the factorial of: "))
    FACTORIAL = factorial(INPUT_NUM)
    if FACTORIAL > 0:
        print("{}! = {}".format(INPUT_NUM, factorial(INPUT_NUM)))
    else:
        print("Your input was invalid")
