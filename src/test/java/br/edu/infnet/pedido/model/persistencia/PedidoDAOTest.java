package br.edu.infnet.pedido.model.persistencia;

import br.edu.infnet.pedido.model.entidade.Pedido;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PedidoDAOTest {

    @Test
    public void testListaPedidos() {
        IDAO pediIdao = new PedidoDAO();
        List<Pedido> lista = pediIdao.listarTodos();
        System.out.println(lista);
        Assert.assertTrue(lista.size() > 0);
    }


}
