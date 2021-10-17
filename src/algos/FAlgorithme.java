package algos;

public class FAlgorithme 
{
	public static IAlgorithme methode(String methode)
	{
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
