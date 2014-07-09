
package fsinv.viewer.javafx;

/**
 *
 * @author Maximilian Irro
 */
public interface FsEntity {
    
    void fromDatabase();
    
    void fromJSON();
    
    void fromXML();
    
    void fromYAML();

}
