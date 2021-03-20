package Package;
import java.lang.Math;

import java.util.ArrayList;

public class Asteroid {
    protected int depth;
    protected ArrayList<Integer> resourceArrayList;
    public boolean checkHollow;
    public boolean radioactivePhase;
    public Resource resource;
    public boolean telGate;


    public Asteroid(int depth,boolean chk_Hollow,boolean radio_AP){
        this.depth = depth;
        telGate = false;
        resource= new Resource();
        checkHollow = chk_Hollow;
        radioactivePhase = radio_AP;
    }

    public int getDepth() {
        return depth;
    }
    public void decreaseDepth(){
        if(depth!=0){
            System.out.println("Decreasing the depth of the the current Asteroid!");
            depth--;
        }
        else
            System.out.println("Cannot decrease depth any further. It's time to mine!");
    }
    public boolean getMined(){
        if(radioactivePhase != true && checkHollow != true){
            System.out.println("Yes, it is minable...");
            return true;
        }
        else {
            System.out.println("No, it is not minable...");
            return false;
        }
    }
    public int Random(int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }
    public void addResource(){
        int number = Random(10, 20);
        resourceArrayList.add(number);
        System.out.println("1 Resource has been added to the asteroid....");
    }
    public void decreaseResource(){
        resource.total--;
        System.out.println("1 Resource has been removed from the asteroid....");
    }
    public void checkTeleportationGate(){
        if(telGate){
            System.out.println("Teleportation gate is present.");}
        else
            System.out.println("Teleportation is not present on the current asteroid.");
    }
}
