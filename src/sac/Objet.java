package sac;

public class Objet 
{
	private String _nom;
	public String nom() { return this._nom; }
	
	private double _poids;
	public double poids() { return this._poids; }
	private double _valeur;
	public double valeur() { return this._valeur; }

	public Objet(String nom, double poids, double valeur)
	{
		this._nom = nom;
		this._poids = poids;
		this._valeur = valeur;
	}
	public Objet(Objet objet)
	{
		this._nom = objet.nom();
		this._poids = objet.poids();
		this._valeur = objet.valeur();
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(this._nom + " ; ");
		s.append(this._poids + " ; ");
		s.append(this._valeur);
		return s.toString();
	}
}
