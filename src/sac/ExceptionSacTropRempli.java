package sac;

public class ExceptionSacTropRempli extends Exception {

	public ExceptionSacTropRempli(float poids, float poids_max) {
		super("Le poids maximal du sac (" + poids_max + ") est incoh�rent avec le poids du sac (" + poids + ").");
	}

}
