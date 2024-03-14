package com.example.demo.Console;

import com.example.demo.Player.Player;
import com.example.demo.Player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/console")
public class ConsoleController {

    private final ConsoleService consoleService;

    @Autowired
    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Autowired
    PlayerRepository playerRepository;



    @GetMapping

    public List<Console> GetConsoles() {
        return consoleService.GetConsoles();

    }

    @GetMapping(path = "{consoleserialNum}")
    public Optional<Console> SearchConsole(@PathVariable("consoleserialNum") Long consoleserialNum) {
        return consoleService.SearchConsole(consoleserialNum);
    }

    @PostMapping

    public void addNewConsole(@RequestBody Console console) {consoleService.addNewConsole(console);}

    @DeleteMapping(path = {"consoleserialNum"})
    public void deleteConsole(@PathVariable(
            "consoleserialNum") Long consoleserialNum){
        consoleService.deleteConsole(consoleserialNum);
    }
    @PutMapping(path = "{consoleserialNum}")
    public void updateConsole(
            @PathVariable("consoleserialNum") Long consoleserialNum,
            @RequestParam(required = false) String Name) {
         consoleService.updateConsole(consoleserialNum, Name);
    }

    @PutMapping("{consoleserialNum}/console")
    Console addConsoletoplayer(
            @PathVariable Long consoleserialNum,
            @PathVariable Long playerId
    ){
        Console console = consoleService.GetConsoles().get(Math.toIntExact(consoleserialNum));
        Optional<Player> player = playerRepository.findById(playerId);
          Player pp =  console.getConsoleOwner();
        return console;
    }


}
