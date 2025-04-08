/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rocks.imsofa.codereview.PuppyCodeReview.apis;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@RestController
public class KeywordsAPIController {
    @GetMapping("/api/keywords")
    public List<String> getKeywords(){
        return List.of(
            "結構清晰",
            "設計合理",
            "設計不足",
            "模組劃分佳",
            "功能完整",
            "功能缺失",
            "操作流暢",
            "操作需改進",
            "邏輯清晰",
            "邏輯混亂",
            "代碼簡潔",
            "代碼冗長",
            "命名清楚",
            "命名需優化",
            "註解不足"
        );
    }
}
