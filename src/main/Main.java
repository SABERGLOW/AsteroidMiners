package main;

public class Main {

	
	public static void main(String[] args) throws Exception {
		//Testing Resources Inventory SpaceStation classes
		Resources res1 = new Uranium();
		Resources res2 = new Iron();
		Resources res3 = new Carbon();
		Resources res4 = new Uranium();
		Resources res5 = new WaterIce();
		
		System.out.println(res1.TypeOfMineral());
		System.out.println(res2.TypeOfMineral());
		System.out.println(res3.TypeOfMineral());
		
		Inventory inv = new Inventory();
		
		inv.addResource(res1);
		inv.addResource(res2);
		inv.addResource(res3);
		inv.addResource(res4);
		inv.addResource(res5);
		inv.addResource(res1);
		inv.addResource(res2);
		inv.addResource(res2);
		inv.addResource(res3);
		inv.addResource(res1);
		//These 2 can't be added
		inv.addResource(res1);
		inv.addResource(res1);
		
		
		
	}
}