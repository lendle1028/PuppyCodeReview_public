/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview;

import java.io.File;

import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import rocks.imsofa.ai.puppychatter.cache.FileSystemCacheService;
import rocks.imsofa.ai.puppychatter.openrouter.OpenrouterPuppyChatter;
import rocks.imsofa.codereview.AbstractPuppyCodeReviewer;

/**
 *
 * @author USER
 */
@Component
public class CacheablePuppyCodeReviewer extends AbstractPuppyCodeReviewer{
    private String apiKey=null;
    @Autowired
    private Environment env;
    @Override
    protected OpenrouterPuppyChatter getPuppyChatterInstance() {
        File rootCacheFolder=new File(".cache");
        if(!rootCacheFolder.exists()){
            rootCacheFolder.mkdirs();
        }
        apiKey=env.getProperty("openrouter.api-key");
        System.out.println("apiKey="+apiKey);
        OpenrouterPuppyChatter puppyChatter = new OpenrouterPuppyChatter(
                apiKey, new FileSystemCacheService(rootCacheFolder));
        return puppyChatter;
    }
    
}
