/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package rocks.imsofa.codereview;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import rocks.imsofa.ai.puppychatter.BarkException;
import rocks.imsofa.ai.puppychatter.PromptParameters;
import rocks.imsofa.ai.puppychatter.PuppyChatter;
import rocks.imsofa.ai.puppychatter.Response;
import rocks.imsofa.ai.puppychatter.gemini.GeminiAqaPromptParameters;
import rocks.imsofa.ai.puppychatter.gemini.GeminiAqaPuppyChatter;
import rocks.imsofa.ai.puppychatter.gemini.InlinePassages;
import rocks.imsofa.ai.puppychatter.openrouter.OpenrouterPuppyChatter;

/**
 *
 * @author lendle
 */
public class PuppyCodeReview2 {

    public static void main(String[] args) throws IOException {
        try {
            String openrouterApiKey = "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";
            DefaultPuppyCodeReviewer puppyCodeReviewer=new DefaultPuppyCodeReviewer(openrouterApiKey);
            
            OpenrouterPuppyChatter puppyChatter = new OpenrouterPuppyChatter(openrouterApiKey, null);
            String sessionId = puppyChatter.createSession();
            
            
//            String testingCode=FileUtils.readFileToString(new File("testcase/java/foreach/student"), "utf-8");
//            String answer=FileUtils.readFileToString(new File("testcase/java/foreach/answer"), "utf-8");
//            String objective=FileUtils.readFileToString(new File("testcase/java/foreach/objective"), "utf-8");
           
            String testingCode=FileUtils.readFileToString(new File("testcase/python/zip/student"), "utf-8");
            String answer=FileUtils.readFileToString(new File("testcase/python/zip/answer"), "utf-8");
            String objective=FileUtils.readFileToString(new File("testcase/python/zip/objective"), "utf-8");
            
            ReviewResults reviewResults=puppyCodeReviewer.review("java", objective, testingCode, answer);
            
            System.out.println(reviewResults);
        } catch (Exception ex) {
            Logger.getLogger(PuppyCodeReview2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
