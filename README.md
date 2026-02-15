# Student Management System (Java Desktop)

> **An advanced Java Swing application for academic administration, featuring binary object serialization for high-speed data persistence.**

This system is a robust desktop solution designed to manage the lifecycle of students and courses. It demonstrates high-level **Object-Oriented Programming (OOP)**, implementing custom serialization logic to ensure data persists across sessions without the overhead of a traditional SQL database.

---

## 🏗️ System Architecture

The application is built using a **Modular Manager Pattern**, where specific controllers handle data logic while a utility layer manages the file system.



```text
       [ Swing GUI Layer ]
      (User Events & Forms)
               |
               v
    [ Management Controllers ]
 (StudentManager / CourseManager)
               |
       /-------+-------\
       |               |
 [ Logic/CRUD ]  [ Data Objects ]
 (Validation)    (Serializables)
       |               |
       \-------+-------/
               |
               v
      [ FileManager Utility ]
      (Binary Serialization)
               |
               v
     [ Local Storage (.dat) ]
```

---

## 🚀 Key Features

### 👤 Student & Course Administration
* **Comprehensive CRUD:** Add, remove, search, and list entities via an intuitive dashboard.
* **Unique Identification:** Automated validation to prevent duplicate IDs for both students and curriculum modules.
* **Data Integrity:** Ensures all records (ID, name, age, email/credits) meet system requirements before persistence.

### 💾 High-Performance Persistence
* **Java Serialization:** Implements the `Serializable` interface to convert complex objects into byte streams for storage in `students.dat` and `courses.dat`.

* **Generic File Manager:** A reusable utility class designed to handle I/O operations for any serializable object type.

### 🖥️ User Experience
* **Swing-Based GUI:** A modern, menu-driven interface that replaces complex console commands with clickable navigation and form-based entry.
* **Real-Time Data Sync:** The UI reflects file changes instantly, providing immediate feedback for every administrative action.

---

## 🧰 Technical Stack

| Category | Technology |
| :--- | :--- |
| **Language** | Java 8+ |
| **UI Framework** | Java Swing / AWT |
| **Persistence** | Java Object Serialization (Binary I/O) |
| **Architecture** | OOP / Modular Design |

---

## 🛠️ Installation & Usage

### Prerequisites
* **Java JDK 8** or higher.
* An IDE (IntelliJ, Eclipse) or terminal access.

### Setup
1. **Clone the Repository:**
   ```bash
   git clone [https://github.com/AngelosFikias0/Student_Management_System_Java.git](https://github.com/AngelosFikias0/Student_Management_System_Java.git)
   cd Student_Management_System_Java
   ```
2. **Compile:**
   ```bash
   javac *.java
   ```
3. **Run:**
   ```bash
   java StudentManagementSystem
   ```

---

## 📚 Engineering Highlights

* **Scalability Ready:** The modular design allows for seamless migration to a **PostgreSQL** or **MySQL** backend by simply swapping the `FileManager` logic.
* **Abstraction:** Heavy use of abstraction and encapsulation ensures that the GUI logic is completely decoupled from the data management logic.
* **Validation Logic:** Implements error-handling blocks to manage I/O exceptions and data type mismatches during user input.

---

## 📄 License
This project is open-source and available under the **MIT License**.

---
**Developed by Angelos Fikias** *Crafting reliable enterprise-grade desktop solutions.*
