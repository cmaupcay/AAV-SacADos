package glouton;

import sac.Objet;

public class Ratio
{
	public int index;
	public final double valeur;
	
	public Ratio(int index, Objet objet)
	{
		this.index = index;
		this.valeur = (objet.valeur() / objet.poids());
	}
	
	public boolean superieur(Ratio b, Objet[] objets) // this plus interessant que b
	{
		if (this.valeur > b.valeur) return true;
		else return (this.valeur == b.valeur && objets[this.index].poids() > objets[b.index].poids());
	}
}
