/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tx.TXCrypto;
import tx.models.KeyHandler;
import tx.models.Vigenere;

/**
 * FXML Controller class
 *
 * @author jbmartin
 */
public class ChiffreDechiffreWindowController implements Initializable {


    @FXML
    private TextArea inputTextField;
    @FXML
    private TextArea outputTextField;

    @FXML
    private Button cryptButton;
    @FXML
    private Button decryptButton;

    @FXML
    private TextField keyText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reset();
    }

    public void reset() {
        this.cryptButton.setDisable(true);
        this.decryptButton.setDisable(true);
        this.inputTextField.setText("");
        this.keyText.setText("");
        this.outputTextField.setText("");
    }

    public void onKeyChanged(final KeyEvent keyEvent) {
        keyText.setText(KeyHandler.filterText(keyText.getText()));
        keyText.positionCaret(keyText.getText().length());
        if (keyText.getText().equals("")) {
            this.cryptButton.setDisable(true);
            this.decryptButton.setDisable(true);
        } else {
            this.cryptButton.setDisable(false);
            this.decryptButton.setDisable(false);
        }
    }

    public void onInputChanged(final KeyEvent keyEvent) {
        inputTextField.setText(KeyHandler.filterText(inputTextField.getText()));
        inputTextField.positionCaret(inputTextField.getText().length());
    }

    @FXML
    private void decryptButtonPressed(ActionEvent e) {
        outputTextField.setText(Vigenere.decode(inputTextField.getText(), keyText.getText(), 0));
    }

    @FXML
    private void cryptButtonPressed(ActionEvent event) {
        outputTextField.setText(Vigenere.encode(inputTextField.getText(), keyText.getText()));
    }

    @FXML
    private void exit() {
        TXCrypto.gotoAccueil();
    }

}
