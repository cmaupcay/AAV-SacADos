package pse;

import sac.Objet;

public class Noeud 
{
	public static int crees = 0;
	
	public int profondeur;
	public int[] objets;

	private double _borne_sup;
	public double borne_sup() { return this._borne_sup; }
	
	private boolean _valide;
	public boolean valide() { return this._valide; }
	private double _poids;
	public double poids() { return this._poids; }
	private double _valeur;
	public double valeur() { return this._valeur; }
	
	public Noeud fils_haut;
	public Noeud fils_bas;
	
	private void _creer_fils(Objet[] objets_possibles, double poids_max)
	{
		if (this._valide && (this.profondeur + 1 < objets_possibles.length))
		{
			this.fils_haut = new Noeud(objets_possibles, poids_max, this, true);
			this.fils_bas = new Noeud(objets_possibles, poids_max, this, false);
		}
		else
		{
			this.fils_haut = null;
			this.fils_bas = null;
		}
	}
	
	public Noeud(Objet[] objets_possibles, double poids_max) 
	{
		this.profondeur = 0;
		this.objets = new int[0];
		this._poids = 0.d;
		this._valeur = 0.d;
		this._valide = true;

		this._borne_sup = 0.d;
		for (int i = 0; i < objets_possibles.length; i++)
			this._borne_sup += objets_possibles[i].valeur();
		
		Noeud.crees = 1;		
		this._creer_fils(objets_possibles, poids_max);
	}
	public Noeud(Objet[] objets_possibles, double poids_max, Noeud pere, boolean inclure_objet)
	{
		this.profondeur = pere.profondeur + 1;
		this._poids = pere.poids();
		this._valeur = pere.valeur();
		this._borne_sup = pere.borne_sup();
		
		if (inclure_objet)
		{
			this._poids += objets_possibles[profondeur].poids();
			this._valide = this._poids <= poids_max;
			if (!this._valide) return;
			this._valeur += objets_possibles[profondeur].valeur();
			this.objets = new int[pere.objets.length + 1];
			for (int i = 0; i < pere.objets.length; i++)
				this.objets[i] = pere.objets[i];
			this.objets[pere.objets.length] = profondeur;
		}
		else
		{
			this.objets = pere.objets;
			this._valide = pere.valide();
			this._borne_sup -= objets_possibles[profondeur].valeur();
		}
		
		Noeud.crees++;
		this._creer_fils(objets_possibles, poids_max);
	}
}
