/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jbmartin
 */
public class TXCrypto extends Application {

    private Stage stage;
    private static TXCrypto instance;

    public TXCrypto() {
        instance = this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            gotoAccueil();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(TXCrypto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gotoAccueil() {
        try {
            instance.replaceSceneContent("views/MainWindow.fxml");
        } catch (Exception ex) {
            Logger.getLogger(TXCrypto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gotoChiffrerDechiffrer() {
        try {
            instance.replaceSceneContent("views/ChiffreDechiffreWindow.fxml");
        } catch (Exception ex) {
            Logger.getLogger(TXCrypto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gotoCrypto() {
        try {
            instance.replaceSceneContent("views/CryptoView.fxml");
        } catch (Exception ex) {
            Logger.getLogger(TXCrypto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(TXCrypto.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            //scene.getStylesheets().add(App.class.getResource("demo.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

}
