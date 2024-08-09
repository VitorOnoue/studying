import requests # pip install requests

response = requests.get("http://randomfox.ca/floof")
print(response.status_code)
print(response.text) # this may look like a dictionary, but it returns a string
print(response.json()) # this returns an actual dictionary
fox = response.json() # dictionary
print(fox['image'])