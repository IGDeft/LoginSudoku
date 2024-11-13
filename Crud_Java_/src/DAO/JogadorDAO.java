package DAO;

import DTO.JogadorDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class JogadorDAO {

    Connection conn;
    PreparedStatement pstm;

    public void cadastrarLogin(JogadorDTO objjogadordto) {

        String sql = "insert into usuario (nome_usuario, senha_usuario) values (?, ?)";

        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objjogadordto.getLogin_jogador());
            pstm.setString(2, objjogadordto.getSenha_usuario());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "JogadorDAO" + erro);
        }

    }
    
    public void atualizarPontuacao(String nomeUsuario, int novaPontuacao) {
        
        String sql = "update usuario set pontuacao = ? where nome_usuario = ? ";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, novaPontuacao);
            pstm.setString(2, nomeUsuario);
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null, "JogadorDAO erro ao atualizar " + erro);
        }
    }
    
    public int obterPontuacao(String nome_Usuario){
        
        String sql = "select pontuacao from usuario where nome_usuario = ? ";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome_Usuario);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("pontuacao");
            }
            
            rs.close();
            pstm.close();
            
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null, "JogadorDAO erro ao obter " + erro);
        }
        
        return 0;
        
    }
}
