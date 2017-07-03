package vues.jfx;

import controleur.Controleur;
import controleur.exceptions.DejaConnecte;
import controleur.exceptions.IdentifiantsErrones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 29/06/2017.
 */
public class ControleurConnexion {

    @FXML
    TextField pseudo;

    @FXML
    PasswordField motDePasse;


    public VBox getConteneur() {
        return conteneur;
    }

    @FXML
    VBox conteneur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    Controleur controleur;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    Scene scene;


    public static ControleurConnexion getInstance(Controleur controleur) {

        URL location = ControleurConnexion.class.getResource("/vues/jfx/Connexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControleurConnexion vue = fxmlLoader.getController();
        vue.setScene(new Scene(vue.getConteneur(),ControleurVueApplicationJFX.LONGUEUR,ControleurVueApplicationJFX.LARGEUR));
        vue.setControleur(controleur);
        return vue;
    }


    public void rendreActive(Stage stage) {
        stage.setScene(getScene());
    }

    public void seConnecter(ActionEvent actionEvent) {
        if (pseudo.getText() == null || pseudo.getText().length()<3 ) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Le pseudo doit au moins contenir 4 lettres", ButtonType.OK);
            m.show();
            return;
        }

        if (motDePasse.getText() == null || motDePasse.getText().length() == 0 ) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Un mot de passes est obligatoire", ButtonType.OK);
            m.show();
            return;
        }

        try {
            controleur.login(pseudo.getText(), motDePasse.getText());
        } catch (DejaConnecte dejaConnecte) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Votre pseudo est déjà connecté", ButtonType.OK);
            m.show();
        } catch (IdentifiantsErrones identifiantsErrones) {
            Alert m = new Alert(Alert.AlertType.ERROR,"vos identifiants de connexion sont erronnés", ButtonType.OK);
            m.show();
        }


    }
}
