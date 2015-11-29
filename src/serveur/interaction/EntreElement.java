package serveur.interaction;

import java.rmi.RemoteException;
import java.util.logging.Level;

import serveur.Arene;
import serveur.ClientElement;
import serveur.ClientPersonnage;

public abstract class EntreElement <T extends ClientElement> {
	
	/**
	 * Arene (pour retrouver la reference du leader.
	 */
	protected Arene arene;
	/**
	 * Reference de l'attaquant.
	 */
	protected ClientPersonnage attaquant; 
	/**
	 * Reference du defenseur.
	 */
	protected T defenseur;
	
	
	public static final int distanceMinInteraction = 2;
	
	/**
	 * Constructeur
	 * @param arene arene
	 * @param attaquant la reference de l'attaquant
	 * @param defenseur la reference du defenseur
	 * @throws RemoteException
	 */
	public EntreElement(Arene arene, ClientPersonnage attaquant, T defenseur) {
		this.arene = arene;
		this.attaquant = attaquant;
		this.defenseur = defenseur;
	}
	
	public abstract void interagir()  throws RemoteException;
	
	protected void logs(Level level, String msg) {
		try {
			arene.log(Level.INFO, this.getClass().toString(), msg);
			arene.logClient(attaquant, Level.INFO, this.getClass().toString(), msg);
			arene.logClient(defenseur, Level.INFO, this.getClass().toString(), msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}