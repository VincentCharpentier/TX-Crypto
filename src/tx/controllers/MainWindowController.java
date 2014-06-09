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
import tx.model.Vigenere;

/**
 * FXML Controller class
 *
 * @author jbmartin
 */
public class MainWindowController implements Initializable {

    @FXML
    private TextArea cryptAndDecryptOutputTextField;

    @FXML
    private Button cryptButton;

    @FXML
    private TextArea analysisInputTextField;

    @FXML
    private TextField key;

    @FXML
    private TextArea cryptAndDecryptInputTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void decryptButtonPressed(ActionEvent e) {
        cryptAndDecryptOutputTextField.setText(Vigenere.decode(cryptAndDecryptInputTextField.getText(), key.getText(), 0));
    }

    @FXML
    private void cryptButtonPressed(ActionEvent event) {
        cryptAndDecryptOutputTextField.setText(Vigenere.encode(cryptAndDecryptInputTextField.getText(), key.getText()));
    }

}
