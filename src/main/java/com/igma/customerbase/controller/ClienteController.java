package com.igma.customerbase.controller;

import com.igma.customerbase.entities.Cliente;
import com.igma.customerbase.repositories.ClienteRepository;
import com.igma.customerbase.service.ValidacaoCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) {
        if (!ValidacaoCPF.isValido(cliente.getCpf())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("CPF inválido");
        }
        clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{cpf}")
    public Cliente buscarPorCpf(@PathVariable String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @GetMapping
    public Page<Cliente> listarTodos(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return clienteRepository.findAll(PageRequest.of(page, size));
    }
}