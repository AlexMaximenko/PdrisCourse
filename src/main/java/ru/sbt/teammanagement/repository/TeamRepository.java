package ru.sbt.teammanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.teammanagement.entities.Team;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    public Optional<Team> findTeamByName(String team_name);
    public Optional<Team> findTeamById(Integer team_id);

    public boolean existsTeamByName(String team_name);
}
