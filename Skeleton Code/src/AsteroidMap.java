import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import java.util.Scanner;

public class AsteroidMap
{
    //Contains the list of the asteriod present on the asteriod belt
    public ArrayList<Asteroid> asteroidArrayList;
    public HashMap<String, Integer> assignedResourcesToAsteroid;

    public AsteroidMap()
    {
        asteroidArrayList  = new ArrayList<Asteroid>();
        assignedResourcesToAsteroid = new HashMap<>();
        Carbon carbon = new Carbon();
        Iron iron = new Iron();
        WaterIce waterIce = new WaterIce();
        Uranium uranium = new Uranium();
        assignedResourcesToAsteroid.put(uranium.resourceName, uranium.resourceQuantity);
        assignedResourcesToAsteroid.put(carbon.resourceName, carbon.resourceQuantity);
        assignedResourcesToAsteroid.put(iron.resourceName, iron.resourceQuantity);
        assignedResourcesToAsteroid.put(waterIce.resourceName, waterIce.resourceQuantity);

    }


    public void populateAsteroids(int asteroidLocationX, int asteroidLocationY)
    {
        Asteroid asteroidObject = new Asteroid();

        //... give properties to asteroidObject
        asteroidObject.asteroidLocationX = asteroidLocationX;
        asteroidObject.asteroidLocationY = asteroidLocationY;


        //.................................. Assigning Radioactive Asteroids .................................//
        if((asteroidLocationX == 1 && asteroidLocationY == 1) ||
                (asteroidLocationX == 1 && asteroidLocationY == 3) ||
                (asteroidLocationX == 4 && asteroidLocationY == 1) ||
                (asteroidLocationX == 4 && asteroidLocationY == 3) )
        {
            asteroidObject.isRadioactive = true;
        }
        //.................................. Assigning Hollow Asteroids .................................//
        if((asteroidLocationX == 0 && asteroidLocationY == 2) ||
                (asteroidLocationX == 2 && asteroidLocationY == 0) ||
                (asteroidLocationX == 4 && asteroidLocationY == 2) ||
                (asteroidLocationX == 2 && asteroidLocationY == 4) )
        {
            asteroidObject.isHollow = true;
        }

        //.................................. Assigning Resources .................................//
        if(!asteroidObject.isHollow)
        {
            assignResourcesToAsteroid(asteroidObject);
        }

        //.................................. Assigning Depth .................................//
        asteroidObject.depth = Random(2, 5);


        //... add asteroid object to AsteroidArrayList
        asteroidArrayList.add(asteroidObject);
    }

    public void assignResourcesToAsteroid(Asteroid asteroidObject)
    {
        Object resourceKey = assignedResourcesToAsteroid.keySet().toArray()[Random(0, assignedResourcesToAsteroid.size()-1)]; //...get random resource from array
        int randomResourceQuantity = Random((int)(assignedResourcesToAsteroid.get(resourceKey)*0.1), assignedResourcesToAsteroid.get(resourceKey)); //... assign random quantity between 20% and max available
        assignedResourcesToAsteroid.put((String) resourceKey, assignedResourcesToAsteroid.get(resourceKey)-randomResourceQuantity); //... reduce max available


        if(randomResourceQuantity == 0)
        {
            asteroidObject.isHollow = true;
            asteroidObject.resourceName = null;
        }
        asteroidObject.resourceName = (String)resourceKey;
        asteroidObject.resourceQuantity = randomResourceQuantity;
    }


    //Receives the parameter of asteriod
    //and returns the neighbour of that asteriod
    public Asteroid getNeighbour(Asteroid A)
    {
        System.out.println("Returned the neighbouring asteroid!");
        return A;
    }

    /*
      Receive a settler object as parameter
      when player want to travel settler from one asteriod to other
      It removes the settler from that asteriod
    */
    public void removeSettler(Settler S)
    {
        System.out.println("Settler has been removed from the asteroid.");
    }
    
    //Add the settler S to an asteriod A
    public void addSettler(Asteroid A, Settler S)
    {
        System.out.println("Settler has been added to the asteroid.");
    }
    //Remove the Robot R from the asteriod
    public void removeRobot(Robot R)
    {
        System.out.println("Robot has been removed from the asteroid.");
    }

     //Add Robot R to the Asteriod A
    public void addRobot(Asteroid A, Robot S)
    {
        System.out.println("Robot has been added to the asteroid.");
    }
    
    //Remove the asteriod
    public void removeAsteroid(Asteroid A)
    {
        asteroidArrayList.remove(A);
        //... asteroidMap[x][y] = 0;
        System.out.println("This current Asteroid has been removed from Asteroid belt.");
    }

    public int Random(int min, int max) //... Random Function...//
    {
        return (int)(Math.random() * (max - min + 1) + min);
    }
}