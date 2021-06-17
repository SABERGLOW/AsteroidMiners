import java.lang.Math;

public class WaterIce implements Resource
{
	public String resourceName;
	public int resourceQuantity;
	public boolean isRadioactive;


	WaterIce()
	{
		resourceName = "WaterIce";
		resourceQuantity = (int)(Math.random() * (100 - 50 + 1) + 50);
		isRadioactive = false;
	}

	@Override
	public String toString()
	{
		return resourceName.toString();
	}

	public int resourceQuantity()
	{
		return resourceQuantity;
	}

	public boolean isRadioactive()
	{
		return isRadioactive;
	}
}
