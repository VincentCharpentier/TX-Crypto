/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx.controllers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tx.TXCrypto;
import tx.models.Constantes;
import static tx.models.Constantes.KEY_MAX_LENGTH;
import tx.models.Cryptanalyse;
import tx.models.HistoFreq;
import tx.models.KeyHandler;
import tx.models.SimpleText;
import tx.models.Vigenere;
import static tx.models.Vigenere.encode;

/**
 * FXML Controller class
 *
 * @author vincetn
 */
public class CryptoViewController implements Initializable {

    @FXML
    private TextField keySizeText;

    private int keySize;

    @FXML
    private Label keyText;

    private String key;

    @FXML
    private TextArea encodedText;
    @FXML
    private TextArea decodedText;
    @FXML
    private Label indiceMoyen;
    @FXML
    private Label comparNo;
    @FXML
    private Label comparTot;
    @FXML
    private ChoiceBox langues;
    @FXML
    private Button minButton;
    @FXML
    private Button plusButton;
    @FXML
    private Button prevHistoButton;
    @FXML
    private Button nextHistoButton;

    @FXML
    private Label indiceLangue;


    /**
     * Un histoFreq pour chaque lettre de la clé.
     */
    private List<HistoFreq> listHisto;

    /**
     * Un shift (valeur de décalage) pour chaque histogramme.
     */
    private List<Integer> listShift;

    //private final List<XYChart.Series> listSeries = new ArrayList<>();

    /**
     * Histogramme actuellement affiché (indice dans listHisto).
     */
    private int currentHisto;

    /**
     * La série de donnée frequement modifiée dans le graphe.
     */
    private XYChart.Series serie;

    @FXML
    private LineChart<Number,Number> diagramme;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        keySize = 1;
        listShift = new ArrayList();
        listShift.add(0);
        serie = new XYChart.Series();
        updateKeyText();
        updateKeySizeText();

        serie.setName("Calculé");

        langues.getItems().add("Français");
        langues.getItems().add("Anglais");
        langues.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(
                            final ObservableValue<? extends Number> observableValue,
                            final Number number,
                            final Number number2)
                    {
                        onLangueChange();
                    }
                });



        //xAxis.setLabel("Number of Month");
        //shift = new LineChart<Number, Number>(xAxis, yAxis);
        diagramme.setTitle("Comparatif des occurences");
        //defining a series
        XYChart.Series ref = new XYChart.Series();
        ref.setName("Reference");
        //populating the series with data
        for (int i = 0; i < 26; i++) {
            ref.getData().add(new XYChart.Data(
                    Character.toString((char)('A'+i)),
                    Constantes.diagFR.get(i)));
        }
        diagramme.getData().add(ref);
        diagramme.getData().add(serie);

        updateHistoDisplay();
        updatePrevText();

        minButton.setDisable(true);

    }

    private void onLangueChange() {
        final int selected = langues.getSelectionModel().getSelectedIndex();
        switch (selected) {
            // FR
            case 0:
                indiceLangue.setText(String.valueOf(Cryptanalyse.INDICE_FR));
                break;
            default:
                indiceLangue.setText("NOT IMPLEMENTED");
                break;
        }

    }

    /**
     * mettre à jour l'affichage de la clé.
     */
    private void updateKeyText() {
        final StringBuilder buff = new StringBuilder(keySize);
        for (int i = 0; i < keySize; i++) {
            buff.append((char) ('A' + listShift.get(i)));
        }
        key = buff.toString();
        keyText.setText(key);
    }

    /**
     * met à jour l'affichage de la taille de la clé.
     */
    private void updateKeySizeText() {
        keySizeText.setText(String.valueOf(keySize));
        onKeySizeChange();
    }

    public void onGuessKeySize() {
        final int currentSize = keySize;
        keySize = Cryptanalyse.AutoFindKeyLength(encodedText.getText());
        if (keySize < currentSize) {
            key = key.substring(0, keySize);
            for (int i = currentSize; i < keySize; i--) {
                listShift.remove(i);
            }
        } else {
            if (keySize > currentSize) {
                for (int i = currentSize; i < keySize; i++) {
                    key = key + 'A';
                    listShift.add(0);
                }
            }
        }
        updateKeySizeText();
        updateKeyText();
        updatePrevText();
    }

    public void onKeySizeChange() {
        listHisto = Cryptanalyse.makeAllHisto(encodedText.getText(),keySize);
        updateIndiceMoyen();
        updatePlusMinButtons();
        currentHisto = 0;
        comparTot.setText("" + keySize);
        updateHistoDisplay();
    }

    /**
     * Activer / desactiver bouttons +/- en fontion de la taille de clé.
     */
    public void updatePlusMinButtons() {
        plusButton.setDisable(false);
        minButton.setDisable(false);
        if (keySize < 2) {
            minButton.setDisable(true);
        } else {
            if (keySize > KEY_MAX_LENGTH - 1) {
                plusButton.setDisable(true);
            }
        }
    }


    /**
     * Augmenter la taille de la clé de 1.
     */
    public final void onPlus() {
        keySize++;
        key = key + 'A';
        listShift.add(0);
        updateKeyText();
        updateKeySizeText();
    }

    /**
     * Diminuer la taille de la clé de 1.
     */
    public void onMin() {
        keySize--;
        key = key.substring(0, keySize);
        listShift.remove(keySize);
        updateKeyText();
        updateKeySizeText();
    }
/*
    public void onHistoUpdate() {
        listSeries.clear();

        for(HistoFreq histo : listHisto) {
            XYChart.Series serie = new XYChart.Series();
            //populating the series with data
            for (int i = 0; i < 26; i++) {
                char c = (char)('A'+i);
                serie.getData().add(new XYChart.Data(
                        Character.toString(c),
                        histo.getFreq(c)*100));
            }
            listSeries.add(serie);
        }

        currentHisto = 0;
        updateHistoDisplay();
    }
*/
    /**
     * Nombre de chiffre après la virgule.
     */
    private static int INDICE_DETAIL = 5;

    private void updateIndiceMoyen() {
        String result = String.valueOf(
                        Cryptanalyse.indiceMoyen(listHisto));

        if (result.length() > INDICE_DETAIL + 2) {
            result = result.substring(0, INDICE_DETAIL + 2);
        }
        indiceMoyen.setText(result);

    }

    @FXML
    private void onGuessKey() {
        key = Cryptanalyse.guessKey(listHisto, keySize);
        final int length = key.length();
        for (int i = 0; i < length; i++) {
            listShift.set(i, key.charAt(i) - 'A');
        }
        updateKeyText();
        updateHistoDisplay();
        updatePrevText();
    }

    public void onNextHisto() {
        if (currentHisto < keySize - 1) {
            currentHisto++;
        }
        updateHistoDisplay();
    }

    public void onPrevHisto() {
        if (currentHisto > 0) {
            currentHisto--;
        }
        updateHistoDisplay();
    }

    /**
     * augmenter le shift courant de 1.
     */
    public final void moreShift() {
        final int value = (listShift.get(currentHisto) + 1)
                % Constantes.ALPHABET_SIZE;
        listShift.set(currentHisto, value);
        onShiftUpdated();
    }

    /**
     * diminuer le shift courant de 1.
     */
    public final void lessShift() {
        int value = (listShift.get(currentHisto) - 1)
                % Constantes.ALPHABET_SIZE;
        if (value < 0) {
            value += Constantes.ALPHABET_SIZE;
        }
        listShift.set(currentHisto, value);
        onShiftUpdated();
    }

    public final void onShiftUpdated() {
        updateKeyText();
        updateHistoDisplay();
        updatePrevText();
    }



    /**
     * met à jour le diagramme de l'inteface.
     */
    private void updateHistoDisplay() {
        prevHistoButton.setDisable(false);
        nextHistoButton.setDisable(false);
        if (currentHisto < 1) {
            prevHistoButton.setDisable(true);
        }

        if (currentHisto > keySize - 2) {
            nextHistoButton.setDisable(true);
        }

        comparNo.setText("" + (currentHisto + 1));

        serie.getData().clear();
        XYChart.Series newSerie = new XYChart.Series();
        final HistoFreq histo = listHisto.get(currentHisto);
        final int shift = listShift.get(currentHisto);

        //populating the series with data
        for (int i = 0; i < Constantes.ALPHABET_SIZE; i++) {
            final char ref = (char) ('A' + i);
            final char charac =
                    (char) ('A' + ((i + shift) % Constantes.ALPHABET_SIZE));
            newSerie.getData().add(new XYChart.Data(
                    Character.toString(ref),
                    histo.getFreq(charac) * 100));
        }
        if (diagramme.getData().size() > 1) {
            diagramme.getData().set(1,newSerie);
        }
    }


    private void updatePrevText() {
        decodedText.setText(Vigenere.decode(encodedText.getText(), key, 100) + "...");
    }

    public void decode() {
        decodedText.setText(Vigenere.decode(encodedText.getText(), key, 0));
    }

    public void pasteText() {
        encodedText.setText(KeyHandler.filterText(getClipboardContents()));
    }

    private String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch (UnsupportedFlavorException | IOException ex){
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return result;
    }



    @FXML
    private void exit() {
        TXCrypto.gotoAccueil();
    }
}
