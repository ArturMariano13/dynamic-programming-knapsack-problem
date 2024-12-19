package br.edu.ifsul.bcc.mi.aca.problemamochila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaMochila {

    // Matriz:
        // Colunas: capacidade da mochila + 1
        // Linhas: número de elementos + 1
    
    /**
          0  1  2  3  4  5  6  7  8  9  10
    [0,0] 0  0  0  0  0  0  0  0  0  0   0
    [1,2] 0  0  1  1  1  1  1  1  1  1   1
    [4,3] 0  0  1  4  4  5  0  0  0  0   0
    [5,6] 0  0  0  0  0  0  0  0  0  0   0
    [6,7] 0  0  0  0  0  0  0  0  0  0   0
    */
    
    

    public static List<List<Integer>> problemaMochila(int[][] itens, int capacidadeMochila) {
        List<List<Integer>> resultado = new ArrayList<>();
        int[][] itensMochila = new int[itens.length + 1][capacidadeMochila + 1];
        
        for (int linha = 1; linha < itensMochila.length; linha++){
            int valorItemAtual = itens[linha - 1][0];
            int pesoItemAtual = itens[linha - 1][1];
            
            
            for (int coluna = 0; coluna < itensMochila[linha].length; coluna++){
                int capacidadeMochilaAtual = coluna;
                
                if (capacidadeMochilaAtual < pesoItemAtual){
                    itensMochila[linha][coluna] = itensMochila[linha - 1][coluna];
                } else{
                    int capacidadeRestante = capacidadeMochilaAtual - pesoItemAtual;
                    int valorAgregadoCapacidadeRestante = itensMochila[linha - 1][capacidadeRestante];
                    int valorAtualizado = valorItemAtual + valorAgregadoCapacidadeRestante;
                    
                    itensMochila[linha][coluna] = Math.max(valorAtualizado, itensMochila[linha - 1][coluna]);
                }
               
            }
        }
        
        int linha = itensMochila.length - 1;
        int coluna = itensMochila[linha].length - 1;
        
        int valorAgregado = itensMochila[linha][coluna];
        
        resultado.add(Arrays.asList(valorAgregado));
        resultado.add(new ArrayList<>());
        
        while (valorAgregado > 0){
            if (valorAgregado == itensMochila[linha - 1][coluna]){
                linha--;
            } else{
                resultado.get(1).add(linha - 1);
                int capacidadeAtual = coluna;
                int capacidadeRestante = capacidadeAtual - itens[linha - 1][1]; 
                coluna = capacidadeRestante;
                linha--;
            }
            
            valorAgregado = itensMochila[linha][coluna];
        }
        
        System.out.println(Arrays.deepToString(itensMochila));
                
        return resultado;
    }

    public static void main(String[] args){
        int capacidadeMochila = 10;
        
        // primeira coluna = valor
        // segunda coluna = peso
        
        int[][] itens = {
            {1,2},
            {4,3},
            {5,6},
            {6,7}
        };

        //[[10], [3,1]]

        System.out.println(problemaMochila(itens, capacidadeMochila));
    }
}
