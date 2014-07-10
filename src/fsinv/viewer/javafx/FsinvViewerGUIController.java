/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fsinv.viewer.javafx;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maximilian Irro
 */
public class FsinvViewerGUIController implements Initializable {
    
    @FXML private Label statusLabel;
    @FXML private Button loadButton;
    @FXML private ToolBar toolBar;
    @FXML private TreeView filestructureTreeView;
    @FXML private ProgressBar progressBar;
    
    @FXML
    private void loadButtonAction(ActionEvent event) {
        System.out.println("Load Button clicked");
        statusLabel.setText("Choose an inventory file");
        
        Stage stage = (Stage) loadButton.getScene().getWindow();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open inventory File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            System.out.println("File choosen: " + file.getAbsolutePath());
        }
        
        //label.setText("Hello World!");
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        statusLabel.setText("Ready");
    }    
    
}
