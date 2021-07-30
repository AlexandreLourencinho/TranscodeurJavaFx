package pt.alexandre.gui.leTranscodeur.tools;

/**
 * classe contenant simplement une constante sous forme de tableau contenant les différents caractères à encoder.
 */
public class Constantes
{
    public static final Character[] ALPHABET = {
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            ' ',',','.','!',':','\'','?',
    };

    private static final String SECRET_KEY = "Afpa Rocks!!!!";

    public static String getSecretKey() {
        return SECRET_KEY;
    }
}
