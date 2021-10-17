package app;

import java.io.IOException;

import algos.FAlgorithme;
import algos.IAlgorithme;
import sac.ExceptionSacTropRempli;
import sac.Objet;
import sac.SacADos;

abstract public class Application 
{

	private static final float POIDS_MAX = 7.f;
	private static final Objet[] OBJETS()
	{
		return new Objet[]{
			new Objet("epée", 1.f, 2.3f),
			new Objet("bouclier", 2.1f, 1.3f),
			new Objet("gemme verte", 0.4f, 3.2f),
			new Objet("gemme rouge", 0.98f, 6.3f),
			new Objet("bois", 4.3f, 1.72f),
			new Objet("plante", 1.87f, 1.98f)
		};
	}
	private static final String METHODE = "gloutonne";
	
	public static void main(String[] args) 
	{
		SacADos sac = null;
		StringBuilder methode = new StringBuilder();
		
		if (args.length == 0)
		{
			sac = new SacADos();
			sac.objets_possibles = OBJETS();
			sac.modifier_poids_max(POIDS_MAX);
			methode.append(METHODE);
		}
		else if (args.length >= 3)
		{
			try { sac = new SacADos(args[0], Float.parseFloat(args[1])); }
			catch (IOException e) 
			{
				System.err.println("Erreur durant la lecture du fichier \"" + args[0] + "\".");
				System.err.println(e.getMessage());
				System.exit(-2);
			}
			catch (NumberFormatException e)
			{
				System.err.println("Le deuxième argument (" + args[1] + ") doit être un réel.");
				System.exit(-1);
			}
			
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
		
		IAlgorithme algo = FAlgorithme.methode(methode.toString());
		if (algo == null)
		{
			System.err.println("La méthode \"" + methode.toString() + "\" est inconnue.");
			System.exit(-1);
		}
		
		algo.resoudre(sac);
		System.out.print(sac);
		
		try { sac.verifier_integrite(); }
		catch (ExceptionSacTropRempli e)
		{ System.err.println(e.getMessage()); }
	}

}
