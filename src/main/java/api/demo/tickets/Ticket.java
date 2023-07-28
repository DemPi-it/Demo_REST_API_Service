package api.demo.tickets;

import api.demo.cinemahalls.CinemaHall;
import api.demo.clients.Client;
import api.demo.employee.Employee;
import api.demo.films.Film;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    @Column(name="ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ticketId;
    @Column(name="show_date")
    private String showDate;
    @Column(name="show_time")
    private String showTime;
    @Column(name="seat")
    private String seat;
    @ManyToOne(targetEntity = CinemaHall.class)
    @JoinColumn(name="cinema_hall_id", nullable=false)
    private CinemaHall cinema_halls;
    @ManyToOne()
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employees;
    @ManyToOne()
    @JoinColumn(name="client_id", nullable=false)
    private Client clients;
    @ManyToOne
    @JoinColumn(name="film_id", nullable=false)
    private Film films;
    public Ticket(String showDate, String showTime, String seat) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return ticketId != null && Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
