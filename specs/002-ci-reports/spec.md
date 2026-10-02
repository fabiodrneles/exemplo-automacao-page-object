# 002 — CI, relatório e suíte contra o site real

- **Prioridade:** P1
- **Status:** Approved — decisões respondidas pelo dono em 2026-10-02
- **Código afetado:** `.github/workflows/`, `build.gradle.kts`

## Requisitos funcionais

- **FR-1 (D3)** O CI MUST publicar o relatório HTML dos testes como artefato, inclusive quando falham.
- **FR-2 (D1)** Um workflow agendado (diário) e manual MUST rodar os testes contra o site real e falhar visivelmente se o site mudar.
- **FR-3 (D3)** Na Fase 3, o relatório SHOULD ser publicado no GitHub Pages (Allure).

## Critérios de aceite

- **AC-1** Dado um PR, quando o CI termina, então o artefato `test-report` existe.
- **AC-2** Dado o workflow `site-real`, quando roda, então usa `-DbaseUrl=https://automacao.testerglobal.com/`.

## Decisões

- D1 e D3 respondidas pelo dono em 2026-10-02 conforme as recomendações de [ANALYSIS.md §7](../ANALYSIS.md#7-decisões-respondidas-pelo-dono-em-2026-10-02).
