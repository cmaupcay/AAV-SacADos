package pse;

import algos.IAlgorithme;
import sac.SacADos;

public class PSE implements IAlgorithme 
{
	private int _noeuds_crees;
	private int _noeuds_parcourus;

	private double _borne_inf;
	
	@Override
	public String nom() 
	{ return "PSE (Noeuds parcourus : " + this._noeuds_parcourus + " / " + this._noeuds_crees + ")"; }

	private Noeud _rechercher_solution(Noeud noeud, double poids_max)
	{
		this._noeuds_parcourus++;
		if (noeud.borne_sup() < this._borne_inf) return noeud;
		
		Noeud h = null;
		if (noeud.fils_haut == null)
			h = noeud;
		else h = _rechercher_solution(noeud.fils_haut, poids_max);
		if (noeud.fils_bas == null) return noeud;
		
		if (h.valeur() > noeud.valeur()) return h;
		else return _rechercher_solution(noeud.fils_bas, poids_max);
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		Noeud racine = new Noeud(sac.objets_possibles, sac.poids_max());
		this._noeuds_crees = Noeud.crees;
		
		this._noeuds_parcourus = -1; // On ne compte pas la racine
		this._borne_inf = 3.d;
		Noeud solution = _rechercher_solution(racine, sac.poids_max());
		
		for (int i = 0; i < solution.objets.length; i++)
			sac.objets.add(sac.objets_possibles[solution.objets[i]]);
	}

}
