package daily;

import java.util.Random;
import java.util.Scanner;
public class GuessingGame {
	public static void main(String[] args) {
		Record[] top10 = new Record[10];
		for (int i = 0; i < top10.length; i++) {
			Record r = new Record(Record.nullName, Record.nullTries, Record.nullTime);
			top10[i] = r;
		}
		System.out.println("***Welcome to the Guessing Game!***");
		game(top10);
		System.out.println();
		System.out.println();
		System.out.println("***GAME OVER***");
	}
	public static void game(Record[] top10) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		boolean cont = true;
		while(cont) {
			System.out.println("***The number is between 1-1000!***");
			System.out.println();
			int toGuess = rand.nextInt(1000) + 1;
			System.out.println("//The number to guess = " + toGuess + "\\\\");
			System.out.println();
			System.out.print(">>>Your guess: ");
			long startTime = System.currentTimeMillis();
			int userGuess = in.nextInt();
			int tries = 1;
			while(userGuess != toGuess) {
				if (userGuess > toGuess) {
					System.out.println();
					System.out.println("***Too high!***");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("***Too low!***");
					System.out.println();
				}
				System.out.println();
				System.out.print(">>>Your guess: ");
				userGuess = in.nextInt();
				tries++;
			}
			double finish = (System.currentTimeMillis() - startTime) / 1000.0;
			System.out.println();
			System.out.println("***You guessed correct in " + tries + " tries!***");
			System.out.println("***It took you " + finish + " seconds!***");
			System.out.println();
			System.out.print(">>>Enter your name: ");
			String name = in.next();
			Record r = new Record(name, tries, finish);
			top10 = r.sort(top10);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("*******************************************");
			System.out.println("********SCOREBOARD SHOWS THE TOP 10********");
			System.out.println("*******************************************");
			for (int i = 0; i < top10.length; i++) {
				top10[i].printString();
				System.out.println();
			}
			System.out.println("*******************************************");
			System.out.println();
			System.out.println();
			System.out.print(">>>Do you want to play again? (y/n) ");
			if (!in.next().equalsIgnoreCase("y")) {
				cont = false;
			}
		}
	}
}
