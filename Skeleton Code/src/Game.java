import java.util.*;
import java.lang.*;

public class Game
{
    public int asteroidMap [][];
    public int numberOfAsteroids = 10;
    public int numberOfOperationsPerformed = 0;
    public HashMap<String, Integer> nameOfResources = new HashMap<>();
    public HashMap<String, Integer> resourcesToWin = new HashMap<>();
    public HashMap<String, Integer> resourcesToBuildGate = new HashMap<>();
    public HashMap<String, Integer> resourcesToBuildRobot = new HashMap<>();


    AsteroidMap asteroidMapObject = new AsteroidMap();
    Asteroid asteroidObject = new Asteroid();
    SpaceStation spaceStationObject = new SpaceStation();
    Timer timerObject = new Timer();
    Inventory inventoryObject = new Inventory();
    Settler settlerObject;
    Robot robotObject;
    TravelMethod travelMethodObject = new TravelMethod();
    SunStorm sunStormObject = new SunStorm();



    //... A hashmap of resources needed to build the space
    // station and win the game. The hashmap will consist of:
    // Resource name as a string, and resource quantity as integer.
    // example: ["carbon", 10], ["iron", 5]


    //... Constructor ...//
    public Game()
    {
        asteroidMap = new int [5][5];
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                asteroidMap[i][j] = 0;
            }
        }
        Carbon carbon = new Carbon();
        Iron iron = new Iron();
        WaterIce waterIce = new WaterIce();
        Uranium uranium = new Uranium();
        nameOfResources.put(carbon.resourceName, carbon.resourceQuantity);
        nameOfResources.put(iron.resourceName, iron.resourceQuantity);
        nameOfResources.put(waterIce.resourceName, waterIce.resourceQuantity);
        nameOfResources.put(uranium.resourceName, uranium.resourceQuantity);

        resourcesToWin = new HashMap<>();
        resourcesToBuildGate = new HashMap<>();
    }



    public void StartGame() //... function starts the game, and calls initialization functions like InitializeAsteroidMap and Game Mission ...//
    {
        System.out.println("The game has been started...");
        InitializeAsteroidMap();
        InitializeResourcesToWin();
        InitializeResourcesToBuildGate();
        InitializeResourcesToBuildRobot();
        GameMission();
        Operation();
    }



    public void InitializeResourcesToWin()
    {
        for(int i=0; i<nameOfResources.size(); i++)
        {
            Object resourceKey = nameOfResources.keySet().toArray()[Random(0, nameOfResources.size()-1)];
            int randomResourceQuantity = Random(10, nameOfResources.get(resourceKey)-30);
            resourcesToWin.put(resourceKey.toString(), randomResourceQuantity);
        }
    }

    public void InitializeResourcesToBuildGate()
    {
        resourcesToBuildGate.put("Carbon", 2);
        resourcesToBuildGate.put("Uranium", 2);
        resourcesToBuildGate.put("WaterIce", 1);
    }

    public void InitializeResourcesToBuildRobot()
    {
        resourcesToBuildRobot.put("Carbon", 2);
        resourcesToBuildRobot.put("Iron", 2);
        resourcesToBuildRobot.put("WaterIce", 1);
    }

    public void InitializeAsteroidMap() //... function distributes the asteroids across the asteroid belt ...//
    {
        /////////////////////////////////////////////////////////////////////////
        System.out.println("\n\n\n\n\n\n          Asteroid Mining\n\n");

        System.out.println("How many ASTEROIDS do u want...?");

        Scanner inputScanner = new Scanner(System.in);
        numberOfAsteroids = inputScanner.nextInt();

        /////////////////////////////////////////////////////////////////////////

        //... populated asteroids in the map ...//
        int x = 0;
        int y = 0;
        for(int i=0; i<numberOfAsteroids; i++)
        {
            x = Random(0,4);
            y = Random(0,4);
            if(asteroidMap[x][y] == 1 || asteroidMap[2][2] == 1)
            {
                i--;
                continue;
            }
            asteroidMap[x][y] = 1;
            asteroidMapObject.populateAsteroids(x, y); //... passing x,y to populate the asteroid
        }
        System.out.println("Settler X: " + x + " Settler Y: "+ y);
        settlerObject = new Settler(x, y);
        System.out.println("The asteroids have been initialized in the asteroid belt...");


        //... checking and printing the asteroid map on console ...//
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                System.out.print(asteroidMap[i][j] + " ");
            }
            System.out.println("");
        }

        //... ArrayList of Asteroids ...//
       System.out.println("ArrayList size: " + asteroidMapObject.asteroidArrayList.size());

        //... Check for Hollow ...//

        for(Asteroid asteroid: asteroidMapObject.asteroidArrayList)
        {
            System.out.println("__________________________________________________________");
            if(asteroid.isHollow)
            {
                System.out.println("is Hollow");
            }
            System.out.println("Depth: " + asteroid.depth);
            System.out.println("Resource: " + asteroid.resourceName + " | Quantity: " + asteroid.resourceQuantity);
            System.out.println("__________________________________________________________");
        }

    }

    public void GameMission() //... functions describes  the winning requirements to the player ...//
    {
        System.out.println("\n\nThe mission is to build a space station. You need to gather following resources: ");

        for(String key : resourcesToWin.keySet())
        {
            System.out.println(key + " " + resourcesToWin.get(key));
        }
    }

    public void Operation()
    {
        for(Asteroid asteroid: asteroidMapObject.asteroidArrayList) //... Settler Asteroid ...//
        {
            if(asteroid.asteroidLocationX == settlerObject.settlerLocationX && asteroid.asteroidLocationY == settlerObject.settlerLocationY)
            {
                asteroidObject = asteroid;
            }
        }

        //... Count number of operations performed, so we can control Robot + SunStorm ...//
        numberOfOperationsPerformed++;
        if(numberOfOperationsPerformed == 5) //... after 5 operations, robot drills until depth = 0, and then travels to nearest asteroid ...//
        {
            if(inventoryObject.inventoryHashMap.containsKey("Robot"))
            {
                Asteroid robotAsteroidObject = new Asteroid();
                for(Asteroid asteroid: asteroidMapObject.asteroidArrayList) //... Robot is on THIS Asteroid ...//
                {
                    if(asteroid.asteroidLocationX == robotObject.settlerLocationX && asteroid.asteroidLocationY == robotObject.settlerLocationY)
                    {
                        robotAsteroidObject = asteroid;
                    }
                }
                robotObject.Drill(robotAsteroidObject, robotObject, asteroidMapObject, asteroidMap);
            }
        }

        if(numberOfOperationsPerformed == 10) //... after 10 operations, we create a SunStorm ...//
        {
            //... GUI shows SunStorm ...//
            //... ask user to hide ...//
            //... start the SunStorm ...//
            sunStormObject.collideWith(settlerObject);
            sunStormObject.collideWith(robotObject);
            numberOfOperationsPerformed = 0;
        }


        System.out.println("\t\t1. Travel");
        System.out.println("\t\t2. Drill\n\n");

        Scanner inputScanner = new Scanner(System.in);
        int operation = inputScanner.nextInt();

        if(operation == 1) //... settler travels
        {
            //... NEEDS GUI CLICK IMPLEMENTATION ...//
            //... ask user to choose between travel methods and update travelmethod.boolean accordingly
            //... ask user to click on destination
            //... get destination x,y and update settler's prev location
            if(settlerObject.Travel(settlerObject, asteroidMapObject, asteroidObject, travelMethodObject) >=1 )
            {
                for(Asteroid asteroid: asteroidMapObject.asteroidArrayList)
                {
                    if(asteroid.asteroidLocationX == settlerObject.settlerLocationX && asteroid.asteroidLocationY == settlerObject.settlerLocationY)
                    {
                        asteroidObject = asteroid;
                    }
                }
            }
        }
        if(operation == 2) //... settler drills
        {
            System.out.println("Depth: " + asteroidObject.depth);
            settlerObject.Drill(asteroidObject, settlerObject);
            System.out.println("Depth: " + asteroidObject.depth);
        }
        if(operation == 3) //... settler mines
        {
            settlerObject.Mine(asteroidObject, settlerObject);
        }
        if(operation == 4) //... settler hides
        {
            settlerObject.Hide(asteroidObject, settlerObject);
        }
        if(operation == 5) //... settler revives health
        {
            settlerObject.ReviveHealth(settlerObject, timerObject);
        }
        if(operation == 6) //... settler deposits resources
        {
            settlerObject.Deposit(asteroidObject, settlerObject);
        }
        if(operation == 7) //... settler builds space station
        {
            settlerObject.BuildSpaceStation(asteroidObject, settlerObject, resourcesToWin, spaceStationObject);
        }
        if(operation == 8) //... settler builds robot
        {
            settlerObject.BuildRobot(settlerObject, inventoryObject, resourcesToBuildRobot);
            if(inventoryObject.inventoryHashMap.containsKey("Robot") && inventoryObject.inventoryHashMap.get("Robot") >= 1)
            {
                //... make robotPositionX and Y same as settler ...//
                robotObject.settlerLocationX = settlerObject.settlerLocationX;
                robotObject.settlerLocationY = settlerObject.settlerLocationY;
            }
        }
        if(operation == 9) //... settler builds gate
        {
            settlerObject.BuildTeleportationGate(settlerObject, inventoryObject, resourcesToBuildGate);
        }
        if(operation == 10) //... settler deploys gate
        {
            settlerObject.DeployTeleportationGate(settlerObject, asteroidObject, inventoryObject);
        }
        if(operation == 11) //... settler picksup gate
        {
            settlerObject.PickupTeleportationGate(settlerObject, asteroidObject, inventoryObject);
        }
    }


    public void EndGame() //... function ends the game ...//
    {
        System.out.println("Game Over...");
        System.exit(1);
    }


    public int Random(int min, int max) //... Random Function...//
    {
        return (int)(Math.random() * (max - min + 1) + min);
    }


    public static void main(String[] args)
    {
        Game newGame = new Game();
        System.out.println("\t\t\n\n          Asteroid Mining\n\n");
        System.out.println("\t\tChoose one of the following options:");
        System.out.println("\t\t1. Start The Game");
        System.out.println("\t\t2. Exit\n\n");

        Scanner inputScanner = new Scanner(System.in);
        int input = inputScanner.nextInt();

        if(input == 1)
        {
            newGame.StartGame();
        }
        else
        {
            newGame.EndGame();
        }
    }
}
