import java.util.Scanner;

import logic.WordCounter;

public class Application {

	public static void main(String[] args) {

		System.out.println("Word Frequency Counter application. Enter input as a sentence in one line. Type \"exit\" to terminate the app.");
		WordCounter wc = new WordCounter();

		boolean running = true;
		do {
			System.out.println("\nInput: ");
			Scanner scanner = new Scanner(System.in);
			String str = scanner.nextLine();
			
			// Check for "exit" command to terminate the application
			if (str.equalsIgnoreCase("exit")) {
				scanner.close();
				running = false;
				System.out.println("Word Frequency Counter application closed.");
			}
			else {
				System.out.println(wc.getWordFrequency(str));
			}
		}
		while (running);
		
		System.exit(0);
	}

}
