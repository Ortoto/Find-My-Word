package words;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.Duration;

public class Main{

public static void main(String[]args) throws Exception{


    LocalTime tempTotal = LocalTime.now();     // Va permettre d’afficher le temps total à la fin de la partie.

    System.out.println("================================");
    System.out.println("FIND MY WORD - BUT1");
    System.out.println("================================");

    System.out.println("");

    boolean choix = false;
    int nbJoueur = 0;

    while (choix == false){
		/*
        	Permet de choisir le mode de jeu : Solo ou Duo.
    	*/
        Scanner inte = new Scanner(System.in);
        System.out.print("Choisissez le nombre de Joueurs (1 ou 2) : ");
        nbJoueur = inte.nextInt();
        if (nbJoueur == 1 || nbJoueur == 2){
            choix = true;
        }
    }
    
    Joueur player1 = new Joueur();
    player1.saisieNom();

    Joueur player2 = null;      // Obligé de laisser ça car certaines de nos conditions dépendent du deuxième joueur même en mode solo.

    if (nbJoueur == 2) {
		/*
        	Crée un deuxième joueur si le mode de jeu choisi est duo.
    	*/
        player2 = new Joueur(); 
        player2.saisieNom();
    }

    boolean tourValide = false;
    int nbTour = 0;

    while (tourValide == false){
        /*
        	Permet de choisir un nombre de tours, quel que soit le mode de jeu, tant qu’il est compris dans les valeurs proposées.
    	*/
        Scanner inte = new Scanner(System.in);
        System.out.print("Choisir votre nombre de tour (1/2/3/5/10/20) : ");
        nbTour = inte.nextInt();
        if (nbTour == 1|| nbTour == 2 || nbTour == 3  || nbTour == 5 || nbTour == 10 || nbTour == 20){
            tourValide = true;
        }
    }

    Game partie = new Game(player1.getNom(), nbTour);
    String [] listResultats = new String[6*nbTour*nbJoueur];        // On crée une liste capable d’accueillir toutes les tentatives de tous nos joueurs.
    int tentativeTotal= 0;

    System.out.println("");

    System.out.println("Bonjour " + player1.getNom());
    
    if (nbJoueur == 2) {
        System.out.println("Bonjour " + player2.getNom());
    }

    for (int k=0; k<nbTour*nbJoueur; k++) {

        /*
        	Tant que tous les joueurs n’auront pas effectué tous leurs tours, le jeu continuera.
    	*/
    
        if (nbJoueur == 1){
            partie = new Game(player1.getNom(), nbTour);
        }
        else{
            /*
        	    Si le nombre de tours est pair, alors Player1 joue sinon Player2 joue.
    	    */
            if(k%2==0){
                partie = new Game(player1.getNom(), nbTour);  
            }
            else{
                partie = new Game(player2.getNom(), nbTour); 
            }
        }

        if (nbTour != 1) {
                    /*
        	    Si le nombre de tours choisi par l’utilisateur est supérieur à 1, alors on affiche le tour sinon, c’est inutile de l'afficher.
    	    */
	    System.out.println("");

	    if (nbJoueur == 1) {
		System.out.println("--- Tour " + (k+1) + ", de " + player1.getNom() + " ---");
	    }
	    else {
		if (k % 2 == 0) {
		    System.out.println("--- Tour " + (k+1) + ", c'est à " + player1.getNom() + "  de jouer ---");
		}
		else {
		    System.out.println("--- Tour " + (k+1) + ", c'est à " + player2.getNom() + " de jouer ---");
		}
	    }

	    System.out.println("");
	}

        LocalTime temp = LocalTime.now();       // Va permettre d’afficher le temps que la personne a pris pour essayer de trouver son mot secret.

        System.out.println("");
        System.out.println("[   ] [   ] [   ] [   ] [   ]  =>  ******  ******  ******  ******  ******");
        System.out.println("");   

        for (int i=0; i < 6; i++){

            /*
                Tant que la personne n’a pas terminé ses 6 essais ou n’a pas trouvé le mot secret, il doit continuer.
            */

            String [] reponse = partie.essai();
            while(reponse[1] == null) {             // On vérifie si l’utilisateur a bien écrit quelque chose.
                reponse = partie.essai();
            }
            String resultat = partie.emplacementValide(reponse);
            listResultats[i+6*k] = partie.getLigne(resultat, i);
            System.out.println("");
            for (int j=0; j < 6; j++) {
                /*
                    Tant qu'il y a des tentative relié a ce mot secret on les affiche.
                */
                if (listResultats[j+6*k] != null){          // Condition qui permet d’afficher tous les essais précédents tant qu’il y a un résultat et que j ne soit pas égal à 6.
                    System.out.println(listResultats[j+6*k]);
                }else {
                    System.out.println("");
                    System.out.println("------------");
                    System.out.println("");
                    j = 6;
                }
            }
            if (partie.getPoint() > 0){
                /*
                    Si on a gagné des points, c’est que l’on a trouvé le mot secret ; dans ce cas, on termine le tour en mettant i à 6.
                */
                i = 6;
            }
        }

        LocalTime new_temp = LocalTime.now();  
        Duration duree = Duration.between(temp, new_temp);  

        tentativeTotal += partie.getTentative(); 

        if (nbJoueur == 1){
            /*
                Sauvegarde les statistiques du joueur.
            */
            player1.ajouteScore(partie.getPoint());
            player1.ajouteTentative(partie.getTentative());
            player1.ajouteTemps(duree.getSeconds());
        }
        else{
            /*
                Sauvegarde les statistiques du joueur en fonction de celui qui a joué.
            */
            if(k%2==0){
                player1.ajouteScore(partie.getPoint()); 
                player1.ajouteTentative(partie.getTentative());
                player1.ajouteTemps(duree.getSeconds());
            }
            else{
                player2.ajouteScore(partie.getPoint());
                player2.ajouteTentative(partie.getTentative());
                player2.ajouteTemps(duree.getSeconds());
            }
        }

        if ( partie.getPoint() >0){
            /*
                Affiche les statistiques du tour si le joueur a gagné.
            */
            System.out.println("");
            System.out.println("Temps :  " +duree.toHours() + "h " + duree.toMinutes() %60+ "min " + duree.getSeconds() %60+ "s"  );
            System.out.println("Bravo "+ partie.getJoueur());
            System.out.println("Le mot secret était bien : " +partie.getMotSecret().getMot());
            System.out.println("Vous avez trouvé le mot en " +partie.getTentative() + " tentatives");
            System.out.println("Vous avez acquis : "+partie.getPoint()+" points");
            System.out.println("");
        }
        else{
            /*
                Affiche les statistiques du tour si le joueur a perdu.
            */
            System.out.println("");
            System.out.println("Dommage "+ partie.getJoueur());
            System.out.println("Temps :  " +duree.toHours() + "h " + duree.toMinutes() %60+ "min " + duree.getSeconds() %60+ "s"  );
            System.out.println("Vous avez utilisé toutes vos tentatives.");
            System.out.println("Partie perdue.");
            System.out.println("Mot secret : "+partie.getMotSecret().getMot());
            System.out.println(""); 
        }

        // On remet les statistiques de la partie à 0 après chaque tour.

        partie.setTentative(0); 
        partie.setPoint(0);
    }

    if (nbTour != 1){

        /*
            Affiche les statistiques globales de la partie uniquement s’il y a eu plus d’un tour.
        */

        LocalTime new_temp = LocalTime.now();
        Duration duree = Duration.between(tempTotal, new_temp);
        System.out.println("");
        System.out.println("Temps total :  " +duree.toHours() + "h " + duree.toMinutes() %60+ "min " + duree.getSeconds() %60+ "s"  );
        System.out.println("Vous avez utilisé un total de "+tentativeTotal+" tentatives");
        System.out.println("Vous avez un total de "+player1.getScore()+" points");
        System.out.println("");

        if (nbJoueur == 2){

            /*
                Affiche le gagnant s’il y a eu 2 joueurs.
            */
            System.out.println(player2.getNom()+" a un total de "+player2.getScore()+" points");
            System.out.println("");
            

            if (player1.getScore() > player2.getScore()){
                System.out.println("Le Vainqueur est : "+player1.getNom());
            }
            else if (player2.getScore() > player1.getScore()){
                System.out.println("Le Vainqueur est : "+player2.getNom());
            }
            else{
                System.out.println("Égalité entre les deux joueurs.");
            }
        }

        System.out.println(""); 
    }

}
}
