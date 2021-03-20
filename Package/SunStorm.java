package Package;

public class SunStorm {
    public long time;
    public long damageDone;

    SunStorm(long time){
        this.time = time;
    }

    public void collideWith(Settler S){
        S.die();
        System.out.println("Settler died.");
    }
    public void collideWith(Robot R){
        R.health = (int) (100 - damageDone);
        System.out.println("Robot has taken a damage.");
    }

}
