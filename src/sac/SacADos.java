package sac;

import java.io.IOException;
import java.util.ArrayList;

import app.LecteurFichier;

public class SacADos
{
	public Objet[] objets_possibles;
	
	private float _poids_max;
	public float poids_max() { return this._poids_max; }
	public void modifier_poids_max(float poids_max) { this._poids_max = poids_max; }
	public void verifier_integrite() throws ExceptionSacTropRempli
	{ 
		float poids = this.poids();
		if (this._poids_max < poids) 
			throw new ExceptionSacTropRempli(poids, this._poids_max);
	}
	
	public ArrayList<Objet> objets;
	public float poids()
	{
		float poids = 0.f;
		for (int i = 0; i < this.objets.size(); i++)
			poids += this.objets.get(i).poids();
		return poids;
	}
	public float valeur()
	{
		float valeur = 0.f;
		for (int i = 0; i < this.objets.size(); i++)
			valeur += this.objets.get(i).valeur();
		return valeur;
	}
	
	public SacADos()
	{
		this._poids_max = 0;
		this.objets = new ArrayList<>();
	}
	public SacADos(String chemin, float poids_max) throws IOException
	{
		this._poids_max = poids_max;
		this.objets = new ArrayList<>();
		this.objets_possibles = LecteurFichier.objets(chemin);
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append("Objets possibles (" + this.objets_possibles.length + ") : \n");
		for (int i = 0; i < this.objets_possibles.length; i++)
			s.append("\t" + this.objets_possibles[i] + "\n");
		s.append("\n");
		s.append("Sac | " + this.objets.size() + " élement(s) | Poids : " + this.poids() + " / " + this.poids_max());
		s.append(" | Valeur : " + this.valeur() + "\n");
		for (int i = 0; i < this.objets.size(); i++)
			s.append("\t" + this.objets.get(i).toString() + "\n");
		return s.toString();
	}
}
