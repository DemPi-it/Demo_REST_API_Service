package api.demo.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
    public Client findByPhoneNumber(String phoneNumber);
    public Client findByEmail(String email);
}
