import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsPlayed = 0;
        int roundsWon = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Step 1: random number 1–100
            int attemptsAllowed = 7; // Step 5: limit attempts
            int attemptsTaken = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI have picked a number between 1 and 100.");
            System.out.println("You have " + attemptsAllowed + " attempts to guess it!");

            // Step 2, 3, 4: Guess loop
            while (attemptsTaken < attemptsAllowed) {
                System.out.print("Enter your guess: ");
                int guess;

                // Input validation
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // clear invalid input
                    continue;
                }

                guess = scanner.nextInt();
                attemptsTaken++;

                if (guess == numberToGuess) {
                    System.out.println("Correct! You guessed it in " + attemptsTaken + " attempts.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("📉 Too low! Try again.");
                } else {
                    System.out.println("📈 Too high! Try again.");
                }

                System.out.println("Attempts left: " + (attemptsAllowed - attemptsTaken));
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The correct number was: " + numberToGuess);
            }

            roundsPlayed++;

            // Step 6: Multiple rounds option
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("yes");
        }

        // Step 7: Final score
        System.out.println("\nGame Over!");
        System.out.println("Rounds Played: " + roundsPlayed);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Score: " + roundsWon + "/" + roundsPlayed + " rounds won ");
        System.out.println("Thanks for playing! ");

        scanner.close();
    }
}
