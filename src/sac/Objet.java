package sac;

public class Objet 
{
	private String _nom;
	public String nom() { return this._nom; }
	
	private float _poids;
	public float poids() { return this._poids; }
	private float _valeur;
	public float valeur() { return this._valeur; }

	public Objet(String nom, float poids, float valeur)
	{
		this._nom = nom;
		this._poids = poids;
		this._valeur = valeur;
	}
}
