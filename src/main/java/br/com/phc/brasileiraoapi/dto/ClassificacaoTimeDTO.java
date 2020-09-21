package br.com.phc.brasileiraoapi.dto;

import lombok.Data;

@Data
public class ClassificacaoTimeDTO  implements Comparable<ClassificacaoTimeDTO>{
    private String time;
    private long idTime;
    private Integer posicao;
    private Integer pontos;
    private Integer jogos;
    private Integer vitorias;
    private Integer empates;
    private Integer derrotas;
    private Integer golsMarcados;
    private Integer golsSofridos;

    @Override
    public int compareTo(ClassificacaoTimeDTO o) {
        return this.getPontos().compareTo(o.getPontos());
    }
}
