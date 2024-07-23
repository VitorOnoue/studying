import numpy as np

# all 0s array
print(np.zeros(5))  # 1d array, 5 elements
print(np.zeros((2,3)))    # 2d array, 6 elements - sent as a tuple

# all 1s array
print(np.ones((4,2), dtype='int32'))    # also possible to set up data type

# any other number
print(np.full((2,2), 99))   # full of 99s
a = np.array([[4,2], [3,1]])
print(a.shape)
# its possible to use .shape + .full, using full_like, or just using a.shape in full
print(np.full_like(a, 99)) # creates an array with the same shape as 'a', full of 99s
print(np.full(a.shape, 98)) # same effect

# random decimals
print(np.random.rand(4,2))  # 4 by 2 array
print(np.random.random_sample(a.shape))   # with sample .shape is accepted

# random ints
print(np.random.randint(2,4, size=(3,3)))   # fills a 3 by array with numbers from 2 (including, 0 by default) to 4 (excluding) - works with a.shape

# identity matrix (main diagonal all 1s, remaining elements are 0s)
print(np.identity(4))   # 4 by 4

# repeating array
a = np.array([1, 2, 3])
print(np.repeat(a, 3))

# challengeish
array = np.ones((5,5), dtype='int32')
for i in range(1,4):
    array[i,1:4] = 0
array[2,2] = 9
print(array)

# solution for the challengeish
array = np.ones((5,5))
replace = np.zeros((3,3))
replace[1,1] = 9
array[1:4, 1:4] = replace   # from rows 1 to 3, and columns 1 to 3, replace with the values of "replace"
print(array)

# copying
array_copy = array.copy()
print(array_copy)
# be careful when copying arrays, if what was done was array_copy = array, what really happens is that both will point to the same object (so, if you change one value of the copy, it will change the original's too)