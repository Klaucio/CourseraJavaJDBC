
import java.util.List;
import manipulacaousuarios.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public interface UsuarioDAO {
    //insere um novo usuário no banco de dados
   public void inserir();
   
   //recupera o usuário pelo seu login
   public void recuperar();
   
   //adiciona os pontos para o usuário no banco
   public void adicionarPontos();
   
   //retorna a lista de usuários ordenada por pontos (maior primeiro)
   public void ranking();
}
