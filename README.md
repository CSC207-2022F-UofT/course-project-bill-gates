# Bill Gates - Your next Bill Management System

- This software serves as the project for the course CSC207 at the University of Toronto.

- **Distribution without permission is strongly prohibited**

# Specifications

- Our application will utilize a fully implemented Swing GUI, 
where users can log in to the application and start recording bill entries into the database.
- Users will be able to obtain simple statistics on the current entries recorded in the bill.
- Within each recorded bill entry, users can provide additional details on the payment. 
- Users can also utilize this application to record a split bill with other people.

## Design Patterns

### User: Singleton

- The `User` class represents a user entity in our program. 
- Only **ONE** user (Singleton) is allowed in the running time of our program.
- The `UserJoinUseCase` will initialize the user by calling `getInstance` with parameters.
- Then, all the use cases can use `getInstance` without parameters to obtain the current user via Singleton design pattern.

### DatabaseGateway: Strategy

- We have laid out this class as a collection of methods that can be implemented by different classes.
- For each specific database type (MariaDB, MongoDB, MySQL, etc.), we can implement the `DatabaseGateway` using different syntax to adapt.
- Currently, our project uses `MySQL`, therefore, `MySQLDatabaseGateway` is a specific "strategy" to connect the application to our database.

### EntryBuilder: Builder

- The instance variables define the default values for each attribute.
- We can build our desired `Entry` by calling the `set___` methods to give values for the entry.
- After we are done, we can call `buildEntry` or `buildSplitterEntry` to obtain the desired Entry.
- In a sense, we are "building" the entry by giving it attributes one by one. 

## Test Coverage

Currently, we have implemented the following test suit:

```
MySQLDatabaseGatewayTests
- testCreateUsersTable
- testGetUserData
- testCreateBillTable
- testCreateSplitBillTable
- testInsertUser
- testInsertEntryAllAttributes
- testInsertEntryMainAttributes
- testInsertEntryAutoIncrement
- testGetEntry
- testGetBillDataAll
- testGetBillDataPartial
- testModifyEntryDescription
- testModifyEntryValue
- testModifyEntryDate
- testModifyEntryAll
- testDeleteEntry
```

## Database Specifications

### Related issues 
- [#11 [Feature 7] Design and Implement Bill Splitter](https://github.com/CSC207-2022F-UofT/course-project-bill-gates/issues/11) 
- [#22 [Improvement 1] Database Specification](https://github.com/CSC207-2022F-UofT/course-project-bill-gates/issues/22)
- [#80 [Feature] Add support for adding a splitter entry](https://github.com/CSC207-2022F-UofT/course-project-bill-gates/issues/80)
- [#91 [Feature] Add a use case to fill in the inherited attributes of a splitter entry automatically](https://github.com/CSC207-2022F-UofT/course-project-bill-gates/issues/91)

### The User Table

- Table Name: `users`.

Table Specifications:

| Column Name | Data Type     | Notes                                 |
|-------------|---------------|---------------------------------------|
| `user_id`   | `INT`         |                                       |
| `user_name` | `VARCHAR(10)` | The max user name length is 10        |
| `password`  | `VARCHAR(16)` | The max password length is 16         |
| `bill_id`   | `INT`         | This is the main bill id of this user |

### The Main Bill Table

- Table Name: `bill_{bill_id}`.

- Side Note: `bill_id = user_id`. Any user has only main bill.

Table Specifications:

| Column Name     | Data Type        | Notes                                                                                  |
|-----------------|------------------|----------------------------------------------------------------------------------------|
| `entry_id`      | `INT`            |                                                                                        |
| `date`          | `TIMESTAMP`      |                                                                                        |
| `value`         | `DECIMAL(16, 2)` | We expect `value` to have 16 integer places and 2 decimal places                       |
| `currency`      | `CHAR(3)`        | As defined in ISO 4217, currency codes all have a length of 3                          |
| `description`   | `TEXT`           |                                                                                        |
| `from`          | `TEXT`           |                                                                                        |
| `to`            | `TEXT`           |                                                                                        |
| `location`      | `TEXT`           |                                                                                        |
| `split_bill_id` | `INT`            | The splitter bill id associated with this entry. We define `entry_id = split_bill_id`. |

### The Splitter Bill Table

- Table Name: `bill_{main_bill_id}_{splitter_bill_id}`.

- Side Note: `bill_id = user_id`. Any user has only main bill. `splitter_bill_id = entry_id`. Any entry has only one splitter bill.

Table Specifications:

| Column Name   | Data Type        | Notes                                                            |
|---------------|------------------|------------------------------------------------------------------|
| `entry_id`    | `INT`            |                                                                  |
| `date`        | `TIMESTAMP`      | Inherited from the parent entry.                                 |
| `value`       | `DECIMAL(16, 2)` | We expect `value` to have 16 integer places and 2 decimal places |
| `currency`    | `CHAR(3)`        | Inherited from the parent entry.                                 |
| `description` | `TEXT`           |                                                                  |
| `from`        | `TEXT`           | Inherited from the parent entry.                                 |
| `to`          | `TEXT`           | Inherited from the parent entry.                                 |
| `location`    | `TEXT`           | Inherited from the parent entry.                                 |
| `payee`       | `TEXT`           | This represents the person who is responsible for this split.    |
| `paid_back`   | `BOOLEAN`        | This represents whether the payee had paid the money back.       |

- Related Use Case and Details

    - Since we want to replace the entries directly, we may want to call `BillUpdateUseCase`.

    - We will set the `currentBill` in the `User` class as this bill id.

    - Every time we clicked the "Back" button, we just change the `currentBill` to the bill id of the main bill of this user, and then call `BillUpdateUseCase`.

## Cloud Specifications

### Related issue

- [#25 [Feature 8] Cloud Storage](https://github.com/CSC207-2022F-UofT/course-project-bill-gates/issues/25)

### Connection details
```
hostname=bill-gates-database.mysql.database.azure.com
username=scott
password=Billgates@
ssl-mode=require
```

- Database Name: `bill`.

## Use Case Specifications

#### User Join Use Case

- This use case will initialize the `User` instance.

# About Us

We are the group "Bill Gates" and we are developing the software "Bill Gates".

We hope that you can be the next Bill Gates with the help of "Bill Gates".

Group Members:

- Brandon Fu [Yuhan-Ut](https://github.com/Yuhan-Ut)
- Charlotte Chen [CharlotteChenyy](https://github.com/CharlotteChenyy)
- Ellen Zhang [Ellen141319](https://github.com/Ellen141319)
- Eva Dai [eevadai](https://github.com/eevadai)
- Ray Hung [Lei-Tin](https://github.com/Lei-Tin)
- Ruby Li [Yunshan-522](https://github.com/Yunshan-522)
- Scott Cui [ScottCTD](https://github.com/ScottCTD)
- Xinxiang Gao [xgao28](https://github.com/xgao28)
