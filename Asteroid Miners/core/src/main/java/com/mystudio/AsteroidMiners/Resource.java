package com.mystudio.AsteroidMiners;

public interface Resource
{
	//Getter function for resource name
	public String toString();

	//Getter function for resource quantity
	public int resourceQuantity();

	//Getter function for radioactivity
	public boolean isRadioactive();
}
