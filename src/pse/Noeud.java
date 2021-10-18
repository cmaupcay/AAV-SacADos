package pse;

import sac.Objet;

public class Noeud 
{
	public static int crees = 0;
	
	public int[] objets;

	//private double _borne_sup;
	//public double borne_sup() { return this._borne_sup; }
	
	private double _poids;
	public double poids() { return this._poids; }
	private double _valeur;
	public double valeur() { return this._valeur; }
	
	public Noeud fils_haut;
	public Noeud fils_bas;
	
	private void _creer_descendance(Objet[] objets_possibles, double poids_max, int index_objet)
	{
		if (index_objet < objets_possibles.length)
		{
			this.fils_haut = new Noeud(objets_possibles, poids_max, this, index_objet, true);
			this.fils_bas = new Noeud(objets_possibles, poids_max, this, index_objet, false);
		}
	}
	public Noeud(Objet[] objets_possibles, double poids_max) 
	{
		this.objets = new int[0];
		this._poids = 0.d;
		this._valeur = 0.d;
		
		Noeud.crees = 1;		
		this._creer_descendance(objets_possibles, poids_max, 0);
	}
	public Noeud(Objet[] objets_possibles, double poids_max, Noeud pere, int index_objet, boolean inclure_objet)
	{
		this._poids = pere.poids();
		this._valeur = pere.valeur();
		
		if (inclure_objet)
		{
			this._poids += objets_possibles[index_objet].poids();
			this._valeur += objets_possibles[index_objet].valeur();
			this.objets = new int[pere.objets.length + 1];
			for (int i = 0; i < pere.objets.length; i++)
				this.objets[i] = pere.objets[i];
			this.objets[pere.objets.length] = index_objet;
		}
		else
			this.objets = pere.objets;
		
		Noeud.crees++;
		this._creer_descendance(objets_possibles, poids_max, index_objet + 1);
	}
}
