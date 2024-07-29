import java.util.Scanner;

class NumberGenerator {
    public int generate(int upperBound, int lowerBound) {
        return (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }
}

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        NumberGenerator numberGen = new NumberGenerator();
        int totalGuesses = 0;
        int wins = 0;

        while (true) {
            System.out.println("Enter the maximum number:");
            int upperLimit = inputScanner.nextInt();
            System.out.println("Enter the minimum number:");
            int lowerLimit = inputScanner.nextInt();
            inputScanner.nextLine();

            int targetNumber = numberGen.generate(upperLimit, lowerLimit);
            int currentGuesses = 0;

            while (true) {
                System.out.println("Guess a number between " + lowerLimit + " and " + upperLimit + ":");
                int guessedNumber = inputScanner.nextInt();
                currentGuesses++;

                if (guessedNumber > targetNumber) {
                    System.out.println("It's Greater");
                } else if (guessedNumber < targetNumber) {
                    System.out.println("It's Lower");
                } else {
                    System.out.println("Correct Guess");
                    wins++;
                    break;
                }
            }
            totalGuesses += currentGuesses;
            System.out.println("Number of guesses = " + currentGuesses);
            System.out.println("Wins = " + wins);

            double winRate = (double) wins / totalGuesses * 100;
            System.out.printf("Your win rate is %.2f%%\n", winRate);

            System.out.println("Do you want to play again (yes/no)?");
            String playAgain = inputScanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                inputScanner.close();
                System.exit(0);
            }
            inputScanner.nextLine();
        }
    }
}
