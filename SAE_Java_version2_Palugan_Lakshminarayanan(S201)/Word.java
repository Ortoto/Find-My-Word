package words;
public class Word {

/* L' attribut */
	 
	private String mot;
	private WordRepository aleatoire;
	
/* Les constructeurs */

	public Word(String mot) throws Exception {
		/*
            Permet d’initialiser la variable d’instance grâce à un mot (String).
        */
	    this.mot = mot;
	    this.aleatoire = new WordRepository("wordset_bundle_etudiant/data/mots.json");
	}

	public Word(String mot, String filePath) throws Exception {
		/*
            Permet d’initialiser la variable d’instance grâce à un mot (String) et un chemin (String).
        */
	    this.mot = mot;
	    this.aleatoire = new WordRepository(filePath);
	}

	public Word() throws Exception {
		/*
            Permet d’initialiser la variable grâce au constructeur vide.
        */
	    this.mot = "faire";
	    this.aleatoire = new WordRepository("wordset_bundle_etudiant/data/mots.json");
	}
	
/* Accesseurs et Mutateurs */	

	public String getMot(){
		/*
            Retourne un mot (String).
        */
		return this.mot;
	}

	public char getLettre (int i){
		/*
            Retourne une lettre (char).
        */
		return this.mot.charAt(i);
	}
		
	public void setMot(String mot){
		/*
            Modifie le mot (variable) si le mot passé en paramètre est valide.
        */
		if (motValide(mot)){
			this.mot=mot;
		}
	}
	
/* Méthodes */


	public boolean memeLettre (char Lettre, int position){
		/*
            Retourne un booléen en fonction de la lettre mise en paramètre et de sa position dans le mot (position), afin de vérifier si elles sont identiques.
        */
		if (this.mot.charAt(position) == Lettre){
			return true;
		}
		return false;
	}
		
	public boolean contientLettre (char lettre){
		/*
            Retourne un booléen en fonction de la lettre mise en paramètre et de sa présence dans le mot.
        */
		for (int i = 0; i <this.mot.length(); i++){
			if (this.mot.charAt(i) == lettre){
				return true;
			}
		}
		return false;
	}

	public boolean repetitionLettre (String mot){
		/*
            Retourne un booléen en fonction du mot mis en paramètre et de la présence de lettres en double dans le mot.
        */
		for (int i = 0; i < mot.length(); i++){
			for (int j = i + 1; j < mot.length(); j++){
				if (mot.charAt(i) == mot.charAt(j)){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public boolean uniquementLettre(String mot) {
		/*
            Retourne un booléen en fonction du mot mis en paramètre et du fait qu’il ne contienne que des lettres.
        */
		for (int i = 0; i < mot.length(); i++) {
				char m = mot.charAt(i);

			if (m < 'a' || m > 'z') {
					return false;
			}
		}
		return true;
	}
	
	
	
	
	public boolean motValide (String mot){
		/*
            Retourne un booléen en fonction du mot mis en paramètre et de sa validité selon les règles du jeu.
        */
		if (mot.length()==5 && repetitionLettre(mot) && uniquementLettre(mot)  ){
			return true;
		}
		erreurMot(mot);
		return false;
	}
	

	
	
	public void erreurMot(String mot){
		/*
            Affiche des erreurs dans le terminal en fonction de leur type.
        */
		if( mot.length()!=5){
			System.out.println("Erreur : le mot doit contenir exactement 5 lettres. ");
			}
	
		else if(!repetitionLettre(mot)){
			System.out.println("Erreur : le mot ne doit pas contenir de lettre répétée. ");
			}
	
		else if (!uniquementLettre(mot)){
			System.out.println("Erreur : le mot doit contenir uniquement des lettres. ");
			}
	}
	
	
/* toString */

	public String toString(){
		return "Word [ Mot : " + this.mot + "]";
	}
}
	
	
	
		
	
		
		
		
		
	


