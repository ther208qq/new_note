package com.javaweb.springboot_web.controller;


import com.javaweb.springboot_web.pojo.Note;
import com.javaweb.springboot_web.pojo.NoteBook;
import com.javaweb.springboot_web.service.NoteBookService;
import com.javaweb.springboot_web.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notebook")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
public class NoteBookController {

    @Autowired
    private NoteBookService noteBookService;

    //展示所有book
    @RequestMapping("showNoteBook")
    public List<NoteBook> showBook(){
        List<NoteBook> noteBookList = noteBookService.list();
        return noteBookList;
    }

    //插入book
    @RequestMapping("/insert")
    public String insert(@RequestBody NoteBook noteBook){

        System.out.println("正在调用");
        noteBookService.save(noteBook);
        return "success";
    }

    //根据id删除单个book
    @RequestMapping("/deletebyid")
    public String deletebyid(@RequestParam Integer id){

        noteBookService.removeById(id);

        return "success delete";
    }

//    //根据id更新
    @PutMapping("/updatebyid")
    public String updatebyid(@RequestBody NoteBook noteBook){

        noteBookService.updateById(noteBook);

        return "success update";

    }

    //删除所有book
    @RequestMapping("/clear")
    public String clear(){
        noteBookService.remove(null);
        return "success clear";
    }

}
