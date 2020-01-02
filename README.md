# cz-lgbt-books-be

Backend of website for book database of LGBT literature in Czech language.

## REST API Documentation

- [Common](#common)
- [Books](#books)
    - [Total Books](#total-books)
    - [Show List Of Books](#show-list-of-books)
    - [Book Details](#book-details)

### Common

##### Error Responses
 
__Condition__: Generic server error

__Code__: `500 INTERNAL SERVER ERROR`

__Content example__: 

```json
{
  "detail": "Connection to database has failed"
}
```

### Books

#### Total Books

Returns total number of books in database.

__URL__: `/api/books/total`

__Method__: `GET`

##### Success Responses

__Condition__: If total number of books can successfully returned

__Code__: `200 SUCCESS`

__Content example__:

```json
{
  "total": 214
}
```

#### Show List Of Books

Returns list of books by specified parameters. If no parameters specified, returns list of all books ordered by title. 

__URL__: `/api/books?orderBy={orderBy}&order={order}&from={from}&to={to}`

__Method__: `GET`

__URL parameters__

| URL Parameter | Required | Description                       | Possible Values                                              | Default              |
|---------------|----------|-----------------------------------|--------------------------------------------------------------|----------------------|
| `orderBy`     | no       | Key to order the list of books by | `AUTHOR`<br>`TITLE`<br>`DATE_OF_ADDITION`<br>`YEAR_OF_ISSUE` | `TITLE`              |
| `order`       | no       | Specifies order direction         | `ASC`<br>`DESC`                                              | `ASC`                |
| `from`        | no       | Start index used for pagination   | `<integer>` above `0`                                        | `0`                  |
| `to`          | no       | End index used for pagination     | `<integer>` above `{from}` param                             | the last index in DB |

##### Success Responses

__Condition__: If data can be successfully returned

__Code__: `200 SUCCESS`

__Content example__:

```json
[
  {
    "id": 1,
    "title": "Obraz Doriana Graye",
    "author": {
      "firstName": "Oscar",
      "lastName": "Wilde"
    },
    "yearOfIssue": 2018,
    "dateOfAddition": "2020-01-01",
    "imageURL": "/images/jhsu87r.jpeg",
    "tags": [
      {
        "id": 1,
        "name": "Gay"
      },
      {
        "id": 2,
        "name": "Zahraniční"
      }
    ]
  }
]
```

##### Error Responses

__Condition__: If there are no books for given page indexes

__Code__: `404 NOT FOUND`

__Content example__: 

```json
{
  "detail": "No data found for given indexes"
}
```

### Book Details

Show all details of given book.

__URL__: `/api/books/{id}
`

__Method__: `GET`

__URL parameters__

| URL Parameter | Required | Description | Possible Values       | Default |
|---------------|----------|-------------|-----------------------|---------|
| `id`          | yes      | Book id     | `<integer>` above `0` | N/A     |

##### Success Responses

__Condition__: If data can be successfully returned

__Code__: `200 SUCCESS`

__Content example__

```json
{
  "title": "Obraz Doriana Graye",
  "id": 1,
  "author": {
    "firstName": "Oscar",
    "lastName": "Wilde"
  },
  "yearOfIssue": 2018,
  "dateOfAddition": "2020-01-01",
  "imageURL": "/images/jhsu87r.jpeg",
  "tags": [
    {
      "id": 1,
      "name": "Gay"
    },
    {
      "id": 2,
      "name": "Zahraniční"
    }
  ],
  "description": "Příběh s utopickými prvky se odehrává v 19. století v Londýně.\r\nJediná rozsáhlejší próza Oscara Wilda předvádí na fantastickém příběhu aristokrata, jemuž kouzelná moc propůjčila věčnou krásu a mládí, rozpor mezi morálkou a estetickým prožitkem - zatímco Dorian Gray zůstává stále dvacetiletý, jeho dokonalá podoba na plátně stárne a ohyzdí se podle toho, kolik dívčích srdcí zlomil a kolik nadějných mladíků přivedl do zkázy.",
  "ISBN": "978-80-207-1814-3",
  "numberOfPages": 264,
  "originalLanguage": "English",
  "publisher": "Odeon",
  "links": [
    {
      "cbdb": "https://www.cbdb.cz/kniha-2569-obraz-doriana-graye-picture-of-dorian-gray",
      "goodreads": "https://www.goodreads.com/book/show/10545185-obraz-doriana-graye",
      "dtabazeKnih": "https://www.databazeknih.cz/knihy/obraz-doriana-graye-3182"
    }
  ]
}
```

##### Error Responses

__Condition__: If there is no book with given ID

__Code__: `404 NOT FOUND`

__Content example__: 

```json
{
  "detail": "No book matches the given ID"
}
```

## License
MIT