import java.util.List;

/**
 * The TextMain a Main class to execute the program
 */
public class TextMain {
    /**
     * Main method to execute the program
     */
    public static void main(String[] args) {
	String inputFile1 = "src/CourseFile.txt";
	String inputFile2 = "src/NameFile.txt";
	String outputFile = "src/output.txt";

	ProcessData dataProcessor = new ProcessData(inputFile1);
	List<Object> courseData = dataProcessor.courseData();

	ProcessData dataProcessor1 = new ProcessData(inputFile2);
	List<Object> nameData = dataProcessor1.nameData();

	TextFormat formatter = new TextFormat(inputFile2, outputFile);
	formatter.formatAndWriteData(nameData, courseData);
    }
}