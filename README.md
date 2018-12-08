# EmployeeAPI
This is spring boot based api for employee management API. 

#API endpoints are create , update, delete and filter api.

## API

## Create employee

* Post JSON to `/createEmployee`

### Request

* Type: `POST`
* Header: `Content-Type: application/json`
* Body:

```json
{ 
  "id": 2, 
  "fullName": "Employee B", 
  "age": 22, 
  "salary": 5504
}

```

#### Response Body:

```json
  Created Successfully. 
```

## Update employee

* Post JSON to `/updateEmployee`

### Request

* Type: `POST`
* Header: `Content-Type: application/json`
* Body:

```json
{"id": 2, "fullName": "Employee B", "age": 22, "salary": 5504}
```

#### Response Body:

```json
  Updated Successfully
```

## Delete employee

* Post JSON to `/deleteEmployee`

### Request

* Type: `POST`
* Header: `Content-Type: application/json`
* Body:

```json
{
  "id":2
}
```
#### Response Body:

```json
  Record Deleted
```

## Filter employee

* Post JSON to `/filterByAge`
* Post JSON to `/filterBySalary`

### Request

* Type: `POST`
* Header: `Content-Type: application/json`
* Body:

```json
{"operator":"lt","sort":"asc","value":20}
{"operator":"lte","sort":"desc","value":20}
```

#### Response Body:

```json
  [
    {
        "fullName": "Employee A",
        "id": 1,
        "salary": 5504,
        "age": 22
    },
    {
        "fullName": "Employee B",
        "id": 2,
        "salary": 5504,
        "age": 22
    }
]
```

#### Settings:

In applicaiton.properties file 

```json
filePath=E:\\testAPP\\employee.json
```
