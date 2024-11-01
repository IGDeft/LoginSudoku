package VIEW;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class frmLoginVIEW extends javax.swing.JFrame {

    public frmLoginVIEW() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNomeUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        txtSenhaUsuario = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do Usuário");

        jLabel2.setText("Senha do Usuário");

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEntrar)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(txtSenhaUsuario))
                .addGap(0, 132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnEntrar)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        Logar();

    }//GEN-LAST:event_btnEntrarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLoginVIEW().setVisible(true);
            }
        });
    }

    public static int pontuacao(int nivel, int acertos, int pontuacao) {
        switch (nivel) {
            case 1 -> {
                pontuacao += 200 + (acertos * 10);
                return pontuacao;
            }
            case 2 -> {
                pontuacao += 250 + (acertos * 13);
                return pontuacao;

            }
            case 3 -> {
                pontuacao += 300 + (acertos * 17);
                return pontuacao;
            }
            case 4 -> {
                pontuacao += 350 + (acertos * 20);
                return pontuacao;
            }
            default -> {
                return 0;
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JPasswordField txtSenhaUsuario;
    // End of variables declaration//GEN-END:variables

    private void Logar() {
        try {
            String nome_usuario, senha_usuario;

            nome_usuario = txtNomeUsuario.getText();
            senha_usuario = txtSenhaUsuario.getText();

            UsuarioDTO objusuariodto = new UsuarioDTO();
            objusuariodto.setNome_usuario(nome_usuario);
            objusuariodto.setSenha_usuario(senha_usuario);

            UsuarioDAO objusuariodao = new UsuarioDAO();
            ResultSet rsusuariodao = objusuariodao.autenticacaoUsuario(objusuariodto);

            if (rsusuariodao.next()) {

                Tabuleiro tabuleiroJogador = new Tabuleiro();
                Scanner input = new Scanner(System.in);
                int nivel = 0;
                System.out.println("ESCOLHA O NÍVEL - 1. FÁCIL | 2. MÉDIO | 3. DIFÍCIL | 4. MESTRE");

                do {
                    nivel = input.nextInt();
                } while (nivel <= 0 || nivel > 4);
                System.out.println();
                tabuleiroJogador.setNivel(nivel);
                int pontuacao = 0;
                int[][] tabuleiro = tabuleiroJogador.mostrarNumero();
                tabuleiroJogador.imprimirTabuleiro();
                int linha, coluna, numero, erros = 0, acertos = 0, dica;
                do {

                    System.out.println("Gostaria de uma dica? 1.SIM 2.NÃO");
                    dica = input.nextInt();
                     System.out.println();
                    if (dica == 1) {
                        tabuleiroJogador.getDicas();
                        acertos = 0;
                        tabuleiroJogador.imprimirTabuleiro();
                    }

                    do {
                        do {
                            System.out.println("Escreva uma linha: ");
                            linha = input.nextInt();
                        } while (linha < 1 || linha > 9);
                        --linha;
                        do {
                            System.out.println("Escreva uma coluna: ");
                            coluna = input.nextInt();
                        } while (coluna < 1 || coluna > 9);
                        --coluna;
                        do {
                            System.out.println("Escreva um número: ");
                            numero = input.nextInt();
                        } while (numero < 1 || numero > 9);
                        if (tabuleiro[linha][coluna] != 0) {
                            System.out.println("Posição inválida");
                        }
                    } while (tabuleiro[linha][coluna] != 0);
                    tabuleiroJogador.setNumero(numero);
                    if (tabuleiroJogador.realizarJogada(linha, coluna)) {
                        tabuleiro[linha][coluna] = numero;
                        pontuacao = pontuacao(nivel, acertos, pontuacao);
                        acertos++;
                    } else {
                        erros++;
                        acertos = 0;
                    }
                    System.out.println("Pontuação: " + pontuacao);
                    System.out.println("Erros: " + erros);
                    System.out.println();
                    tabuleiroJogador.imprimirTabuleiro();
                    if (tabuleiroJogador.concluido()) {
                        System.out.println("Jogo concluído, digite -1 para sair");
                        numero = input.nextInt();
                    }
                } while (numero != -1);

            } else {
                //enviar mensagem dizendo incorreto.
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválida");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FRMLOGINVIEW" + erro);
        }
    }

}
