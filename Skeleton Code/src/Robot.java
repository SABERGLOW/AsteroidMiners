import java.math.*;

public class Robot extends Settler
{

    public void Damage(int damageDone)
    {
        Lives -= damageDone; //... damageDone variable is in SunStorm/RadioactiveAsteroid Class
        System.out.println("Robot health is: " + Lives);
    }

    //... add asteroid as an argument
    public void Drill(Asteroid asteroidObject, Robot robotObject, AsteroidMap asteroidMapObject, int[][] asteroidMap) //... function decreases the Asteroid Depth ...//
    {
        if(asteroidObject.isRadioactive && asteroidObject.depth == 1)
        {
            //... Robot is dumb, drills radioactive asteroid and gets damaged ...//
            RadioactiveAsteroid radioactiveAsteroidObject = new RadioactiveAsteroid(true);
            asteroidObject.depth = 0;
            radioactiveAsteroidObject.Explode(asteroidObject, robotObject);

            //... Robot travels to nearest Asteroid ...//
            TravelToNearestAsteroid(robotObject, asteroidMapObject, asteroidMap);
        }
        if(!asteroidObject.isRadioactive && asteroidObject.depth > 0)
        {
            asteroidObject.depth = 0; // drilled all the way to core //

            //... Robot travels to nearest Asteroid ...//
            TravelToNearestAsteroid(robotObject, asteroidMapObject, asteroidMap);
        }
    }

    public void TravelToNearestAsteroid(Robot robotObject, AsteroidMap asteroidMapObject, int[][] asteroidMap)
    {
        //... find nearest asteroid ...//
        double euclidean = 0.0f;
        double nearest = 100.0f;
        int destinationX = 0;
        int destinationY = 0;
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                //... Calculate euclidean Distance ...//
                if(asteroidMap[i][j] == 1)
                {
                    euclidean = Math.sqrt(i*i + j*j);
                    if(euclidean <= nearest)
                    {
                        nearest = euclidean;
                        destinationX = i;
                        destinationY = j;
                    }
                }
            }
        }
        //... update robot x,y ...//
        robotObject.settlerLocationX = destinationX;
        robotObject.settlerLocationY = destinationY;
    }
}
