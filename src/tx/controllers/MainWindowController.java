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
    private TextArea outputTextField;

    @FXML
    private Button cryptButton;

    @FXML
    private TextArea inputTextField;
    
    @FXML
    private TextField key;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void decryptButtonPressed(ActionEvent e) {
        outputTextField.setText(Vigenere.decode(inputTextField.getText(), key.getText()));
    }
    
    @FXML
    private void cryptButtonPressed(ActionEvent event) {
        outputTextField.setText(Vigenere.encode(inputTextField.getText(), key.getText()));
    }
    
}
