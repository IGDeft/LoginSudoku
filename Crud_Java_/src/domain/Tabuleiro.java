
package domain;

import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro {
    Solucao solucao = new Solucao();
    int[][] tabuleiroSolucao = solucao.getTabuleiro();
    private int[][] tabuleiro = new int[9][9];
    private int nivel;
    private int numero; 
    //inserir tempo

    public int[][] getTabuleiro() {
        return this.tabuleiroSolucao;
    }
    public int getTabuleiro(int lin, int col) {
        return this.tabuleiroSolucao[lin][col];
    }
    public int getNivel() {
        switch(this.nivel){
            case 1 -> {
                return 38;
            }
            case 2 -> { 
                return 33;
            }
            case 3 -> {
                return 28;
            }
            case 4 -> {
                return 27;
            }
            default -> {
                return 0;
            }
                
        }
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
     public int[][] mostrarNumero(){
        
        int numerosMostrados = getNivel();
        Random aleatorio = new Random();
        int contador=0;
        do{
            int linha = aleatorio.nextInt(9);
            int coluna = aleatorio.nextInt(9);
            if(this.tabuleiro[linha][coluna]==0){
                this.tabuleiro[linha][coluna]=tabuleiroSolucao[linha][coluna];
                contador++;
            }
        }while (contador<=numerosMostrados);
        return this.tabuleiro;
    }
    public boolean concluido(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(this.tabuleiro[i][j]!=tabuleiroSolucao[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public void imprimirTabuleiro() {
    		for(int lin = 0; lin < this.tabuleiro.length; lin++){
                if(lin !=0 && lin %3==0){
                    System.out.println();
                }
                for(int col = 0; col < this.tabuleiro[0].length; col++){
                    if(col !=0 && col %3==0){
                        System.out.print(" ");
                    }
                    System.out.print(this.tabuleiro[lin][col] + "\t");

                }
                System.out.println("\n");

            }
    	}
    public void getDicas(){
        ArrayList<Integer> colunaDisponivel = new ArrayList<>();
        int lin, tamanho, indice;
        Random aleatorio = new Random();
        do{
            lin = aleatorio.nextInt(9);       
            for(int i = 0; i < 9; i++){
                if(this.tabuleiro[lin][i]==0){
                   colunaDisponivel.add(i);
                }
            }
         tamanho= colunaDisponivel.size();
        }while(tamanho<1);
        indice = aleatorio.nextInt(0, tamanho);     
        int coluna = colunaDisponivel.get(indice);
        this.tabuleiro[lin][coluna] = tabuleiroSolucao[lin][coluna];
    }    
    public boolean realizarJogada(int linha, int coluna){
        if(this.numero==tabuleiroSolucao[linha][coluna]){       
         return true;
        }
        return false;
    }
}
