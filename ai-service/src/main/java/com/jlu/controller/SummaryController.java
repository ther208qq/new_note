package com.jlu.controller;


import com.jlu.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/summary")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @PostMapping
    public String summary(@RequestBody String content){

        String id = summaryService.summary(content);

        if(id == "请勿重复总结"){
            return "请勿重复总结";
        }

        //返回给前端，之后前端拿着这个数据轮询调用check方法
        return id;
    }

    @GetMapping("/check/{id}")
    public String check(@PathVariable String id){
        String res = summaryService.check(id);

        if(res != "processing"){
            return res;
        }
        return "processing";
    }
}
