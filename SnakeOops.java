import java.util.*;

class Player 
{
    private String name;
    private int position;

    public Player(String name) 
    {
        this.name = name;
        this.position = 0; 
    }

    public String getName() 
    {
        return name;
    }

    public int getPosition() 
    {
        return position;
    }

    public void setPosition(int pos) 
    {
        this.position = pos;
    }

    public boolean hasWon(int maxScore) 
    {
        return this.position == maxScore;
    }
}

class Dice 
{
    private Random rand;

    public Dice() 
    {
        rand = new Random();
    }

    public int roll() 
    {
        return rand.nextInt(6) + 1;
    }
}

class Board 
{
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;
    private final int MAX_SCORE = 100;

    public Board() 
    {
        snakes = new HashMap<>();
        ladders = new HashMap<>();

        snakes.put(99, 54);
        snakes.put(70, 55);
        snakes.put(52, 42);
        snakes.put(25, 2);
        snakes.put(95, 72);

        ladders.put(6, 25);
        ladders.put(11, 40);
        ladders.put(60, 85);
        ladders.put(46, 90);
        ladders.put(17, 69);
    }

    public int getMaxScore() 
    {
        return MAX_SCORE;
    }

    public int checkPosition(int pos) 
    {
        if (snakes.containsKey(pos)) 
        {
            System.out.println("Oh no! You got bitten by a snake!");
            return snakes.get(pos);
        } 
        else if (ladders.containsKey(pos)) 
        {
            System.out.println("🪜 Yay! You climbed a ladder!");
            return ladders.get(pos);
        }
        return pos;
    }
}

class SnakeLadderGame 
{
    private Player[] players;
    private Dice dice;
    private Board board;
    private Scanner sc;

    public SnakeLadderGame(int numPlayers, Scanner sc) 
    {
        this.sc = sc;
        this.dice = new Dice();
        this.board = new Board();
        this.players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) 
        {
            System.out.print("Enter player " + (i + 1) + " name: ");
            String name = sc.nextLine();
            players[i] = new Player(name);
        }
    }

    public void startGame() 
    {
        boolean gameOver = false;
        System.out.println("\n Game Start! Reach exactly 100 to win!\n");

        while (!gameOver) 
        {
            for (Player player : players) 
            {
                System.out.println(player.getName() + ", press Enter to roll the dice...");
                sc.nextLine();

                int roll = dice.roll();
                System.out.println("You rolled a " + roll);

                int newPos = player.getPosition() + roll;

                if (newPos > board.getMaxScore()) 
                {
                    System.out.println("You need exact roll to reach 100. Try next turn!");
                } 
                else 
                {
                    newPos = board.checkPosition(newPos);
                    player.setPosition(newPos);
                    System.out.println(player.getName() + " is now at position " + newPos);
                }

                if (player.hasWon(board.getMaxScore())) {
                    System.out.println("\n" + player.getName() + " wins the game!");
                    gameOver = true;
                    break;
                }

                System.out.println("--------------------------");
            }
        }
    }
}


public class SnakeOops 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        SnakeLadderGame game = new SnakeLadderGame(n, sc);
        game.startGame();

        sc.close();
    }
}
