package glouton;

public abstract class Tri implements ITri 
{	
	protected static void echanger(Ratio[] ratio, int a, int b)
	{
		Ratio tmp = ratio[a];
		ratio[a] = ratio[b];
		ratio[b] = tmp;
	}
}
