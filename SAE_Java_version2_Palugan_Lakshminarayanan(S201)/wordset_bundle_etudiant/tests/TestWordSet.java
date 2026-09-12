package words;

/**
 * Simple test class for the WordSet library.
 */
public class TestWordSet {

    /**
     * Runs a simple test.
     *
     * @param args command line arguments
     * @throws Exception if an error occurs
     */
    public static void main(String[] args) throws Exception {
        WordSet words = new JsonWordSet("data/mots.json");

        System.out.println("Number of words: " + words.size());
        System.out.println("Random word: " + words.random());
        System.out.println("Word at index 1: " + words.word(1));
        System.out.println("Word starting with 'm': " + words.startingWith('m'));
    }
}
