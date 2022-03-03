//資管三B 陳柏澔 107403046

import java.util.ArrayList;
import java.awt.Image;
 
public class undoManager {
    private ArrayList List;
    private int undoIndex, redoIndex;
    private int maxUndo;
    
    public undoManager() {
        List = new ArrayList();
        undoIndex = -1;
        redoIndex = 0;
        maxUndo = 10;
    }
    
    public Image undoImage() {
        Image image = null;
        
        if(undoIndex >= 0) {
            image = (Image) List.get(undoIndex);
            undoIndex--;
            redoIndex--;
        }
        
        return image;
    }
    
    public Image redoImage() {
        Image image = null;
        
        if(redoIndex < List.size() -1) {
            redoIndex++;
            undoIndex++;
            image = (Image) List.get(redoIndex);
            
            if(redoIndex == List.size() -1) {
                List.remove(redoIndex);
            }
        }
        
        return image;
    }
    
    public void addImage(Image image) {
        undoIndex++;
        redoIndex++;
        List.add(undoIndex, image);
        
        if(List.size() > getMaxUndo()) {
            clearUndo(5);
        }
        
        int remove = List.size() - undoIndex - 1;
        for(int i = 1; i <= remove; i++) {            
            List.remove(undoIndex+1);
        }
    }
    
    public void clearUndo(int number) {
        for(int i = 0; i < number; i++) {
            List.remove(0);
        }
        undoIndex = undoIndex - number;
        redoIndex = redoIndex - number;
    }
    
    public int getUndoIndex() {
        return undoIndex;
    }
    public int getRedoIndex() {
        return redoIndex;
    }
    
    public int getMaxUndo() {
        return maxUndo;
    }
    public void setMaxUndo(int maxUndo) {
        this.maxUndo = maxUndo;
    }
    
    public boolean isUndoable() {
        return !(getUndoIndex() == -1);
    }
    
    public boolean isFirstUndo() {
        return (List.size() == getRedoIndex());
    }
    
    public boolean isRedoable() {
        return !isFirstUndo();
    }
}