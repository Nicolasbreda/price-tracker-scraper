# price-tracker-scraper
Aplicação desenvolvida em Java com Spring Boot e Selenium que realiza web scraping automatizado no Mercado Livre, buscando produtos e retornando os melhores preços com link direto para compra.

## Funcionalidades
- Busca automatizada de produtos por termo no Mercado Livre
- Ordenação automática do mais barato para o mais caro
- Destaque do melhor preço encontrado
- Link direto para o produto na página do marketplace
- Exibição dos Top 5 produtos mais baratos

## Tecnologias utilizadas
- **Java 21**
- **Spring Boot 3.5** (estrutura base da aplicação)
- **Selenium 4.34** (automação do navegador para web scraping dinâmico)
- **Selenium Manager** (gerenciamento automático do ChromeDriver, sem instalação manual)
- **Maven** (gerenciamento de dependências)

### Pré-requisitos
- Java 21+
- Maven
- Google Chrome instalado

## Marketplaces disponíveis
| Marketplace   | Status     |
| Mercado Livre | Disponível |
| OLX           | Em breve   |
| Kabum         | Em breve   |

## Melhorias previstas para v2.0
- [ ] Suporte a múltiplos marketplaces (OLX e Kabum)
- [ ] Menu interativo para seleção de marketplace
- [ ] Exposição via API REST (endpoint /produtos/buscar)
- [ ] Comparação de preços entre marketplaces automaticamente
- [ ] Exportação dos resultados em .csv

## Observações técnicas
Este projeto utiliza Selenium com ChromeDriver para renderizar páginas com JavaScript dinâmico — necessário para e-commerces modernos que carregam preços via JS, onde bibliotecas de scraping estático (como Jsoup) não funcionam.
