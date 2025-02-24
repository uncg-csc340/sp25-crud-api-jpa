# Students API
## Description
Simple CRUD API for Student Objects
### Version
0.1.9

## Installation
- Get the project
    - clone
  
        `git clone https://github.com/uncg-csc340/sp25-crud-api-jpa.git` OR
    - download zip.
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
## Notes
- [Entity](https://github.com/uncg-csc340/sp25-crud-api-jpa/blob/8a7a97df778a6bb2280d680a80f3c3ffe6166ec1/src/main/java/com/csc340/sp25_crud_api_demo/student/Student.java#L5)
  - The Student class is annotated as an `@Entity `. This is used by Hibernate (an implementation of the Jakarta Persistence API) to map class attributes to database tables and SQL types.
  - We also annotated with `@Table` to give Hibernate directions to use this specific table name. This is optional but it helps with naming conventions.
  - Any Entity must have at least one attribute that is annotated as an `@Id`. In our case it's conveniently the `studentId` attribute.
    - We are also using an autogeneration strategy for the ID. This way we are not manually assigning IDs to our students. This is optional.
  - An Entity must have a no-argument constructor.
- [Repository](https://github.com/uncg-csc340/sp25-crud-api-jpa/blob/8a7a97df778a6bb2280d680a80f3c3ffe6166ec1/src/main/java/com/csc340/sp25_crud_api_demo/student/StudentService.java#L15)
  - We are using an extension of the JPA Repository that comes with prebuilt database operations such as select all, select by id, select by any other reference, insert, delete, etc.
  - Annotate it as a `@Repository`.
  - We parametrize this using our object and its ID type.
    - `public interface StudentRepository extends JpaRepository<Student, Integer>` => We want to apply the JPA repository operations on the `Student` type. The `Student` has an ID of type `int`.
  - If we need special database queries that are not the standard ones mentioned above, we can create a method with a special purpose query as shown. This is an interface so no implementation body.
- [Rest Controller](https://github.com/uncg-csc340/sp25-crud-api-jpa/blob/8a7a97df778a6bb2280d680a80f3c3ffe6166ec1/src/main/java/com/csc340/sp25_crud_api_demo/student/StudentController.java#L15)
  - Annotates as a `@RestController`.
  - All the API endpoints mapped inside this controller will start with `/students`.
  - Return a Response Object. 
  - The Service class is [`@Autowired`](https://github.com/uncg-csc340/sp25-crud-api-jpa/blob/8a7a97df778a6bb2280d680a80f3c3ffe6166ec1/src/main/java/com/csc340/sp25_crud_api_demo/student/StudentController.java#L19). Do not use a constructor, you will get errors.
    - In the Service class,the Repository class is also [`@Autowired`](https://github.com/uncg-csc340/sp25-crud-api-jpa/blob/8a7a97df778a6bb2280d680a80f3c3ffe6166ec1/src/main/java/com/csc340/sp25_crud_api_demo/student/StudentService.java#L15)  :)
    - 
## API Endpoints
Base URL: [`http://localhost:8080/students`](http://localhost:8080/students)


### [`/all`](http://localhost:8080/students/all) (GET)
Gets a list of all Students in the database.

#### Response - A JSON array of Student objects.

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

### [`/{studentId}`](http://localhost:8080/students/1) (GET)
Gets an individual Student in the system. Each Student is identified by a numeric `studentId`

#### Parameters
- Path Variable: `studentId` &lt;integer&gt; - REQUIRED

#### Response - A single Student

```
{
  "studentId": 1,
  "name": "Alice Smith",
  "major": "CSC",
  "gpa": 3.88
}
```

### [`/name`](http://localhost:8080/students/name?search=ob) (GET)
Gets a list of students with a name that contains the given string.

#### Parameters
- query parameter: `search` lt;String&gt; - REQUIRED

#### Response - A JSON array of Student objects.

```
[
  {
    "studentId": 2,
    "name": "Bobby Stewart",
    "major": "MAT",
    "gpa": 2.97
  },
  {
    "studentId": 5,
    "name": "Jobadiah Evans",
    "major": "REL",
    "gpa": 3.46
  }
]
```

### [`/major/{major}`](http://localhost:8080/students/major/csc) (GET)
Gets a list of students for a named major.

#### Parameters
- path variable: `major` &lt;String&gt; - REQUIRED

#### Response - A JSON array of Student objects.

```
[
  {
    "studentId": 1,
    "name": "Alice Smith",
    "major": "CSC",
    "gpa": 2.97
  },
  {
    "studentId": 7,
    "name": "John Doe",
    "major": "CSC",
    "gpa": 3.65
  }
]
```

### [`/honors`](http://localhost:8080/students/honors?gpa=3.5) (GET)
Gets a list of students with a GPA meeting the Threshold.

#### Parameters
- query parameter: `gpa` &lt;Double&gt; - REQUIRED

#### Response - A JSON array of Student objects.

```
[
  {
    "studentId": 1,
    "name": "Alice Smith",
    "major": "CSC",
    "gpa": 3.88
  },
  {
    "studentId": 7,
    "name": "John Doe",
    "major": "CSC",
    "gpa": 3.65
  }
]
```

### [`/new`](http://localhost:8080/students/new) (POST)
Create  a new Student entry
 
#### Request Body
A student object. Note that the studentId is auto assigned in the database so is not needed in the request.
```
{
  "name": "Mister New Student",
  "major": "CSC",
  "gpa": 3.28
}
```
#### Response - The updated list of Students.

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
  },
  {
    "studentId": 3,
    "name": "Mister New Student",
    "major": "CSC",
    "gpa": 3.28
  }
]
```

### [`/update/{studentId}`](http://localhost:8080/students/update/1) (PUT)
Update an existing Student.

#### Parameters
- Path Variable: `studentId` &lt;integer&gt; - REQUIRED

#### Request Body
A student object with the updates.
```
{
  "name": "Mister Updated Student",
  "major": "CSC",
  "gpa": 3.45
}
```
#### Response - the updated Student object.
```
{
  "studentId": 1,
  "name": "Mister Updated Student",
  "major": "CSC",
  "gpa": 3.45
}
```

### [`/delete/{studentId}`](http://localhost:8080/students/delete/1) (DELETE)
Delete an existing Student.

#### Parameters
- Path Variable: `studentId` &lt;integer&gt; - REQUIRED

#### Response - the updated list of Students.
```
[
  {
    "studentId": 2,
    "name": "Bobby Stewart",
    "major": "MAT",
    "gpa": 2.97
  },
{
    "studentId": 3,
    "name": "Mister Updated Student",
    "major": "CSC",
    "gpa": 3.28
  }
]
```
