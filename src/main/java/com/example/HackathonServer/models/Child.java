package com.example.HackathonServer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int age;
    private String description;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Game> games;
}
