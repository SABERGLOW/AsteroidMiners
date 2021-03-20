package Package;

public class Resource {
    public int total;
    public String type;

    public Resource(){}

    public boolean sufficient(int value){
        if(total>value)
            return true;
        return false;
    }

}
