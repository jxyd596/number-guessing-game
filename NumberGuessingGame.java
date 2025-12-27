/**
 A number guessing game where the computer generates a
 random number The user must try guess the correct
 number in a limited amount of guesses.
 **/

import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int targetNum = makeNum();
        int lvl = gameDifficulty(scanner);
        int maxChances = levelAndChances(lvl);
        int guess = askNum(scanner);
        int i;

        for (i = 0; i < maxChances; i++){
            if (checkAnswer(targetNum, guess)){
                System.out.println("Well done! You guessed correctly in " + (i + 1) + " attempts.");
                break;
            }
            else{
                int remainingChances = maxChances - (i + 1);
                if (remainingChances == 0) break;
                System.out.println("Incorrect guess. Try again.");
                System.out.println("You have " + remainingChances + " chances left.");
                guess = askNum(scanner);
            }
        }
        // If user runs out of chances
        if (i == maxChances - 1 ){
            System.out.println("Game over! You have run out of attempts.The correct number was: " + targetNum);
        }
   
    }
    
    
    // Generate the random number
    public static int makeNum(){

        Random random = new Random();

        int num = random.nextInt(101);

        return num;
    }

    // Ask user for their guess
    public static int askNum(Scanner scanner){

        int inputNum = 0;

        System.out.println("Enter your guess:");
        try {
            inputNum = Integer.parseInt(scanner.nextLine());
            if (inputNum < 0 || inputNum > 100){
                System.out.println("Invalid input. Please enter an integer between 0 and 100.");
                inputNum = askNum(scanner);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }


        return inputNum;
    }

    // Choose difficulty
    public static int gameDifficulty(Scanner scanner){

        //loop until valid input
        while (true) { 
            System.out.println("Please select difficulty level:");
            System.out.println("1. Easy (10 chances)");
            System.out.println("2. Medium (5 chances)");
            System.out.println("3. Hard (3 chances)");
            System.out.println("Enter your choice (1, 2, or 3):");

            String line = scanner.nextLine();

            try {
                //if input invalid type, catch exception and loop again
                int lvl = Integer.parseInt(line);
                if(lvl>=1 && lvl<=3) {
                    return lvl;
                }
                else{
                    System.out.println("Input out of range. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input type. Input must be an integer. Please enter 1, 2, or 3.");
            }
            
        }
    }


    // Print the difficulty level
    public static int levelAndChances(int level){

        String difficulty;
        int chances;


        difficulty = switch (level){
            case 1 -> "Easy";
            case 2 -> "Medium";
            case 3 -> "Hard";
            default -> {
                System.out.println("Not an option. Enter 1, 2, or 3.");
                yield "";
            }
        };

        chances = switch (level){
            case 1 -> 10;
            case 2 -> 5;
            case 3 -> 3;
            default -> 0;
        };

        System.out.println("You have selected " + difficulty + " difficulty.");

        return chances;
    }

    // Compare the guess to the target number
    public static boolean  checkAnswer(int target, int guess){
        if(guess == target){
            System.out.println("Congratulations! You guessed the correct number: " + target);
            return true;
        } 
        return false;
    }


}
