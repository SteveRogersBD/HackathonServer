package com.example.HackathonServer.repositories;

import com.example.HackathonServer.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepo extends JpaRepository<Child, Long> {


}
