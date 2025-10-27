package com.mycompany.projetosigaa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class SigaaTest extends BaseTest {

    private static final String SIGAA_URL = "https://sig.ifrs.edu.br/sigaa/mobile/touch/login.jsf";

    @Test
    public void testExtrairNotaPOO() {
        // 1. Login
        login("usuario", "senha"); // ATENÇÃO: Remova suas credenciais antes de entregar

        // 2. Navegação
        navegarParaMinhasNotas();

        // 3. Extração da Nota
        String nota = extrairNotaDisciplina("PROGRAMAÇÃO ORIENTADA A OBJETOS II");

        // 4. Impressão do Resultado
        if (nota != null) {
            System.out.println("\n=============================================================");
            System.out.println("  A nota da Unidade 1 de PROGRAMAÇÃO ORIENTADA A OBJETOS II é: " + nota);
            System.out.println("=============================================================");
        } else {
            System.err.println("❌ Não foi possível encontrar a nota para a disciplina.");
        }
    }

    /**
     * Realiza o login no SIGAA.
     */
    private void login(String usuario, String senha) {
        driver.get(SIGAA_URL);
        System.out.println("➡ Acessando SIGAA...");

        try {
            WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='text']")));
            WebElement passInput = driver.findElement(By.cssSelector("input[type='password']"));

            userInput.sendKeys(usuario);
            passInput.sendKeys(senha);
            driver.findElement(By.cssSelector("input[type='submit'][value='Entrar']")).click();
            System.out.println("⏳ Realizando login...");

            // Aguarda o login ser bem-sucedido
            wait.until(ExpectedConditions.invisibilityOf(passInput));
            System.out.println("✅ Login realizado!");

        } catch (Exception e) {
            System.err.println("❌ Falha no login. Verifique usuário/senha ou mudanças na página.");
            throw new RuntimeException("Falha no login", e); // Interrompe o teste se o login falhar
        }
    }

    /**
     * Navega da página principal até a tela "Minhas Notas".
     */
    private void navegarParaMinhasNotas() {
        try {
            System.out.println("Acessando 'Minhas Notas'...");
            ((JavascriptExecutor) driver).executeScript(
                    "jsfcljs(document.getElementById('form-portal-discente')," +
                            "{'form-portal-discente:lnkMinhasNotas':'form-portal-discente:lnkMinhasNotas'},'_self');"
            );
            
            // Aguarda a tabela de notas carregar
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
            System.out.println("✅ Página 'Minhas Notas' carregada.");

        } catch (Exception e) {
            System.err.println("❌ Falha ao navegar para 'Minhas Notas'.");
            throw new RuntimeException("Falha na navegação", e);
        }
    }

    /**
     * Localiza a nota de uma disciplina específica na tabela.
     * @param nomeDisciplina O nome exato da disciplina.
     * @return A nota encontrada (String) ou null se não encontrar.
     */
    private String extrairNotaDisciplina(String nomeDisciplina) {
        System.out.println("\n🔎 Procurando a disciplina '" + nomeDisciplina + "'...");
        try {
            // Usa o XPath direto que funcionou da última vez
            String xpathDaNota = "//td[contains(normalize-space(.), '" + nomeDisciplina + "')]/following-sibling::td[1]";
            WebElement celulaNota = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathDaNota)));
            
            String nota = celulaNota.getText().trim();
            System.out.println("✅ Disciplina encontrada!");
            return nota;

        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            System.err.println("❌ Não foi possível encontrar a disciplina na página.");
            return null;
        }
    }
}
