package sac;

public class ExceptionSacTropRempli extends Exception {

	public ExceptionSacTropRempli(float poids, float poids_max) {
		super("Le poids maximal du sac (" + poids_max + ") est incohérent avec le poids du sac (" + poids + ").");
	}

}
