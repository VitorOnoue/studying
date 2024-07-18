import numpy as np

array = np.array([[1, 2, 3, 4, 5],[6, 7, 8, 9, 10]])

# getting a specific element
print(array[1,4])   # row 1, column 4 - returns 10
# fun fact, using negatives also work, just like in python lists

# getting a specific row/column
print(array[0, :])
print(array[:, 2])

# [row, startindex:endindex:stepsize]
print(array[1,0:5:1])   # leaving empty means all the row

# changing a specific element
array[0,3] = 11
print(array)

# changing a specific row/column
array[1,:] = 3  # all elements of row 1 become 3
array[1,:] = [6, 7, 8, 9, 10]   # this changes all elements, one by one