import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pig {
    public static void main(String[] args) {
        System.out.println("Welcome to the Pig game!!");
        Scanner sc = new Scanner(System.in);
        // Create score variables in list as learned on https://www.geeksforgeeks.org/list-interface-java-examples/
        List<Integer> scores = new ArrayList<>();
        scores.add(0, 0);
        scores.add(1, 0);

        List<String> names = new ArrayList<>();
        names.add(0, "Player 1");
        names.add(1, "Player 2");

        System.out.println("Would you like to play with the computer?\n1 for Yes 0 for No");
        int computer_in = sc.nextInt();
        if (computer_in == 1) {
            names.add(2, "Computer");
            scores.add(2, 0);
        }
        // Start basic two player version of game
        while (scores.get(0) <= 100 && scores.get(1) <= 100) {
            int pre_score = 0;
            for(int i = 0; i < scores.size();){
                System.out.println(names.get(i) +" is up!");
                int die_roll = (int)(Math.random()*6)+1;
                if(die_roll != 1){
                    System.out.println(names.get(i) + " rolled a " + die_roll);
                    pre_score += die_roll;
                    System.out.println("You have " +pre_score);
                    System.out.println("Would you like to roll again?\n1 for Yes 0 for No");
                    int roll_again = sc.nextInt();
                    if(roll_again == 0 ){
                        int a = scores.get(i);
                        a += pre_score;
                        scores.set(i,a);
                        i++;
                        pre_score = 0;
                        System.out.println(scores+"\n"+names);
                        if(i==names.size()){
                            i = 0;
                        }
                    }
                }else{
                    System.out.println("Sorry " + names.get(i) + ", You rolled a 1\nYour turn is over");
                    i++;
                    pre_score = 0;
                }
            }
            System.out.println(scores+"\n"+names);
        }
    }
}