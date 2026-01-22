# Fitness Club 

***

### Course: Object Orientated Programming (Java) 
### Assignment: №3 - Project milestone 1

***

###

### *Team Members*
* Madiuly Kuanysh 
* Alam Zaib

###

### 👥 *Team Work Distribution*
#### Madiuly Kuanysh

* **Database Infrastructure:** Created the `edu.aitu.oop3.db.data` package, implemented the `IDB` interface, and the `DatabaseConnection` class for Supabase/PostgreSQL connectivity.
* **Business Logic Layer:** Developed the services package and structured user actions including registration, booking, and data visualization.
* **Database Design:** Wrote SQL scripts for database initialization, creating tables for `members`, `classes`, `membership_type` and `bookings`.
* **Project Documentation:** Authored the `README.md` file, defined the project topic, and organized the package structure.

#### Alam Zaib

* **Domain Models:** Implemented the `entities` package, creating core classes: `Member`, `MembershipType`, `FitnessClass` and `Booking`.
* **Data Access Layer:** Implemented the `Repository` pattern (package repositories), including interfaces and their JDBC-based implementations.
* **Exception Handling:** Developed the exceptions package and implemented custom error classes: `ClassFullException`, `MemberNotFoundException` and `MembershipExpiredException`.

###

### 🛠 *Technical Status (Milestone 1)*
#### According to the Assignment 3 requirements, the following has been implemented: ####
* **Entities:** 4 core `entities` created (Member, MembershipType, FitnessClass, Booking).
* **JDBC:** Established a stable connection to a remote `PostgreSQL` database.
* **SOLID:** The code follows a layered `architecture`, separating concerns into `data`, `models`, `repositories` and `services`. 
* **Error Handling:** Custom exception logic is integrated to prevent invalid actions like overbooking.


