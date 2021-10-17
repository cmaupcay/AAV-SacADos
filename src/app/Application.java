package app;

import java.io.IOException;

import algos.FAlgorithme;
import algos.IAlgorithme;
import sac.ExceptionSacTropRempli;
import sac.Objet;
import sac.SacADos;

public class Application {

	public static void main(String[] args) 
	{
		SacADos sac = null;
		StringBuilder methode = new StringBuilder();
		
		if (args.length == 0)
		{
			sac = new SacADos();
			sac.objets.add(new Objet("epée", 1.f, 2.f));
			methode.append("gloutonne");
		}
		else if (args.length >= 3)
		{
			try { sac = new SacADos(args[0], Float.parseFloat(args[1])); }
			catch (IOException e) 
			{
				System.err.println("Erreur durant la lecture du fichier \"" + args[0] + "\".");
				System.exit(-2);
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
		
		System.out.println("# AVANT");
		System.out.print(sac);
		try { sac.verifier_integrite(); }
		catch (ExceptionSacTropRempli e)
		{ System.err.println(e.getMessage()); }
		
		algo.resoudre(sac);
		
		System.out.println("# APRES");
		System.out.print(sac);
		try { sac.verifier_integrite(); }
		catch (ExceptionSacTropRempli e)
		{ System.err.println(e.getMessage()); }
	}

}
