package br.com.aptare.cpt.security;

import java.util.*;

public class TirarTimeNew {
    static class Dupla {
        String a;
        String b;

        Dupla(String a, String b) {
            this.a = a;
            this.b = b;
        }

        String chave(){
            return a + "-" + b;
        }

        @Override
        public String toString() {
            return a + "/" + b;
        }
    }

    static class Jogo {
        Dupla d1;
        Dupla d2;

        Jogo(Dupla d1, Dupla d2) {
            this.d1 = d1;
            this.d2 = d2;
        }

        List<String> jogadores() {
            return Arrays.asList(d1.a, d1.b, d2.a, d2.b);
        }

        @Override
        public String toString() {
            return d1 + " x " + d2;
        }
    }

    public static void gerarTabela() {

        String[] grupoA = {"greg", "juliel", "huan", "rick", "diego"};
        String[] grupoB = {"wan", "rho", "ligia", "jess", "gus"};

        int MAX_PARTIDAS = grupoA.length;
        int MIN_PARTIDAS = grupoA.length - 1;

        List<Dupla> duplas = new ArrayList<>();

        for (String a : grupoA) {
            for (String b : grupoB) {
                duplas.add(new Dupla(a, b));
            }
        }

        List<Jogo> todosJogos = new ArrayList<>();

        for (int i = 0; i < duplas.size(); i++) {
            for (int j = i + 1; j < duplas.size(); j++) {

                Dupla d1 = duplas.get(i);
                Dupla d2 = duplas.get(j);

                if (d1.a.equals(d2.a)) continue;
                if (d1.b.equals(d2.b)) continue;

                todosJogos.add(new Jogo(d1, d2));
            }
        }

        Collections.shuffle(todosJogos);

        Map<String,Integer> partidasJogador = new HashMap<>();
        Map<String,Integer> ultimaRodada = new HashMap<>();

        for(String p : grupoA){
            partidasJogador.put(p,0);
            ultimaRodada.put(p,-1);
        }

        for(String p : grupoB){
            partidasJogador.put(p,0);
            ultimaRodada.put(p,-1);
        }

        Set<String> duplasUsadas = new HashSet<>();

        List<Jogo> resultado = new ArrayList<>();

        int rodadaAtual = 0;

        while(true){

            Jogo melhorJogo = null;
            int melhorScore = Integer.MIN_VALUE;

            for(Jogo jogo : todosJogos){

                if(resultado.contains(jogo)) continue;

                // 🚫 não repetir dupla
                if(duplasUsadas.contains(jogo.d1.chave())) continue;
                if(duplasUsadas.contains(jogo.d2.chave())) continue;

                boolean pode = true;

                for(String j : jogo.jogadores()){
                    if(partidasJogador.get(j) >= MAX_PARTIDAS){
                        pode = false;
                        break;
                    }
                }

                if(!pode) continue;

                int scoreDescanso = 0;

                for(String j : jogo.jogadores()){
                    scoreDescanso += (rodadaAtual - ultimaRodada.get(j));
                }

                if(scoreDescanso > melhorScore){
                    melhorScore = scoreDescanso;
                    melhorJogo = jogo;
                }
            }

            if(melhorJogo == null) break;

            resultado.add(melhorJogo);

            duplasUsadas.add(melhorJogo.d1.chave());
            duplasUsadas.add(melhorJogo.d2.chave());

            for(String j : melhorJogo.jogadores()){
                partidasJogador.put(j, partidasJogador.get(j)+1);
                ultimaRodada.put(j,rodadaAtual);
            }

            rodadaAtual++;
        }

        System.out.println("\n=== JOGOS GERADOS ===");
        resultado.forEach(System.out::println);

        System.out.println("\n=== PARTIDAS POR JOGADOR ===");
        partidasJogador.forEach((k,v)-> System.out.println(k+" -> "+v));

        System.out.println("\n=== JOGADORES ABAIXO DO IDEAL ===");
        partidasJogador.forEach((k,v)->{
            if(v < MIN_PARTIDAS){
                System.out.println(k+" ficou com "+v+" partidas");
            }
        });
    }

    public static void main(String[] args) {
        gerarTabela();
    }
}
