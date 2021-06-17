import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

public class Asteroid  ///Should know whether it contains a teleportation gate or not
{
    protected int depth;
    //Stores th e list of the resources an asteriod contained
    public HashMap<Object, Integer> resourceHashMap;
    String resourceName;
    int resourceQuantity;
    public boolean isHollow;
    public boolean isRadioactive; //Tells the state of the radioactive asteriod (perihelion or apehilion)
    public boolean isTeleportable;
    public Resource resource;
    public int asteroidLocationX;
    public int asteroidLocationY;

    public Asteroid(int depth,boolean chk_Hollow,boolean radio_AP)
    {
        this.depth = depth;
        isTeleportable = false;
        //resource= new Resource();
        isHollow = chk_Hollow;
        isRadioactive = radio_AP;
    }


    public Asteroid() {

    }

    //Returns the depth of the asteriod
    public int getDepth()
    {
        return depth;
    }
 
    /*
     When settler mines an asteriod 
     It decreases the depth of that particulaar asteriod
    */
    public void decreaseDepth()
    {
        if(depth!=0)
        {
            System.out.println("Decreasing the depth of the the current Asteroid!");
            depth--;
        }
        else
            System.out.println("Cannot decrease depth any further. It's time to mine!");
    }
      
    /*
      It provides the information whether the particular asteriod is minable or not
      If radioactivePhase and checkHollow are not true its mean asteriod can be mined and it returns true value
      Otherwise returns false
    */
    public boolean getMined()
    {
        if(isRadioactive != true && isHollow != true)
        {
            System.out.println("Yes, it is minable...");
            return true;
        }
        else
        {
            System.out.println("No, it is not minable...");
            return false;
        }
    }

    //When the game starts it randomly  generates a number  which specifies 
    // the nomber of resources to allocate to particular asteriod
    public int Random(int min, int max)
    {
        return (int)(Math.random() * (max - min + 1) + min);
    }
    public void addResource()
    {
        int number = Random(10, 20);
        //resourceArrayList.add(number);
        System.out.println("1 Resource has been added to the asteroid....");
    }
    
    //When settler mines the resource from the asteriod it decrease th at amount from the asteriod
    public void decreaseResource()
    {
        //resource.total--;
        System.out.println("1 Resource has been removed from the asteroid....");
    }

    //Determines whether teleportation gate is available on the asteriod or not
    public void checkTeleportationGate()
    {
        if(isTeleportable)
        {
            System.out.println("Teleportation gate is present.");
        }
        else
        {
            System.out.println("Teleportation is not present on the current asteroid.");
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Location X: " + asteroidLocationX + " Location Y: " + asteroidLocationY);
        stringBuilder.append(" Resource: " + resourceName + "Quantity: "+ resourceQuantity);
        stringBuilder.append("    | HollowState " + isHollow);
        return stringBuilder.toString();
    }
}
