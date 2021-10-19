package glouton;

import java.util.Random;

public abstract class TriRapide 
{
	public static void echanger(Ratio[] ratio, int a, int b)
	{
		Ratio tmp = ratio[a];
		ratio[a] = ratio[b];
		ratio[b] = tmp;
	}
	
	private static int _partitionner(Ratio[] ratio, int debut, int fin, int pivot) 
	{
		echanger(ratio, pivot, fin);
		int j = debut;
		for (int i = debut; i < fin; i++)
		{
			if (ratio[i].valeur > ratio[fin].valeur)
			{
				echanger(ratio, i, j);
				j++;
			}
		}
		echanger(ratio, fin, j);
		return j;
	}

	public static final boolean PIVOT_ALEATOIRE = true;
	
	private static int _choisir_pivot(int debut, int fin) 
	{
		if (PIVOT_ALEATOIRE) return (Math.abs((new Random()).nextInt()) % (fin - debut)) + debut;
		return debut;
	}
	
	private static void _tri(Ratio[] ratio, int debut, int fin)
	{
		if (debut < fin)
		{
			int pivot = _choisir_pivot(debut, fin);
			pivot = _partitionner(ratio, debut, fin, pivot);
            _tri(ratio, debut, pivot - 1);
            _tri(ratio, pivot + 1, fin);
		}
	}

	public static void trier(Ratio[] ratios)
	{ _tri(ratios, 0, ratios.length - 1); }
}
