# cz-lgbt-books-be

Backend of website for book database of LGBT literature in Czech language.

## Endpoints DEV status


```
GET /api/books/info
```
- __FULLY IMPLEMENTED__


```
GET /api/books/filterParams
```
- __NOT IMPLEMENTED__

```
GET /api/books
```
- __PARTIALLY IMPLEMENTED__
- params `order`, `orderBy`, `size` and `page` supported
- params `tags`, `originalLanguages` and `bookSizes` waiting for implementation

```
GET /api/books/:slug
```
- __FULLY IMPLEMENTED__

```
GET /api/books/random
```
- __NOT IMPLEMENTED__

## REST API Documentation

- [Common](#common)
- [Books](#books)
    - [Books Info](#books-info)
    - [Parameters To Filter Books](#parameters-to-filter-books)
    - [Show List Of Books](#show-list-of-books)
    - [Book Details](#book-details)
    - [Random Book](#random-book)

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

#### Books Info

Gets information about the whole database.

__URL__: `/api/books/info`

__Method__: `GET`

##### Success Responses

__Condition__: If information have been successfully returned

__Code__: `200 SUCCESS`

__Content example__:

```json
{
  "totalBooks": 214,
  "dateOfLastBookAddition": "2020-01-01"
}
```

#### Parameters To Filter Books

Returns parameters which can be used to filter books (see [Show List Of Books](#show-list-of-books) for more details).

__URL__: `/api/books/filterParams`

__Method__: `GET`

##### Success Responses

__Condition__: If filter parameters have been successfully returned

__Code__: `200 SUCCESS`

__Content example__:

```json
{
  "originalLanguage": [
    {
      "booksMatchesValue": 34,
      "slug": "cestina",
      "name": "čeština"
    },
    {
      "booksMatchesValue": 2,
      "slug": "anglictina",
      "name": "angličtina"
    }
  ],
  "bookSize": [
    {
      "slug": "kratka",
      "minPages": null,
      "maxPages": 100,
      "booksMatchesValue": 5,
      "name": "krátká"
    },
    {
      "slug": "stredni",
      "minPages": 100,
      "maxPages": 200,
      "booksMatchesValue": 10,
      "name": "střední"
    },
    {
      "slug": "stredni",
      "minPages": 300,
      "maxPages": null,
      "booksMatchesValue": 63,
      "name": "dlouhá"
    }
  ],
  "tags": [
    {
      "name": "Gay",
      "slug": "gay",
      "booksMatchesValue": 45
    },
    {
      "name": "Zahraniční",
      "slug": "zahranicni",
      "booksMatchesValue": 31
    }
  ]
}
```

#### Show List Of Books

Returns list of books by specified parameters. If no parameters specified, returns list of all books ordered by title. 

__URL__: `/api/books?orderBy={orderBy}&order={order}&from={from}&to={to}&tags={tags[]}&originalLanguages={originalLanguage[]}&bookSizes={bookSize[]}`

__Method__: `GET`

__URL parameters__

| URL Parameter       | Required | Description                             | Possible Values                                              | Default              |
|---------------------|----------|-----------------------------------------|--------------------------------------------------------------|----------------------|
| `orderBy`           | no       | Key to order the list of books by       | `TITLE`<br>`DATE_OF_ADDITION`<br>`YEAR_OF_ISSUE`             | `DATE_OF_ADDITION `  |
| `order`             | no       | Specifies order direction               | `ASC`<br>`DESC`                                              | `ASC`                |
| `page`              | no       | Number of required page                 | `<integer>` above `0`                                        | `0`                  |
| `size`              | no       | Number of books at one page             | `<integer>` above `0`                                        | the last index in DB |
| `tags`              | no       | Filter based on tags (AND)              | `<string[]>`, example `tags=gay,zahranicni`                  | N/A                  |
| `originalLanguages` | no       | Filter based on original languages (OR) | `<string[]>`, example `originalLanguages=anglictina,cestina` | N/A                  |
| `bookSizes`         | no       | Filter based on book sizes (OR)         | `<string[]>`, example `bookSizes=kratka,stredni`             | N/A                  |

##### Success Responses

__Condition__: If data can be successfully returned

__Code__: `200 SUCCESS`

__Content example__:

```json
[
  {
    "slug": "obraz-doriana-graye-2018",
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
        "slug": "gay",
        "name": "Gay",
        "color": "red"
      },
      {
        "slug": "zahranicni",
        "name": "Zahraniční",
        "color" "purple"
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

__URL__: `/api/books/{slug}
`

__Method__: `GET`

__URL parameters__

| URL Parameter | Required | Description | Possible Values      | Default |
|---------------|----------|-------------|----------------------|---------|
| `slug`        | yes      | Book slug   | non empty `<string>` | N/A     |

##### Success Responses

__Condition__: If data can be successfully returned

__Code__: `200 SUCCESS`

__Content example__

```json
{
  "slug": "obraz-doriana-graye-2018",
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
      "slug": "gay",
      "name": "Gay",
      "color": "red"
    },
    {
      "slug": "zahranicni",
      "name": "Zahraniční",
      "color" "purple"
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

#### Random Book

Gets a random book.

__URL__: `/api/books/random`

__Method__: `GET`

##### Success Responses

__Condition__: If data can be successfully returned

__Code__: `200 SUCCESS`

__Content example__:

Same as [Book Details](#book-details).


## License
MIT