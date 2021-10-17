package glouton;

import algos.IAlgorithme;
import sac.Objet;
import sac.SacADos;

public class Glouton implements IAlgorithme 
{
	private Ratio[] _ratio_objets;
	private void _inserer_ratio(int n, Objet objet)
	{
		Ratio ratio = new Ratio(objet);
		for (int i = 0; i < n; i++)
		{
			if (this._ratio_objets[i] != null && this._ratio_objets[i].valeur < ratio.valeur)
			{
				Ratio tmp = this._ratio_objets[i];
				this._ratio_objets[i] = ratio;
				ratio = tmp;
			}
		}
		this._ratio_objets[n] = ratio;
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		this._ratio_objets = new Ratio[sac.objets_possibles.length];
		for (int i = 0; i < sac.objets_possibles.length; i++)
			this._inserer_ratio(i, sac.objets_possibles[i]);
		float poids = 0.f;
		for (int i = 0; i < sac.objets_possibles.length; i++)
		{
			poids += this._ratio_objets[i].objet.poids();
			if (poids <= sac.poids_max())
				sac.objets.add(this._ratio_objets[i].objet);
			else break;
		}
	}

}
