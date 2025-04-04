/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview;

import java.io.File;
import rocks.imsofa.ai.puppychatter.cache.FileSystemCacheService;
import rocks.imsofa.ai.puppychatter.openrouter.OpenrouterPuppyChatter;
import rocks.imsofa.codereview.AbstractPuppyCodeReviewer;

/**
 *
 * @author USER
 */
public class CacheablePuppyCodeReviewer extends AbstractPuppyCodeReviewer{

    @Override
    protected OpenrouterPuppyChatter getPuppyChatterInstance() {
        File rootCacheFolder=new File(".cache");
        if(!rootCacheFolder.exists()){
            rootCacheFolder.mkdirs();
        }
        OpenrouterPuppyChatter puppyChatter = new OpenrouterPuppyChatter(
                "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", new FileSystemCacheService(rootCacheFolder));
        return puppyChatter;
    }
    
}
