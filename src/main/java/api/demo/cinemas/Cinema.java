package api.demo.cinemas;

import api.demo.cinemahalls.CinemaHall;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="cinemas")
public class Cinema {
    @Id
    @Column(name="cinema_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="cinema_name")
    private String cinemaName;
    @Column(name="address")
    private String address;
    @Column(name="phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "cinemas")
    List<CinemaHall> halls;

    public Cinema(String cinemaName, String address, String phoneNumber) {
        this.cinemaName = cinemaName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cinema cinema = (Cinema) o;
        return id != null && Objects.equals(id, cinema.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
