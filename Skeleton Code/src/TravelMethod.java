public class TravelMethod
{
    public boolean normalTravelCheck = false;
    public boolean teleportationCheck = false;

    //... add destination asteroid in this method ...//
    public void NormalTravel(Settler settlerObject)
    {
        settlerObject.settlerLocationX = settlerObject.destinationX;
        settlerObject.settlerLocationX = settlerObject.destinationY;
    }

    //... add destination asteroid in this method ...//
    public int Teleportation(Settler settlerObject, AsteroidMap asteroidMapObject, Asteroid asteroidObject)
    {

        Asteroid destinationAsteroid = new Asteroid();
        //... Checking if destination asteroid is teleportable or not.
        for(Asteroid asteroid: asteroidMapObject.asteroidArrayList)
        {
            if(asteroid.asteroidLocationX == settlerObject.destinationX && asteroid.asteroidLocationY == settlerObject.destinationY)
            {
                destinationAsteroid = asteroid;
            }
        }
        if(asteroidObject.isTeleportable && destinationAsteroid.isTeleportable)
        {
            settlerObject.settlerLocationX = settlerObject.destinationX;
            settlerObject.settlerLocationX = settlerObject.destinationY;
            return 2; //... 2 means Teleportation
        }
        else
        {
            System.out.println("Sorry, Teleportation failed. Source/Destination is not teleportable.");
            return 0;
        }
    }
}
