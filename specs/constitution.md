# Constituição do exemplo-automacao-page-object

Princípios que toda spec e todo PR devem respeitar. Mudá-los exige uma spec própria.

1. **Testado e verificável.** Todo critério de aceite tem teste automatizado que roda no CI; ninguém precisa da máquina do autor.
2. **Determinístico no PR.** O CI de PR não depende de sites de terceiros; testes contra sites reais rodam em job separado.
3. **Page Object.** Testes falam a língua do negócio; seletores e esperas ficam nas páginas.
4. **Sem esperas cegas.** Nada de `Thread.sleep` nem espera implícita como mecanismo de sincronização: esperas explícitas por condição.
5. **Seletores estáveis.** Preferir id, name, atributos de teste ou CSS curto; nunca caminhos absolutos do DOM.
6. **Documentação verificada.** Os comandos do README funcionam como estão escritos.
