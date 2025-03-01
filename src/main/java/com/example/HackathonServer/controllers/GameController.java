package com.example.HackathonServer.controllers;

import com.example.HackathonServer.models.Game;
import com.example.HackathonServer.reponses.ApiResponse;
import com.example.HackathonServer.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/id/{id}")
    public ApiResponse<Game> getGameById(@PathVariable Long id)
    {
        Game game = gameService.getById(id);
        return ApiResponse.onSuccess("Retrieved Successfully", game);
    }

    @PostMapping()
    public ApiResponse<Game> createGame(@RequestBody Game game)
    {
        Game savedGame = gameService.saveGame(game);
       return ApiResponse.onSuccess("Game Created!!!", savedGame);
    }

    @PutMapping("/id/{id}")
    public ApiResponse<Game> updateGame(@PathVariable Long id,
                                        @RequestBody Game game)
    {
        Game savedGame = gameService.updateGame(id,game);
        return ApiResponse.onSuccess("Game updated!!!", savedGame);
    }

    @DeleteMapping("/id/{id}")
    public ApiResponse<Game> deleteGame(@PathVariable Long id)
    {
        Game deleted = gameService.deleteGame(id);
        return ApiResponse.onSuccess("Game deleted!!!", deleted);
    }


}
