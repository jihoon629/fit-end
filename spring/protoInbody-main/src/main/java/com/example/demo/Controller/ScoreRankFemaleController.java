package com.example.demo.Controller;

import com.example.demo.Entity.ScoreRankFemale;
import com.example.demo.Service.ScoreRankFemaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scorerankfemale")
public class ScoreRankFemaleController {

    private final ScoreRankFemaleService service;

    public ScoreRankFemaleController(ScoreRankFemaleService service) {
        this.service = service;
    }

    @GetMapping
    public List<ScoreRankFemale> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ScoreRankFemale save(@RequestBody ScoreRankFemale entity) {
        return service.save(entity);
    }
}
