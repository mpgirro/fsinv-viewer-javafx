/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fsinv.viewer.javafx;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Maximilian Irro
 */
class FsinvIconFactory {
    
    private final LookupTable mimeTab;
    
    public FsinvIconFactory(LookupTable mimeTab){
        this.mimeTab = mimeTab;
    }

    public Node getIconbyMime(BaseDescription fse) {
        if(fse instanceof DirectoryDescription)
            return new ImageView(new Image(getClass().getResourceAsStream("icons/folder_16.png")));
        else{
            
            long mimeId = ((FileDescription) fse).mimeId;
            String mimeType = mimeTab.getDescription(mimeId);

            switch( mimeType ){
                case "text/plain":
                case "text/x-nfo":
                    return new ImageView(new Image(getClass().getResourceAsStream("icons/document_16.png")));
                case "video/x-matroska":
                case "application/mp4, audio/mp4, video/mp4, video/vnd.objectvideo":
                case "video/x-msvideo":
                case "video/x-m4v, video/vnd.objectvideo":
                    return new ImageView(new Image(getClass().getResourceAsStream("icons/video_16.png")));
                case "audio/mpeg":
                    return new ImageView(new Image(getClass().getResourceAsStream("icons/saudio_16.png")));
                case "image/jpeg":
                case "image/png":
                    return new ImageView(new Image(getClass().getResourceAsStream("icons/image_16.png")));
                default:
                    return new ImageView(new Image(getClass().getResourceAsStream("icons/file_16.png")));
            }
        
        
        }
    }

}
