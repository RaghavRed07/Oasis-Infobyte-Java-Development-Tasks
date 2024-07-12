import java.util.Random;
import java.util.Scanner;

public class Task2 {
    private static final int Min_Range = 1;
    private static final int Max_Range = 100;
    private static final int Max_Attempts = 10;
    private static final int Max_Rounds = 3;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Total Number of Rounds: 3");
        System.out.println("You have 10 attempts to guess the number.");

        for (int i = 1; i <= Max_Rounds; i++) {
            int Number = random.nextInt(Max_Range - Min_Range + 1) + Min_Range;
            int attempts = 0;

            System.out.printf("Round %d: Guess the Number between %d and %d in %d attempts.%n", i, Min_Range, Max_Range, Max_Attempts);

            while (attempts < Max_Attempts) {
                System.out.print("Enter your guess: ");
                int guess_number = scanner.nextInt();
                attempts++;

                if (guess_number == Number) {
                    int score = Max_Attempts - attempts + 1;
                    totalScore += score;
                    System.out.printf("Congratulations! You guessed the number in %d attempts. Your score is %d.%n", attempts, score);
                    break;
                } else if (guess_number < Number) {
                    System.out.printf("The number is greater than %d. You have %d attempts left.%n", guess_number, Max_Attempts - attempts);
                } else if (guess_number > Number) {
                    System.out.printf("The number is less than %d. You have %d attempts left.%n", guess_number, Max_Attempts - attempts);
                }
            }

            if (attempts == Max_Attempts) {
                System.out.printf("Round %d: You ran out of attempts. The number was %d.%n", i, Number);
            }
        }

        System.out.println("Game over. Your total score is: " + totalScore);
        scanner.close();
    }
}
