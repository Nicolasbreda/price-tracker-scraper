package com.nicolas.todo_api.scraper;

import com.nicolas.todo_api.model.Produto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MercadoLivreScraper {

    private WebDriver driver;
    private WebDriverWait wait;

    public void iniciar() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public List<Produto> buscarTodosProdutos(String termoBusca) {
        List<Produto> produtos = new ArrayList<>();

        try {
            System.out.println("🔍 Acessando Mercado Livre...");
            driver.get("https://www.mercadolivre.com.br");

            WebElement campoBusca = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cb1-edit"))
            );
            campoBusca.clear();
            campoBusca.sendKeys(termoBusca);
            campoBusca.submit();

            System.out.println("⏳ Aguardando resultados...");

            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("li.ui-search-layout__item")
            ));

            List<WebElement> cards = driver.findElements(
                    By.cssSelector("li.ui-search-layout__item")
            );

            System.out.println("📦 " + cards.size() + " produtos encontrados. Extraindo preços...");

            for (WebElement card : cards) {
                try {
                    // Tenta pegar o título
                    String titulo = "";
                    try {
                        titulo = card.findElement(By.cssSelector("h2")).getText();
                    } catch (Exception e) {
                        titulo = card.findElement(By.cssSelector("a.poly-component__title")).getText();
                    }

                    // Tenta pegar o preço — testa múltiplos seletores
                    String precoTexto = "";
                    try {
                        precoTexto = card.findElement(
                                By.cssSelector("span.andes-money-amount__fraction")
                        ).getText();
                    } catch (Exception e) {
                        precoTexto = card.findElement(
                                By.cssSelector(".price-tag-fraction")
                        ).getText();
                    }

                    // Limpa o texto do preço (remove pontos de milhar, espaços)
                    precoTexto = precoTexto.replaceAll("\\.", "")
                            .replaceAll(",", ".")
                            .trim();

                    // Pega a URL
                    String url = card.findElement(
                            By.cssSelector("a.poly-component__title")
                    ).getAttribute("href");

                    double preco = Double.parseDouble(precoTexto);

                    if (!titulo.isEmpty() && preco > 0) {
                        produtos.add(new Produto(titulo, preco, "Disponível", url));
                    }

                } catch (Exception e) {
                    // Ignora cards sem preço ou título válido
                }
            }

            System.out.println("✅ " + produtos.size() + " produtos com preço extraído com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro durante o scraping: " + e.getMessage());
        }

        return produtos;
    }

    public Produto encontrarMaisBarato(List<Produto> produtos) {
        return produtos.stream()
                .min((a, b) -> Double.compare(a.getPreco(), b.getPreco()))
                .orElse(null);
    }

    public void encerrar() {
        if (driver != null) {
            driver.quit();
        }
    }
}