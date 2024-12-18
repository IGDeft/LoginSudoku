
package domain;

import java.util.Random;

public class Solucao {
    private int[][] tabuleiro = new int[9][9];
    //criar Sudoku
    public Solucao(){
        do{
            organizarQuadradosPrincipais(this.tabuleiro);
        }while(!resolverSudoku(this.tabuleiro));
        
    }
    public int[][] getTabuleiro() {
        return this.tabuleiro;
    }
    //iniciar Sudoku
    private static void inicializarQuadrado(int[][] tabuleiro){
        //colocar os numeros de 1 ate 9 na ordem nos quadrados 3x3
        int numero = 1;
        for(int linha = 0; linha < tabuleiro.length; linha++){
            for(int coluna = 0; coluna < tabuleiro.length; coluna++){
                tabuleiro[linha][coluna]= numero;
                numero++;
            }
        }
    }
    private static void aleatorizarQuadrado(int[][] organizado) {
        //alatorizar os quadrados 3x3;
        inicializarQuadrado(organizado);
        Random aleatorio = new Random();
        for(int i=0;i<100;i++){
            int linha = aleatorio.nextInt(3);
            int coluna = aleatorio.nextInt(3);
            int linhaDois = aleatorio.nextInt(3);
            int colunaDois = aleatorio.nextInt(3);

            int aux = organizado[linha][coluna];
            organizado[linha][coluna] = organizado[linhaDois][colunaDois];
            organizado[linhaDois][colunaDois] = aux;
        }
    }
    private static void quadradosOrganizacao(int[][] tabuleiro, int indicesTabuleiro) {
        //colocar cada quadrado 3x3 no lugar certo do quadrado 9x9
        int[][] quadradoTabuleiro = new int[3][3];
        aleatorizarQuadrado(quadradoTabuleiro);
        for(int linha = 0, linhaPrincipal = indicesTabuleiro; linha < 3; linha++, linhaPrincipal++){
            for(int coluna = 0, colunaPrincipal = indicesTabuleiro; coluna < 3; coluna++, colunaPrincipal++){
                tabuleiro[linhaPrincipal][colunaPrincipal]= quadradoTabuleiro[linha][coluna];
            }
        }
    }
    private static void organizarQuadradosPrincipais(int [][] tabuleiro) {
        //preencher os quadrados que não interferem um no outro
        quadradosOrganizacao(tabuleiro, 0);
        quadradosOrganizacao(tabuleiro, 3);
        quadradosOrganizacao(tabuleiro, 6);


    }
    //completar Sudoku
    private static boolean resolverSudoku(int[][] tab) {
        for(int lin = 0; lin < 9; lin++) {
            for(int col = 0; col < 9; col++) {
                if(tab[lin][col] == 0) {
                    for(int num = 1; num <=9; num++) {
                        if(posicaoValida(tab, num, lin, col)) {
                            tab[lin][col] = num;
                            if (resolverSudoku(tab)) {
                                return true;
                            }else {
                                tab[lin][col]= 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    //regras Sudoku
    private static boolean posicaoValida(int[][] tab, int num, int lin, int col) {
        return !possuiNumLinha(tab, num, lin) && !possuiNumColuna(tab, num, col) && !possuiNumQuadrado(tab, num, lin, col);
    }
    private static boolean possuiNumLinha(int[][] tab, int num, int lin){
        for(int i = 0; i < 9; i++){
            if(tab[lin][i] == num){
                return true;
            }
        }
        return false;
    }
    private static boolean possuiNumColuna(int[][] tab, int num, int col){
        for(int i = 0; i < 9; i++){
            if(tab[i][col] == num){
                return true;
            }
        }
        return false;
    }
    private static boolean possuiNumQuadrado(int[][] tab, int num, int lin, int col) {
        int linQuadrado = lin - lin % 3;
        int colQuadrado = col - col % 3;
        for(int i = linQuadrado; i < linQuadrado + 3; i++){
            for(int j = colQuadrado; j < colQuadrado + 3; j++){
                if( tab[i][j] == num){
                    return true;

                }
            }
        }

    return false;
    }
}
