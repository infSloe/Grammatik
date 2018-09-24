
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;

// Bibliothek RiTa
import rita.*;

public class Controller {

    @FXML 
    private TextField txtA; 

    @FXML 
    private TextField txtB; 
    
    @FXML
    private TextArea txtRegeln; 

    @FXML
    private TextField txtStart;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextArea txtBeispiele;

    // Grammatik
    private RiGrammar rg;

    /*
     * Löscht alle Beispiele
     */
    @FXML
    void beispieleLoeschen(ActionEvent event) {
        txtBeispiele.clear();

    }

    /*
     * Löscht alle Produktionsregeln
     */
    @FXML
    void grammatikLoeschen(ActionEvent event) {
        rg.reset();
        txtRegeln.setText(rg.getGrammar());

    }

    /*
     * Erzeugt ein Wort der beschriebenen Sprache
     */
    @FXML
    void beispielErzeugen(ActionEvent event) {
        String beispiel = rg.expandFrom(txtStart.getText());       
        txtBeispiele.appendText(beispiel + "\n");
    }

    /*
     * Fügt eine neue Produktionsregel der Grammatik hinzu
     */
    @FXML
    void regelEinfuegen(ActionEvent event) {
        String var = txtA.getText();
        String zu = txtB.getText();
        rg.addRule(var,zu);
        txtRegeln.setText(rg.getGrammar());
        txtA.clear();
        txtB.clear();
    }

    @FXML 
    void initialize() {
        // Grammatik vorbereiten
        rg = new RiGrammar();
        
        //unfocus pathField
        Platform.runLater( () -> pane.requestFocus() );   // sorgt dafür, dass am Anfang kein Feld ausgewählt ist

        // Auswahl der Grammatik
        grammatikLaden();
     
        // Gibt die Produktionsregeln im Feld txtRegeln aus.
        txtRegeln.setText(rg.getGrammar());
    }

    
    /*
     * Hier stehen die Regeln der Sprache!
     */
    public void grammatikLaden()
    {
        txtStart.setText("<satz>");   // Legt die Startvariable fest
        // Produktionsregeln
        rg.addRule("<satz>" , "<S> <P> <O>");
        rg.addRule("<S>","i | you | we");
        rg.addRule("<P>","like | hate");
        rg.addRule("<O>","bananas");
    }

    
}
