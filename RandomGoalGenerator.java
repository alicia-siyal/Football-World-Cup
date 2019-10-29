import java.util.Random;
/**
 * 
 */public class RandomGoalGenerator
{
    private int randomGoal;   
    
    /**
     * default constructor
     */
    public RandomGoalGenerator()
    {
        randomGoal = 0;
    }
    
    /**
     * non-default constructor
     */
    public RandomGoalGenerator(int randomGoal)
    {
        this.randomGoal = randomGoal;
    }
    
    /**
     * Retrieved from week 5 exercise
     * Generate and return a random number between minimun number and the
     * maximum number, inclusive, entered as a parameter
     */
    
    public int generateRandomGoal(int minNumber, int maxNumber)
    {
        return minNumber + (int)(Math.random() * (maxNumber - minNumber+1));
    } 
    
    /**
     * mutator for randomNumber
     */
    public int getRandomGoal()
    {
        return randomGoal;
    }
    
    /**
     * accessor for randomNumber
     */
    public void setRandomGoal(int randomGoal)
    {
        this.randomGoal = randomGoal;
    }
}