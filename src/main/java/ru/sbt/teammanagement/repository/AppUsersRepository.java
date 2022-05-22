package ru.sbt.teammanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.teammanagement.entities.AppUser;

import java.util.Optional;

@Repository
public interface AppUsersRepository extends JpaRepository<AppUser, Integer> {
    public Optional<AppUser> findAppUserByUsername(String username);

    public boolean existsAppUserByUsername(String username);
}
