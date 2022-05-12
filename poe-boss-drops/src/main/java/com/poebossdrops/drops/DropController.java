package com.poebossdrops.drops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/drops")
public class DropController {

    @GetMapping("/{bossName}")
    public String helloWorld(@PathVariable String bossName){
        return "Hello World" + bossName;
    }
}
