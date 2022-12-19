package com.example.solva.controllers;


import com.example.solva.service.LimitServiceImpl;
import com.example.solva.web.limit.SaveLimitDTO;
import com.example.solva.web.limit.LimitDTO;
import com.example.solva.web.limit.UpdateLimitDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/limits")
@AllArgsConstructor
public class LimitController {

    private final LimitServiceImpl limitService;

    @GetMapping(value = "/{account}")
    public List<LimitDTO> getAccountLimits(@PathVariable String account) {
        return limitService.getAll(account);
    }

    @PostMapping
    public LimitDTO create(@RequestBody SaveLimitDTO initLimitDTO) {
        return limitService.create(initLimitDTO);
    }

    @PutMapping
    public LimitDTO update(@RequestBody UpdateLimitDTO updateLimitDTO) {
        return limitService.update(updateLimitDTO);
    }
}
