package glouton;

import sac.Objet;

public class TriInsertion extends Tri
{
	@Override
	public String nom() { return "par insertion"; }
	
	private static void _inserer(Ratio[] ratios, int index, Objet[] objets)
	{
		Ratio ratio = new Ratio(index, objets[index]);
		for (int i = 0; i < index; i++)
		{
			if (ratios[i] != null && ratio.superieur(ratios[i], objets))
			{
				Ratio tmp = ratios[i];
				ratios[i] = ratio;
				ratio = tmp;
			}
		}
		ratios[index] = ratio;
	}
	
	@Override
	public void trier(Ratio[] ratios, Objet[] objets) 
	{
		for (int i = 0; i < objets.length; i++)
			_inserer(ratios, i, objets);
	}

}
