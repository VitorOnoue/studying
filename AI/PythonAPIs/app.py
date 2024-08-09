# api = "place" to allow the usage of resources
# 1 goal - create an api to create, update and read books
# 2 url base - localhost.com, for example
# 3 endpoint
    # - localhost/books (GET)
    # - localhost/books/id (GET)
    # - localhost/books/id (PUT)
    # - localhost/books/id (DELETE)
    # - localhost/books (POST)
# 4 resources - books

from flask import Flask, jsonify, request
# import csv
from pathlib import Path

ROOT_PATH = Path(__file__).parent

app = Flask(__name__)

books = [
    {"id":1,
     "title":"Lord of the Rings - The Fellowship of the Ring",
     "author":"J.R.R Tolkien"
    },
    {"id":2,
     "title":"Harry Potter and the Philosopher's Stone",
     "author":"J.K. Rowling"
    },
    {"id":3,
     "title":"Atomic Habits",
     "author":"James Clear"
    },
]

@app.route("/")
def greetings():
    print("It's better to use postman to create the requests for APIs")
    print("methods:")
    print("GET = /books - returns all books")
    print("GET = /books/id - returns the book that has the id sent as parameter")
    print("PUT = /books/id - updates information of the book that has the id sent as parameter, information sent in the body")
    print("POST = /books - creates a book with the information sent in the body, returns all books")
    print("DELETE = /books/id - deletes the book with the id specified")

@app.route("/books", methods=["GET"])
def get_books():
    return jsonify(books)

@app.route("/books/<int:id>", methods=["GET"])
def get_book_by_id(id):
    for book in books:
        if book.get("id") == id:
            return jsonify(book)
        
@app.route("/books/<int:id>", methods=["PUT"])
def update_book_by_id(id):
    updated_book = request.get_json()
    for book in books:
        if book.get("id") == id:
            book.update(updated_book)
            return jsonify(book)

@app.route("/books", methods=["POST"])
def create_book():
    new_book = request.get_json()
    books.append(new_book)

    return jsonify(books)

@app.route("/books/<int:id>", methods=["DELETE"])
def delete_book(id):
    for index, book in enumerate(books):
        if book.get("id") == id:
            del books[index]
    return jsonify(books)

app.run(port=5000,host='localhost',debug=True)