package glouton;

import java.util.Random;

import sac.Objet;

public class TriRapide extends Tri
{
	@Override
	public String nom() { return "rapide (pivot " + (PIVOT_ALEATOIRE ? "aléatoire" : "constant") + ")"; }
	public static final boolean PIVOT_ALEATOIRE = true;
	
	private static int _partitionner(Ratio[] ratio, int debut, int fin, int pivot, Objet[] objets) 
	{
		Tri.echanger(ratio, pivot, fin);
		int j = debut;
		for (int i = debut; i < fin; i++)
		{
			if (ratio[i].superieur(ratio[fin], objets))
			{
				Tri.echanger(ratio, i, j);
				j++;
			}
		}
		Tri.echanger(ratio, fin, j);
		return j;
	}

	private static int _choisir_pivot(int debut, int fin) 
	{
		if (PIVOT_ALEATOIRE) return (Math.abs((new Random()).nextInt()) % (fin - debut)) + debut;
		return debut;
	}
	
	private static void _tri(Ratio[] ratio, int debut, int fin, Objet[] objets)
	{
		if (debut < fin)
		{
			int pivot = _choisir_pivot(debut, fin);
			pivot = _partitionner(ratio, debut, fin, pivot, objets);
            _tri(ratio, debut, pivot - 1, objets);
            _tri(ratio, pivot + 1, fin, objets);
		}
	}

	@Override
	public void trier(Ratio[] ratios, Objet[] objets)
	{
		for (int i = 0; i < objets.length; i++)
			ratios[i] = new Ratio(i, objets[i]);
		_tri(ratios, 0, ratios.length - 1, objets);
	}
}