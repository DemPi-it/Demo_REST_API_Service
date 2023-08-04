package api.demo.cinemahalls;

import api.demo.cinemas.Cinema;
import api.demo.tickets.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cinema_halls")
public class CinemaHall {
    @Id
    @Column(name="cinema_hall_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hallId;
    @Column(name="cinema_hall_name")
    private String hallName;
    @Column(name="capacity")
    private Integer capacity;
    @Column(name="status")
    private String status;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id", nullable=false)
    private Cinema cinemas;
    @JsonIgnore
    @OneToMany(mappedBy = "cinema_halls")
    private List<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CinemaHall that = (CinemaHall) o;
        return hallId != null && Objects.equals(hallId, that.hallId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public CinemaHall(String hallName, Integer capacity, String status, Cinema cinemas) {
        this.hallName = hallName;
        this.capacity = capacity;
        this.status = status;
        this.cinemas = cinemas;
    }
}
