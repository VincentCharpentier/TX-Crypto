/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import tx.TXCrypto;

/**
 * FXML Controller class
 *
 * @author vincetn
 */
public class MainWindowController implements Initializable {

    @FXML
    private Button chiffreDechiffreButton;
    @FXML
    private Button cryptanalyseButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void showChiffreDechiffre() {
        TXCrypto.gotoChiffrerDechiffrer();
    }

    @FXML
    private void showCrypto() {
        TXCrypto.gotoCrypto();
    }

}
