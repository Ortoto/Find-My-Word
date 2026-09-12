package words;

public class JsonWordRepository extends WordRepository {

/* L'attribut */

    private WordSet listeMots;

/* Le constructeur */

    public JsonWordRepository(String filePath) throws Exception {
        /*
            Permet d’initialiser la liste de mots grâce au fichier JSON
        */
        this.listeMots = new JsonWordSet(filePath);
    }

/* La méthode */

    public Word getWord() throws Exception {
        /*
            Retourne un mot aléatoire provenant du fichier JSON
        */
        return new Word(this.listeMots.random());
    }
}
