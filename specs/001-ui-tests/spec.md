# 001 — Testes de UI determinísticos

- **Prioridade:** P0
- **Status:** Draft — aguarda as decisões D1, D2 e D4 do dono
- **Código afetado:** `src/test/java`, `build.gradle.kts`
- **Resolve:** C1, A2, A3, M1, M2, M3

## Requisitos funcionais

- **FR-1 (D1)** Os testes MUST rodar com o Chrome headless quando `CI` estiver definido ou `-Dheadless=true`.
- **FR-2 (D1)** A URL base MUST ser configurável (`-DbaseUrl=...`). O padrão do PR é uma página local de fixture, servida pelo próprio teste, com o mesmo formulário de busca do site.
- **FR-3** O Page Object MUST usar seletores curtos e estáveis e esperas explícitas (`WebDriverWait`), sem espera implícita.
- **FR-4** O driver MUST ser encerrado com `quit()` depois dos testes, mesmo quando um teste falha.
- **FR-5** A busca vazia MUST limpar o campo antes de pesquisar; a busca por produto MUST validar a URL e o título do produto na página.

## Critérios de aceite

- **AC-1** Dado o CI sem display, quando `make ci` roda, então os dois testes passam contra a fixture local.
- **AC-2** Dada a fixture, quando a busca vazia roda, então a URL tem `s=` vazio e `post_type=product`.
- **AC-3** Dada a fixture, quando a busca por "Camera" roda, então a página do produto abre e o título é "Camera".
- **AC-4** Dado `-DbaseUrl=https://automacao.testerglobal.com/`, quando os testes rodam, então usam o site real.

## Decisões

- Pendentes: D1, D2 e D4 ([ANALYSIS.md §7](../ANALYSIS.md#7-decisões-em-aberto)).
