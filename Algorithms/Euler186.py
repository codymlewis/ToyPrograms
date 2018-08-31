'''
    Caller(n) = S_{2n - 1}, Called(n) = S_{2n}, if Caller(n) = Called(n) then there is a misdial
    any pair of users X and Y are friend if X called Y or vice-versa a user Z is a friend of a
    friend of X if Z is friend of Y
    Author: Cody Lewis
    Since: 2018-08-29
'''
def lagged_fibonacci(k, fib_nums=[]):
    '''
        A lagged fibonacci generator function
    '''
    if len(fib_nums) < k:
        for i in range(len(fib_nums) + 1, k + 1):
            if i >= 1 and i <= 55:
                fib_nums.append((100003 - 200003 * i + 300007 * i**3) % 1000000)
            elif i >= 56:
                fib_nums.append((fib_nums[i - 24] + fib_nums[i - 55]) % 1000000)
    return fib_nums[k - 1]

class DisSet():
    '''
        Disjoint set class
    '''
    def __init__(self):
        '''
            Initialize with the maximum number of nodes (determined by mod on LFG)
        '''
        self.nodes = [DisSet.Node() for i in range(1000000)]
    def friend(self, lhs, rhs):
        '''
            Assign lhs as a friend of rhs
        '''
        self.nodes[lhs].add(self.nodes[rhs])
    def find_size(self, num):
        '''
            Find connectedness size of the speified node number
        '''
        current = self.nodes[num]
        while current.parent != current: # got to root node
            current = current.parent
        if self.nodes[num] != current: # optimize path for future calls
            self.nodes[num].parent = current
        return current.size
    class Node():
        '''
            A node of the disjoint set
        '''
        def __init__(self):
            '''
                Initialize a node as connected to itself
            '''
            self.size = 1
            self.parent = self
        def add(self, other):
            '''
                Connect this node to another node
            '''
            this = self
            while this.parent != this: # shorten path to parent
                this = this.parent
            if self.parent != this:
                self.parent = this
            og_other = other
            while other.parent != other:
                other = other.parent
            if og_other.parent != other:
                og_other.parent = other
            if this == other: # stop random resets
                return
            other.parent = this
            this.size += other.size
            other.size = 0

PM_NUMBER = 524287 # constant for the number of the prime minister

def find_friends():
    '''
        Solves the Project Euler question 186
    '''
    fib_nums = [] # memoize the LF numbers
    users = DisSet()
    k = 1
    result = 0
    while users.find_size(PM_NUMBER) < 990000:
        caller = lagged_fibonacci(2 * k - 1, fib_nums)
        called = lagged_fibonacci(2 * k, fib_nums)
        if caller == called: # no through call
            k += 1 # increment still, but not the answer
            continue
        users.friend(caller, called)
        result += 1
        k += 1
    return result

if __name__ == "__main__":
    print("Started calculating...")
    print("99% or more users are connected by friends with the PM at k = {}".format(find_friends()))