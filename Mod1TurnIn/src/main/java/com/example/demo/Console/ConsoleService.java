package com.example.demo.Console;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsoleService {

    private final ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleService(ConsoleRepository consoleRepository) {this.consoleRepository = consoleRepository;}

    public List<Console> GetConsoles() {return consoleRepository.findAll();}

    public void addNewConsole(Console console) {
        Optional<Console> consoleOptional = consoleRepository
                .findConsoleByCname(console.getName());
        if (consoleOptional.isPresent()) {
            throw new IllegalStateException("Console already exist");
        }
        consoleRepository.save(console);
        System.out.println(console);

    }

    public void deleteConsole(Long consoleserialNum){
        consoleRepository.findAllById(Collections.singleton(consoleserialNum));
        boolean exist = consoleRepository.existsById(consoleserialNum);
        if (!exist){
            throw new IllegalStateException("Console Does Not Exist");

        }
        consoleRepository.deleteById(consoleserialNum);


    }
    public Optional<Console> SearchConsole(Long consoleserialNum){

        return Optional.ofNullable(consoleRepository.findById(consoleserialNum).orElseThrow(() -> new IllegalStateException("Console doesn't exist")));
    }
    @Transactional
    public void updateConsole(Long consoleserialNum,
                              String Name)
                               {
        Console console = consoleRepository.findById(consoleserialNum)
                .orElseThrow(() -> new IllegalStateException(
                        "Console with id" + consoleserialNum + " does not exist"));

        if (Name != null &&
                Name.length() > 0 &&
                !Objects.equals(console.getName(), Name)) {
            console.setName(Name);
        }



                }


    public Console save(Console console) {
        return console;
    }
}


