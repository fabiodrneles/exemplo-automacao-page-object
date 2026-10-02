# 003 — Higiene do projeto

- **Prioridade:** P1
- **Status:** Draft — aguarda a decisão D2 do dono
- **Código afetado:** `build.gradle.kts`, `src/`, `.idea/`, `README.md`

## Requisitos funcionais

- **FR-1 (D2)** O build MUST declarar o toolchain Java 21.
- **FR-2** O código MUST NOT importar pacotes `devtools.vNNN` sem uso; `Main.java` de exemplo e `.idea/` MUST sair do repositório.
- **FR-3** O README MUST refletir o jeito real de rodar (`./gradlew test`, Selenium Manager, sem ChromeDriver manual), com os comandos verificados no CI.

## Critérios de aceite

- **AC-1** Dado o repositório, quando o CI roda, então `git ls-files` não lista `.idea/` nem `Main.java`, e nenhum `.java` importa `devtools.v`.
- **AC-2** Dado o README, quando o CI roda, então os blocos `bash` funcionam.

## Decisões

- Pendente: D2 ([ANALYSIS.md §7](../ANALYSIS.md#7-decisões-em-aberto)).
