import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class homeworkcheck {
	
	private static FileWriter fileWriter;
	private static BufferedWriter bufferedWriter;
	private static FileWriter mainFileWriter;
	private static BufferedWriter mainWriter;
	
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Welcome Print Out
		System.out.println("Welcome to the HomeworkCheck v.2018SP");
		
		//Initialize To-Do List Objects
		course CSE310 = new course("CSE310");
		course CSE360 = new course("CSE360");
		course SPA210 = new course("SPA210");
		course MAT300 = new course("MAT300");
		course Personal = new course("Personal");
		
		//Create timestamp string for unique file creation
		String dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString();
		dateTime = dateTime.replace(':', '-');
		String recordFile = "History/todo_"+ dateTime + ".txt";
		
		//Create "current" file for batch file to reference
		String mainFile = "todo.txt";
		
		//initialize FileWriters and write headers
		try {
			fileWriter = new FileWriter(recordFile);
			bufferedWriter = new BufferedWriter(fileWriter);
			mainFileWriter = new FileWriter(mainFile);
			mainWriter = new BufferedWriter(mainFileWriter);
		
			bufferedWriter.write("Courses:");
			bufferedWriter.newLine();
			bufferedWriter.write("-----------------");
			bufferedWriter.newLine();
			
			mainWriter.write("Courses:");
			mainWriter.newLine();
			mainWriter.write("-----------------");
			mainWriter.newLine();	
		} 
		catch (IOException e) {
			System.out.println("Failed to initialize FileWriter/BufferedWriter.");
			e.printStackTrace();
		}
		
		//Initial Instructions
		System.out.println("\nEnter '1' if completed, '0' if incomplete.\n");
		
		//Write responses to files (for coursework)
		writeAssignments(CSE310);
		writeAssignments(CSE360);
		writeAssignments(SPA210);
		writeAssignments(MAT300);
		
		//Create header for personal goals sections
		try {
			bufferedWriter.newLine();
			bufferedWriter.newLine();
			bufferedWriter.write("Chores/Tasks/Goals:");
			bufferedWriter.newLine();
			bufferedWriter.write("-----------------");
			bufferedWriter.newLine();
			
			mainWriter.newLine();
			mainWriter.newLine();
			mainWriter.write("Chores/Tasks/Goals:");
			mainWriter.newLine();
			mainWriter.write("-----------------");
			mainWriter.newLine();
		} catch (IOException e) {
			System.out.println("Failed print to document.");
			e.printStackTrace();
		}
		
		//Write responses to files (for personal goals)
		writeAssignments(Personal);
		
		
		//Close writers
		try {
			bufferedWriter.close();
			fileWriter.close();
			mainWriter.close();
			mainFileWriter.close();
		} catch (IOException e) {
			System.out.println("Failed to properly close FileWriter/BufferedWriter");
			e.printStackTrace();
		}
	}
	
	private static void writeAssignments (course myCourse) {
		System.out.println("\n");
		int count = 0;
		String courseName = myCourse.getName();
		String[] checks = myCourse.getChecks();
		int completed;
		while (count < 10 && !checks[count].equals("done")) {
			System.out.println(checks[count]);
			completed = keyboard.nextInt();
			if (completed == 0) {
				try {
					bufferedWriter.write("() " + courseName + " - " + checks[count]);
					bufferedWriter.newLine();
					
					mainWriter.write("() " + courseName + " - " + checks[count]);
					mainWriter.newLine();
				} catch (IOException e) {
					System.out.println("Failed print to document.");
					e.printStackTrace();
				}
			}
			count++;
		}
	}
}

