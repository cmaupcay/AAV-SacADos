package sac;

import java.util.ArrayList;

public class SacADos
{
	private float _poids_max;
	public float poids_max() { return this._poids_max; }
	public void verifier_integrite() throws ExceptionSacTropRempli
	{ 
		float poids = this.poids();
		if (this._poids_max < poids) 
			throw new ExceptionSacTropRempli(poids, this._poids_max);
	}
	public void modifier_poids_max(float poids_max) throws ExceptionSacTropRempli
	{
		this._poids_max = poids_max;
		this.verifier_integrite();
	}
	
	public ArrayList<Objet> objets;
	public float poids()
	{
		float poids = 0.f;
		for (int i = 0; i < this.objets.size(); i++)
			poids += this.objets.get(i).poids();
		return poids;
	}
	
	public SacADos()
	{
		this._poids_max = 0;
		this.objets = new ArrayList<>();
	}
	public SacADos(String chemin, float poids_max)
	{
		this._poids_max = poids_max;
		this.objets = new ArrayList<>();
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append("Sac | " + this.objets.size() + " élement(s) | Poids : " + this.poids() + " / " + this.poids_max() + "\n");
		for (int i = 0; i < this.objets.size(); i++)
			s.append("\t" + this.objets.toString());
		return s.toString();
	}
}
