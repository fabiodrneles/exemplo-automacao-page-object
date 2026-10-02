# Changelog

Todas as mudanças relevantes deste projeto. Formato [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/); versões [SemVer](https://semver.org/lang/pt-BR/).

## [Unreleased]

## [0.2.0] - 2026-10-02

### Adicionado

- Relatório HTML dos testes como artefato do CI (`test-report`), inclusive quando os testes falham.
- Workflow "Site real": os testes rodam todo dia e sob demanda contra `automacao.testerglobal.com`.
- Checagens no `make ci`: sem arquivos de IDE nem imports `devtools.vNNN`, e os comandos do README funcionam.

### Alterado

- Toolchain Java 21; o Selenium passa a dependência de teste.
- README reescrito: o que é testado, como os testes são organizados e como executar.

### Removido

- `.idea/` e o `Main.java` de exemplo do IntelliJ.

## [0.1.0] - 2026-10-02

Primeira versão com o processo SDD do [sdd-kit](https://github.com/fabiodrneles/sdd-kit). Specs em [`specs/`](specs/README.md).

### Adicionado

- CI com os testes de UI em todo PR (Chrome headless), contra uma réplica local da busca da loja.
- `-DbaseUrl` para rodar contra o site real, e `-Dheadless` e `-Dchrome.binary` para rodar localmente.
- Page Object `PaginaProduto`; o teste de busca valida o título do produto.

### Alterado

- Page Object `Home` com seletores curtos e esperas explícitas; o driver é encerrado com `quit()`.
- A busca vazia limpa o campo antes de pesquisar.

### Corrigido

- `gradlew` executável no Linux e no macOS.
