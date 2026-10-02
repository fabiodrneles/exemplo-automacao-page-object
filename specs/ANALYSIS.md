# Análise e verificação do exemplo-automacao-page-object

Fase de descoberta (skill `sdd-delivery`), feita em 2026-10-02 sobre a `main` em `4816c71` (último commit em 2024-11-29). O que está marcado como **verificado** foi executado; o que está como **inferido** vem da leitura do código.

## 1. Resumo executivo

Projeto de portfólio de QA: dois testes de UI em Java 21, JUnit 5 e Selenium 4.27, no padrão Page Object, que fazem buscas no site de e-commerce de treino `automacao.testerglobal.com`. O código compila, mas **os testes não rodam fora da máquina do autor**:

- o `gradlew` não tem permissão de execução;
- o Chrome abre com interface gráfica;
- o site é de terceiros;
- não há CI.

Um import sem uso de uma classe interna do Selenium (`devtools.v129`) quebra a compilação na próxima atualização da biblioteca.

## 2. O que foi verificado

| Comando | Resultado |
|---|---|
| `./gradlew compileTestJava` | `Permission denied`: `gradlew` está como `100644` no git |
| `sh ./gradlew compileTestJava` (JDK 21, Gradle 8.8) | ok |
| `curl https://automacao.testerglobal.com/` | bloqueado pela rede deste ambiente (proxy); o site não pôde ser verificado daqui |
| `git ls-files` | `.idea/` versionado; `src/main/java/org/example/Main.java` é o "Hello and welcome!" gerado pelo IntelliJ |
| `markdownlint-cli2` no README | 18 erros; arquivo com BOM e CRLF |
| Adoção do sdd-kit (`adopt.sh --lang java`) | o template só conhece Maven (`mvn verify`, `cache: maven`, dependabot `maven`); adaptado para Gradle neste PR |

## 3. Observações por severidade

### Críticas

- **C1** Os testes não rodam em CI. O `ChromeDriver` abre com interface gráfica (`TestaPaginas.java:64`), e não existe workflow. O alvo é um site de terceiros, que pode mudar ou sair do ar a qualquer momento. → spec 001, decisão D1.
- **C2** O `gradlew` não é executável no git: `./gradlew` falha em Linux e macOS. → corrigido neste PR (pré-condição do CI).

### Altas

- **A1** `import org.openqa.selenium.devtools.v129...` sem uso (`TestaPaginas.java:4`): o pacote `v129` some quando o Selenium atualiza, e a compilação quebra. → spec 003.
- **A2** Seletores CSS absolutos, de 10 níveis (`Home.java:9`, `Home.java:12`): qualquer mudança de layout quebra os testes. → spec 001.
- **A3** Espera implícita de 10 ms e nenhuma espera explícita (`TestaPaginas.java:66`): os testes dependem da velocidade da página. → spec 001.

### Médias

- **M1** `driver.close()` no lugar de `driver.quit()` (`TestaPaginas.java:73`): o processo do driver fica vivo. → spec 001.
- **M2** `naoInsereNadaNaBarraDePesquisa()` envia `""`, que não faz nada (`Home.java:27`). O teste "campo vazio" não exercita nada além do clique. → spec 001.
- **M3** O nome `tituloEsperado` guarda uma URL, e a validação é só a URL. Para um portfólio de QA, falta verificar o conteúdo da página (título do produto). → spec 001.
- **M4** `Main.java` de exemplo do IntelliJ e `.idea/` versionado. → spec 003.
- **M5** O README pede JDK 11 e um ChromeDriver instalado à mão (desnecessário desde o Selenium Manager), e traz a URL de clone de exemplo `seu-usuario/seu-repositorio`. → spec 003.

### Baixas

- **B1** README com BOM, CRLF e erros de markdownlint. → corrigido neste PR (pré-condição do CI).
- **B2** Classes de teste no pacote padrão (sem `package`).

## 4. Pontos positivos (manter)

- Page Object simples e legível, com nomes em português que descrevem a intenção.
- `@DisplayName` descritivo nos testes.
- Gradle Wrapper versionado e JUnit 5 via BOM.

## 5. Avaliação do README

Bem estruturado e didático (descrição, estrutura, casos de teste). Desatualizado nos pré-requisitos e na execução, e sem um resultado verificável (badge de CI ou relatório).

## 6. Melhorias recomendadas (priorizadas)

1. **Fase 1 (P0) → `v0.1.0`:** testes headless e determinísticos em CI (D1), Page Object robusto (seletores estáveis, esperas explícitas, `quit`).
2. **Fase 2 (P1) → `v0.2.0`:** relatório dos testes no CI (D3), suíte contra o site real agendada, limpeza (`Main.java`, `.idea/`, import `v129`), README atualizado.
3. **Fase 3 (P2) → `v1.0.0`:** relatório publicado (GitHub Pages) e o projeto ligado ao qa-portfolio.

## 7. Decisões em aberto

| ID | Pergunta | Opções | Recomendação |
|---|---|---|---|
| D1 | Contra o que os testes de UI rodam no CI? | (a) em todo PR, contra uma **página local de fixture** (réplica mínima do cabeçalho de busca, servida pelo próprio teste); contra o **site real**, num job agendado diário e manual; (b) só o site real, em todo PR; (c) só a fixture | **(a)**: o PR fica determinístico, e o job diário ainda mostra os testes contra o site real |
| D2 | Versão do Java? | (a) toolchain Java 21 (LTS); (b) 17; (c) 11, como diz o README | **(a)**: já compila com 21; o Gradle 8.8 suporta |
| D3 | Relatório dos testes? | (a) relatório HTML do Gradle como artefato do CI; (b) Allure, publicado no GitHub Pages | **(a)** na Fase 2 e **(b)** na Fase 3: o relatório público vira vitrine no qa-portfolio |
| D4 | Manter Selenium + JUnit 5? | (a) sim, melhorando o Page Object; (b) migrar para Selenide ou Playwright | **(a)**: o objetivo do repositório é demonstrar Selenium com Page Object |
