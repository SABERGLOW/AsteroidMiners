package Package;


public class RadioActiveAsteroid {
    private boolean state;

    RadioActiveAsteroid(boolean state){
        this.state = state;
    }

    public void Explode(Settler S){
        System.out.println("Settler died.");
    }
    public void Explode(Robot R){
        System.out.println("Robot damaged.");
    }
}
