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
}
