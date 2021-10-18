package pse;

import algos.IAlgorithme;
import sac.SacADos;

public class PSE implements IAlgorithme 
{
	private int _noeuds_crees;
	private int _noeuds_parcourus;
	@Override
	public String nom() 
	{ return "PSE (Noeuds: " + this._noeuds_crees + " | Parcourus: " + this._noeuds_parcourus + ")"; }

	private Noeud _rechercher_solution(Noeud noeud, double poids_max)
	{
		if (noeud.fils_haut == null || noeud.fils_bas == null)
			return noeud;
		this._noeuds_parcourus++;
		
		Noeud h = _rechercher_solution(noeud.fils_haut, poids_max);
		Noeud b = _rechercher_solution(noeud.fils_bas, poids_max);
		if ((h.poids() <= poids_max) && (h.valeur() > b.valeur())) return h;
		else return b;
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		Noeud racine = new Noeud(sac.objets_possibles, sac.poids_max());
		this._noeuds_crees = Noeud.crees;
		
		this._noeuds_parcourus = 0;
		Noeud solution = _rechercher_solution(racine, sac.poids_max());
		
		for (int i = 0; i < solution.objets.length; i++)
			sac.objets.add(sac.objets_possibles[solution.objets[i]]);
	}

}
