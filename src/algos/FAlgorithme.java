package algos;

import dynamique.Dynamique;
import glouton.Glouton;
import pse.PSE;

public class FAlgorithme 
{
	public static IAlgorithme methode(String methode)
	{
		methode = methode.toLowerCase();
		switch (methode)
		{
		case "gloutonne":
			return new Glouton();
		case "prog. dynamique":
			return new Dynamique();
		case "pse":
			return new PSE();
		default:
			return null;
		}
	}
}
