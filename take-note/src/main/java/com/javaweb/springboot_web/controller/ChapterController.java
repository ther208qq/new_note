package com.javaweb.springboot_web.controller;


import com.javaweb.springboot_web.pojo.Chapter;
import com.javaweb.springboot_web.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
public class ChapterController {

    @Autowired //自动注入
    private ChapterService chapterService;

    //展示所有笔记
    @GetMapping()
    public List<Chapter> showNote(){

        List<Chapter> chapterList = chapterService.list();
        return chapterList;
    }

    //插入笔记
    @PostMapping
    public Chapter insert(@RequestBody Chapter chapter){

        Chapter res = chapterService.insert(chapter);
        return res;
    }

    //根据id删除单个笔记
    @DeleteMapping("/{id}")
    public String deletebyid(@PathVariable Long id){

        boolean res = chapterService.deleteById(id);

        return res? "success":"fasle";

    }
    //根据id更新笔记
    @PutMapping
    public String updatebyid(@RequestBody Chapter chapter){

        boolean res = chapterService.updateById(chapter);

        return res ? "success":"false";
    }

    //删除所有笔记
    @DeleteMapping("clear")
    public String clear(){
        chapterService.clear();

        return "success clear";
    }

}
