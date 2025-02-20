# Students API
## Description
Simple CRUD API for Student Objects
### Version
0.1.9

## Installation
- Get the project
    - clone
  
        `git clone https://github.com/uncg-csc340/sp25-crud-api-jpa.git`
    - OR download zip.
- Open the project in IntelliJ.
- This project is built to run with jdk 21.
- [`/src/main/resources/application.properties`](https://github.com/uncg-csc340/sp25-crud-api-jpa/blob/d117dd0cad30a0453254dd261248c249be9654aa/src/main/resources/application.properties) file  is the configuration for the MySQL database on your localhost.
  - the database name is on the `datasource.url` property between the last `/` and the `?`. In this case the database name is `student-database`.
  - You MUST have the database up and running before running the project! 
    - Start your AMPPS Server.
    - Click on the Home icon to open the localhost server on your browser.
    - Go to Database Tools and open phpMyAdmin to start up the MySQL Dashboard.
    - Ensure the database that you need is available. Either
      - Create a database called `student-database`
      - OR edit `datasource.url` to point to a database that you do have.
- Build and run the main class. You should see a new table created in the aforementioned database.

## API Endpoints
Use POSTMAN to try the following endpoints:

## Get list of Students

### Request

    `GET /students/all`

    `http://localhost:8080/students/all`

   
### Response

 ```
[
  {
    "studentId": 1,
    "name": "Alice Smith",
    "major": "CSC",
    "gpa": 3.88
  },
  {
    "studentId": 2,
    "name": "Bobby Stewart",
    "major": "MAT",
    "gpa": 2.97
  }
]
```
## Get a specific Student

### Request

`GET /students/{studentId}`

`http://localhost:8080/students/1`

### Response

```
{
  "studentId": 1,
  "name": "Alice Smith",
  "major": "CSC",
  "gpa": 3.88
}
```

     
## Create a new Student

### Request

    POST /students/new
    
    http://localhost:8080/students/new --data '{ "name": "sample4", "major": "csc", "gpa": 3.55}'

   ### Response

   [
   
     {"studentId": 1, "name": "sample1", "major": "csc", "gpa": 3.89}, 
   
     {"studentId": 2, "name": "sample2", "major": "mat", "gpa": 4.0}, 
   
     { "studentId": 3, "name": "sample3", "major": "eng", "gpa": 3.25},

     { "studentId": 4, "name": "sample4", "major": "csc", "gpa": 3.55}
   
  ]

## Get Students by major

### Request

    `GET /students?major=csc`

    `http://localhost:8080/students?major=csc`

   
### Response

     [
   
      {"studentId": 1, "name": "sample1", "major": "csc", "gpa": 3.89}, 
   
      { "studentId": 4, "name": "sample4", "major": "csc", "gpa": 3.55}
   
     ]

## Get Honors students

### Request

    `GET /students/honors?gpa=3.5`

    `http://localhost:8080/students/honors?gpa=3.5`

   
### Response

   [
   
     {"studentId": 1, "name": "sample1", "major": "csc", "gpa": 3.89}, 
   
     {"studentId": 2, "name": "sample2", "major": "mat", "gpa": 4.0},    

     { "studentId": 4, "name": "sample4", "major": "csc", "gpa": 3.55}
     
   ]

## Update an existing Student

### Request

    `PUT /students/update/{studentId}`
    
    `http://localhost:8080/students/update/1` --data '{ "name": "sampleUpdated", "major": "csc", "gpa": 3.92}'

   ### Response
   
    {
      "studentId": 1, "name": "sampleUpdated", "major": "csc", "gpa": 3.92
    }


## Delete an existing Student

### Request

    `DELETE /students/delete/{studentId}`
    
    `http://localhost:8080/students/delete/1`

   ### Response
   
   [
   
     {"studentId": 2, "name": "sample2", "major": "mat", "gpa": 4.0}, 
   
     { "studentId": 3, "name": "sample3", "major": "eng", "gpa": 3.25},

     { "studentId": 4, "name": "sample4", "major": "csc", "gpa": 3.55}
   
  ]








