package com.javaweb.springboot_web.controller;


import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.service.NoteService;
import com.javaweb.springboot_web.service.NoteServiceImp;
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
    @RequestMapping("/showNote")
    public List<Note> showNote(){

//        NoteService noteservice = new NoteServiceImp();

        List<Note> noteList = noteService.findAll();

        return noteList;
    }

    //插入笔记
    @RequestMapping("/insert")
    public String insert(@RequestBody Note note){

        System.out.println("正在调用");
        noteService.insert(note);
        return "sucess";
    }

    //根据id删除单个笔记
    @RequestMapping("/deletebyid")
    public String deletebyid(@RequestParam Integer id){

        Integer lines = noteService.deleteById(id);

        System.out.println("删除成功,已删除:"+lines+"行");

        return "sucess delete";
    }

    //根据id更新笔记
    @RequestMapping("/updatebyid")
    public String updatebyid(@RequestBody Note note){

        Integer lines = noteService.updateById(note);

        return "sucess update";

    }

    //删除所有笔记
    @RequestMapping("/clear")
    public String clear(){
        noteService.clear();

        return "sucess clear";
    }


}
