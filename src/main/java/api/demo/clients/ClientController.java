package api.demo.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {
    List<ClientEntity> listOfClients = new ArrayList<>();

    @GetMapping("/all")
    public List<ClientEntity> getAllClients(){
        return listOfClients;
    }

    @GetMapping("/id")
    public List<ClientEntity> getClientById(@RequestParam Integer id){
        return listOfClients.stream().filter(x -> x.getId().equals(id)).toList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewClient(@RequestBody ClientEntity client){
        if(listOfClients.contains(client)){
            ClientEntity.nextId.decrementAndGet();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такой клиент уже существует");
        }
        listOfClients.add(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/phone")
    public ResponseEntity<?> getClientByPhoneNumber(@RequestParam String phoneNumber){
        var clientsByPhoneNumber = listOfClients.stream().filter(x -> x.getPhoneNumber().equals(phoneNumber)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(clientsByPhoneNumber);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getClientByEmail(@RequestParam String email){
        var clientsByPhoneNumber = listOfClients.stream().filter(x -> x.getEmail().equals(email)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(clientsByPhoneNumber);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClientInfo(@RequestParam Integer id, @RequestBody ClientEntity client){
        var oldClientInfo = listOfClients.stream().filter(x -> x.getId().equals(id)).findFirst();
        if(oldClientInfo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Клиента с таким ID не существует");
        }
        ClientEntity.nextId.decrementAndGet();
        ClientEntity oldClient = oldClientInfo.get();
        oldClient.switchInfo(client);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
}
