package com.ipldashboard.ipldashboard.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipldashboard.ipldashboard.Repository.MatchRepository;
import com.ipldashboard.ipldashboard.Repository.TeamRepository;
import com.ipldashboard.ipldashboard.model.Match;
import com.ipldashboard.ipldashboard.model.Team;
@RestController
@CrossOrigin
public class TeamController {
    private TeamRepository teamRepo;
    private MatchRepository matchRepository;
    public TeamController(TeamRepository teamRepo, MatchRepository matchRepo) {
        this.teamRepo = teamRepo;
        this.matchRepository = matchRepo;
    } 
    @GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepo.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepo.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName,4));
        return team;
    }
    @GetMapping("/team/{teamName}/match")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year,1, 1);
        LocalDate endDate = LocalDate.of(year+1,1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
            teamName,
            startDate,
            endDate
        );
    }
}
