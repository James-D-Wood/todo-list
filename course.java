import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class course {
	private String[] checks = new String[10];
	private String courseName;
	
	public course(String fileName) {
		courseName = fileName;
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileName + ".txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int count = 0;
			do {
				this.checks[count] = bufferedReader.readLine();
				count++;
			} while(!this.checks[count-1].equals("done") && count <= 9);
			bufferedReader.close();
			fileReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName() {
		System.out.println(courseName);
		return courseName;
	}
	
	public String[] getChecks() {
		return checks;
	}
	
	public void printChecks() {
		int count = 0;
		System.out.println("\n" + this.courseName + ":");
		while(!this.checks[count].equals("") && count <= 9) {
			System.out.println(this.checks[count]);
			count++;
		}
	}
}
