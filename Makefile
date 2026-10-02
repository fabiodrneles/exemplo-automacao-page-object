# Verificação local igual à do CI: rode `make ci` antes de todo push.
# Projeto Gradle: usa o Gradle Wrapper (./gradlew).
GRADLE := ./gradlew --no-daemon

.DEFAULT_GOAL := ci

.PHONY: ci
ci: hygiene ## Compila, roda os testes de UI (headless, contra a fixture local) e os comandos do README
	$(GRADLE) build
	sh scripts/doc-commands.sh

.PHONY: docs
docs: ## markdownlint (o CI também verifica links)
	npx --yes markdownlint-cli2@0.23.3

.PHONY: sdd-check
sdd-check: ## Rastreabilidade specs × testes × ROADMAP (falha se houver aviso)
	sh scripts/sdd-check.sh --strict

.PHONY: hygiene
hygiene: ## Sem .idea/, Main.java de exemplo nem imports devtools.vNNN (spec 003 AC-1)
	sh scripts/check-hygiene.sh

.PHONY: doc-commands
doc-commands: ## Os blocos bash do README funcionam (spec 003 AC-2)
	sh scripts/doc-commands.sh
