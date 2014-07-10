/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fsinv.viewer.javafx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("YAML files (*.yal|*.yml)", "*.yaml", "*.yml"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQLite3 DB files (*.db|*.sqlite3)", "*.db", "*.sqlite3"));
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            String filePath = file.getAbsolutePath();
            
            statusLabel.setText("Loading " + filePath);
            System.out.println("Loading " + filePath);

            String ext = "";
            int i = filePath.lastIndexOf('.');
            int p = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
            if (i > p) 
                ext = filePath.substring(i+1);
            
            FsInventory fsInv = null;
            switch(ext.toLowerCase()){
            
                case "json":
                    String jsonFileString = "";
                    try (Scanner scanner =  new Scanner(file.getAbsoluteFile())){
                      while (scanner.hasNextLine()){
                        jsonFileString += scanner.nextLine();
                      }
                    } catch (FileNotFoundException ex){
                        Logger.getLogger(FsinvViewerGUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fsInv = FsInventory.fromJSON(jsonFileString);
                    break;
                case "yml":
                case "yaml":
                    throw new UnsupportedOperationException("Not supported yet."); 
                    //break;
                case "db":
                case "sqlite3":
                    throw new UnsupportedOperationException("Not supported yet."); 
                    //break;
                case "xml":
                    throw new UnsupportedOperationException("Not supported yet.");    
                    //break;
                default:
                    System.out.println("File extension " + ext + " not valid");
            } 
        } else {
            System.out.println("Choosen file is not a file (file==null)");
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
