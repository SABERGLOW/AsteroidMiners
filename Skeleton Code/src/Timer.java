public class Timer extends Settler
{
    public int healthReviveTime;
    public int sunStormTime = 5;
    public int travelTime = 3;
    public int drillTime = 1;
    public int mineTime = 1;

    public int ReviveTimeCalculator(int Lives)
    {
        healthReviveTime = (100-Lives)%10;
        return healthReviveTime;
    }

}
