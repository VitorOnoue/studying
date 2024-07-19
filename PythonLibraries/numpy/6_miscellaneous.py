import numpy as np
from pathlib import Path

ROOT_PATH = Path(__file__).parent

# load data from file
array = np.genfromtxt(ROOT_PATH/'miscellaneous.txt', delimiter=',')
print(array)

# boolean masking
print(array > 5)    # returns true/false for all elements greater than 5
print(array[array > 5]) # returns all elements greater than 5

# advanced indexing
print(array[[0, 1], [2, 4]])    # returns a[0, 2] and a[2,4]