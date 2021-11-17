# practicabp
Practica de Spring Boot

Requerimientos

## PERSON
- List
GET api/persons

- Read
GET api/persons/[id]

- Create POST api/persons

- Update
PUT api/persons

Request body POST and PUT:

    {
      "firstName": "Allan",
      "lastName": "Alarcón",
      "dni": "0930699543",
      "active": true
    }


## ACCOUNT
- List
GET api/accounts

- ListByPersonId
GET api/accounts/persons/[idPerson]

- Read
GET api/accounts/[id]

- Create
POST api/accounts

- Update
PUT api/accounts

Request body POST and PUT:

    {
      "email": "example@devsu.com",
      "password": "12345",
      "person": {
                  "id": 1
              }
    }

## TRANSACTION
- List
GET api/transactions

- ListByAccountIdAndDateBetween
GET api/transactions/accounts/[idAccount]/?dateTransactionStart=2020-01-01&dateTransactionEnd=2021-01-01

- Read
GET api/transactions/[id]

- Create
POST api/transactions

- Create List
POST api/transactions/all

- Delete
DELETE api/transactions/[id]

Request body POST:

    {
      "description": "prueba2",
      "dateTransaction": "2020-06-01",
      "account": {
                  "id": 1
              }
     }

Request body POST All:

    [
      {
        "description": "prueba1",
        "dateTransaction": "2020-06-01",
        "account": {
                    "id": 1
                }
      },
      {
        "description": "prueba2",
        "dateTransaction": "2020-06-01",
        "account": {
                    "id": 1
                }
      }
    ]
