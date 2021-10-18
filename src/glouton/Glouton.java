package glouton;

import algos.IAlgorithme;
import sac.Objet;
import sac.SacADos;

public class Glouton implements IAlgorithme 
{

	@Override
	public String nom() { return "Glouton"; }
	
	private Ratio[] _ratio_objets;
	private void _inserer_ratio(int index, Objet objet)
	{
		Ratio ratio = new Ratio(index, objet);
		for (int i = 0; i < index; i++)
		{
			if (this._ratio_objets[i] != null && this._ratio_objets[i].valeur < ratio.valeur)
			{
				Ratio tmp = this._ratio_objets[i];
				this._ratio_objets[i] = ratio;
				ratio = tmp;
			}
		}
		this._ratio_objets[index] = ratio;
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		if (sac.objets_possibles.length == 0) return;
		this._ratio_objets = new Ratio[sac.objets_possibles.length];
		for (int i = 0; i < sac.objets_possibles.length; i++)
			this._inserer_ratio(i, sac.objets_possibles[i]);
		double poids = 0.d;
		for (int i = 0; i < sac.objets_possibles.length; i++)
		{
			poids += sac.objets_possibles[this._ratio_objets[i].index].poids();
			if (poids <= sac.poids_max())
				sac.objets.add(sac.objets_possibles[this._ratio_objets[i].index]);
			else break;
		}
	}

}
