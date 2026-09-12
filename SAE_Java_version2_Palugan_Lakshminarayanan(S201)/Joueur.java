package words;
import java.util.Scanner;

public class Joueur {

/* L' attribut */

	private String nom;
	private int scoreTotal;
	private int nbrTentative;
	private long tempsTotal;
	
	
/* Les constructeurs */

	public Joueur (){
		/*
            Permet d’initialiser la variable grâce au constructeur vide.
        */
		this.nom=" ";
		this.scoreTotal=0;
		this.nbrTentative=0;
		this.tempsTotal=0;
		}
	
	public Joueur ( String nom){
		/*
            Permet d’initialiser la variable d’instance grâce à un nom (String).
        */
		this.nom=nom;
		this.scoreTotal=0;
		this.nbrTentative=0;
		this.tempsTotal=0;
		
		}


/* Accesseurs et Mutateurs */	

	public String getNom(){
		/*
            Retourne le nom du joueur (String).
        */
		return this.nom;
		}
		
	public void setNom(String nom){
		/*
            Modifie le nom (variable) avec le nom passé en paramètre.
        */
		this.nom=nom;
		}
		
	public int getScore(){
		/*
            Retourne le score du joueur (int).
        */
		return this.scoreTotal;
		}
		
	public void setScore(int score){
		/*
            Modifie le score (variable) avec le score passé en paramètre.
        */
		this.scoreTotal=score;
		}
		
	public int getTentative(){
		/*
            Retourne le nombre de tentative du joueur (int).
        */
		return this.nbrTentative;
		}
		
	public void setTentative(int tentative){
		/*
            Modifie le nombre de tentative du joueur (variable) avec le nombre de tentative passé en paramètre.
        */
		this.nbrTentative=tentative;
		}
		
		
	public long getTemps(){
		/*
            Retourne le temps du joueur (long).
        */
		return this.tempsTotal;
		}
		
	public void setTemps(long temps){
		/*
            Modifie le temps du joueur (variable) avec le temps passé en paramètre.
        */
		this.tempsTotal=temps;
		}


/* Méthodes */

	public void ajouteScore( int score){
		/*
            Permet d’ajouter au score (variable) du joueur le score passé en paramètre.
        */
		this.scoreTotal+=score;
	}
	
	public void ajouteTentative (int tentative){
		/*
            Permet d’ajouter au nombre de tentative (variable) du joueur le nombre de tentative passé en paramètre.
        */
		this.nbrTentative+=tentative;
	}
	
	public void ajouteTemps (long temps){
		/*
            Permet d’ajouter au temps (variable) du joueur le temps passé en paramètre.
        */
		this.tempsTotal+=temps;
	}
	
	public void saisieNom(){
		/*
            Permet au joueur de saisir son nom dans le terminal afin de l’enregistrer dans la variable nom.
        */
		Scanner str = new Scanner(System.in);
    	System.out.print("Entrez votre nom : ");
        this.nom = str.nextLine();
	}
}