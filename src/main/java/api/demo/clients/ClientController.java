package api.demo.clients;

import api.demo.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController extends BaseCrudController<Client, ClientsRepository> {
    @Autowired
    public ClientController(ClientsRepository repository) {
        super(repository);
    }

    @GetMapping("/phone")
    public Client getClientByPhoneNumber(@RequestParam String phoneNumber){
        return repository.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email")
    public Client getClientByEmail(@RequestParam String email){
        return repository.findByEmail(email);
    }


}
