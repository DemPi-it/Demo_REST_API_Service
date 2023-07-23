package api.demo.clients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
