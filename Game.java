import java.util.*;
import java.lang.Math;
import java.io.*;
/**
 * the game class describes the game process, 
 * including the preliminary stage, final stage, display game records etc.
 * 
 * @author Siya Li
 * @version 20/05/2018
 */
public class Game
{
    private ArrayList<Team> teamList;
    private Team winner;
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        teamList = new ArrayList<Team>();
    }

    /**
     * non-default constructor
     */
    public Game(Team teamA, Team teamB, Team teamC, Team teamD)
    {
        teamList = new ArrayList<Team>();
        teamList.add(teamA);
        teamList.add(teamB);
        teamList.add(teamC);
        teamList.add(teamD);
    }

    /**
     * display cup results for option E
     */
    public void displayCupResults()
    {
        System.out.println ("Football World Cup Winner: " +winner.getTeamName());
        System.out.println ("Golden Boot Award: " + goldenBootAward());
        System.out.println ("Fair Play Award: " + fairAward());
    }

    /**
     * display game results and the records for red cards and yellow cards of each team
     */
    public void displayGameResult(Team team1, Team team2, int r1, int r2, int y1,int y2 , int goal1, int goal2)
    {
        System.out.println ("Game result: " + team1.getTeamName() + "-"+ goal1 + " vs. " + team2.getTeamName() + "-" +goal2);
        System.out.println ("Cards awarded: "  + team1.getTeamName() + " - " + r1 + " red cards, " + y1 + "yellowCards");
        System.out.println ("Cards awarded: " + team2.getTeamName() + " - " + r2 + "red cards, " + y2 + " yellowCards");
    }

    /**
     * display player's information for option D
     */
    public void displayPlayers()
    {
        for (Team team : teamList)
        {
            System.out.println (team.getPlayer1().getName() + "(" + team.getTeamName() + ")" + " - " + team.getPlayer1().getGoal());
            System.out.println (team.getPlayer2().getName() + "(" + team.getTeamName() + ")" + " - " + team.getPlayer2().getGoal());
        }
    }

    /**
     * display the team's information for option C
     */
    public void displayTeams()
    {
        System.out.println("\t\t" + "Played\t" + "Won\t" + "Lost\t" + "Drawn\t" + "Goals\t" + "Points\t" + "FairPlayScore\t");
        for(Team team : teamList)
        {
            System.out.println(team.getTeamName() + "\t" + team.getPlayed() + "\t" + team.getWon() + "\t" 
                +  team.getLost() + "\t" +  team.getDrawn() + "\t" + team.getNumberOfGoals() 
                + "\t" +  team.getOverallPoints() + "\t" +  team.getFairPlayScore());
        }
    }

    /**
     * input player's name for each team
     */
    public void enterName(String input)
    {
        Scanner console = new Scanner(System.in);
        for (int i = 0; i < teamList.size(); i ++)
        {
            int count = 1;
            System.out.println (teamList.get(i).getTeamName() + " player1: ");
            teamList.get(i).getPlayer1().checkName(input);
            //teamList.get(i).getPlayer1().setName(input);
            System.out.println (teamList.get(i).getTeamName() + " player2: ");
            teamList.get(i).getPlayer2().checkName(input);
            //teamList.get(i).getPlayer2().setName(input);
            while (!teamList.get(i).checkSameName() && count < 2 )
            {
                count++;
                System.out.println (teamList.get(i).getTeamName() + " player2: ");
                teamList.get(i).getPlayer2().checkName(input);
                //teamList.get(i).getPlayer2().setName(input);
            }
            if (count == 2)
                teamList.get(i).getPlayer2().setName("defaultPlayer");

            //teamCheckName.checkSameName(teamList.get(i).getPlayer2().getName());
        }
    }

    /**
     * read teams.txt to get team information
     */
    public void readFromFile()
    {
        String filename = "teams.txt";
        Team setTeam = new Team();
        try
        {
            FileReader inputFile = new FileReader(filename);
            try
            {
                Scanner parser = new Scanner(inputFile);
                while (parser.hasNextLine())
                {
                    String[] parserEachLine = parser.nextLine().split(",");
                    Team team = new Team();
                    team.setTeamName(parserEachLine[0]);
                    team.setTeamRanking(Integer.parseInt(parserEachLine[1]));
                    teamList.add(team);

                    //ArrayList<Team> teamList = new ArrayList<Team>();
                    //teamList.add(0).setTeamName();
                    //teamList.get(0).getTeamName();
                }
                System.out.println ("file successfully read");
            }
            finally
            {
                inputFile.close();
                System.out.println ("file closed");
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println (filename + " not found");
        }
        catch (IOException exception)
        {
            System.out.println ("Unexpected I/O exception occurs");
        }
    }

    /**
     * simulate the playing of a game between two teams
     */
    public void playGame(Team team1, Team team2)
    {
        RandomGoalGenerator goal = new RandomGoalGenerator();
        int goal1 = 0;
        int goal2 = 0;
        int red1 = randomCard(16);
        int red2 = randomCard(16);
        int yellow1 = randomCard(4);
        int yellow2 = randomCard(4);
        int gap = Math.abs(team1.getTeamRanking() - team2.getTeamRanking());
        if (team1.getTeamRanking() < team2.getTeamRanking())
        {
            goal1 = goal.generateRandomGoal(0,5+goal.generateRandomGoal(0,2));
            goal2 = goal.generateRandomGoal(0,5+goal.generateRandomGoal(0,2)-gap);
        }
        else
        {
            goal2 = goal.generateRandomGoal(0,5+goal.generateRandomGoal(0,2));
            goal1 = goal.generateRandomGoal(0,5+goal.generateRandomGoal(0,2)-gap);

        }
        team1.setPlayed(team1.getPlayed()+1 );
        team2.setPlayed(team2.getPlayed()+1 );
        team1.setNumberOfGoals(team1.getNumberOfGoals()+goal1);
        team2.setNumberOfGoals(team2.getNumberOfGoals()+goal2);
        distributeGoals(team1,goal1);
        distributeGoals(team2,goal2);
        matchScore(team1,team2,goal1,goal2);
        team1.setRedCards(team1.getRedCards()+red1);
        team1.setYellowCards(team1.getRedCards()+yellow1);
        team2.setRedCards(team2.getRedCards()+red2);
        team2.setYellowCards(team2.getRedCards()+yellow2);
        displayGameResult(team1,team2,red1,red2,yellow1,yellow2,goal1,goal2);
    }


    /**
     * distribute team's total goals with two players in that team
     */
    public void distributeGoals(Team team1,int goal)
    {
        RandomGoalGenerator randomGoal = new RandomGoalGenerator();
        int playerGoals = randomGoal.generateRandomGoal(0, goal);
        team1.getPlayer1().setGoal(team1.getPlayer1().getGoal() + playerGoals);
        team1.getPlayer2().setGoal(team1.getPlayer2().getGoal() + goal - playerGoals);
    }

    public int randomCard(int size)
    {
        RandomGoalGenerator randomNumber = new RandomGoalGenerator();
        int card = 0;
        while(randomNumber.generateRandomGoal(1,size) == size)
        {
            card ++;
        }
        return card;
    }

    /**
     * simulate the playing of a penalty shoot out
     */
    public Team playerPenaltyShootOut(Team team1, Team team2)
    {
        Team winner = new Team();
        RandomGoalGenerator goalPenalty = new RandomGoalGenerator();
        int team1Goals = goalPenalty.generateRandomGoal(0,5) + goalPenalty.generateRandomGoal(0,5);
        int team2Goals = goalPenalty.generateRandomGoal(0,5) + goalPenalty.generateRandomGoal(0,5);
        if (team1Goals > team2Goals)
        {
            winner = team1;
        }
        else if (team1Goals < team2Goals)
        {
            winner = team2;
        }
        else
        {
            playerPenaltyShootOut(team1, team2);
        }
        System.out.println ("Winner is " + winner);
        return winner;
    }

    /**
     * preliminary stage for four teams, totally six matches
     */
    public boolean playPreliminary()
    {
        updateRecords();
        boolean valid = false;
        Team teams = new Team();
        int index = 1;
        for (int i = 0; i < teamList.size(); i ++)
        {
            for (int j = i + 1; j < teamList.size(); j++)
            {
                System.out.println ("This is match " + index);
                //System.out.println (teamList.get(i).getTeamName() + " VS " + teamList.get(j).getTeamName());
                playGame(teamList.get(i), teamList.get(j));

            }
        }
        Collections.sort(teamList);
        valid = true;
        return valid;
    }

    /**
     * score for each match at preliminary stage
     */
    public void matchScore(Team team1,Team team2,int goal1,int goal2)
    {
        if (goal1 > goal2)
        {
            //System.out.println(team1.getTeamName() + "wins!");
            team1.setOverallPoints(team1.getOverallPoints() + 3);
            team1.setWon(team1.getWon() + 1);
            team2.setLost(team2.getLost() + 1);
        }
        else if (goal1 < goal2)
        {
            //System.out.println(team2.getTeamName() + "wins!");
            team2.setOverallPoints(team2.getOverallPoints() + 3);
            team2.setWon(team2.getWon() + 1);
            team1.setLost(team1.getLost() + 1);
        }
        else 
        {
            //System.out.println("Draws!");
            team1.setOverallPoints(team1.getOverallPoints() + 1);
            team2.setOverallPoints(team2.getOverallPoints() + 1);
            team1.setDrawn(team1.getDrawn() + 1);
            team2.setDrawn(team2.getDrawn() + 1);
        }
    }

    /**
     * simulate of playing final game
     */
    public void playFinal()
    {
        RandomGoalGenerator goal = new RandomGoalGenerator();
        int randomNumber = goal.generateRandomGoal(0,2);
        int finalGoal1 = 5 + randomNumber;
        int finalGoal2 = 5 - Math.abs(1 - 2) + randomNumber;
        if (finalGoal1 == finalGoal2)
        {
            System.out.println ("Drawns, penalty shoot out will be played");
            this.winner = playerPenaltyShootOut(teamList.get(0), teamList.get(1));
        }
        else if (finalGoal1 > finalGoal2)
        {
            this.winner = teamList.get(0);
            System.out.println ("Football World Cup Winner: " + winner.getTeamName()); //winner = teamlist.get(0)
        }
        else
        {
            this.winner = teamList.get(1);
            System.out.println ("Football World Cup Winner: " + winner.getTeamName());
        }
    }

    /**
     * to refresh the data of each game
     */
    public void updateRecords()
    {
        for (int i = 0; i < teamList.size(); i ++)
        {
            teamList.get(i).setPlayed(0);
            teamList.get(i).setWon(0);
            teamList.get(i).setLost(0);
            teamList.get(i).setDrawn(0);
            teamList.get(i).setNumberOfGoals(0);
            teamList.get(i).setOverallPoints(0);
            teamList.get(i).setFairPlayScore(0);
        }
    }

    /**
     * total marks for determine Cup's Fair Play Award, the lower the total marks, the faier the team
     */
    public void fairPlayScore()
    {
        //int fairPlayScore = 0;
        for (Team team : teamList)
        {
            team.setFairPlayScore(team.getFairPlayScore() + team.getRedCards() * 2 + team.getYellowCards());
        }
    }

    /**
     * determine gold boot award for each game
     */
    public String goldenBootAward()
    {
        int maxGoal = 0;
        String name = "";
        for (Team team: teamList)
        {
            if(team.getPlayer1().getGoal() > maxGoal)
                maxGoal = team.getPlayer1().getGoal();
            if(team.getPlayer2().getGoal() > maxGoal)
                maxGoal = team.getPlayer2().getGoal();
        }

        for (Team team: teamList)
        {
            if(team.getPlayer1().getGoal() == maxGoal)
                name  = name + team.getPlayer1().getName()+ " Form " + team.getTeamName() + "\n";
            if(team.getPlayer2().getGoal() == maxGoal)
                name  = name + team.getPlayer1().getName()+ " Form " + team.getTeamName()+ "\n";
        }
        /*
        for (int i = 0; i < teamList.size(); i ++)
        {
        for (int j = 1; j < teamList.size() - i; j ++)
        {
        if (teamList.get(i).getNumberOfGoals() > teamList.get(j).getNumberOfGoals())
        {
        winner = teamList.get(i);
        System.out.println ("Golden Boot Award: " + winner);
        }
        else if (teamList.get(i).getNumberOfGoals() > teamList.get(j).getNumberOfGoals())
        {
        winner = teamList.get(j);
        System.out.println ("Golden Boot Award: " + winner);
        }
        else
        System.out.println ("Golden Boot Award: " + teamList.get(i).getTeamName() + ", " + teamList.get(j).getTeamName());
        }
        } */
        return name;
    }

    /**
     * determine fair play score for each game
     */
    public String fairAward()
    {
        int minScore = 100000000;
        String teamNames = "";
        for (Team team: teamList)
        {
            if(team.getFairPlayScore() < minScore)
                minScore = team.getFairPlayScore();

        }

        for (Team team: teamList)
        {
            if(team.getFairPlayScore() == minScore)
                teamNames = teamNames + " " +team.getTeamName();
        }

        /*
        Team winner = teamList.get(0);
        for (int i = 0; i < teamList.size(); i ++)
        {
        for (int j = 1; j < teamList.size() - i; j ++)
        {
        if (teamList.get(i).getFairPlayScore() < teamList.get(j).getFairPlayScore())
        {
        winner = teamList.get(i);
        System.out.println ("Fair Play Award: " + winner);
        }
        else if (teamList.get(i).getFairPlayScore() > teamList.get(j).getFairPlayScore())
        {
        winner = teamList.get(j);
        System.out.println ("Fair Play Award: " + winner);
        }
        else
        System.out.println ("Fair Play Award: " + teamList.get(i).getTeamName() + ", " + teamList.get(j).getTeamName());
        }
        } 
        return winner;
        //Collections.sort(teamList);
        //String winner = teamList.get(0).getTeamName();
        //return winner;
         */
        return teamNames;
    }

    /**
     * write the information into files statistics.txt
     */
    public void writeToFile(String fileName)
    {
        fileName = ("statistics.txt");
        try
        {
            PrintWriter outputFile = new PrintWriter(fileName);
            try
            {
                outputFile.println("Football World Cup Winner: " +winner.getTeamName());
                outputFile.println("Golden Boot Award: " + goldenBootAward());
                outputFile.println("Fair Play Award: " + fairAward());
            }
            finally
            {
                System.out.println("Close the file");
                outputFile.close();
                //System.out.println ("end game");
            }
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs");
        }
    }
}
