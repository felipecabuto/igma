package com.igma.customerbase;

import com.igma.customerbase.controller.ClienteController;
import com.igma.customerbase.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClienteControllerTest {

    @Test
    public void testCriarCliente() {
        ClienteController controller = new ClienteController();

        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Felipe Cabuto");
        novoCliente.setCpf("33868438831");
        novoCliente.setDataNascimento(LocalDate.of(1997, 3, 13));

        ResponseEntity<?> resposta = controller.criarCliente(novoCliente);

        assertEquals(200, resposta.getStatusCodeValue());

        Cliente clienteResposta = (Cliente) resposta.getBody();
        assertNotNull(clienteResposta);
        assertEquals("Felipe Cabuto", clienteResposta.getNome());
        assertEquals("33868438831", clienteResposta.getCpf());
    }

    @Test
    public void testBuscarPorCpf() {
        ClienteController controller = new ClienteController();

        Cliente clienteTeste = new Cliente();
        clienteTeste.setNome("Thayná Cabuto");
        clienteTeste.setCpf("81302122022");
        clienteTeste.setDataNascimento(LocalDate.of(1997, 3, 13));

        Cliente resposta = controller.buscarPorCpf("81302122022");

        assertEquals(200, resposta.getStatusCodeValue());

        Cliente clienteResposta = (Cliente) resposta.getBody();
        assertNotNull(clienteResposta);
        assertEquals("Thayná Cabuto", clienteResposta.getNome());
        assertEquals("81302122022", clienteResposta.getCpf());
    }

    @Test
    public void testListarTodosClientes() {
        ClienteController controller = new ClienteController();

        ResponseEntity<List<Cliente>> resposta = (ResponseEntity<List<Cliente>>) controller.listarTodos(0, 10);

        assertEquals(200, resposta.getStatusCodeValue());

        List<Cliente> clientes = resposta.getBody();
        assertNotNull(clientes);
    }
}