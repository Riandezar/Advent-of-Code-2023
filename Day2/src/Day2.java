import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day2 {
	
	public static void main(String[] args) {
		//Read File		
		int currId = 1;
		int sum = 0;
		int powerSum = 0;
		try {
		File file = new File("input.txt");
		FileReader fr = new FileReader(file);
		Scanner scanner = new Scanner(file);
		//Parse through each line in file
		while(scanner.hasNextLine()) {
			//We assume its a valid line
			boolean valid = true;
			//Trim the game number off the string before splitting
			String line = scanner.nextLine();
			line = line.substring(line.indexOf(":") + 1);
			line.trim();
			//Split the string to its individual pulls which are separated by , and ;
			String[] split = line.split(",|;");
			//Look at each individual pull
			for(int i = 0; i < split.length; i++) {
				//System.out.println(split[i]);
				String current = split[i];
				Scanner currentString = new Scanner(current);
				int value = currentString.nextInt();
				//System.out.println(value);
				currentString.close();
				//Check if the pull is valid all pulls greater than 14 are invalid
				if(value > 14) {
					valid = false;
					break;
				}
				//Only blue pulls greater than 13 are valid
				else if(value > 13 && !current.contains("blue")) {
					valid = false;
					break;
				}
				//Only red pulls greater than 12 are invalid
				else if(value > 12 && current.contains("red")) {
					valid = false;
					break;
				}
			}
			String[] splitGame = line.split("\n");
			for(int i = 0; i < splitGame.length; i++) {
				int maxRed = 0;
				int maxBlue = 0;
				int maxGreen = 0; 
				String[] splitColor = line.split(",|;");
				for(int j = 0; j < splitColor.length; j++) {
					Scanner currentString = new Scanner(splitColor[j]);
					int value = currentString.nextInt();
					currentString.close();
					if(splitColor[j].contains("red") && value > maxRed) {
						maxRed = value;
					}
					if(splitColor[j].contains("blue") && value > maxBlue) {
						maxBlue = value;
					}
					if(splitColor[j].contains("green") && value > maxGreen) {
						maxGreen = value;
					}
				}
				//Multiply the max together and add it to the power sum
				powerSum += maxRed * maxBlue * maxGreen;
			}	
			//If its a valid line add to running sum
			if(valid) {
				sum += currId;
			}
			//Next game counter
			currId++;
		}
		fr.close();
		scanner.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Valid game sum is " + sum);
		System.out.println("Sum of Power of sets is " + powerSum);
	}
}