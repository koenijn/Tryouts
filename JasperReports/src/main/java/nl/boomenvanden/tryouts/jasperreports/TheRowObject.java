package nl.boomenvanden.tryouts.jasperreports;

/**
 * Hello world!
 *
 */
public class TheRowObject {
    private TheGroupObject parent;
    private String myText;

    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
    }

    public TheGroupObject getParent() {
        return parent;
    }

    public void setParent(TheGroupObject parent) {
        this.parent = parent;
    }
    
}
