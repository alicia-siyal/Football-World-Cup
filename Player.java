import java.lang.*;
import java.util.Scanner;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private int goal;

    /**
     * Default Constructor for objects of class Player
     */
    public Player()
    {
        name = "";
        goal = 0;
    }

    /**
     * Non-default Constructor for objects of class Player
     */
    public Player(String name, int goal)
    {
        this.name = name;
        this.goal = goal;
    }

    /**
     * Accessor for name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Accessor for goal
     */
    public int getGoal()
    {
        return goal;
    }

    /**
     * Mutator for name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Mutator for goal
     */
    public void setGoal(int goal)
    {
        this.goal = goal;
    }

    /**
     * check name is alphabetical character
     */
    public boolean isCharacter(String input)
    {
        boolean valid = true;
        for (int i = 0; i < input.length(); i++)
        {
            if (!Character.isLetter(input.charAt(i)))
            {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * check if there is at most one hyphen 
     */
    public boolean checkHyphen(String input)
    {
        boolean valid = true;
        int condition = 0;
        int hyphenNum = 0;
        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(0) == '-' || input.charAt(input.length() - 1) == '-')
            {
                condition = 1;
                valid = false;
            }
            else if (input.charAt(i) == '-')
            {
                hyphenNum ++;
            }
            if (hyphenNum > 1)
            {
                condition = 2;
                valid = false;
            }
        }
        if (condition == 1)
            System.out.println ("Your name cannot begin or end with a hyphen");
        else if (condition == 2)
            System.out.println ("Your name can only contain at most one hyphen");
        return valid;
    }

    /**
     * check the input name length is within resonable range 2-20
     */
    public boolean checkLength(String input)
    {
        boolean valid = true;
        Scanner console = new Scanner(System.in);
        if ((input.length() < 2) || (input.length() > 20)) //if the player input nothing or the length of input is more than 20 characters
        {
            System.out.println("Your name must be within 2-20 characters or not empty");
            valid = false;
        }
        return valid;
    }

    /**
     * check the input name is valid
     */
    public boolean checkName(String playerName)
    {
        boolean valid = false;
        Scanner console = new Scanner(System.in);
        for (int enterTime = 1; enterTime < 3; enterTime ++)
        {
            playerName = console.nextLine().trim();
            if (checkLength(playerName))
            {
                if(checkHyphen(playerName))
                {
                    if(checkNameChar(playerName))
                    {    
                        setName(playerName);
                        valid = true;
                        enterTime = 10;
                    }
                }  
            }
            if (!valid && enterTime == 2)
            {
                System.out.print("no other chance to enter, a default name will be allocated: ");
                playerName = "Mr-Player";
                setName(playerName);
                System.out.println (getName());
            }
        }
        return valid;
    }

    /**
     * check the name is character before and after the hyphen
     */
    public boolean checkNameChar(String input)
    {
        boolean valid = true;
        for (int i = 0; i < input.length(); i ++)
        {
            if (input.charAt(i) == '-')
            {
                String[] inputSplit = new String[2];
                inputSplit = input.split("-");
                for (int index = 0; index < 2; index ++)
                {
                    valid = isCharacter(inputSplit[index]);
                }
            }
            else if (input.indexOf('-') == -1)
                valid = isCharacter(input);
        }
        if (valid == false)
            System.out.println ("Your name can only contain alphabetical characters and at most one hyphen");
        return valid;
    }
}
