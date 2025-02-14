package com.example.demo.Controller;

import com.example.demo.Entity.ScoreRankMale;
import com.example.demo.Service.ScoreRankMaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scorerankmale")
public class ScoreRankMaleController {

    private final ScoreRankMaleService service;

    public ScoreRankMaleController(ScoreRankMaleService service) {
        this.service = service;
    }

    @GetMapping
    public List<ScoreRankMale> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ScoreRankMale save(@RequestBody ScoreRankMale entity) {
        return service.save(entity);
    }
}
