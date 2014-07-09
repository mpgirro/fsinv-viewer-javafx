
package fsinv.viewer.javafx;

/**
 *
 * @author Maximilian Irro
 */
public interface FileStructureEntity {
    
    void fromDatabase();
    
    void fromJSON();
    
    void fromXML();
    
    void fromYAML();

}
