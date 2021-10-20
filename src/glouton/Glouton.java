package glouton;

import algos.IAlgorithme;
import sac.SacADos;

public class Glouton implements IAlgorithme 
{

	@Override
	public String nom() 
	{ return "Glouton " + TRIEUR.nom(); }

	private static final ITri TRIEUR = new TriInsertion();
	
	@Override
	public void resoudre(SacADos sac) 
	{
		if (sac.objets_possibles.length == 0) return;
		Ratio[] ratios = new Ratio[sac.objets_possibles.length];
		
		TRIEUR.trier(ratios, sac.objets_possibles);
		
		double poids = 0.d;
		for (int i = 0; i < sac.objets_possibles.length; i++)
		{
			if (poids + sac.objets_possibles[ratios[i].index].poids() <= sac.poids_max())
			{
				sac.objets.add(sac.objets_possibles[ratios[i].index]);
				poids += sac.objets_possibles[ratios[i].index].poids();
			}
		}
	}

}
