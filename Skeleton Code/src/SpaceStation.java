import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class SpaceStation
{
	public boolean buildSpaceStation(Asteroid asteroidObject, Settler settlerObject, SpaceStation spaceStationObject, HashMap<String, Integer> resourcesToWin)
	{
		boolean resourceCheck = false;
		if(asteroidObject.isRadioactive)
		{
			System.out.println("Can't build a space station on a Radioactive Asteroid");
		}
		else
		{
			if(settlerObject.CollectedResources.equals(resourcesToWin))
			{
				System.out.println("Space Station is Built... Game Won...");
				return true;
			}
			else
			{
				for(Map.Entry mapElement : resourcesToWin.entrySet())
				{
					String resource = (String) mapElement.getKey();
					int resourceQuantity = (int) mapElement.getValue();
					if((int)settlerObject.CollectedResources.get(resource) >= resourceQuantity)
					{
						resourceCheck = true;
					}
					else
					{
						return false;
					}
				}
				return true;
			}
		}
		return true;
	}
}
