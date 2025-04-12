/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lendle
 */
public class ReviewResultParser {
    public ReviewResults parse(String json){
        Gson gson=new Gson();
        int index=json.lastIndexOf("}");
        json=json.substring(0, index+1);
        System.out.println(json);
        Map raw=gson.fromJson(json, Map.class);
        ReviewResults ret=new ReviewResults();
        Map rawScores=(Map) raw.get("scores");
        Map rawIssues=(Map) raw.get("issues");
        ret.setCorrectnessScore((double) rawScores.get("correctness"));
        ret.setComplexityScore((double) rawScores.get("complexity"));
        ret.setDesignScore((double) rawScores.get("design"));
        ret.setFunctionalityScore((double) rawScores.get("functionality"));
        ret.setSmellScore((double) rawScores.get("smell"));
        ret.setCorrectnessExplain((String) raw.get("explain"));
        ret.setReviewOverview((String) rawIssues.get("overview"));
        ret.setSmellOverview((String) rawIssues.get("smell"));
        
        List<Map> designIssues=(List<Map>) rawIssues.get("design");
        List<Map> functionalityIssues=(List<Map>) rawIssues.get("functionality");
        List<Map> complexityIssues=(List<Map>) rawIssues.get("complexity");
        
        if(designIssues!=null){
            for(Map issueMap : designIssues){
                ret.getDesign().add(new Issue(IssueType.DESIGN, (String)issueMap.get("description"), 
                        ((Double)issueMap.get("startLine")).intValue(), ((Double)issueMap.get("endLine")).intValue()));
            }
        }
        
        if(functionalityIssues!=null){
            for(Map issueMap : functionalityIssues){
                ret.getFunctionality().add(new Issue(IssueType.FUNCTIONALITY, (String)issueMap.get("description"), 
                        ((Double)issueMap.get("startLine")).intValue(), ((Double)issueMap.get("endLine")).intValue()));
            }
        }
        
        if(complexityIssues!=null){
            for(Map issueMap : complexityIssues){
                ret.getComplexity().add(new Issue(IssueType.COMPLEXITY, (String)issueMap.get("description"), 
                        ((Double)issueMap.get("startLine")).intValue(), ((Double)issueMap.get("endLine")).intValue()));
            }
        }
        
        List<String> keywords=(List<String>) raw.get("keywords");
        ret.getKeywords().addAll(keywords);
        return ret;
    }
}
