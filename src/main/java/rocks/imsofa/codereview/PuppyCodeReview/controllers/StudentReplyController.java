/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lendle
 */
@Controller
public class StudentReplyController {
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
}
