package dynamique;

import algos.IAlgorithme;
import sac.Objet;
import sac.SacADos;

public class Dynamique implements IAlgorithme 
{
	// La précision s'applique au poids, car il est nécessairement convertit en entier
	private static final int PRECISION = 10;
	private static int _index(double poids)
	{ return (int)Math.round(poids) * PRECISION; }

	@Override
	public String nom() { return "Programmation Dynamique (Précision : " + (1.d / PRECISION) + ")"; }
	
	private static double[][] _matrice(double poids_max, Objet[] objets)
	{
		final int j_max = _index(poids_max) + 1;
		double[][] matrice = new double[objets.length][j_max];
		int poids_entier = 0;
		for (int i = 0; i < objets.length; i++)
		{
			poids_entier = _index(objets[i].poids());
			for (int j = 0; j < j_max; j++)
			{
				if (i == 0)
				{
					if (poids_entier <= j) matrice[i][j] = objets[i].valeur();
					else matrice[i][j] = 0;
				}
				else
				{
					if (poids_entier <= j) 
					{
						matrice[i][j] = Math.max(
							matrice[i - 1][j],
							matrice[i - 1][j - poids_entier] + objets[i].valeur()
						);
					}
					else matrice[i][j] = matrice[i - 1][j];
				}
			}
		}
		return matrice;
	}
	
	private static int _poids_optimal(double[][] matrice)
	{
		final int i = matrice.length - 1; 
		int j = matrice[i].length - 1;
		while (matrice[i][j] == matrice[i][j - 1]) j--;
		return j;
	}

	@Override
	public void resoudre(SacADos sac) 
	{
		if (sac.objets_possibles.length == 0) return;
		double[][] matrice = _matrice(sac.poids_max(), sac.objets_possibles);
		
		int poids = _poids_optimal(matrice);
		int i = sac.objets_possibles.length - 1;
		
		while (poids > 0)
		{
			while (i > 0 && matrice[i][poids] == matrice[i - 1][poids]) i--;
			poids -= _index(sac.objets_possibles[i].poids());
			if (poids >= 0)
				sac.objets.add(sac.objets_possibles[i]);
			i--;
		}
	}

}
