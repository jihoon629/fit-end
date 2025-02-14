package com.example.demo.Service;

import com.example.demo.Entity.ScoreRankFemale;
import com.example.demo.Repo.ScoreRankFemaleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoreRankFemaleService {

    private final ScoreRankFemaleRepository repository;

    public ScoreRankFemaleService(ScoreRankFemaleRepository repository) {
        this.repository = repository;
    }

    public List<ScoreRankFemale> getAll() {
        return repository.findAll();
    }

    public ScoreRankFemale save(ScoreRankFemale entity) {
        return repository.save(entity);
    }
}
