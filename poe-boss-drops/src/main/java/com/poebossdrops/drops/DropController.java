package com.poebossdrops.drops;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drops")
@RequiredArgsConstructor
public class DropController {

    private final DropService dropService;

    @GetMapping("/{bossName}")
    public String helloWorld(@PathVariable String bossName){
        return "Hello World " + bossName;
    }
}
