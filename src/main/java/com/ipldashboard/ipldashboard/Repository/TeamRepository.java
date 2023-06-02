package com.ipldashboard.ipldashboard.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ipldashboard.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{
    Team findByTeamName(String teamName);
    
}
