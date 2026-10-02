# Verificação local igual à do CI: rode `make ci` antes de todo push.
# Projeto Gradle: usa o Gradle Wrapper (./gradlew).
GRADLE := ./gradlew --no-daemon

.DEFAULT_GOAL := ci

.PHONY: ci
ci: ## Compila o código e os testes (os testes de UI entram com a decisão D1)
	$(GRADLE) build -x test

.PHONY: docs
docs: ## markdownlint (o CI também verifica links)
	npx --yes markdownlint-cli2@0.23.3

.PHONY: sdd-check
sdd-check: ## Rastreabilidade specs × testes × ROADMAP (falha se houver aviso)
	sh scripts/sdd-check.sh --strict
