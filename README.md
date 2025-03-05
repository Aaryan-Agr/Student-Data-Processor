# 📚 StudentDataProcessor  

## 📖 Overview  
StudentDataProcessor is a **Java-based application** designed to read, process, and format student course data from a text file. It extracts **student names, IDs, course codes, and grades**, validates the data, calculates grade averages, and writes the formatted results to an output file.

---

## 🛠 Features  
✅ Reads student data from a text file  
✅ Extracts student names, IDs, course codes, and grades  
✅ Validates course codes, student IDs, and grades  
✅ Computes average grades for each student  
✅ Writes formatted data to an output file  

---


## ⚙️ Installation & Usage  

### 🏗 Prerequisites  
🔹 Java Development Kit (JDK) installed (Java 8 or later)  
🔹 A text editor or IDE (VS Code, IntelliJ, Eclipse)  

### 🚀 Running the Program  
1️⃣ Clone the repository:  
```sh
git clone https://github.com/Aaryan-Agr/StudentDataProcessor.git
```

2️⃣ Navigate to the project directory:

```sh
cd StudentDataProcessor
```
3️⃣ Compile the Java files:

```sh
javac src/*.java
```
4️⃣ Run the program:

```sh
java src.Main
```

## 📄 Input File Format (input.txt)
The input file should contain student data in CSV format, where:

- The first column is the student ID (9 digits).
- The second column is the course code (2 letters followed by 3 digits).
- The following columns contain grades (0-100).

## 📊 Output File Format (output.txt)
The processed output file contains:

- Student ID
- Student Name
- Course Code
- Average Grade

## 🚀 Future Improvements
-  Implement a GUI for user-friendly data entry
-  Support JSON and Excel file formats
-  Add database integration for better data management
