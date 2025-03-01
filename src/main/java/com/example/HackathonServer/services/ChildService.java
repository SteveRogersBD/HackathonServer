package com.example.HackathonServer.services;

import com.example.HackathonServer.exceptions.ChildNotFoundException;
import com.example.HackathonServer.models.Child;
import com.example.HackathonServer.models.Game;
import com.example.HackathonServer.repositories.ChildRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {
    @Autowired
    private ChildRepo childRepo;


    public Child saveChild(Child child)
    {
        return childRepo.save(child);
    }

    public Child deleteChild(Long id)
    {
        Child child = getById(id);
        childRepo.delete(child);
        return child;
    }

    public Child updateChild(Long id)
    {
        Child child = getById(id);
        childRepo.delete(child);
        return child;
    }



    public List<Child> findAll()
    {
        return childRepo.findAll();
    }
    public Child getById(Long id)
    {
        Child child = childRepo.findById(id).orElseThrow(
                ()->new ChildNotFoundException("Child not found with id "+id));
        return child;
    }

    public int totalNumberOfGamesPlayed(Long childId)
    {
        Child child = getById(childId);
        return child.getGames().size();
    }

    public int totalIntellectualScore(Long childId){
        Child child = getById(childId);
        List<Game> games = child.getGames();
        int score = 0;
        for(Game game : games)
        {
            if(game.getGameType()==Game.Type.INTELLECTUAL)
            {
                score+=game.getScore();
            }
        }
        return score;
    }

    public int totalPhysicalScore(Long childId){
        Child child = getById(childId);
        List<Game> games = child.getGames();
        int score = 0;
        for(Game game : games)
        {
            if(game.getGameType()==Game.Type.PHYSICAL)
            {
                score+=game.getScore();
            }
        }
        return score;
    }



}
