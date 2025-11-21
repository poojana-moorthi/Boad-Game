import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class Snakeexcln 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int n = 0;

        try 
            {
            System.out.print("Enter the number of players: ");
            n = sc.nextInt();
            sc.nextLine(); 

            if (n <= 0) 
            {
                throw new IllegalArgumentException("Number of players must be greater than 0!");
            }

            int maxscore = 100;
            String[] player = new String[n];
            int[] position = new int[n]; 

           
            for (int i = 0; i < n; i++) 
            {
                System.out.print("Enter player " + (i + 1) + " name: ");
                player[i] = sc.nextLine();
                position[i] = 0;
            }

        
            int[][] snakes = { {99, 54}, {70, 55}, {52, 42}, {25, 2}, {95, 72} };
            int[][] ladders = { {6, 25}, {11, 40}, {60, 85}, {46, 90}, {17, 69} };

            boolean gameOver = false;
            System.out.println("\n Game Start! Reach exactly 100 to win!\n");

            while (!gameOver) {
                for (int i = 0; i < n; i++) {
                    try {
                        System.out.println(player[i] + "'s turn! Press Enter to roll the dice...");
                        sc.nextLine();

                        int roll = rand.nextInt(6) + 1;
                        System.out.println( player[i] + " rolled a " + roll);

                        int newPos = position[i] + roll;

                        if (newPos > maxscore) 
                        {
                            System.out.println("You need exact roll to reach 100. Stay at " + position[i]);
                        } 
                        else 
                        {
                            boolean climbed = false;
                            for (int[] ladder : ladders) 
                            {
                                if (newPos == ladder[0]) 
                                {
                                    System.out.println("Yay! " + player[i] + " climbed a ladder to " + ladder[1]);
                                    newPos = ladder[1];
                                    climbed = true;
                                    break;
                                }
                            }

                            boolean bitten = false;
                            for (int[] snake : snakes) 
                            {
                                if (newPos == snake[0]) 
                                {
                                    System.out.println("Oh no! " + player[i] + " got bitten by a snake and slid down to " + snake[1]);
                                    newPos = snake[1];
                                    bitten = true;
                                    break;
                                }
                            }

                            position[i] = newPos;
                            if (!climbed && !bitten) 
                            {
                                System.out.println(player[i] + " moved to " + position[i]);
                            }
                        }

                        if (position[i] == maxscore) 
                        {
                            System.out.println(player[i] + " wins the game!");
                            gameOver = true;
                            break;
                        }

                        System.out.println("--------------------------");
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Invalid action. Please press Enter only.");
                        sc.nextLine(); // clear invalid input
                    }
                }
            }
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Invalid input! Please enter a valid number for players.");
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        } 
        catch (Exception e) 
        {
            System.out.println(" Unexpected error: " + e.getMessage());
        } 
        finally 
        {
            System.out.println("Thanks for playing Snake & Ladder!");
            sc.close();
        }
    }
}
