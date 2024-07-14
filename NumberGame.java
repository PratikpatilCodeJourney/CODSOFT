package org.example.Task1;

import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxAttempts = 5;
        int score = 0;

        do {

            int randomNumber = (int) (Math.random() * 100) + 1; // Range 1-100
            int attempts = playRound(scanner, randomNumber, maxAttempts);
            score += maxAttempts - attempts + 1;
            System.out.println("Your score is: " + score);
            System.out.print("Play again? (y/n): ");

        } while (scanner.nextLine().equalsIgnoreCase("y"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    public static int playRound(Scanner scanner, int randomNumber, int maxAttempts) {
        System.out.println("Guess a number between 1 and 100 (You have " + maxAttempts + " attempts):");

        int guess;
        for (int attempts = 0; attempts < maxAttempts; attempts++) {
            guess = scanner.nextInt();

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number in " + (attempts + 1) + " attempts.");
                return attempts;
            } else if (guess > randomNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }
        }

        System.out.println("You ran out of attempts. The number was: " + randomNumber);
        return maxAttempts;
    }
}
