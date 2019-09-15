/**
 * Lab 3 - Pig Game with Computer player.
 * @author Steven Archuleta
 * @date 06 Sep 2019
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pig {
    public static void main(String[] args) throws InterruptedException {
        // Intro
        System.out.println("Welcome to the Pig game!!");
        Scanner sc = new Scanner(System.in);

        // Create base score and name variables.
        List<Integer> scores = new ArrayList<>();
        scores.add(0, 0);
        scores.add(1, 0);

        // Player one name entry.
        System.out.println("Player one, enter your name?");
        String playerOneName = sc.nextLine();
        List<String> names = new ArrayList<>();
        names.add(0, "< " + playerOneName+ " >");

        // Player two name entry.
        System.out.println("Player two, enter your name?");
        String playerTwoName = sc.nextLine();
        names.add(1, "< " + playerTwoName+ " >");


        // If user wants to add computer player. Add player and score to lists
        System.out.println("Would you like to play with the computer?\n1 for Yes 0 for No");
        int computer_in = sc.nextInt();
        if (computer_in == 1) {
            names.add(2, "< Computer >");
            scores.add(2, 0);
        }
        // Game run and win variable
        boolean x = true;
        int win = 50;

        // Start game with proper list
        while (x) {
            // begin die roll and set pre-score to hold values during player's turn.
            int pre_score = 0;
            for (int i = 0; i < scores.size(); ) {
                System.out.println("\n/" +names.get(i) + " is up! \\" +
                        "");
                int die_roll = (int) (Math.random() * 6) + 1;

                // all values other than 1 score branch.
                if (die_roll != 1) {
                    System.out.println("  " + names.get(i) + " rolled: " + die_roll);
                    pre_score += die_roll;
                    System.out.println("-> You currently have: " + pre_score );

                    // Human user branch for user input and choice to continue rolling or end turn.
                    if (i <= 1) {
                        System.out.println("Would you like to roll again?\n  1 for Yes 0 for No   ");
                        int roll_again = sc.nextInt();

                        // Stop turn and update player score
                        if (roll_again == 0) {
                            int a = scores.get(i);
                            a += pre_score;
                            scores.set(i, a);

                            // Check if player has won
                            if (a >= win){
                                System.out.println("\n  **" + names.get(i)+" WINS!!**");
                                x = false;
                                break;
                            }
                            // Reset/update iterable variables
                            i++;
                            pre_score = 0;
                            for (int k = 0; k < names.size(); k++) {
                                System.out.println(names.get(k) + ": " + scores.get(k));
                            }

                            // Two player reset for loop for another round
                            if (i == names.size()) {
                                i = 0;
                            }
                        }
                    // AI branch rolls again as long as prelim score is below 10. Updates computer score.
                    } else {
                        if (pre_score > 10) {
                            // Thread.sleep learned @ https://www.mkyong.com/java/java-how-to-delay-few-seconds/
                            // Delay computer player by half a second to improve playability of the game.
                            Thread.sleep(1000);
                            int a = scores.get(i) + pre_score;
                            scores.set(i, a);
                            // Check if computer has won
                            if (a >= win){
                                System.out.println("\n**" + names.get(i)+" WINS!!**");
                                x = false;
                                break;
                            }
                            pre_score = 0;
                            for (int j = 0; j < names.size(); j++) {
                                System.out.println(names.get(j) + ": " + scores.get(j));
                            }
                            // reset i to cycle back to first player
                            i = 0;
                        }
                    }
                // If player roles a one, zero prelim score and update i according to index values of listed variables.
                } else {
                    System.out.println("Sorry " + names.get(i) + ", You rolled a 1\nYour turn is over\n");
                    // Delay for one second after rolling a one to improve playability of the game.
                    Thread.sleep(1000);
                    pre_score = 0;
                    if (i > 1) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
            for (int k = 0; k < names.size(); k++) {
                System.out.println(names.get(k) + ": " + scores.get(k));
            }
        }
    }
}