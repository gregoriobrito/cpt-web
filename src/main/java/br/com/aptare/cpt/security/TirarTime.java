package br.com.aptare.cpt.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TirarTime {

    public static void main(String[] args) {
        //tirarTimeDuplasFixa();
        tirarTimeDuplasAleatorias();
    }

    public static void tirarTimeDuplasAleatorias() {
        String[] grupoA = new String[]{"thiago", "juliel", "weslley", "italo", "diego"};
        String[] grupoB = new String[]{"rho", "wan", "gus", "may", "ligia"};
        List<Integer[]> listaTotalPartidas = new ArrayList<Integer[]>();
        List<Integer[]> listaFinalPartidas = new ArrayList<Integer[]>();

        int indice = 0;
        Integer[] add = null;
        for (int i = 0; i < grupoB.length; i++) {
            for (int y = 0; y < grupoA.length; y++) {
                indice = y + i;
                if (indice >= grupoB.length) {
                    indice = indice - grupoB.length;
                }
                add = new Integer[2];
                add[0] = y; add[1] = indice;
                listaTotalPartidas.add(add);
            }
        }

        // formar times
        List<List<Integer[]>> listaPartidas = new ArrayList<>();
        List<Integer[]> listaMatch = null;
        int partidaAtual = 2;
        int quantidadePartida = 2;

        for(Integer[] elemento : listaTotalPartidas) {
            if (partidaAtual >= quantidadePartida) {
                if (listaMatch != null) {
                    listaPartidas.add(listaMatch);
                }
                partidaAtual = 0;
                listaMatch = new ArrayList<>();
            }
            listaMatch.add(elemento);
            partidaAtual++;
        }

        for (List<Integer[]> elemento : listaPartidas) {
            Integer[] primeiroElemento = elemento.get(0);
            Integer[] segundoElemento = elemento.get(1);
            System.out.println(grupoA[primeiroElemento[0]] + "/" + grupoB[primeiroElemento[1]] + " x " + grupoA[segundoElemento[0]] + "/" + grupoB[segundoElemento[1]]);
        }
    }

    public static void tirarTimeDuplasFixa() {
        String[] times = new String[]{"greg/wan", "juliel/ligia", "harten/rho", "huan/jes", "henrique/gus"};
        List<String[]> listaSorteio = new ArrayList<String[]>();
        List<String[]> listaFinal = new ArrayList<String[]>();

        for (int i = 0; i < times.length; i++) {
            for (int y = i + 1; y < times.length; y++) {
                listaSorteio.add(new String[]{times[i], times[y]});
            }
        }

        Random gerador = new Random();
        int numeroAleatorio = 0;
        int quantidadeIteracoes = listaSorteio.size();
        for (int i = 0; i < quantidadeIteracoes; i ++) {
            numeroAleatorio = gerador.nextInt(listaSorteio.size());
            listaFinal.add(listaSorteio.get(numeroAleatorio));
            listaSorteio.remove(numeroAleatorio);
        }

        for(String[] elemento : listaFinal) {
            System.out.println(elemento[0] + " x " + elemento[1]);
        }

    }
}
