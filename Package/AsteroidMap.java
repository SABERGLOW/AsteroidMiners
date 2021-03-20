package Package;
import java.util.ArrayList;

public class AsteroidMap {
    public ArrayList<Asteroid> totalAsteroids;

    public AsteroidMap(){
        totalAsteroids = new ArrayList<Asteroid>();
    }
    public Asteroid getNeighbour(Asteroid A){
        System.out.println("Returned the neighbouring asteroid!");
        return A;
    }

    public void removeSettler(Settler S){
        System.out.println("Settler has been removed from the asteroid.");
    }
    public void addSettler(Asteroid A, Settler S){
        System.out.println("Settler has been added to the asteroid.");
    }
    public void removeRobot(Robot S){
        System.out.println("Robot has been removed from the asteroid.");
    }
    public void addRobot(Asteroid A, Robot S){
        System.out.println("Robot has been added to the asteroid.");
    }
    public void removeAsteroid(Asteroid A){
        totalAsteroids.remove(A);
        System.out.println("This current Asteroid has been removed from Asteroid belt.");
    }

}
