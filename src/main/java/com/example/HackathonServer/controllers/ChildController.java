package com.example.HackathonServer.controllers;

import com.example.HackathonServer.models.Child;
import com.example.HackathonServer.models.Game;
import com.example.HackathonServer.reponses.ApiResponse;
import com.example.HackathonServer.repositories.ChildRepo;
import com.example.HackathonServer.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    ChildService childService;

    @PostMapping
    public ApiResponse<Child> addChild(@RequestBody Child child)
    {
        Child savedChild = childService.saveChild(child);
        return ApiResponse.onSuccess("Saved Successfully", savedChild);
    }

    @PutMapping("/id/{id}")
    public ApiResponse<Child> updateChild(@RequestBody Child child,
                                          @PathVariable Long id)
    {
        Child savedChild = childService.updateChild(id);
        return ApiResponse.onSuccess("Updated Successfully", savedChild);
    }

    @DeleteMapping("/id/{id}")
    public ApiResponse<Child> deleteChild(@RequestBody Child child,
                                          @PathVariable Long id)
    {
        Child deletedChild = childService.deleteChild(id);
        return ApiResponse.onSuccess("Deleted Successfully", deletedChild);
    }



    @GetMapping("/score/intellectual/{childId}")
    public ApiResponse<Integer> getIntellectualScore(@PathVariable Long childId)
    {
        int score = childService.totalIntellectualScore(childId);
        return ApiResponse.onSuccess("Score Retrieveed Succefully!!!",score);
    }

    @GetMapping("/score/physical/{childId}")
    public ApiResponse<Integer> getPhysicalScore(@PathVariable Long childId)
    {
        int score = childService.totalPhysicalScore(childId);
        return ApiResponse.onSuccess("Score Retrieveed Succefully!!!",score);
    }

    @GetMapping("/games/child/{childId}")
    public ApiResponse<Integer> numberOfGamesPlayed(@PathVariable Long childId)
    {
        int games = childService.totalNumberOfGamesPlayed(childId);
        return ApiResponse.onSuccess("Score Retrieveed Succefully!!!",games);
    }


}
