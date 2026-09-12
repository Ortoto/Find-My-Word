package words;

public class WordRepository implements WordSet{

/* L' attribut */

    private WordSet listeMots;

/* Le constructeur */

    public WordRepository(String filePath) throws Exception {
        /*
            Permet d’initialiser la variable d’instance grâce à un chemin.
        */
        this.listeMots = new JsonWordSet(filePath);
    }

/* Les méthodes */

    public int size() {
        /*
            Permet de renvoyer un entier correspondant à la taille de la liste.
        */
        return this.listeMots.size();
    }

    public String word(int index) {
        /*
            Permet de renvoyer un mot (String) placé à l’indice passé en paramètre.
        */
        return this.listeMots.word(index);
    }

    public String random() {
        /*
            Permet de renvoyer un mot (String) placé aléatoirement dans la liste.
        */
        return this.listeMots.random();
    }

    public String startingWith(char letter) {
        /*
            Permet de renvoyer un mot (String) qui a comme premiere lettre celle passer en paramerte.
        */
        return this.listeMots.startingWith(letter);
    }
}

