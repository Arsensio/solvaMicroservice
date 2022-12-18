package com.example.solva.controllers;


import com.example.solva.service.LimitService;
import com.example.solva.service.LimitServiceImpl;
import com.example.solva.web.limit.InitLimitDTO;
import com.example.solva.web.limit.LimitDTO;
import com.example.solva.web.limit.UpdateLimitDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/limits")
@AllArgsConstructor
public class LimitController {

    private final LimitServiceImpl limitService;

    @GetMapping
    public LimitDTO get(@RequestParam("account") Long account,@RequestParam("category") String category){
        return limitService.findOne(account,category);
    }
    @PostMapping
    public LimitDTO create(@RequestBody InitLimitDTO initLimitDTO) {
        return limitService.create(initLimitDTO);
    }

    @PutMapping
    public LimitDTO update(@RequestBody UpdateLimitDTO updateLimitDTO){
        return limitService.update(updateLimitDTO);
    }
}
