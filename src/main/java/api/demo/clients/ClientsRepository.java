package api.demo.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
    public List<Client> findByPhoneNumber(String phoneNumber);
    public List<Client> findByEmail(String email);
}
