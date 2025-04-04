/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview;

import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import rocks.imsofa.ai.puppychatter.PromptParameters;
import rocks.imsofa.ai.puppychatter.PuppyChatter;
import rocks.imsofa.ai.puppychatter.Response;
import rocks.imsofa.ai.puppychatter.openrouter.OpenrouterPuppyChatter;

/**
 *
 * @author USER
 */
public abstract class AbstractPuppyCodeReviewer implements PuppyCodeReviewer{

    @Override
    public ReviewResults review(String language, String objective, String studentCode, String answer) throws Exception {
        PuppyChatter puppyChatter=this.getPuppyChatterInstance();
        String sessionId = puppyChatter.createSession();
        String prompt = null;
        try (InputStream input = DefaultPuppyCodeReviewer.class.getClassLoader().getResourceAsStream("rocks/imsofa/codereview/prompt.txt")) {
            prompt = IOUtils.toString(input, "utf-8");
        }

        prompt = prompt.replace("${language}", language).replace("${objective}", objective).replace("${studentCode}", studentCode).replace("${answer}", answer);
        Response response = puppyChatter.bark(sessionId, "model:qwen/qwen-2.5-coder-32b-instruct " + prompt, new PromptParameters("user"));
        puppyChatter.closeSession(sessionId);
        List<String> blocks = response.getMessageOfBlockType("json");
        String json = blocks.isEmpty() ? response.getMessage() : blocks.get(0);
        ReviewResultParser parser = new ReviewResultParser();
        ReviewResults results = parser.parse(json);
        return results;
    }
    
    protected abstract OpenrouterPuppyChatter getPuppyChatterInstance();
    
}
