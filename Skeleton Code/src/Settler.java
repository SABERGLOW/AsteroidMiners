import java.util.*;


public class Settler
{
    public int Lives;
    public int settlerLocationX;
    public int settlerLocationY;
    public int destinationX;
    public int destinationY;

    public HashMap<String, Integer> CollectedResources;//... keeps track of collected resources, updates inventory ...//

    public boolean IsHidden = false;

    public Settler()
    {

    }

    public Settler(int settlerLocationX, int settlerLocationY)
    {
        Lives = 100; //... Settler starts with Maximum Health ...//
        this.settlerLocationX = settlerLocationX;
        this.settlerLocationY = settlerLocationY;
        CollectedResources = new HashMap<>();
    }




//... add asteroid as an argument
    public int Travel(Settler settlerObject, AsteroidMap asteroidMapObject, Asteroid asteroidObject, TravelMethod travelMethod) //... function enables the settler to travel by choosing a method...//
    {
        if(travelMethod.normalTravelCheck == true)
        {
            travelMethod.NormalTravel(settlerObject);
            return 1;

        }
        else
        {
            return travelMethod.Teleportation(settlerObject, asteroidMapObject, asteroidObject);
        }
    }

    //... add asteroid as an argument
    public void Drill(Asteroid asteroidObject, Settler settlerObject) //... function decreases the Asteroid Depth by 1 ...//
    {
        if(asteroidObject.depth == 0)
        {
            //... Alert: DEPTH IS ALREADY ZERO
            System.out.println("Asteroid Depth is now ZERO. Can't mine more.");
            //.. back to menu, do nothing
        }
        if(asteroidObject.isRadioactive && asteroidObject.depth == 1)
        {
            //... Alert: Warning, About to EXPLODE
            System.out.println("Asteroid will explode if you keep drilling. Do you want to keep drilling?");
            //.. if yes ..//
            RadioactiveAsteroid radioactiveAsteroidObject = new RadioactiveAsteroid(true);
            asteroidObject.depth--;
            radioactiveAsteroidObject.Explode(asteroidObject, settlerObject);
            //.. if no ..//
            //.. back to menu, do nothing
        }
        if(!asteroidObject.isRadioactive && asteroidObject.depth > 0)
        {
            asteroidObject.depth--;
        }
    }

    public void Mine(Asteroid asteroidObject, Settler settlerObject) //... function adds 1 unit of resource in CollectedResources ...//
    {
        if(settlerObject.CollectedResources.keySet().contains(asteroidObject.resourceName))//...if already exists, +1...//
        {
            settlerObject.CollectedResources.put(asteroidObject.resourceName, settlerObject.CollectedResources.get(asteroidObject.resourceName)+asteroidObject.resourceQuantity);
        }
        else //...doesn't exist in the collected resources? then add new entry...//
        {
            settlerObject.CollectedResources.put(asteroidObject.resourceName, asteroidObject.resourceQuantity);
        }
    }


    public void Hide(Asteroid asteroidObject, Settler settlerObject ) //... function updates the Hidden Status of the Settler, changes the Damage Amount to ZERO ...//
    {
        if(settlerObject.IsHidden)
        {
            System.out.println("Settler is already hiding in a hollow asteroid");
        }
        if(asteroidObject.depth == 0)
        {
            settlerObject.IsHidden = true;
        }
        else if(asteroidObject.depth >= 0)
        {
            settlerObject.IsHidden = false;
            System.out.println("You have to drill first, depth is not zero");
        }
    }


    public void ReviveHealth(Settler settlerObject, Timer timerObject) //... function updates the amount of Lives ...//
    {

        if(settlerObject.Lives == 100)
        {
            System.out.println("Settler is already at Maximum Health");
        }
        else
        {
            //... add a ReviveTimeCalculator in Timer Class ...//
            System.out.println("Settler has to wait for" + timerObject.ReviveTimeCalculator(Lives) + "seconds...");
            Lives = 100;
        }
    }

    public void Die()
    {
        System.out.println("Settler is dead...");
        //... kill the settler GUI, End the game
    }

    //... add Hollow Asteroid as an argument
    public void Deposit(Asteroid asteroidObject, Settler settlerObject)
    {
        System.out.println("Resources were deposited in the hollow asteroid...");
        if(asteroidObject.depth > 0)
        {
            System.out.println("You have to drill first, depth is not zero");
        }
        else
        {
            asteroidObject.resourceHashMap.putAll(settlerObject.CollectedResources);
            settlerObject.CollectedResources.clear();
            //.. ALERT: Collected Resources EMPTY
            //.. Resources deposited in the asteroid
        }
    }

    //... add Hollow Asteroid as an argument
    public boolean BuildSpaceStation(Asteroid asteroidObject, Settler settlerObject, HashMap<String, Integer>  resourcesToWin, SpaceStation spaceStationObject) //... function builds a space station ...//
    {
        boolean isBuilt = spaceStationObject.buildSpaceStation(asteroidObject, settlerObject, spaceStationObject, resourcesToWin);
        return isBuilt;
    }

    public void BuildTeleportationGate(Settler settlerObject, Inventory inventoryObject, HashMap<String, Integer> resourcesToBuildGate) //... function builds a teleportation gate ...//
    {
        if(! CheckForSufficientResources(settlerObject.CollectedResources, resourcesToBuildGate))
        {
            System.out.println("Not enough resources...");
        }
        else
        {
            if(inventoryObject.inventoryHashMap.keySet().contains("Gate"))//...if already exists, +1...//
            {
                inventoryObject.inventoryHashMap.put("Gate", inventoryObject.inventoryHashMap.get("Gate")+1);
            }
            else //...doesn't exist in the Inventory? then add new entry...//
            {
                inventoryObject.inventoryHashMap.put("Gate", 1);
            }

            //... make changes to collectedResources.. subtract used resources
            //... we traverse resourcesToBuildGate hashmap, get corresponding key,value from collectedResources, subtract the values.

            for(Map.Entry mapElement : resourcesToBuildGate.entrySet())
            {
                String resource = (String) mapElement.getKey();
                int resourceQuantity = (int)settlerObject.CollectedResources.get(resource) - (int) mapElement.getValue();
                CollectedResources.put(resource, resourceQuantity);
            }
        }
    }

    //... add Asteroid as an argument
    public void DeployTeleportationGate(Settler settlerObject, Asteroid asteroidObject, Inventory inventoryObject) //... function deploys a teleportation gate on an Asteroid ...//
    {
        asteroidObject.isTeleportable = true; //... asteroid is now teleportable
        //... remove from inventory ...//
        if((inventoryObject.inventoryHashMap.keySet().contains("Gate")) && ((int)inventoryObject.inventoryHashMap.get("Gate") >= 1))//...if already exists, +1...//
        {
            inventoryObject.inventoryHashMap.put("Gate", inventoryObject.inventoryHashMap.get("Gate")-1);
        }
        else
        {
            System.out.println("You have no Teleportation Gates in your Inventory.");
        }

    }

    //... add Asteroid as an argument
    public void PickupTeleportationGate(Settler settlerObject, Asteroid asteroidObject, Inventory inventoryObject) //... function picks up a teleportation gate from an Asteroid ...//
    {
        asteroidObject.isTeleportable = false; //... asteroid is NOT teleportable
        //... add it to inventory ...//
        if(inventoryObject.inventoryHashMap.keySet().contains("Gate"))//...if already exists, +1...//
        {
            inventoryObject.inventoryHashMap.put("Gate", inventoryObject.inventoryHashMap.get("Gate")+1);
        }
        else //...doesn't exist in the Inventory? then add new entry...//
        {
            inventoryObject.inventoryHashMap.put("Gate", 1);
        }
    }

    //... add Asteroid as an argument
    public void BuildRobot(Settler settlerObject, Inventory inventoryObject, HashMap<String, Integer> resourcesToBuildRobot) //... function builds a robot ...//
    {
        if(! CheckForSufficientResources(settlerObject.CollectedResources, resourcesToBuildRobot))
        {
            System.out.println("Not enough resources...");
        }
        else
        {
            if(inventoryObject.inventoryHashMap.keySet().contains("Robot"))//...if already exists, +1...//
            {
                inventoryObject.inventoryHashMap.put("Robot", inventoryObject.inventoryHashMap.get("Robot")+1);
            }
            else //...doesn't exist in the Inventory? then add new entry...//
            {
                inventoryObject.inventoryHashMap.put("Robot", 1);
            }


            //... make changes to collectedResources.. subtract used resources
            //... we traverse resourcesToBuildRobot hashmap, get corresponding key,value from collectedResources, subtract the values.

            for(Map.Entry mapElement : resourcesToBuildRobot.entrySet())
            {
                String resource = (String) mapElement.getKey();
                int resourceQuantity = (int)settlerObject.CollectedResources.get(resource) - (int) mapElement.getValue();
                CollectedResources.put(resource, resourceQuantity);
            }
        }
    }


    public boolean CheckForSufficientResources(HashMap<String, Integer> resoucesIHave, HashMap<String, Integer> resoucesINeed)
    {
        boolean resourceCheck = false;
        if(resoucesIHave.equals(resoucesINeed))
        {
            return true;
        }
        else
        {
            for(Map.Entry mapElement : resoucesINeed.entrySet())
            {
                String resource = (String) mapElement.getKey();
                int resourceQuantity = (int) mapElement.getValue();
                if((int)resoucesIHave.get(resource) >= resourceQuantity)
                {
                    resourceCheck = true;
                }
                else
                {
                    return false;
                }
            }
            resourceCheck = true;
        }
        return resourceCheck;
    }
}

