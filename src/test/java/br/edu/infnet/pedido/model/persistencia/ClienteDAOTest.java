package br.edu.infnet.pedido.model.persistencia;

import br.edu.infnet.pedido.model.entidade.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ClienteDAOTest {

    IDAO<Cliente> clienteDAO = null;
    Cliente cliente = null;

    @Before
    public void inicializar() {
        clienteDAO = new ClienteDAO();
        cliente = new Cliente("Jose das Couves");
        clienteDAO.salvar(cliente);
    }

    @Test
    public void salvarClienteTest() {
        boolean salvo = clienteDAO.salvar(cliente);

        Assert.assertTrue(salvo);
    }

    @Test
    public void atualizarClienteTest() {
        List<Cliente> lista = clienteDAO.listarTodos();
        cliente = new Cliente("Maria das Couves", lista.get(0).getCodigo());

        boolean atualizado = clienteDAO.atualizar(cliente);

        Assert.assertTrue(atualizado);
    }

    @Test
    public void deletarClienteTest() {
        List<Cliente> lista = clienteDAO.listarTodos();
        cliente = new Cliente("Maria das Couves", lista.get(lista.size() - 1).getCodigo());

        boolean deletado = clienteDAO.deletar(cliente);

        Assert.assertTrue(deletado);
    }

    @Test
    public void listarTodosClientesTest() {

        List<Cliente> lista = clienteDAO.listarTodos();

        Assert.assertTrue(lista.size() > 0);
    }

    @Test
    public void obterClienteTest() {
        List<Cliente> lista = clienteDAO.listarTodos();

        cliente = clienteDAO.obter(lista.get(0).getCodigo());

        Assert.assertNotNull(cliente);
    }
}