package glouton;

import sac.Objet;

public class Ratio
{
	public Objet objet;
	public final float valeur;
	
	public Ratio(Objet objet)
	{
		this.objet = objet;
		this.valeur = (objet.valeur() / objet.poids());
	}
}
