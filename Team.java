import java.io.*;
import java.util.*;
/**
 * Write a description of class Team here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Team implements Comparable
{
    private String teamName;
    private int teamRanking;
    private Player player1;
    private Player player2;
    private int redCards;
    private int yellowCards;
    private int played;
    private int won;
    private int lost;
    private int drawn;
    private int numberOfGoals;
    private int overallPoints;
    private int fairPlayScore;

    /**
     * Constructor for objects of class Team
     */
    public Team ()
    {
        teamName = "";
        teamRanking = 0;
        player1 = new Player("playerOne " + teamName, 0);
        player2 = new Player("playerTwo " + teamName, 0);
        redCards = 0;
        yellowCards = 0;
        played = 0;
        won = 0;
        drawn = 0;
        lost = 0;
        numberOfGoals = 0;
        overallPoints = 0;
        fairPlayScore = 0;
    }

    /**
     * non-default constructor for class Team
     */
    public Team (String teamName, int teamRanking, Player player1, Player player2, 
    int numberOfGoals, int overallPoints, int redCards, int yellowCards, int played, 
    int won, int drawn, int lost, int fairPlayScore)
    {
        this.teamName = teamName;
        this.teamRanking = teamRanking;
        this.player1 = player1;
        this.player2 = player2;
        this.numberOfGoals = numberOfGoals;
        this.overallPoints = overallPoints;
        this.redCards = redCards;
        this.yellowCards = yellowCards;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.fairPlayScore = fairPlayScore;
    }

    /**
     * accessor for teamName
     */
    public String getTeamName()
    {
        return teamName;
    }

    /**
     * accessor for teamRanking
     */
    public int getTeamRanking()
    {
        return teamRanking;
    }

    /**
     * accessor for total goals that a team have
     */
    public int getNumberOfGoals()
    {
        return numberOfGoals;
    }

    /**
     * accessor for overall points that a team have
     */
    public int getOverallPoints()
    {
        return overallPoints;
    }

    /**
     * accessor for player1
     */
    public Player getPlayer1()
    {
        return player1;
    }

    /**
     * accessor for player2
     */
    public Player getPlayer2()
    {
        return player2;
    }
    
    /**
     * accessor for number of red cards
     */
    public int getRedCards()
    {
        return redCards;
    }

    /**
     * accessor for number of yellow cards
     */
    public int getYellowCards()
    {
        return yellowCards;
    }
    
    /**
     * accessor for won
     */
    public int getWon()
    {
        return won;
    }
    
    /**
     * accessor for played
     */
    public int getPlayed()
    {
        return played;
    }
    
    /**
     * accessor for lost
     */
    public int getLost()
    {
        return lost;
    }
    
    /**
     * accessor for drawn
     */
    public int getDrawn()
    {
        return drawn;
    }
    
    /**
     * accessor for fairPlayScore
     */
    public int getFairPlayScore()
    {
        return fairPlayScore;
    }
    
    /**
     * mutator for team name
     */
    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    /**
     * mutator for team ranking
     */
    public void setTeamRanking(int teamRanking)
    {
        this.teamRanking = teamRanking;
    }

    /**
     * mutator for total goals for a team
     */
    public void setNumberOfGoals(int NumberOfGoals)
    {
        this.numberOfGoals = numberOfGoals;
    }

    /**
     * mutator for overall points that a team have
     */
    public void setOverallPoints(int overallPoints)
    {
        this.overallPoints = overallPoints;
    }

    /**
     * mutator for number of red cards
     */
    public void setRedCards(int redCards)
    {
        this.redCards = redCards;
    }

    /**
     * mutator for number of yellow cards
     */
    public void setYellowCards(int yellowCards)
    {
        this.yellowCards = yellowCards;
    }
    
    /**
     * mutator for won
     */
    public void setWon(int won)
    {
        this.won = won;
    }
    
    /**
     * mutator for  played
     */
    public void setPlayed(int played)
    {
        this.played = played;
    }
    
    /**
     * mutator for lost
     */
    public void setLost(int lost)
    {
        this.lost = lost;
    }
    
    /**
     * mutator for drawn
     */
    public void setDrawn(int drawn)
    {
        this.drawn = drawn;
    }
    
    /**
     * mutator for fairPlayScore
     */
    public void setFairPlayScore(int fairPlayScore)
    {
        this.fairPlayScore = fairPlayScore;
    }
    
    /**
     * check the duplicate name in one team
     */
    public boolean checkSameName()
    {
        boolean valid = true;
        if (player2.getName().equals(player1.getName()))
        {
            System.out.println ("Two players in one team cannot have the same name");
            valid = false;
        }
        return valid;
    }

    /**
     * randomly generate red card
     */
    public void redCard()
    {
        RandomGoalGenerator randomNumber = new RandomGoalGenerator();
        int redCard = randomNumber.generateRandomGoal(0,4);
        if (redCard == 4)
        {
            redCard ++;
            System.out.println (teamName + "fouls, red card");
        }
    }

    /**
     * randomly generate yellow card
     */
    public void yellowCard()
    {
        RandomGoalGenerator randomNumber = new RandomGoalGenerator();
        int yellowCard = randomNumber.generateRandomGoal(0,1);
        if (yellowCard == 1)
        {
            yellowCard ++;
            System.out.println (teamName + "fouls, yellow card");
        }
    }
    
    /**
     * Retrived from http://www.cnblogs.com/fzzl/archive/2010/08/14/1799408.html
     */
    public int compareTo(Object o) 
    {
        // TODO Auto-generated method stub
        int res = 0;
        Team target = (Team) o;
        if(this.getOverallPoints() > target.getOverallPoints())
            res = -1; //desc
        if(this.getOverallPoints() < target.getOverallPoints())
            res = 1; //aesc
        if(res == 0)
        {
            if(this.getNumberOfGoals() > target.getNumberOfGoals())
                res = -1;
            if(this.getNumberOfGoals() < target.getNumberOfGoals())
                res = 1;
        }
        return res;
    }
}
