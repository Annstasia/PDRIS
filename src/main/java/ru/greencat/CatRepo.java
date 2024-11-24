package ru.greencat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepo extends JpaRepository<CatCounter, Long> {
}
