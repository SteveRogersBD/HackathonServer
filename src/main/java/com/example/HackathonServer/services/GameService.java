package com.example.HackathonServer.services;

import com.example.HackathonServer.exceptions.GameNotFoundException;
import com.example.HackathonServer.models.Game;
import com.example.HackathonServer.repositories.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepo gameRepo;


    public List<Game> getGames()
    {
        return gameRepo.findAll();
    }

    public Game getById(Long id)
    {
        Game game = gameRepo.findById(id).get();
        if(game == null)
            throw new GameNotFoundException("Game not found with id " + id);
        return game;
    }

    public Game updateGame(Long id, Game game)
    {
        Game oldGame = getById(id);
        oldGame.setId(game.getId());
        oldGame.setEndTime(game.getEndTime());
        oldGame.setStartTime(game.getStartTime());
        oldGame.setDuration(game.getDuration());
        oldGame.setScore(game.getScore());
        Game newGame = gameRepo.save(oldGame);
        return newGame;
    }

    public Game deleteGame(Long id)
    {
        Game oldGame = getById(id);
        gameRepo.delete(oldGame);
        return oldGame;
    }

    public Game saveGame(Game game)
    {
        return gameRepo.save(game);
    }
}
