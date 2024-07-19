import numpy as np

# operations on arrays
array = np.array([1, 2, 3, 4, 5])
print(array + 3)    # adds 3 to all elements in the array
# this also works
array += 3
print(array)
# it applies to all operations

# arrays + arrays
array -= 3
array2 = np.array([1, 2, 3, 4, 5])
print(array + array2)


# linear algebra

# matrix multiplication
print(np.matmul(array, array2))

# getting the determinant
print(np.linalg.det(np.identity(3)))   # the determinant of an identity matrix is 1


# "statistics"

# min/max of an array
print(np.max(array))
print(np.min(array2))

# sum of an array
print(np.sum(array))