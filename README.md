# Projeto de Automação de Testes com Selenium

[![CI](https://github.com/fabiodrneles/exemplo-automacao-page-object/actions/workflows/ci.yml/badge.svg)](https://github.com/fabiodrneles/exemplo-automacao-page-object/actions/workflows/ci.yml)
[![Site real](https://github.com/fabiodrneles/exemplo-automacao-page-object/actions/workflows/site-real.yml/badge.svg)](https://github.com/fabiodrneles/exemplo-automacao-page-object/actions/workflows/site-real.yml)

Testes de UI em **Java 21**, **JUnit 5** e **Selenium 4** no padrão **Page Object**, validando a busca de produtos do e-commerce de treino <https://automacao.testerglobal.com/>.

![Página inicial do site testado](https://github.com/user-attachments/assets/8d8aaf2e-b277-410c-9469-6286652886be)

## 📋 O que é testado

| Teste | Ação | Validação |
|---|---|---|
| `pesquisarCampoVazio` | Pesquisa com o campo vazio | A URL tem `post_type=product` e `s=` vazio |
| `pesquisarProduto` | Pesquisa "Camera" | Abre `/product/camera/` e o título do produto é "Camera" |

## 🧱 Como os testes são organizados

- **Page Objects** (`Home`, `PaginaProduto`): seletores curtos e estáveis e esperas explícitas (`WebDriverWait`); os testes só falam a língua do negócio.
- **Fixture local** (`FixtureServer`): uma réplica mínima da busca da loja, servida pelo próprio teste. Os PRs não dependem de um site de terceiros.
- **Site real**: o workflow [Site real](.github/workflows/site-real.yml) roda os mesmos testes contra a loja todo dia e sob demanda.

## 🚀 Como executar

Pré-requisitos: JDK 21 e Google Chrome. O driver do Chrome é baixado automaticamente pelo Selenium Manager.

```bash
# Contra a fixture local, sem abrir janela
./gradlew test -Dheadless=true
```

Para rodar contra o site real, ou vendo o navegador:

```text
./gradlew test -DbaseUrl=https://automacao.testerglobal.com/
./gradlew test
```

O relatório HTML fica em `build/reports/tests/test/index.html`; no CI, ele é publicado como artefato `test-report`.

## 🛠️ Tecnologias

- Java 21 (toolchain do Gradle)
- JUnit 5
- Selenium WebDriver 4 (com Selenium Manager)
- Gradle 8 (Wrapper)
- GitHub Actions

## 🧭 Processo

Este projeto segue Spec Driven Development com o [sdd-kit](https://github.com/fabiodrneles/sdd-kit): specs com critérios de aceite em [`specs/`](specs/README.md), um PR por ticket e `make ci` antes de todo push.
