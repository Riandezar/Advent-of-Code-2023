import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Day4 {

	public static void main(String[] args) {
		int totalPoints = 0;
		Map<Integer, Integer> numScratchCards = new HashMap<>();
		try {
			//read file
			File file = new File("input.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			int currentCardNumber = 1;
			while((currentLine = br.readLine()) !=null) {
				//Split the line between the winning numbers and the the numbers we have
				String winningNumbers = currentLine.substring(currentLine.indexOf(":") + 1, currentLine.indexOf("|"));
				String myNumbers = currentLine.substring(currentLine.indexOf("|") + 1);
				Scanner winningScanner = new Scanner(winningNumbers);
				//Create sets and covert the string to integers to store into the sets
				Set<Integer> winningList = new HashSet<Integer>();
				Set<Integer> myList = new HashSet<Integer>();
				while(winningScanner.hasNext()) {
					winningList.add(winningScanner.nextInt());
				}
				winningScanner.close();
				Scanner myScanner = new Scanner(myNumbers);
				while(myScanner.hasNext()) {
					myList.add(myScanner.nextInt());
				}
				//Keep only winning numbers that are numbers we have (the number of matches)
				winningList.retainAll(myList);
				//Get how many extra cards for the given card number we are on if no extra's return 0
				int num = Optional.ofNullable(numScratchCards.get(currentCardNumber)).orElse(0);
				//Calculate how many of each new scratch card we get
				//loop for each card (we add 1 for the original card)
				for(int i = 0; i < num+1; i++) {
					for(int j = 0; j < winningList.size(); j++) {
						//Add a card to the next card number for each match we had
						numScratchCards.merge(currentCardNumber+1+j, 1, Integer::sum);
					}
				}
				//Add 1 for the original card we did
				numScratchCards.merge(currentCardNumber, 1, Integer::sum);
				//Calculate the points
				totalPoints += calculatePoints(winningList.size());
				myScanner.close();
				currentCardNumber++;
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(totalPoints);
		int totalCards = numScratchCards.values().stream().mapToInt(Integer::intValue).sum();
		System.out.println(totalCards);
	}
	
	public static int calculatePoints(int numMatches) {
		int numPoints = 0;
		for(int i = 0; i < numMatches; i++) {
			if(i == 0) {
				numPoints = 1;
			}
			else {
				numPoints *= 2;
			}
		}
		return numPoints;
	}
}