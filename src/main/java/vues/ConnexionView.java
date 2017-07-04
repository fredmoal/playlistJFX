package vues;

import controleur.Controller;
import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import facade.erreurs.UtilisateurDejaConnecteException;
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
public class ConnexionView {
    @FXML
    TextField pseudo;
    @FXML
    PasswordField motDePasse;
    @FXML
    VBox conteneur;

    private Controller controller;
    private Scene scene;
    private Stage stage;

    public static ConnexionView getInstance(Controller controleur) {
        URL location = ConnexionView.class.getResource("/vues/Connexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConnexionView vue = fxmlLoader.getController();
        vue.setScene(new Scene(vue.getConteneur()));
        vue.setController(controleur);
        return vue;
    }

    public VBox getConteneur() {
        return conteneur;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public void seConnecter(ActionEvent actionEvent) {
        if (pseudo.getText() == null || pseudo.getText().length()<3 ) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Le pseudo doit au moins contenir 4 lettres", ButtonType.OK);
            m.show();
            return;
        }

        if (motDePasse.getText() == null || motDePasse.getText().length() == 0 ) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Un mot de passe est obligatoire", ButtonType.OK);
            m.show();
            return;
        }

        try {
            controller.login(pseudo.getText(), motDePasse.getText());
        } catch (CoupleUtilisateurMDPInconnuException e) {
            Alert m = new Alert(Alert.AlertType.ERROR,"vos identifiants de connexion sont erronnés", ButtonType.OK);
            m.show();
        } catch (UtilisateurDejaConnecteException e) {
            Alert m = new Alert(Alert.AlertType.ERROR,"Votre pseudo est déjà connecté", ButtonType.OK);
            m.show();
        }


    }

    public void display(Stage stage) {
        this.stage = stage;
        stage.setScene(getScene());
        stage.show();
    }

    public void close() {
        stage.close();
    }
}
