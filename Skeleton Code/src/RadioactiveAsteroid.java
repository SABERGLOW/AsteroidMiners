public class RadioactiveAsteroid
{
    //Represents the state of the radioactive asteriod (perihelion or apehlion)
    private boolean state;

    RadioactiveAsteroid(boolean state)
    {
        this.state = state;
    }
    
    //When radioactive asteriod explodes , settler dies
    public void Explode(Asteroid asteroidObject, Settler settlerObject)
    {
        settlerObject.Die();
        System.out.println("Settler died.");

        if(asteroidObject.resourceName == "WaterIce")
        {
            asteroidObject.resourceName = null;
            asteroidObject.resourceQuantity = 0;
            System.out.println("WaterIce has evaporated due to radioactive explosion.");
        }
    }
    //Robot get damaged during explosion // implement RELOCATION
    public void Explode(Asteroid asteroidObject, Robot robotObject)
    {
        robotObject.Damage(50);
        System.out.println("Robot damaged.");

        if(asteroidObject.resourceName == "WaterIce")
        {
            asteroidObject.resourceName = null;
            asteroidObject.resourceQuantity = 0;
            System.out.println("WaterIce has evaporated due to radioactive explosion.");
        }
    }
}