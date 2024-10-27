package DAO;

import DTO.JogadorDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;

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

}
