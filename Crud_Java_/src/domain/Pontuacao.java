package domain;

public class Pontuacao {
    private long tempoResolucao;

     public Pontuacao(long tempoResolucao) {
        this.tempoResolucao = tempoResolucao;
    }
     public Pontuacao() {
    }

    public long getTempoResolucao() {
        return tempoResolucao;
    }
     public int calcularPontuacao(int nivelDificuldade) {
        int pontuacaoBase = 10000;
        int fatorDificuldade = nivelDificuldade == 1 ? 1 : (nivelDificuldade == 2 ? 2 : 3);
        int pontuacaoTempo = (int) (pontuacaoBase - (tempoResolucao / 1000));
        if (pontuacaoTempo < 0) pontuacaoTempo = 0;
        return pontuacaoTempo * fatorDificuldade;
    }

    
}
