package com.javaweb.springboot_web.controller;


import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//该层负责响应请求

@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
public class NoteController {

    @Autowired //自动注入
    private NoteService noteService;

    //展示所有笔记
    @GetMapping
    public List<Note> showNote(){

        List<Note> noteList = noteService.list();

        System.out.println("输出结果为:"+noteList);

        return noteList;
    }

    //插入笔记
    @PostMapping
    public String insert(@RequestBody Note note){

        noteService.insert(note);
        return "success";
    }

    //根据id删除单个笔记
    @DeleteMapping("/{id}")
    public String deletebyid(@PathVariable Integer id){

        boolean res = noteService.deleteById(id);

        return "success delete";
    }

    //根据id更新笔记
    @PutMapping
    public String updatebyid(@RequestBody Note note){

        noteService.updateById(note);

        return "success update";
    }

    //删除所有笔记
    @DeleteMapping
    public String clear(){
        noteService.clear();
        return "success clear";
    }

    //summary
    @PostMapping("/summary")
    public String summary(@RequestBody  String content){
        String id = noteService.summary(content);
        return id;
    }

    //check
    @GetMapping("/check/{id}")
    public String check(@PathVariable String id){
        String res = noteService.check(id);

        if(res != "processing"){
            return res;
        }
        return "processing";
    }

}
