package api.demo.clients;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@EqualsAndHashCode
public class ClientEntity {
    static AtomicInteger nextId = new AtomicInteger();
    @EqualsAndHashCode.Exclude
    private Integer id;
    private Integer ticketId;
    private String visitDate;
    private String phoneNumber;
    private String email;

    public ClientEntity(Integer ticketId, String visitDate, String phoneNumber, String email) {
        id = nextId.incrementAndGet();
        this.ticketId = ticketId;
        this.visitDate = visitDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public void switchInfo(ClientEntity client){
        setTicketId(client.getTicketId());
        setVisitDate(client.getVisitDate());
        setPhoneNumber(client.getPhoneNumber());
        setEmail(client.getEmail());
    }
}
