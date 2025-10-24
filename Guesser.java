import java.util.Random;
import java.util.Scanner;

public class Guesser {

    private static final Scanner in = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Please enter 0-2: ");
            switch (choice) {
                case 0:
                    System.out.println("Bye");
                    return;
                case 1:
                    humanGuesser();
                    break;
                case 2:
                    computerGuesser();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("0) Exit");
        System.out.println("1) Human Guesser");
        System.out.println("2) Computer Guesser");
        System.out.println();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (in.hasNextInt()) {
                int v = in.nextInt();
                in.nextLine();
                return v;
            } else {
                String trash = in.nextLine();
                System.out.println("Not an integer: \"" + trash + "\". Try again.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private static void humanGuesser() {
        System.out.println("human guesser");
        int target = rand.nextInt(100) + 1;
        int tries = 0;

        while (true) {
            tries++;
            int guess = readInt(tries + ") Please enter a number (1-100): ");
            if (guess < 1 || guess > 100) {
                System.out.println("Number must be in [1,100].");
                continue;
            }
            if (guess < target) {
                System.out.println("too low...");
            } else if (guess > target) {
                System.out.println("too high...");
            } else {
                System.out.println("got it!");
                System.out.println("Very good! You used " + tries + " tries.");
                break;
            }
        }
    }

    private static void computerGuesser() {
        System.out.println("computer guesser");
        int low = 1, high = 100;
        int round = 0;

        while (low <= high) {
            round++;
            int guess = low + (high - low) / 2;
            System.out.println(round + ") I guess " + guess);

            String ans = readLine("Too (H)igh, too (L)ow, or (C)orrect? ");
            if (ans.isEmpty()) {
                System.out.println("Please enter H, L, or C.");
                round--;
                continue;
            }

            char c = Character.toLowerCase(ans.charAt(0));
            if (c == 'h') {
                high = guess - 1;
            } else if (c == 'l') {
                low = guess + 1;
            } else if (c == 'c') {
                System.out.println("Nice! I get it in " + round + " steps.");
                return;
            } else {
                System.out.println("Please enter H, L, or C.");
                round--;
            }
        }

        System.out.println("Your feedback was inconsistent. No number fits the answers given.");
    }
}
