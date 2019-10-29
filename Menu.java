import java.util.Scanner;
/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu
{
    Game game = new Game();
    private int valid;
    
    public Menu()
    {
        int valid = 0;
    }
    /**
     * display the menu
     */
    public void displayMenu()
    {
        System.out.println ("=========================");
        System.out.println ("A. Play Preliminary Stage");
        System.out.println ("B. Play Final");
        System.out.println ("C. Display Teams");
        System.out.println ("D. Display Players");
        System.out.println ("E. Display Cup Result");
        System.out.println ("X. Close");
        System.out.println ("=========================");
    }

    /**
     * let user to choose the menu
     */
    public String chooseMenu()
    {
        String input = checkInput();
        if (input.equals("A"))
        {
            System.out.println("Now play Preliminary stage");
            game.playPreliminary();
            valid = 2;
        }
        else if (input.equals("B") && valid == 2)
        {
            System.out.println("Now play Final stage");
            game.playFinal();
            valid = 3;
        }
        else if (input.equals("C"))
            game.displayTeams();
        else if (input.equals("D"))
            game.displayPlayers();
        else if (input.equals("E") && valid == 3)
            game.displayCupResults();
        else if (input.equals("X"))
            game.writeToFile("statistics.txt");
        else if (input.equals("B") && valid == 0)
        {
            System.out.println ("The Final Stage must be played after Preliminary Stage");
            displayMenu();
        }
        return input;
    }

    /**
     * check the validation of input
     */
    public String checkInput()
    {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String choose = input.trim().toUpperCase();
        if (choose.equals("A") || choose.equals("B") 
        || choose.equals("C") || choose.equals("D") 
        || choose.equals("E") || choose.equals("X"))
            choose = choose;
        else
        {
            System.out.println ("You can only choose the letter that show in the Menu above");
        }
        return choose;
    }

    public void startGame()
    {
        game.readFromFile();
        Scanner console = new Scanner(System.in);
        System.out.println ("Input player's name");
        String input = "";
        game.enterName(input);
        while (!chooseMenu().equals("X"))
        {
            System.out.println ("Please choose the menu below to start game");
            displayMenu();
            chooseMenu();
        }
    }
}
