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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Maximilian Irro
 */
public class FsinvViewerGUIController implements Initializable {
    
    @FXML private Label statusLabel;
    @FXML private Button loadButton;
    @FXML private ToolBar toolBar;
    @FXML private TreeTableView filestructureTreeView;
    @FXML private TreeTableColumn pathTableColumn;
    @FXML private TreeTableColumn sizeTableColumn;
    //@FXML private TreeTableColumn mtimeTableColumn;
    @FXML private ProgressBar progressBar;
    
    private static final long BYTES_IN_KB = (long) Math.pow(10,3);
    private static final long BYTES_IN_MB = (long) Math.pow(10,6);
    private static final long BYTES_IN_GB = (long) Math.pow(10,9);
    private static final long BYTES_IN_TB = (long) Math.pow(10,12);
    
    private FsinvIconFactory iconFactory;
    
    @FXML
    private void loadButtonAction(ActionEvent event) {
        System.out.println("Load button clicked");
        statusLabel.setText("Choose an inventory file");
        
        Stage stage = (Stage) loadButton.getScene().getWindow();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open inventory File");
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("YAML files (*.yal|*.yml)", "*.yaml", "*.yml"));
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQLite3 DB files (*.db|*.sqlite3)", "*.db", "*.sqlite3"));
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            String filePath = file.getAbsolutePath();
            
            statusLabel.setText("loading " + filePath);
            System.out.println("loading " + filePath);

            String ext = "";
            int i = filePath.lastIndexOf('.');
            int p = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
            if (i > p) 
                ext = filePath.substring(i+1);
            
            Inventory inventory = null;
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
                    inventory = Inventory.fromJSON(jsonFileString);
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
            
            if( inventory != null ){
                
                iconFactory = new FsinvIconFactory(inventory.mimeTab);
                
                TreeItem<BaseDescription> fsRoot = new TreeItem<>();
                List<TreeItem<BaseDescription>> rootChildList = new ArrayList<>();
                for( BaseDescription child : inventory.fileStructure )
                    rootChildList.add(createNode(child));
                fsRoot.getChildren().setAll(rootChildList);
                //builtFileSystemTree(fsRoot, fsInv.fileStructure);
                filestructureTreeView.setRoot(fsRoot);
                filestructureTreeView.setShowRoot(false);
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

        statusLabel.setText("Ready");

        pathTableColumn.setCellValueFactory(
                new Callback<TreeTableColumn.CellDataFeatures<BaseDescription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<BaseDescription, String> param) {
                BaseDescription fse = param.getValue().getValue();
                if(fse == null || param == null)
                    return new ReadOnlyObjectWrapper<String>("error");
                String name = fse.getName();
                return new ReadOnlyObjectWrapper<String>(name);
            }
        });
        
        sizeTableColumn.setCellValueFactory(
                new Callback<TreeTableColumn.CellDataFeatures<BaseDescription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<BaseDescription, String> param) {
                BaseDescription fse = param.getValue().getValue();
                if(fse == null || param == null)
                    return new ReadOnlyObjectWrapper<String>("error");
                String sizeString = prettyBytesString(fse.getSize());
                return new ReadOnlyObjectWrapper<String>(sizeString);
            }
        });
        /*
        mtimeTableColumn.setCellValueFactory(
                new Callback<TreeTableColumn.CellDataFeatures<FileStructureEntity, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<FileStructureEntity, String> param) {
                FileStructureEntity fse = param.getValue().getValue();
                if(fse == null || param == null)
                    return new ReadOnlyObjectWrapper<String>("error");
                Date mtime = fse.getModificationTime();
                SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
                String dateString = dt.format(mtime);
                return new ReadOnlyObjectWrapper<String>(dateString);
            }
        });
        */
    }    
    
    private void builtFileSystemTree(TreeItem parentItem, BaseDescription[] fsItems){
        for( BaseDescription fse : fsItems ){
            TreeItem<BaseDescription> treeItem = new TreeItem<BaseDescription>(fse);
            parentItem.getChildren().add(treeItem);
            if( fse instanceof DirectoryDescription )
                builtFileSystemTree(treeItem, ((DirectoryDescription)fse).fileList );
        }
    }
    
    private TreeItem<BaseDescription> createNode(final BaseDescription fse) {
        
        Node icon = iconFactory.getIconbyMime(fse);
        
        final TreeItem<BaseDescription> node = new TreeItem<BaseDescription>(fse,icon) {
            private boolean isLeaf;
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;
     
            @Override public ObservableList<TreeItem<BaseDescription>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }
     
            @Override public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    if( getValue() instanceof FileDescription )
                        isLeaf = true;
                    else
                        isLeaf = false;
                }
                return isLeaf;
            }
        };
        return node;
    }
    
    private ObservableList<TreeItem<BaseDescription>> buildChildren(TreeItem<BaseDescription> TreeItem) {
        BaseDescription fse = (BaseDescription) TreeItem.getValue();
        if (fse != null && fse instanceof DirectoryDescription) {
            BaseDescription[] fileList = ((DirectoryDescription)fse).fileList;
            if (fileList != null) {
                ObservableList<TreeItem<BaseDescription>> children = FXCollections.observableArrayList();
     
                //for (FileStructureEntity child : fileList) {
                for(int i = 0; i < fileList.length; i++){
                    BaseDescription child = fileList[i];
                    children.add(createNode(child));
                }
     
                return children;
            }
        }
     
        return FXCollections.emptyObservableList();
    }   
    
    private String prettyBytesString(long bytes){ 
        if( bytes > BYTES_IN_TB)
            return String.format("%.1f TB", ((float) bytes) / BYTES_IN_TB );
        if( bytes > BYTES_IN_GB)
            return String.format("%.1f GB", ((float) bytes) / BYTES_IN_GB );
        if( bytes > BYTES_IN_MB)
            return String.format("%.1f MB", ((float) bytes) / BYTES_IN_MB );
        if( bytes > BYTES_IN_KB)
            return String.format("%.1f KB", ((float) bytes) / BYTES_IN_KB );
        return "" + bytes + " B";
    }
    
}
