import numpy as np

before = np.array([[1, 2, 3, 4], [5, 6, 7, 8]])
print(before.shape)

after = before.reshape(8,1)
print(after)
# just make sure the size contains the same amount of elements

# vertically stacking
a1 = np.array([1, 2, 3, 4])
a2 = np.array([5, 6, 7, 8])

print(np.vstack([a1, a2]))
# sizes cant be different

# horizontally stacking
print(np.hstack([a1,a2]))
# just like an append would do, but this works with x dimensions too:
a1 = np.vstack([a1,a1]) # contains 1,2,3,4 - 1,2,3,4
a2 = np.vstack([a2,a2]) # contains 5,6,7,8 - 5,6,7,8
print(np.hstack([a1, a2]))  # contains 1 to 8 - 1 to 8