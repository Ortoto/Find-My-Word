package words;

public class FixedWordRepository extends WordRepository {

/* L'attribut */

    private Word mot;

/* Le constructeur */

    public FixedWordRepository(String mot) throws Exception {
        /*
            Permet d’initialiser le repository avec un mot fixe
        */
        this.mot = new Word(mot);
    }

/* La méthode */

    public Word getWord() {
        /*
            Retourne toujours le même mot mais cette classe est surtout utile pour les tests
        */
        return this.mot;
    }
}
