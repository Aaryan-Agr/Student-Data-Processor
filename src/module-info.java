package Pivot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Base class representing a CSV file reader
class CSVFileReader {
    protected String filePath;

    public CSVFileReader(String filePath) {
	this.filePath = filePath;
    }

    public List<String[]> readData() {
	List<String[]> data = new ArrayList<>();

	try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	    String line;
	    while ((line = reader.readLine()) != null) {
		String[] values = line.split(",");
		data.add(values);
	    }
	} catch (IOException e) {
	    System.err.println("Error reading CSV file: " + e.getMessage());
	}

	return data;
    }
}

// Derived class representing a CSV file formatter
class CSVFileFormatter extends CSVFileReader {
    private String outputFilePath;

    public CSVFileFormatter(String filePath, String outputFilePath) {
	super(filePath);
	this.outputFilePath = outputFilePath;
    }

    public void formatAndWriteData() {
	List<String[]> inputData = readData();
	List<String[]> formattedData = formatData(inputData);

	try (FileWriter writer = new FileWriter(outputFilePath)) {
	    for (String[] values : formattedData) {
		writer.write(String.join(",", values) + "\n");
	    }
	    System.out.println("Data formatted and written to: " + outputFilePath);
	} catch (IOException e) {
	    System.err.println("Error writing to output file: " + e.getMessage());
	}
    }

    private List<String[]> formatData(List<String[]> inputData) {
	// TODO: Add your data formatting logic here
	// Example: Capitalize the first letter of each value
	List<String[]> formattedData = new ArrayList<>();
	for (String[] values : inputData) {
	    String[] formattedValues = new String[values.length];
	    for (int i = 0; i < values.length; i++) {
		formattedValues[i] = values[i].substring(0, 1).toUpperCase() + values[i].substring(1);
	    }
	    formattedData.add(formattedValues);
	}
	return formattedData;
    }
}
