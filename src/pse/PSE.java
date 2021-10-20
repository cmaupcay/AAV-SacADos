package pse;

import algos.IAlgorithme;
import sac.Objet;
import sac.SacADos;

public class PSE implements IAlgorithme 
{
	private int _noeuds_crees;
	private int _noeuds_parcourus;

	private double _borne_inf;
	
	@Override
	public String nom() 
	{ return "PSE (Noeuds parcourus : " + this._noeuds_parcourus + " / " + this._noeuds_crees + ")"; }

	private Noeud _rechercher_solution(Noeud noeud)
	{
		this._noeuds_parcourus++;
		if (noeud.valeur() > this._borne_inf) this._borne_inf = noeud.valeur(); // On vérifie la borne inférieure

		if (noeud.fils_bas == null) return noeud; // Le noeud est en fin de branche

		if (noeud.fils_haut == null) return _rechercher_solution(noeud.fils_bas); // La partie haute n'a pas été générée
		
		if (noeud.fils_bas.borne_sup() < this._borne_inf) return noeud.fils_haut; // Les solutions descendantes ont une valeur inférieure a la borne inférieur		

		Noeud h = _rechercher_solution(noeud.fils_haut); // On cherche une solution dans la partie haute
		if (h.valeur() >= noeud.fils_bas.borne_sup()) // On ne peut pas éspérer faire mieux avec les solutions descendantes
			return h;

		Noeud b = _rechercher_solution(noeud.fils_bas);
		if (h.valeur() >= b.valeur()) // On compare la solution avec la partie basse
			return h;
		return b; // Sinon on renvoit la solution de la partie basse
	}
	
	private void _definir_borne_inf(Objet[] objets, double poids_max)
	{
		this._borne_inf = 0.d;
		for (int i = 1; i < objets.length; i++)
			if (objets[i].valeur() > this._borne_inf && objets[i].poids() <= poids_max)
				this._borne_inf = objets[i].valeur();
	}
	
	@Override
	public void resoudre(SacADos sac) 
	{
		Noeud racine = new Noeud(sac.objets_possibles, sac.poids_max());
		this._noeuds_crees = Noeud.crees;
		
		this._noeuds_parcourus = -1;
		_definir_borne_inf(sac.objets_possibles, sac.poids_max());
		Noeud solution = _rechercher_solution(racine);
		
		for (int i = 0; i < solution.objets.length; i++)
			sac.objets.add(sac.objets_possibles[solution.objets[i]]);
	}

}
