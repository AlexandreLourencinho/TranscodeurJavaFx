package pt.alexandre.gui.leTranscodeur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.germain.tool.ManaBox;
import pt.alexandre.gui.leTranscodeur.tools.GenClef;
import pt.alexandre.gui.leTranscodeur.tools.Transcoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Classe permettant de gérer les différentes méthodes qui chiffrent / déchiffrent des messages (codés ou non, donc)
 * permet aussi la génération de clef de chiffrage
 *
 * @author Alexandre Lourencinho
 * @see Label
 * @see TextField
 * @see FXML
 * @see GenClef
 * @see GenClef#randomKey()
 * @see Transcoder
 * @see Files
 * @see Path
 */
public class LeTranscodeurController
{

    public Label labelClefSauvee;
    public ToggleButton boutonVerrouillage;
    public HBox testonssa;
    @FXML
    public TextArea txtClair;
    @FXML
    public TextArea txtCode;
    public TextField txtClef;

    Transcoder trans;

    public void initialize()
    {
        txtClair.textProperty().addListener(evt->encoderMessage());

        txtCode.textProperty().addListener(evt->decoderMessage());


        txtClef.textProperty().addListener(evt ->
        {
            trans = new Transcoder(txtClef.getText());
            champsEditable();
        });
    }

    /**
     * méthode permettant l'appel de la génération de clef (via la classe éponyme)
     */
    public void genererClef()
    {
        GenClef gen = new GenClef();
        txtCode.clear();
        txtClair.clear();
        txtClef.setText(ManaBox.encrypt(gen.randomKey()));
    }

    /**
     * méthode appelant l'encodage du message et son écriture dans le champ correspondant
     */
    public void encoderMessage()
    {
//        trans = new Transcoder(txtClef.getText());
        if (txtClair.isFocused())
        {
            StringBuilder msg = new StringBuilder();
            for (String ligne : txtClair.getText().split("\n"))
            {
                msg.append(trans.encode(ligne)).append("\n");
            }
            txtCode.setText(String.valueOf(msg));
        }

    }

    /**
     * méthode appelant le décodage du message et son écriture dans le champ correspondant
     */
    public void decoderMessage()
    {
        if (txtCode.isFocused())
        {
            StringBuilder msg = new StringBuilder();
            for (String ligne : txtCode.getText().split("\n"))
            {
                msg.append(trans.decode(ligne)).append("\n");
            }
            txtClair.setText(msg.toString());
        }
    }

    public void champsEditable()
    {
        if (!txtClef.getText().equals(""))
        {
            txtCode.setDisable(false);
            txtClair.setDisable(false);
        } else
        {
            txtClair.setDisable(true);
            txtCode.setDisable(true);
        }
    }

    /**
     * méthode permettant simplement de vider les différents champs
     */
    public void viderChamps()
    {
        txtCode.clear();
        txtClair.clear();
        if (!txtClef.isDisable())
        {
            txtClef.clear();
        }

    }

    public void protegerClef()
    {
        if (boutonVerrouillage.isSelected())
        {
            txtClef.setDisable(true);
            boutonVerrouillage.setStyle("-fx-background-color: limegreen; -fx-text-fill: black");
            testonssa.setSpacing(12);
            boutonVerrouillage.setText("Déverrouiller");
        } else
        {
            txtClef.setDisable(false);
            boutonVerrouillage.setStyle("-fx-background-color: darkred; -fx-text-fill: cyan");
            testonssa.setSpacing(25);
            boutonVerrouillage.setText("Verrouiller");
        }
    }

    /**
     * méthode permettant l'enregistrement de la clef dans un fichier TXT placé dans le dossier du programme
     * fichier nommé fichierclef + un chiffre aléatoire
     */
    public void sauverClef()
    {
        String dir = System.getProperty("user.dir");
        int rand = (int) (100 * Math.random());
        String fichier = "fichierclef" + rand + ".txt";
        Path fich = Paths.get(dir, fichier);
        try
        {
            Files.writeString(fich, txtClef.getText(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            labelClefSauvee.setText("Votre clef a été sauvegardée dans le fichier " + fichier);
        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("erreur enregistrement de clef");
            alert.setContentText(
                    "Attention ! : la clef ne s'est pas correctement enregistrée. Vérifiez les droits en écriture" +
                            "ou contactez un administrateur.");
            alert.showAndWait();
        }
    }
}
