package br.edu.infnet.pedido.model.persistencia;

import br.edu.infnet.pedido.model.entidade.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDAOTest {

    IDAO<Usuario> usuarioDAO = null;
    Usuario usuario = null;

    @Before
    public void inicializar() {
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario(null, "Pedro Bernardes", "pedro_bernardes@teste.com.br", "123", LocalDate.now());
        usuarioDAO.salvar(usuario);
    }

    @Test
    public void salvarUsuarioTest() {
        boolean salvo = usuarioDAO.salvar(usuario);

        Assert.assertTrue(salvo);
    }


    @Test
    public void atualizarUsuarioTest() {
        usuario.setNome("Pedro Bernardes");
        usuario.setEmail("pedro_bernardes@teste.com.br");
        usuario.setSenha("123456");
        usuario.setId(1L);

        boolean atualizado = usuarioDAO.atualizar(usuario);

        Assert.assertTrue(atualizado);
    }

    @Test
    public void deletarUsuarioTest() {
        List<Usuario> lista = usuarioDAO.listarTodos();

        boolean deletado = usuarioDAO.deletar(lista.get(lista.size() - 1));

        Assert.assertTrue(deletado);
    }


    @Test
    public void listarUsuariosTest() {

        List<Usuario> lista = usuarioDAO.listarTodos();

        Assert.assertTrue(lista.size() > 0);
    }


    @Test
    public void obterUsuarioTest() {
        List<Usuario> lista = usuarioDAO.listarTodos();

        Usuario obtido = usuarioDAO.obter(lista.get(0).getId());

        Assert.assertNotNull(obtido);
    }
}
