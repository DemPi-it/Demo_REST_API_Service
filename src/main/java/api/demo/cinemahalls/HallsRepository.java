package api.demo.cinemahalls;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HallsRepository extends JpaRepository<CinemaHall, Integer> {
}
