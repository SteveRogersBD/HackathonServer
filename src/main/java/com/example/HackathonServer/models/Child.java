package com.example.HackathonServer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="child")
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int age;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> games;
    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "parent_id", nullable = false)
    private User parent;
}
