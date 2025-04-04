/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import rocks.imsofa.ai.puppychatter.BarkException;
import rocks.imsofa.ai.puppychatter.PromptParameters;
import rocks.imsofa.ai.puppychatter.PuppyChatter;
import rocks.imsofa.ai.puppychatter.Response;
import rocks.imsofa.ai.puppychatter.openrouter.OpenrouterPuppyChatter;

/**
 *
 * @author lendle
 */
public class DefaultPuppyCodeReviewer extends AbstractPuppyCodeReviewer{

    private String openrouterApiKey = null;

    public DefaultPuppyCodeReviewer(String openrouterApiKey) {
        this.openrouterApiKey = openrouterApiKey;
    }

    @Override
    protected OpenrouterPuppyChatter getPuppyChatterInstance() {
        OpenrouterPuppyChatter puppyChatter = new OpenrouterPuppyChatter(openrouterApiKey, null);
        return puppyChatter;
    }
}
