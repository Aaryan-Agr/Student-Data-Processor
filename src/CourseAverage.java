
/**
 * Importing the required libraries and packages
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The TextReader class contains a method that can read data from a text file.
 */

class TextReader {

    protected String filePath;

    /**
     * This is a constructor for TextReader.
     *
     * @param filePath - Contains the file path of the file to be read
     */
    public TextReader(String filePath) {
	this.filePath = filePath;
    }

    /**
     * Reads data from from the file path provided in the constructor of a
     * TExtReader object.
     *
     * @return A List of String arrays where each array represents a row of data
     *         from the file. If an error occurs during file reading, an empty list
     *         is returned and an error message is print out.
     */

    public List<String[]> readData() {
	List<String[]> data = new ArrayList<>();

	try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	    String line;
	    // Splitting lines into an array with the delimiter set as "," and adding it to
	    // data
	    while ((line = reader.readLine()) != null) {

		String[] values = line.split(",");
		data.add(values);
	    }
	    // Printing out to let the user know the file has been read
	    // System.out.println(filePath + " Read Successfully");

	} catch (IOException e) {
	    // Error Handling: Letting the user know that the file has not been read
	    // successfully
	    System.err.println("Error reading" + filePath + " file: " + e.getMessage());

	}
	// Returning data - a List of String Arrays or null if the file cannot be read
	return data;

    }
}

/**
 * The Student class creates a Student object along with their student name and
 * number
 */
class Student {
    private long studentId;
    private String studentName;

    /**
     * Constructor for Student.
     *
     * @param studentId   - Contains the ID of a student
     * @param studentName - Contains the name of a student
     */
    public Student(long studentId, String studentName) {
	this.studentId = studentId;
	this.studentName = studentName;
    }

    /**
     * Getter function for studentId
     *
     * @return The Student ID as a long number
     */
    public long getStudentId() {
	return studentId;
    }

    /**
     * Getter function for studentName
     *
     * @return The Student Name as a String
     */

    public String getStudentName() {
	return studentName;
    }

}

/**
 * The Course class creates a Course object along with their student Id,Course
 * code and grades
 */

class Course {
    private long studentId;
    private String courseCode;
    private int[] grades;

    /**
     * Constructor for Course.
     *
     * @param studentId  - Contains the ID of a student,
     * @param courseCode - Contains the Course code of a student
     * @param grades     - Contains The grades of a course.
     *
     *
     */
    public Course(long studentId, String courseCode, int[] grades) {

	this.studentId = studentId;
	this.courseCode = courseCode;
	this.grades = grades;
    }

    /**
     * Getter function for studentID
     *
     * @return The Student ID as a Long value
     */

    public long getStudentId() {
	return studentId;
    }

    /**
     * Getter function for courseCode
     *
     * @return The course code as a String
     */

    public String getCourseCode() {
	return courseCode;
    }

    /**
     * Getter function for grades
     *
     * @return The grades as an array with integer values
     */

    public int[] getGrades() {
	return grades;
    }
}

/**
 * The ProcessData class extends TextReader and processes data from a text file
 * It includes methods for extracting course and student name data
 */

class ProcessData extends TextReader {
    /**
     * Constructor for ProcessData
     *
     * @param filePath - The file path of the input data
     */
    public ProcessData(String filePath) {
	super(filePath);
    }

    /**
     * Extracts course data from the input file.
     *
     * @return A list of Course objects representing the course data.
     */
    public List<Object> courseData() {
	// Reading data
	List<String[]> inputData = readData();
	// Creating a List of objects
	List<Object> data = new ArrayList<>();

	/*
	 * Iterating through the data that has been read and assigning the Lists
	 * StudentId, CourseNumber and grades
	 */
	int ctr = 0;
	for (String[] values : inputData) {
	    ctr++;
	    long studentId = Long.parseLong(values[0]);
	    if (String.valueOf(studentId).length() != 9) {
		System.err.println("Invalid course code: " + studentId + " at line " + ctr + " in " + filePath);
		System.err.println("Data has not been formatted and inputted");
		System.err.println("Make sure the Student Number is 9 digits");

	    }

	    String courseNumber = values[1].trim();

	    // Error Handling
	    if (!isValidCourseCode(courseNumber)) {
		System.err.println("Invalid course code: " + courseNumber + " at line " + ctr + " in " + filePath);
		System.err.println("Data has not been formatted and inputted");
		System.err.println("Make sure the coursecode contains 2 letters and 3 numbers");
		// continue;
	    }

	    int[] grades = new int[values.length - 2];
	    /*
	     * Iterating through values and setting grades to the equivalent grade in the
	     * inputfile
	     */
	    for (int i = 0; i < grades.length; i++) {
		int grade = Integer.parseInt(values[i + 2].trim());
		// Error Handling
		if (!isValidGrade(grade)) {
		    System.err.println("Invalid grade: " + grade + " at line " + i + "in " + filePath);
		    System.err.println("Data has not been formatted and inputted");
		    System.err.println("Make sure the grades is within 0-100");
		    // continue;
		}
		grades[i] = grade;

	    }
	    // Adding course objects to data
	    data.add(new Course(studentId, courseNumber, grades));

	}
	// returning a List of Course Objects
	return data;
    }

    /**
     * Extracts Student Name data from the input file.
     *
     * @return A list of Student objects representing the course data.
     */
    public List<Object> nameData() {
	// Reading data
	List<String[]> inputData = readData();
	// Creating a List of objects
	List<Object> data = new ArrayList<>();
	int dummy = 0;

	// Iterating through inputData to set student ID and Name

	for (String[] values : inputData) {
	    dummy++;
	    long studentId = Long.parseLong(values[0]);
	    String studentName = values[1];

	    if (String.valueOf(studentId).length() != 9) {
		System.err.println("Invalid course code: " + studentId + " at line " + dummy + " in " + filePath);
		System.err.println("Data has not been formatted and inputted");
		System.err.println("Make sure the Student Number is 9 digits");
	    }

	    // Error Handling
	    if (!isValidName(studentName)) {
		System.err.println("Invalid student name: " + studentName + " at line " + dummy + " in: " + filePath);
		System.err.println("Data has not been formatted and inputted");
		System.err.println("Make sure the name only contains English letters and spaces");

		// continue;
	    }

	    // Adding course objects to data
	    data.add(new Student(studentId, studentName));
	}
	// Returning a List of Student Objects
	return data;
    }

    // Private method to validate course code
    private boolean isValidCourseCode(String courseCode) {
	String pattern = "^[A-Za-z]{2}\\d{3}$";
	return Pattern.matches(pattern, courseCode);
    }

    // Private method to validate grade
    private boolean isValidGrade(int grade) {
	return grade >= 0 && grade <= 100;
    }

    // Helper method to validate student name
    private boolean isValidName(String name) {
	String pattern = "^[A-Za-z ]+$";
	return Pattern.matches(pattern, name);
    }

}

/**
 * The TextFormat class extends TextReader and formats data and writes data to
 * an output file. It includes methods for formatting and writing course data It
 * also includes two private functions to find the student number and to
 * calculate the average of grades
 */
class TextFormat extends TextReader {
    private String outputFilePath;

    /**
     * Constructor for TextFormat.
     *
     * @param filePath       - The file path of the input data
     * @param outputFilePath - The file path for writing the formatted data
     */

    public TextFormat(String filePath, String outputFilePath) {
	super(filePath);
	this.outputFilePath = outputFilePath;
    }

    /**
     * Formats course data and writes it to the output file
     *
     * @param dataStudents A list of Student objects
     * @param dataCourses  A list of Course objects
     */
    public void formatAndWriteData(List<Object> dataStudents, List<Object> dataCourses) {
	FileWriter writer = null;
	try {
	    // Setting the filewriter object to the ouptut file
	    writer = new FileWriter(outputFilePath);
	    // Iterating through the Courses object in the List
	    for (Object courseObj : dataCourses) {
		// Checking if courseObj is an object of Course Class
		if (courseObj instanceof Course) {
		    // Creating a Course object and setting it's value as courseOBJ
		    Course course = (Course) courseObj;
		    // FInding and setting studentName
		    long studentId = course.getStudentId();
		    String studentName = findStudentName(dataStudents, studentId);
		    // If Student name exists it calculates the average and writes to the specified
		    // file
		    if (studentName != null) {
			double average = calculateAverage(course.getGrades());
			writer.write(studentId + ", " + studentName.trim() + ", " + course.getCourseCode() + ", "
				+ average + "\n");
		    }
		}
	    }
	    // Printing the status of writing onto the file
	    // System.out.println("Data formatted and written to: " + outputFilePath);
	} catch (IOException e) {
	    System.err.println("Error writing to output file: " + e.getMessage());
	} finally {
	    // Closing writer
	    try {
		if (writer != null) {
		    writer.close();
		}
	    } catch (IOException e) {
		System.err.println("Error closing FileWriter: " + e.getMessage());
	    }
	}
    }

    /**
     * A private function that finds the name of a student with the given student ID
     *
     * @param dataStudents A list of Student objects
     * @param studentId    The ID of the student to find
     * @return The name of the student or null if not found
     */
    private String findStudentName(List<Object> dataStudents, long studentId) {
	// Iterating through dataStudents
	for (Object studentObj : dataStudents) {
	    // Check to see if studentObj is derived from Student
	    if (studentObj instanceof Student) {
		Student student = (Student) studentObj;

		// If the id are the same it returns the student ID
		if (student.getStudentId() == studentId) {
		    return student.getStudentName();
		}
	    }
	}
	// If a matching ID is not found then it returns as null
	return null;
    }

    /**
     * A private functions that calculates the average of an array of grades
     *
     * @param grades An array of grades
     * @return The average of the grades as a double
     */
    private double calculateAverage(int[] grades) {
	int sum = 0;
	// looping through grades
	for (int grade : grades) {
	    sum += grade;
	}
	// returning the sum of grades/length i.e the average
	return (double) sum / grades.length;
    }
}
