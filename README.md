# automacaoNotasSigaaJava
Trabalho desenvolvido em aula, para criar uma automação usando thread, selenium e java.


<img width="1519" height="791" alt="Imagem mostrando que funciona" src="https://github.com/user-attachments/assets/dd649c34-26f1-48b8-9a38-b1611d34abf0" />
Automação Web do SIGAA com Selenium e TestNG

Este é um projeto acadêmico de automação web desenvolvido para a disciplina de Programação Orientada a Objetos II.

O objetivo é utilizar o Selenium WebDriver e o TestNG para automatizar o processo de login na versão mobile do portal SIGAA (IFRS), navegar até a página "Minhas Notas" e extrair uma nota específica da Unidade 1.

🚀 Funcionalidades

Acesso automatizado à versão mobile do SIGAA.

Realização de login com credenciais de usuário.

Navegação até a página "Minhas Notas" do portal do discente.

Extração de uma nota específica (Unidade 1 de POO II) da tabela de notas usando XPath.

Gerenciamento robusto do ciclo de vida do WebDriver através do padrão Driver Factory.

🛠️ Tecnologias Utilizadas

Java 17

Maven: Gerenciamento de dependências do projeto.

Selenium WebDriver: Biblioteca principal para automação de navegador.

TestNG: Framework de testes para gerenciar a execução (@Test, @BeforeSuite, etc.).

WebDriverManager: Para baixar e configurar automaticamente o chromedriver compatível.

📁 Estrutura do Projeto

O projeto segue a estrutura padrão do Maven, separando o código de utilidade do código de teste para seguir as boas práticas de desenvolvimento:

src/main/java/com/mycompany/projetosigaa:

DriverFactory.java: Classe central que gerencia o pool de WebDrivers usando ThreadLocal. É responsável por instanciar e fechar os navegadores para todo o ciclo de testes (@BeforeSuite, @AfterSuite).

WebDriverThread.java: Classe que efetivamente cria a instância do ChromeDriver com as opções desejadas (modo anônimo, maximizado, etc.).

src/test/java/com/mycompany/projetosigaa:

BaseTest.java: Classe base da qual todos os testes herdam. Ela usa @BeforeMethod para garantir que cada teste receba uma instância nova de driver e wait do DriverFactory.

SigaaTest.java: Classe principal do teste. Contém a lógica de negócio separada em métodos claros:

login(): Realiza o acesso.

navegarParaMinhasNotas(): Clica no link e espera a página de notas carregar.

extrairNotaDisciplina(): Localiza e retorna a nota da disciplina alvo.

▶️ Como Executar

Pré-requisitos

JDK 17 ou superior.

Apache Maven.

Google Chrome (o chromedriver será baixado automaticamente pelo WebDriverManager).

1. Configuração

Antes de executar, é essencial atualizar as credenciais de login.

Abra o ficheiro: src/test/java/com/mycompany/projetosigaa/SigaaTest.java

Localize o método @Test (testExtrairNotaPOO).

Altere as credenciais na chamada do método login():

@Test
public void testExtrairNotaPOO() {
    // ATENÇÃO: Altere "SEU_USUARIO" e "SUA_SENHA"
    login("SEU_USUARIO", "SUA_SENHA"); 

    // ... resto do teste ...
}


2. Execução

Você pode executar o teste diretamente pela sua IDE (clicando com o botão direito em SigaaTest.java > Run Test ou Test File) ou via terminal Maven.

Na raiz do projeto, execute o seguinte comando no seu terminal:

mvn test


O Maven irá compilar o projeto, baixar as dependências e executar o teste definido no TestNG. O navegador Chrome será aberto, executará os passos de automação e, ao final, o resultado da extração da nota será impresso no console.
