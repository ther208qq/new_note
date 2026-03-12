package com.javaweb.springboot_web.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Note {

    private Integer id;

    private String content;

//    private LocalDateTime updatetime;

}
