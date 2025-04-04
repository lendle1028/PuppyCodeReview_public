/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USER
 */
@Controller
public class QuizController {
    @GetMapping("/quiz/list")
    public String listAction(){
        return "quiz/list";
    }
    
    @GetMapping("/quiz/edit")
    public String editAction(@RequestParam("id") int id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("mode", "edit");
        return "quiz/edit";
    }
    
    @GetMapping("/quiz/add")
    public String addAction(Model model){
        model.addAttribute("mode", "add");
        return "quiz/edit";
    }
}
