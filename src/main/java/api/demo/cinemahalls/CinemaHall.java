package api.demo.cinemahalls;

import api.demo.cinemas.Cinema;
import api.demo.tickets.Ticket;
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
    private Integer id;
    @Column(name="cinema_hall_name")
    private String hallName;
    @Column(name="capacity")
    private Integer capacity;
    @Column(name="status")
    private String status;
    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Cinema cinemas;
    @OneToMany(mappedBy = "cinema_halls")
    private List<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CinemaHall that = (CinemaHall) o;
        return id != null && Objects.equals(id, that.id);
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
