import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day1 {
	
	public static void main(String[] args) {
		
		final String REGEX = "(\\d|one|two|three|four|five|six|seven|eight|nine)";
		
		//running sum;
		int sum = 0;
		try {
		File file = new File("input.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String currentLine;
		Pattern pat = Pattern.compile(REGEX);
		
		while((currentLine = br.readLine()) !=null) {
			Matcher mat = pat.matcher(currentLine);
			//System.out.println(currentLine);
			List<String> numbers = mat.results().map(MatchResult::group).collect(Collectors.toList());
			//Only single digits are allowed if length is greater than 1 its a word
			if(numbers.get(0).length() > 1) {
				numbers.set(0, convertStringToNumber(numbers.get(0)));
			}
			if(numbers.get(numbers.size()-1).length() > 1) {
				numbers.set(numbers.size()-1, convertStringToNumber(numbers.get(numbers.size()-1)));
			}
			
			String fullNumber = numbers.get(0) + numbers.get(numbers.size()-1);
			sum += Integer.parseInt(fullNumber);
			}
		
		fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(sum);	
	}
	
	private static String convertStringToNumber(String wordNumber) {
		switch(wordNumber) {
		case "one":
			return "1";
		case "two":
			return "2";
		case "three":
			return "3";
		case "four":
			return "4";
		case "five":
			return "5";
		case "six":
			return "6";
		case "seven":
			return "7";
		case "eight":
			return "8";
		}
		return "9";
	}
}