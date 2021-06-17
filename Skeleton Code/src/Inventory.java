import java.util.ArrayList;
import java.util.HashMap;

public class Inventory
{
	public HashMap<String, Integer> inventoryHashMap = new HashMap<>();
	int totalLoad = 10;

	Inventory()
	{

	}

	/*
	
	public ArrayList< Resource> GetCurrentLoad()
	{
		return currentLoad;
	}
	
	//Shouldn't be able to add more resources if inventory is full
	public void addResource( Resource res)
	{
		if(currentLoad.size() < totalLoad)
		{
			currentLoad.add(res);
			//System.out.println(res.TypeOfMineral() + " added.");
			
		}
		else
			{
			System.out.println("Inventory full!");
		}
	}

	 */
	
}
