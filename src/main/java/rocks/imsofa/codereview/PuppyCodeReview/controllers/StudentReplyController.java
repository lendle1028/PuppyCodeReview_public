/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import rocks.imsofa.codereview.PuppyCodeReview.daos.QuizDao;
import rocks.imsofa.codereview.PuppyCodeReview.entities.Quiz;
import rocks.imsofa.codereview.PuppyCodeReview.entities.StudentReply;
import rocks.imsofa.codereview.PuppyCodeReview.services.StudentReplyService;

/**
 *
 * @author lendle
 */
@Controller
public class StudentReplyController {
    @Autowired
    private StudentReplyService studentReplyService=null;
    @Autowired
    private QuizDao quizDao=null;
    
    @GetMapping("/studentReply/list")
    public String listByQuizIdAction(@RequestParam("quizId") int quizId, Model model){
        model.addAttribute("quizId", quizId);
        return "studentReply/list";
    }
    
    @GetMapping("/studentReply/add/quizId/{quizId}")
    public String addAction(@PathVariable("quizId") int quizId, Model model){
        model.addAttribute("quizId", quizId);
        return "studentReply/add";
    }
    
    @GetMapping("/studentReply/addMultiple/quizId/{quizId}")
    public String multipleUploadAction(@PathVariable("quizId") int quizId, Model model){
        model.addAttribute("quizId", quizId);
        return "studentReply/addMultiple";
    }
    
    @PostMapping("/studentReply/addMultiple")
    public String multipleUploadAction(@RequestParam("file") MultipartFile file, 
            @RequestParam("quizId") int quizId,
            @RequestParam("filePattern") String filePattern, Model model) throws IOException{
        Date date=new Date();
        File tempZipFile=File.createTempFile("temp", ".zip");
        file.transferTo(tempZipFile);
        ZipFile zipFile = new ZipFile(tempZipFile);
        File defaultTempFolder=tempZipFile.getParentFile();
        File unzipFolder=new File(defaultTempFolder, ""+System.currentTimeMillis());
        zipFile.extractAll(unzipFolder.getAbsolutePath());
        
        Quiz quiz=quizDao.findById((long)quizId).get();
        File root=unzipFolder.listFiles()[0];
        for(File studentFolder : root.listFiles()){
            String folderName=studentFolder.getName();
            if(folderName.equals("teacher")){
                continue;
            }
//            System.out.println("check "+folderName);
            String studentId=folderName.substring(0, folderName.indexOf("_"));
            StudentReply studentReply=new StudentReply();
            studentReply.setAuthor(studentId);
            studentReply.setCreatedDate(date);
            studentReply.setLastModified(date);
            studentReply.setPeerReviewed(false);
            studentReply.setQuiz(quiz);
            fillStudentReply(studentFolder, studentReply, filePattern);
            studentReplyService.add(studentReply);
        }
        
        FileUtils.delete(tempZipFile);
        FileUtils.deleteDirectory(unzipFolder);
        model.addAttribute("quizId", quizId);
        return "redirect:/studentReply/list?quizId="+quizId;
    }
    
    private void fillStudentReply(File studentAssignmentRoot, StudentReply studentReply, String fileNamePattern) throws IOException{
        for(File file : studentAssignmentRoot.listFiles()){
            if(file.isDirectory()){
                this.fillStudentReply(file, studentReply, fileNamePattern);
            }else{
                if(Pattern.matches(fileNamePattern, file.getName())){
                    studentReply.setCode(FileUtils.readFileToString(file, "utf-8"));
                    return;
                }
            }
        }
    }
}
