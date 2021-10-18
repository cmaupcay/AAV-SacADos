package app;

import java.io.IOException;

import algos.FAlgorithme;
import algos.IAlgorithme;
import sac.ExceptionSacTropRempli;
import sac.SacADos;

public abstract class Application 
{
	// Arguments par défaut
	private static final double POIDS_MAX = 10.d;
	private static final String FICHIER = "sujet.txt";
	private static final String METHODE = "pse";
	
	public static void main(String[] args) 
	{
		String fichier = "";
		double poids_max = 0.d;
		StringBuilder methode = new StringBuilder();
		
		if (args.length == 0) // Sans arguments
		{
			fichier = FICHIER;
			poids_max = POIDS_MAX;
			methode.append(METHODE);
		}
		else if (args.length >= 3) // Arguments définies et assez nombreux pour démarrer
		{
			fichier = args[0];
			try { poids_max = Double.parseDouble(args[1]); }
			catch (NumberFormatException e)
			{
				System.err.println("Le deuxième argument (" + args[1] + ") doit être un réel.");
				System.exit(-1);
			}
			// Le nom de la méthode correspond à la concaténation de tous les arguments à partir du troisième
			for (int i = 2; i < args.length; i++)
			{
				methode.append(args[i]);
				if (i < args.length - 1)
					methode.append(" ");
			}
		}
		else
		{
			System.err.println("Le nombre d'argument (" + args.length + ") n'est pas valide.");
			System.exit(-1);
		}

		// On construit le sac
		SacADos sac = null;
		try { sac = new SacADos(fichier, poids_max); }
		catch (IOException e) 
		{
			System.err.println("Erreur durant la lecture du fichier \"" + fichier + "\".");
			System.err.println(e.getMessage());
			System.exit(-2);
		}
		
		// On défini quel algorithme utilisé selon la méthode indiquée
		IAlgorithme algo = FAlgorithme.methode(methode.toString());
		if (algo == null)
		{
			System.err.println("La méthode \"" + methode.toString() + "\" est inconnue.");
			System.exit(-1);
		}
		
		// Calcul de la proposition optimale
		long debut = System.nanoTime();
		algo.resoudre(sac);
		float temps = (System.nanoTime() - debut) / 1000000.f;
		// Affichage des informations
		System.out.println(sac);
		System.out.println("Résolu avec l'algorithme " + algo.nom().toUpperCase() + " en " + temps + " ms.");
		
		// Indique si le poids du sac n'est pas cohérent
		try { sac.verifier_integrite(); }
		catch (ExceptionSacTropRempli e)
		{ System.err.println(e.getMessage()); }
	}

}
