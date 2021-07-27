package pt.alexandre.gui.leTranscodeur.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static pt.alexandre.gui.leTranscodeur.tools.Constantes.ALPHABET;

/**
 * classe contenant le nécessaire pour générer une clef de chiffrage pour encoder/décoder les messages via
 * le transcodeur
 * @see Transcoder
 * @see pt.alexandre.gui.leTranscodeur.LeTranscodeurController
 * @see Constantes
 * @see Collections
 * @author Alexandre Lourencinho
 */
public class GenClef
{

    /**
     * méthode permettant de générer une clef de chiffrage aléatoire basée sur la constante ALPHABET
     * @return Une chaine de caractère contenant ceux de la constante ALPHABET qui servira de clef
     */
    public String randomKey()
    {
        List<Character> charList = Arrays.asList(ALPHABET);

        Collections.shuffle(charList);

        StringBuilder chaineCode = new StringBuilder();
        for (Character car : charList)
        {
            chaineCode.append(car);
        }
        return String.valueOf(chaineCode);
    }
}
