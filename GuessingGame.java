import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
public class GuessingGame {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		Record[] top10;
		String fileName = "";
		System.out.print(">>>Do you want to import a specific Top 10? (y/n) ");
		if (in.next().equalsIgnoreCase("y")) {
			System.out.print("\n>>>Enter the file name (with extension): ");
			fileName = in.next();
			top10 = readFile(fileName);
		} else {
			System.out.print("\n>>>Do you want the game to use the default Top 10? (y/n) ");
			if (in.next().equalsIgnoreCase("y")) {
				fileName = "GuessingTop10.txt";
				top10 = readFile(fileName);
			} else {
				top10 = new Record[10];
				for (int i = 0; i < top10.length; i++) {
					Record r = new Record(Record.nullName, Record.nullTries, Record.nullTime, false);
					top10[i] = r;
				}
			}
		}
		System.out.println("\n*******************************************");
		System.out.println("************Current  Scoreboard************");
		System.out.println("*******************************************");
		System.out.println("********SCOREBOARD SHOWS THE TOP 10********");
		System.out.println("*******************************************");
		for (int i = 0; i < top10.length; i++) {
			top10[i].printString();
			System.out.println();
		}
		System.out.println("*******************************************\n");
		System.out.println("\n***Welcome to the Guessing Game!***");
		game(top10, fileName);
		System.out.println("\n***GAME OVER!***");
	}
	public static void game(Record[] top10, String fileName) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		boolean cont = true;
		while(cont) {
			System.out.println("***The number is between 1-1000!***\n");
			int toGuess = rand.nextInt(1000) + 1;
			System.out.print(">>>Your guess: ");
			long startTime = System.currentTimeMillis();
			int userGuess = in.nextInt();
			int tries = 1;
			while(userGuess != toGuess) {
				if (userGuess > toGuess) {
					System.out.println("\n***Too high!***\n");
				} else {
					System.out.println("\n***Too low!***\n");
				}
				System.out.print(">>>Your guess: ");
				userGuess = in.nextInt();
				tries++;
			}
			double finish = (System.currentTimeMillis() - startTime) / 1000.0;
			System.out.println("\n***You guessed correct in " + tries + " tries!***");
			System.out.println("***It took you " + finish + " seconds!***");
			System.out.print("\n>>>Enter your name: ");
			String name = in.next();
			Record r = new Record(name, tries, finish, true);
			top10 = r.sort(top10);
			System.out.println("\n*******************************************");
			System.out.println("********SCOREBOARD SHOWS THE TOP 10********");
			System.out.println("*******************************************");
			for (int i = 0; i < top10.length; i++) {
				top10[i].printString();
				System.out.println();
			}
			System.out.println("*******************************************\n");
			System.out.print(">>>Do you want to play again? (y/n) ");
			if (!in.next().equalsIgnoreCase("y")) {
				cont = false;
				System.out.print("\n>>>Enter y to print to a file other than the input file.\n>>>Do you want to print the Top 10 to a specific file? (y/n) ");
				if (in.next().equalsIgnoreCase("y")) {
					System.out.print("\n>>>Enter the file name with the extension: ");
					String outFile = in.next();
					writeFile(outFile, top10);
					System.out.println("\nSuccessfully wrote to file: " + outFile);
				} else {
					System.out.print("\n>>>Standard output file is the input file.\n>>>If no input file was specified, this won't print.\n>>>Print to the standard output file? (y/n) ");
					if (in.next().equalsIgnoreCase("y")) {
						if (!fileName.equalsIgnoreCase("")) {
							writeFile(fileName, top10);
							System.out.println("\nSuccessfully wrote to file: " + fileName);
						} else {
						}
					} else {
					}
				}
			} else {
				System.out.println();
			}
		}
	}
	
	private static Record[] readFile(String fileName) throws FileNotFoundException {
		File inFile = new File(fileName);
		int count = 0;
		Scanner in = new Scanner(inFile);
		while(in.hasNextLine()) {
			count++;
			in.nextLine();
		}
		in.close();
		in = new Scanner(inFile);
		Record[] top10 = new Record[10];
		if (count > 10) {
			for (int i = 0; i < top10.length; i++) {
				String line = in.nextLine();
				Scanner read = new Scanner(line);
				Record r = new Record(read.next(), read.nextInt(), read.nextDouble(), true);
				top10[i] = r;
			}
		} else {
			int var = 0;
			while (var < 10) {
				if (in.hasNextLine()) {
					String line = in.nextLine();
					Scanner read = new Scanner(line);
					Record r = new Record(read.next(), read.nextInt(), read.nextDouble(), true);
					top10[var] = r;
				} else {
					Record r = new Record(Record.nullName, Record.nullTries, Record.nullTime, false);
					top10[var] = r;
				}
				var++;
			}
		}
		in.close();
		return top10;
	}
	
	private static void writeFile(String fileName, Record[] top10) throws FileNotFoundException {
		File out = new File(fileName);
		PrintWriter pw = new PrintWriter(out);
		for (int i = 0; i < top10.length; i++) {
			if (top10[i].getStatus()) {
				pw.println(top10[i].getName() + " " + top10[i].getTries() + " " + top10[i].getTime());
			}
		}
		pw.close();
	}
}
