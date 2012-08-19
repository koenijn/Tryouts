/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jasperreports;

/**
 *
 * @author mark
 */
public class TheGroupObject {
    private String groupText;
    private Long groupNumber;

    public String getGroupText() {
        return groupText;
    }

    public void setGroupText(String groupText) {
        this.groupText = groupText;
    }

    public Long getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Long groupNumber) {
        this.groupNumber = groupNumber;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TheGroupObject other = (TheGroupObject) obj;
        if ((this.groupText == null) ? (other.groupText != null) : !this.groupText.equals(other.groupText)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.groupText != null ? this.groupText.hashCode() : 0);
        return hash;
    }
    
    
}
