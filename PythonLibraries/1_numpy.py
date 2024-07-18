import numpy as np

# initializing arrays
array = np.array([1, 2, 3])
print(array)
array_2d = np.array([[4.0, 5.0, 6.0], [7.0, 8.0, 9.0]])
print(array_2d)

# getting dimension of the array
print(f"{array.ndim} dimensions")
print(f"{array_2d.ndim} dimensions")

# getting shape of the array
print(array.shape) # returns 3 - as there are 3 rows, theoretically
print(array_2d.shape) # returns 2, 3 - as there are 2 rows and 3 columns

# getting data type of the array
print(array.dtype) # expected a int32, but its returning int64
print(array_2d.dtype)

# its possible to specify the data type in the .array method
array = np.array([1, 2, 3], dtype='int32')
print(array.dtype)