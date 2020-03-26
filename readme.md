Here are all endpoints with examples;

To get book list (GET):<br>
localhost:8080/books/list
<br>
====================================<br>
To add book (POST):<br>
localhost:8080/books/addBook

Example:
{
	"author": "author",
	"name": "name",
	"barcode": "barcode",
	"price": 10.25,
	"quantity": 10
}
<br>
====================================<br>
To add journal (POST):<br>
localhost:8080/books/addJournal

Example:
{
	"author": "author",
	"name": "name",
	"barcode": "barcode",
	"price": 10.25,
	"quantity": 10,
	"scienceIndex": 5
}
<br>
====================================<br>
To add antique book (POST):<br>
localhost:8080/books/addAntiqueBook

Example:
{
	"author": "author",
	"name": "name",
	"barcode": "barcode",
	"price": 10.25,
	"quantity": 10,
	"year": 2000
}
<br>
====================================<br>
Get book by barcode (GET):<br>
localhost:8080/books/getBook?barcode=444
<br>
====================================<br>
To update book (PUT):<br>
localhost:8080/books/updateBook?barcode=111

Example:
{
	"author": "newAuthor",
	"name": "newName",
	"price": 100,
	"quantity": 100,
}
<br>
====================================<br>
To get total prices (GET):<br>
localhost:8080/books/getTotalPrice

Example:
[
    {
        "barcode": "barcode1"
    },
    {
        "barcode": "barcode2"
    }
]