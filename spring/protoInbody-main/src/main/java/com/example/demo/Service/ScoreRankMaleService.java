package com.example.demo.Service;

import com.example.demo.Entity.ScoreRankMale;
import com.example.demo.Repo.ScoreRankMaleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoreRankMaleService {

    private final ScoreRankMaleRepository repository;

    public ScoreRankMaleService(ScoreRankMaleRepository repository) {
        this.repository = repository;
    }

    public List<ScoreRankMale> getAll() {
        return repository.findAll();
    }

    public ScoreRankMale save(ScoreRankMale entity) {
        return repository.save(entity);
    }
}
