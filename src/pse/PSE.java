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
		
		if (noeud.valeur() > this._borne_inf) this._borne_inf = noeud.valeur(); // On vérifie la borne inférieure
		
		if (noeud.fils_bas == null) return noeud; // Le noeud est en fin de branche

		if (noeud.fils_haut == null) return _rechercher_solution(noeud.fils_bas, poids_max); // La partie haute n'a pas été générée

		Noeud h = _rechercher_solution(noeud.fils_haut, poids_max); // On cherche une solution dans la partie haute
		if (noeud.fils_bas.borne_sup() < this._borne_inf) return h; // Les solutions descendantes ont une valeur inférieure a la borne inférieur		
		if (h.valeur() > noeud.fils_bas.borne_sup()) return h; // On ne peut pas éspérer faire mieux avec les solutions descendantes
		
		Noeud b = _rechercher_solution(noeud.fils_bas, poids_max); // On cherche une solution dans la partie basse
		if (h.valeur() > b.valeur()) return h; // On compare la valeur haute avec le noeud, on renvoit le noeud haut si il est interessant
		else return b; // Sinon on renvoit la solution de la partie basse
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		Noeud racine = new Noeud(sac.objets_possibles, sac.poids_max());
		this._noeuds_crees = Noeud.crees;
		
		this._noeuds_parcourus = -1; // On ne compte pas la racine
		this._borne_inf = 0.d;
		Noeud solution = _rechercher_solution(racine, sac.poids_max());
		
		for (int i = 0; i < solution.objets.length; i++)
			sac.objets.add(sac.objets_possibles[solution.objets[i]]);
	}

}
