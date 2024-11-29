# Modelo de teste

## Teste realizado no site: https://automacao.testerglobal.com/

![image](https://github.com/user-attachments/assets/8d8aaf2e-b277-410c-9469-6286652886be)



# Projeto de Automação de Testes com Selenium

Este projeto foi desenvolvido para demonstrar habilidades em automação de testes de interface utilizando o framework **Selenium WebDriver** em Java. Ele simula cenários reais de testes em um site de e-commerce fictício, destacando boas práticas de escrita e organização de testes automatizados.

## 📋 Descrição do Projeto

O objetivo deste projeto é validar a funcionalidade de pesquisa de produtos em um site de e-commerce. Dois cenários de teste foram desenvolvidos:

1. **Busca com campo de pesquisa vazio:** Verifica se o sistema mantém um comportamento adequado ao realizar uma pesquisa sem fornecer um termo.
2. **Busca com produto específico:** Testa a pesquisa de um produto específico ("Camera") e valida se a página correta do produto é exibida.

## 🛠️ Tecnologias Utilizadas

- **Java 11**: Linguagem de programação principal.
- **Selenium WebDriver**: Para controle e automação do navegador.
- **JUnit 5**: Framework de testes para organizar e executar os casos de teste.
- **ChromeDriver**: Driver utilizado para automação no navegador Google Chrome.

## 📂 Estrutura do Projeto

### **Classe `Home`**
A classe `Home` encapsula as interações com os elementos da página inicial do site.

**Funções implementadas:**
- **`insereNomeCampoPesquisa(String nomeDoProdutoParaBuscar)`**  
  Insere o nome de um produto no campo de pesquisa.
- **`clicaBotaoPesquisar()`**  
  Clica no botão de pesquisa para executar a busca.
- **`naoInsereNadaNaBarraDePesquisa()`**  
  Simula a tentativa de pesquisa sem preencher o campo.

### **Classe `TestaPaginas`**
A classe de testes utiliza a biblioteca **JUnit 5** para estruturar e executar os casos de teste.

**Casos de Teste:**
1. **`pesquisarCampoVazio()`**
    - Ação: Clica no botão de pesquisa sem inserir texto.
    - Validação: Compara a URL gerada com a URL esperada para pesquisas vazias.
    - Resultado esperado: A URL deve conter os parâmetros corretos indicando que a pesquisa foi feita sem entrada.

2. **`pesquisarProduto()`**
    - Ação: Insere o termo "Camera" no campo de pesquisa e clica no botão.
    - Validação: Verifica se a URL redireciona para a página de detalhes do produto "Camera".
    - Resultado esperado: A página correta do produto deve ser exibida.

**Métodos de Configuração:**
- **`@BeforeAll setup()`**: Configura o ambiente de teste, incluindo a inicialização do ChromeDriver e definição de timeouts.
- **`@AfterAll tearDown()`**: Finaliza os testes e fecha o navegador.

## 🔍 Pré-requisitos

Antes de executar os testes, certifique-se de ter os seguintes itens configurados:

1. **Java Development Kit (JDK) 11** ou superior instalado.
2. Navegador **Google Chrome** atualizado.
3. **ChromeDriver** compatível com a versão do navegador.
4. Biblioteca do **Selenium WebDriver** e dependências do **JUnit 5** adicionadas ao projeto.

## 🚀 Como Executar os Testes

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Instale as dependências do projeto.
3. Configure o caminho do **ChromeDriver** no ambiente ou no projeto.
4. Execute os testes com um IDE como **IntelliJ IDEA** ou diretamente no terminal:
   ```bash
   ./gradlew test
   ```

## 🧪 Exemplos de Resultados

### **Teste: Campo de Pesquisa Vazio**
- **URL esperada:**  
  `https://automacao.testerglobal.com/?post_type=product&s=&product_cat=`
- **Resultado:** Sucesso.

### **Teste: Pesquisa de Produto "Camera"**
- **URL esperada:**  
  `https://automacao.testerglobal.com/product/camera/`
- **Resultado:** Sucesso.

## 🌟 Destaques do Projeto

- Organização clara com separação de responsabilidades entre classes de página (Page Objects) e classes de teste.
- Boas práticas no uso de JUnit para configuração e limpeza do ambiente de teste.
- Testes automatizados reproduzíveis e robustos para validação de funcionalidade.


