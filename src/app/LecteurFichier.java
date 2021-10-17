package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sac.Objet;

abstract public class LecteurFichier 
{
	public static Objet[] objets(String chemin) throws IOException
	{
		BufferedReader fichier = null;
		fichier = new BufferedReader(new FileReader(chemin));
		
		ArrayList<Objet> _objets = new ArrayList<>();
		String ligne = null;
		String[] infos = null;
		while ((ligne = fichier.readLine()) != null)
		{
			infos = ligne.split(" ; ");
			if (infos.length != 3) continue;
			_objets.add(
				new Objet(infos[0], Float.parseFloat(infos[1]), Float.parseFloat(infos[2]))
			);
		}
		fichier.close();
		
		// Conversion en tableau
		final int n_objets = _objets.size();
		Objet[] objets = new Objet[n_objets];
		for (int i = 0; i < n_objets; i++)
			objets[i] = _objets.get(i);
		return objets;
	}
}
