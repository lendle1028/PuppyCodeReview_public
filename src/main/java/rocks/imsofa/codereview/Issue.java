/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview;

/**
 *
 * @author lendle
 */
public class Issue {
    private IssueType type=null;
    private String description=null;
    private int startLine=-1, endLine=-1;

    public Issue(IssueType type, String description, int startLine, int endLine) {
        this.type=type;
        this.description=description;
        this.startLine=startLine;
        this.endLine=endLine;
    }

    public Issue() {
    }
    
    
    
    
    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }
    
    

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
