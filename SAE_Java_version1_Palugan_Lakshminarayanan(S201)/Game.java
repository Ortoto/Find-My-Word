package words;
import java.util.Scanner;

public class Game {

/* Les attributs */
	
	public static final String[] TENTATIVELISTE={"OK     ", "PRESENT", "ABSENT "};
	
	private String[] listeDesTentatives;
	private Word motSecret ;
	private int tentative;
	private int nbTour;
	private int point;
	private WordRepository aleatoire;
	private String joueur;


/* Les constructeurs */

    public Game(String joueur, int nbTour)  throws Exception {
		/*
        	Permet d’initialiser la variable d’instance grâce au nom (String) du joueur et à son nombre de tours (int).
    	*/
		this.listeDesTentatives = new String[6];
		this.tentative = 0;
		this.nbTour = nbTour;
		this.joueur = joueur;
		this.aleatoire= new WordRepository("wordset_bundle_etudiant/data/mots.json");
		this.motSecret = new Word (aleatoire.motAleatoire());
    }

    public Game(Game player)  throws Exception {
		/*
        	Permet d’initialiser la variable d’instance grâce à un constructeur par copie.
    	*/
		this.listeDesTentatives = new String[6];
		this.tentative = 0;
		this.nbTour = player.getNbTour();
		this.joueur = player.getJoueur();
		this.aleatoire= new WordRepository("wordset_bundle_etudiant/data/mots.json");
		this.motSecret = new Word (aleatoire.motAleatoire());
    }

/* Get */

	public String getMotListe (int i) {
		/*
            Retourne le mot d’indice i dans la liste de ses tentatives.
        */
		return this.listeDesTentatives[i];
	}

	public Word getMotSecret () {
		/*
            Retourne le mot secret de type Word.
        */
		return this.motSecret;
	}

	public int getTentative () {
		/*
            Retourne le nombre de tentatives effectuées.
        */
		return this.tentative;
	}

	public int getNbTour () {
		/*
            Retourne le nombre de tours effectués.
        */
		return this.nbTour;
	}

	public int getPoint (){
		/*
            Retourne le nombre de points.
        */
		return this.point;
	}

	public String getJoueur (){
		/*
            Retourne le nom du joueur.
        */
		return this.joueur;
	}


/* Set */

	public void setMotListe (String mot, int i) {
		/*
            Modifie le mot de la liste à l’indice i avec le mot passé en paramètre.
        */
		this.listeDesTentatives[i] = mot;
	}

	public void setTentative (int tentative) {
		/*
            Modifie le nombre de tentatives avec le nombre de tentatives passé en paramètre.
        */
		this.tentative = tentative;
	}

	public void setNbTour (int nbTour) {
		/*
            Modifie le nombre de tours avec le nombre de tours passé en paramètre.
        */
		this.nbTour = nbTour;
	}

	public void setPoint (int point){
		/*
            Modifie le nombre de points avec le nombre de points passé en paramètre.
        */
		this.point = point;
	}

	public void setJoueur (String nom){
		/*
            Modifie le nom du joueur avec le celui passé en paramètre.
        */
		this.joueur = nom;
	}


/* Méthodes */


	public boolean saisieTentative (){
		/*
            Permet au joueur d’effectuer une tentative. Le mot saisi est ensuite vérifié, s’il est valide, la méthode renvoie TRUE, sinon elle renvoie FALSE.
        */
		Scanner str = new Scanner(System.in);
    	System.out.print("Tentative " + (this.tentative + 1) + " : ");
    	String motSaisie = str.nextLine().toLowerCase();
		if (this.motSecret.motValide(motSaisie)){
			listeDesTentatives[tentative] = motSaisie;
			tentative = tentative + 1;
			vainqueur(motSaisie);
			return true;
		}
		return false;
	}

	public boolean vainqueur (String mot){
		/*
            Permet de vérifier si le mot passé en paramètre est le même que le mot secret. 
			Si c’est le cas, les points sont attribués en fonction du nombre de tentatives et la méthode renvoie TRUE sinon, elle renvoie FALSE.
        */
		if(this.motSecret.getMot().equals(mot)){
			this.point = 7 - tentative;
			return true;
		}
		return false;
	}

	public String[] essai (){
		/*
          Va créer un tableau contenant les réponses associées à la tentative, puis le renvoyer. Exemple : {"OK", "ABSENT", "OK", "PRÉSENT", "ABSENT"}.
        */
		String[] reponse = new String[5];
		if (saisieTentative()){
				for (int j=0; j < listeDesTentatives[this.tentative - 1].length(); j++){
					if (this.motSecret.contientLettre (listeDesTentatives[this.tentative - 1].charAt(j))){
						if (this.motSecret.memeLettre (listeDesTentatives[this.tentative - 1].charAt(j), j)){
							reponse[j] = TENTATIVELISTE[0];
						}else{
							reponse[j] = TENTATIVELISTE[1];
						}
					}else{
						reponse[j] = TENTATIVELISTE[2];
					}
			}
		}
		return reponse;
	}


	public String emplacementValide (String [] reponse) {
		/*
          Prend en paramètre un tableau de la forme {"OK", "ABSENT", "OK", "PRÉSENT", "ABSENT"} 
		  et renvoie une chaîne de caractères (String) de la forme : OK      ABSENT   OK      PRESENT ABSENT
        */
		String resultats = "";
        for (int j = 0; j < reponse.length; j++) {
            resultats += reponse[j];
            if (j < reponse.length - 1) {
                resultats += "  ";
            }
        }
    	resultats += "";
		return resultats;
	}

	public String getLigne (String resultats, int i){
		/*
          Prend en paramètre une chaîne de caractères (String) de la forme : OK      ABSENT   OK      PRESENT ABSENT
		  ainsi qu’un indice i correspondant à un mot dans la liste des tentatives.
		  Retoutne une une chaîne de caractères (String) de la forme : [ L ] [ O ] [ G ] [ E ] [ R ]  =>  OK      ABSENT   OK      PRESENT ABSENT
        */
		String motSaisie =  getMotListe(i);
		return "[ " + Character.toUpperCase(motSaisie.charAt(0)) + " ] [ " + Character.toUpperCase(motSaisie.charAt(1)) + " ] [ " + Character.toUpperCase(motSaisie.charAt(2)) + " ] [ " + Character.toUpperCase(motSaisie.charAt(3)) + " ] [ " + Character.toUpperCase(motSaisie.charAt(4)) + " ]  => " + resultats;
	}
	


/* toString */

	public String toString() {
		String resultat = "Game [ Mot Secret : " + this.motSecret.getMot() + ", Joueur : " + this.joueur + ", Tentative : " + this.tentative + ", Liste des tentatives :[";
		for (int i = 0; i < listeDesTentatives.length; i++) {
			resultat += listeDesTentatives[i];
			if (i < listeDesTentatives.length - 1) {
				resultat += ", ";
			}
		}
		resultat += "]]";
		return resultat;
	}
}
