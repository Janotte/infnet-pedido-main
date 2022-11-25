package br.edu.infnet.pedido.model.persistencia;

import br.edu.infnet.pedido.model.entidade.Usuario;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends JdbcDAO<Usuario> {

    public UsuarioDAO() {
    }

    @Override
    public Boolean salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, email, senha, data_cadastro) VALUES (?,?,?,?)";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());
            pstm.setDate(4, Date.valueOf(LocalDate.now()));
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, data_cadastro = ? WHERE id = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());
            pstm.setDate(4, Date.valueOf(usuario.getDataCadastro()));
            pstm.setLong(5, usuario.getId());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deletar(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setLong(1, usuario.getId());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Usuario obter(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;
        try {
            pstm = con.prepareStatement(sql);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                Long usuario_id = rs.getLong("id");
                String usuario_nome = rs.getString("nome");
                String usuario_email = rs.getString("email");
                String usuario_senha = rs.getString("senha");
                Date usuario_dataCadastro = rs.getDate("data_cadastro");
                usuario = new Usuario(usuario_id, usuario_nome, usuario_email, usuario_senha, usuario_dataCadastro.toLocalDate());
            }
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Long usuario_id = rs.getLong("id");
                String usuario_nome = rs.getString("nome");
                String usuario_email = rs.getString("email");
                String usuario_senha = rs.getString("senha");
                Date usuario_dataCadastro = rs.getDate("data_cadastro");
                Usuario usuario = new Usuario(usuario_id, usuario_nome, usuario_email, usuario_senha, usuario_dataCadastro.toLocalDate());
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
