/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacaousuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAOImpl implements UsuarioDAO{
    
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(Usuario u) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/coursera", "postgres", "secret")){
            String sql = "INSERT INTO public.usuario(login, nome, email,senha, pontos) "
                    + "VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getNome());
            stm.setString(3, u.getEmail());
            stm.setString(4, u.getSenha());
            stm.setString(5, u.getPontos());
            
            stm.executeUpdate();
            
        }catch(UnsupportedOperationException | SQLException e){
            throw new RuntimeException("Nao foi possivel conectar a DB");
        }
    }

    @Override
    public Usuario recuperar(String login) {
        Usuario  usuario= null;
        
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/coursera", "postgres", "secret")){
            String sql = "SELECT * FROM usuario WHERE login = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, login);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPontos(rs.getString("pontos"));
            }
        }catch(UnsupportedOperationException | SQLException e){
            throw new RuntimeException("Nao foi possivel conectar a DB");
        }
        
        return usuario;    
    }

    @Override
    public void adicionarPontos(String login, int pontos) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/coursera", "postgres", "secret")){
            String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?;";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, pontos);
            stm.setString(2, login);
            
            stm.executeUpdate();
            
        }catch(UnsupportedOperationException | SQLException e){
            throw new RuntimeException("Nao foi possivel conectar a DB", e);
        }
    }

    @Override
    public List<Usuario> ranking() {
        List<Usuario> todos = new ArrayList<>();
        
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/coursera", "postgres", "secret")){
            String sql = "SELECT * FROM usuario ORDER BY pontos ASC";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setPontos(rs.getString("pontos"));
                todos.add(u);
            }
        }catch(UnsupportedOperationException | SQLException e){
            throw new RuntimeException("Nao foi possivel conectar a DB");
        }
        
        return todos;
    }
    
}
