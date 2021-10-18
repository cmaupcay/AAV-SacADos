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

	private static boolean _valide(Noeud noeud)
	{ return (noeud != null && noeud.valide()); }
	private Noeud _rechercher_solution(Noeud noeud, double poids_max, double valeur_min)
	{
		this._noeuds_parcourus++;
	
		if (noeud.borne_sup() < valeur_min) return noeud;
		if (noeud.valeur() > valeur_min)
			valeur_min = noeud.valeur();
		
		boolean h_invalide = !_valide(noeud.fils_haut);
		boolean b_invalide = !_valide(noeud.fils_bas);
		
		if (h_invalide && b_invalide) return noeud;
		else if (h_invalide) 
			return _rechercher_solution(noeud.fils_bas, poids_max, valeur_min);
		else if (b_invalide)
			return _rechercher_solution(noeud.fils_haut, poids_max, valeur_min);
		
		Noeud h = _rechercher_solution(noeud.fils_haut, poids_max, valeur_min);
		Noeud b = _rechercher_solution(noeud.fils_bas, poids_max, valeur_min);
		if (h.valeur() >= b.valeur()) return h;
		else return b;
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		Noeud racine = new Noeud(sac.objets_possibles, sac.poids_max());
		this._noeuds_crees = Noeud.crees;
		
		this._noeuds_parcourus = 0;
		Noeud solution = _rechercher_solution(racine, sac.poids_max(), 0.d);
		
		for (int i = 0; i < solution.objets.length; i++)
			sac.objets.add(sac.objets_possibles[solution.objets[i]]);
	}

}
