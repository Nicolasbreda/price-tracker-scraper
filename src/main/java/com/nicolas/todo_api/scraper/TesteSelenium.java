package com.nicolas.todo_api.scraper;

import com.nicolas.todo_api.model.Produto;

import java.util.List;
import java.util.Scanner;

public class TesteSelenium {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MercadoLivreScraper scraper = new MercadoLivreScraper();

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     PRICE TRACKER - MERCADO LIVRE        ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.print("🔎 O que você quer buscar? ");
        String busca = scanner.nextLine();

        scraper.iniciar();
        List<Produto> produtos = scraper.buscarTodosProdutos(busca);
        scraper.encerrar();

        if (produtos.isEmpty()) {
            System.out.println("\n❌ Nenhum produto encontrado para: " + busca);
        } else {
            System.out.println("\n📋 Top 5 mais baratos encontrados:");
            System.out.println("─".repeat(60));
            produtos.stream()
                    .sorted((a, b) -> Double.compare(a.getPreco(), b.getPreco()))
                    .limit(5)
                    .forEach(p -> {
                        System.out.println(p);
                        System.out.println("─".repeat(60));
                    });

            Produto maisBarato = scraper.encontrarMaisBarato(produtos);
            System.out.println("\n🏆 MELHOR PREÇO DA BUSCA:");
            System.out.println("═".repeat(60));
            System.out.println(maisBarato);
            System.out.println("═".repeat(60));
        }

        scanner.close();
    }
}