package br.com.aptare.cpt.security;

import java.util.*;

public class TirarTime {

    static class Dupla {
        String a;
        String b;

        Dupla(String a, String b) {
            this.a = a;
            this.b = b;
        }

        String chave() {
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

        String chave() {
            List<String> chaves = Arrays.asList(d1.chave(), d2.chave());
            Collections.sort(chaves);
            return chaves.get(0) + " x " + chaves.get(1);
        }

        @Override
        public String toString() {
            return d1 + " x " + d2;
        }
    }

    static Set<String> adversariosNoJogo(String jogador, Jogo jogo) {
        if (jogador.equals(jogo.d1.a) || jogador.equals(jogo.d1.b)) {
            return new HashSet<>(Arrays.asList(jogo.d2.a, jogo.d2.b));
        }

        return new HashSet<>(Arrays.asList(jogo.d1.a, jogo.d1.b));
    }

    public static void gerarTabela() {

        String[] grupoA = {"greg", "huan", "harten", "rick", "mateus"};
        String[] grupoB = {"wan", "rho", "jess", "gus", "berg"};

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

        Map<String, Integer> partidasJogador = new HashMap<>();
        Map<String, Integer> ultimaRodada = new HashMap<>();
        Map<String, Set<String>> adversariosPorJogador = new HashMap<>();

        for (String p : grupoA) {
            partidasJogador.put(p, 0);
            ultimaRodada.put(p, -1);
            adversariosPorJogador.put(p, new HashSet<>());
        }

        for (String p : grupoB) {
            partidasJogador.put(p, 0);
            ultimaRodada.put(p, -1);
            adversariosPorJogador.put(p, new HashSet<>());
        }

        Set<String> duplasUsadas = new HashSet<>();
        Set<String> jogosUsados = new HashSet<>();

        List<Jogo> resultado = new ArrayList<>();

        int rodadaAtual = 0;

        while (true) {

            Jogo melhorJogo = null;
            int melhorScore = Integer.MIN_VALUE;

            for (Jogo jogo : todosJogos) {

                if (jogosUsados.contains(jogo.chave())) continue;

                if (duplasUsadas.contains(jogo.d1.chave())) continue;
                if (duplasUsadas.contains(jogo.d2.chave())) continue;

                boolean pode = true;

                for (String jogador : jogo.jogadores()) {
                    if (partidasJogador.get(jogador) >= MAX_PARTIDAS) {
                        pode = false;
                        break;
                    }
                }

                if (!pode) continue;

                int score = 0;

                for (String jogador : jogo.jogadores()) {
                    Set<String> adversariosDoJogo = adversariosNoJogo(jogador, jogo);

                    for (String adversario : adversariosDoJogo) {
                        if (adversariosPorJogador.get(jogador).contains(adversario)) {
                            score -= 300;
                        } else {
                            score += 50;
                        }
                    }
                }

                for (String jogador : jogo.jogadores()) {
                    score += rodadaAtual - ultimaRodada.get(jogador);
                }

                for (String jogador : jogo.jogadores()) {
                    score -= partidasJogador.get(jogador) * 10;
                }

                if (score > melhorScore) {
                    melhorScore = score;
                    melhorJogo = jogo;
                }
            }

            if (melhorJogo == null) break;

            resultado.add(melhorJogo);
            jogosUsados.add(melhorJogo.chave());

            duplasUsadas.add(melhorJogo.d1.chave());
            duplasUsadas.add(melhorJogo.d2.chave());

            for (String jogador : melhorJogo.jogadores()) {
                partidasJogador.put(jogador, partidasJogador.get(jogador) + 1);
                ultimaRodada.put(jogador, rodadaAtual);

                adversariosPorJogador
                        .get(jogador)
                        .addAll(adversariosNoJogo(jogador, melhorJogo));
            }

            rodadaAtual++;
        }

        System.out.println("\n=== JOGOS GERADOS ===");
        resultado.forEach(System.out::println);

        System.out.println("\n=== PARTIDAS POR JOGADOR ===");
        partidasJogador.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\n=== ADVERSARIOS POR JOGADOR ===");
        adversariosPorJogador.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\n=== JOGADORES ABAIXO DO IDEAL ===");
        partidasJogador.forEach((k, v) -> {
            if (v < MIN_PARTIDAS) {
                System.out.println(k + " ficou com " + v + " partidas");
            }
        });
    }

    public static void main(String[] args) {
        gerarTabela();
    }
}