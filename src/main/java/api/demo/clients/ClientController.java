package api.demo.clients;

import api.demo.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("clients")
public class ClientController extends BaseCrudController<Client, ClientsRepository> {
    @Autowired
    public ClientController(ClientsRepository repository) {
        super(repository);
    }

    @GetMapping("/phone/{phone}")
    public List<Client> getClientByPhoneNumber(@PathVariable String phoneNumber){
        return repository.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email/{email}")
    public List<Client> getClientByEmail(@PathVariable String email){
        return repository.findByEmail(email);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClientInfo(@PathVariable Integer id, @RequestBody Client client){
        var oldClient = repository.findById(id);
        if(oldClient.isEmpty()){ return badRequest().body("No entity with this id "+id); }
        Client oldClientInfo = oldClient.get();
        oldClientInfo.setId(id);
        oldClientInfo.setEmail(client.getEmail());
        oldClientInfo.setPhoneNumber(client.getPhoneNumber());
        repository.save(oldClientInfo);
        return ok(oldClient);
    }
}
