# REST API

### CLIENTS
Чтобы взаимодействовать с клиентами необходимо указать следующие параметры:

#### Добавить клиента

POST [localhost:8080/clients/add] 
```json
{
  "name": "name123123",
  "short_name": "name",
  "address": "a11",
  "org_legal_form": "1111231111"
}
```

#### Изменить клинта

PUT [localhost:8080/clients/update] 
```json
{
  "id": 1,
  "name": "name123123",
  "short_name": "name",
  "address": "a11",
  "org_legal_form": "1111231111"
}
```
#### Удалить клинта
DELETE [localhost:8080/clients/delete/1] - Указать ID удаляемого клиента

#### Получить список всех клиентов
GET [localhost:8080/clients]

#### Получить клиента по id
GET [localhost:8080/clients/1] - Указать ID клиента, которого хотим получить

#### Получить клиента по name
GET [localhost:8080/clients/by?name=Иван] - Указать имя клиента, которого хотим получить

### BANKS
Чтобы взаимодействовать с банками необходимо указать следующие параметры:

#### Добавить банк

POST [localhost:8080/banks/add]
```json
{
  "name" : "SBER",
  "bic" : "1"
}
```

#### Изменить банк

PUT [localhost:8080/banks/update]
```json
{
  "id": 1,
  "name": "SBER",
  "bic": "2"
}
```
#### Удалить банк
DELETE [localhost:8080/banks/delete/1] - Указать ID удаляемого банка

#### Получить список всех банков
GET [localhost:8080/banks]

#### Получить банк по id
GET [localhost:8080/banks/1] - Указать ID банка, которого хотим получить

#### Получить банк по name
GET [localhost:8080/banks/by?name=СБЕР] - Указать имя банка, которого хотим получить

#### Получить банк по bic
GET [localhost:8080/banks/byBic?bic=1] - Указать бик банка, которого хотим получить

### DEPOSITS
Чтобы взаимодействовать с вкладами необходимо указать следующие параметры:

#### Добавить вклад

POST [localhost:8080/deposits/add]
```json
{
  "client_id": 1,
  "bank_id": 1,
  "date": "2024-04-01",
  "percent": 10,
  "month_num": 1
}
```

#### Изменить вклад

PUT [localhost:8080/deposits/update]
```json
{
  "id": 1,
  "client": {
    "id": 1,
    "name": "name",
    "short_name": "name",
    "address": "perm",
    "org_legal_form": "1"
  },
  "bank": {
    "id": 1,
    "name": "SBER",
    "bic": "1"
  },
  "date": "2024-04-01",
  "percent": 10,
  "month_num": 1
}
```
#### Удалить вклад
DELETE [localhost:8080/deposits/delete/1] - Указать ID удаляемого вклада

#### Получить список всех вкладов
GET [localhost:8080/deposits]

#### Получить вклад по id
GET [localhost:8080/deposits/1] - Указать ID вклада, которого хотим получить

#### Получить вклад по client_id
GET [localhost:8080/deposits/byClient?client=1] - Указать клиента вклада, которого хотим получить

#### Получить вклад по bank_id
GET [localhost:8080/deposits/byBank?client=1] - Указать банк вклада, которого хотим получить