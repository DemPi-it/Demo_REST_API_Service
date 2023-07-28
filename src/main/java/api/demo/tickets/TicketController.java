package api.demo.tickets;

import api.demo.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets")
public class TicketController extends BaseCrudController<Ticket, TicketRepository> {
    @Autowired
    public TicketController(TicketRepository repository) {
        super(repository);
    }
}
