public class SunStorm
{
    public int damageDone;//Represents the health of the Robot

    SunStorm()
    {
        damageDone = 20;
    }
    //Damage the settler when sun storm happens
    public void collideWith(Settler settlerObject)
    {
        if(!settlerObject.IsHidden)
        {
            settlerObject.Lives -= damageDone; //... damageDone variable is in SunStorm/RadioactiveAsteroid Class
            System.out.println("Robot health is: " + settlerObject.Lives);
        }
    }
    
    //Decrease the health of Robot R if it caught 
    //by the sun storm
    public void collideWith(Robot robotObject)
    {
        if(!robotObject.IsHidden)
        {
            robotObject.Lives -= damageDone; //... damageDone variable is in SunStorm/RadioactiveAsteroid Class
            System.out.println("Robot health is: " + robotObject.Lives);
        }
    }
}