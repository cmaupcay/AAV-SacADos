package pse;

import algos.IAlgorithme;
import sac.SacADos;

public class PSE implements IAlgorithme 
{
	@Override
	public String nom() { return "PSE (Procédure par Séparation et Evaluation)"; }

	private static boolean _valide(Noeud noeud)
	{ return (noeud != null && noeud.valide()); }
	private static Noeud _rechercher_solution(Noeud noeud, double poids_max)
	{
		boolean h_valide = _valide(noeud.fils_haut);
		boolean b_valide = _valide(noeud.fils_bas);
		if (!h_valide && !b_valide) return noeud;
		else if (!h_valide) 
			return _rechercher_solution(noeud.fils_bas, poids_max);
		else if (!b_valide) 
			return _rechercher_solution(noeud.fils_haut, poids_max);
		
		Noeud h = _rechercher_solution(noeud.fils_haut, poids_max);
		Noeud b = _rechercher_solution(noeud.fils_bas, poids_max);
		if (h.valeur() >= b.valeur()) return h;
		else return b;
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		Noeud racine = new Noeud(sac.objets_possibles, sac.poids_max());
		Noeud solution = _rechercher_solution(racine, sac.poids_max());
		for (int i = 0; i < solution.objets.length; i++)
			sac.objets.add(solution.objets[i]);
	}

}
