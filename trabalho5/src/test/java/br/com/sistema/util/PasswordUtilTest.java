package br.com.sistema.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    void deveGerarHashDiferenteDaSenhaOriginal() {
        String senha = "123456";
        String hash = PasswordUtil.gerarHash(senha);

        assertNotEquals(senha, hash);
        assertTrue(hash.startsWith("$2a$") || hash.startsWith("$2b$"));
    }

    @Test
    void deveValidarSenhaCorreta() {
        String senha = "123456";
        String hash = PasswordUtil.gerarHash(senha);

        assertTrue(PasswordUtil.verificar(senha, hash));
    }

    @Test
    void deveRecusarSenhaIncorreta() {
        String hash = PasswordUtil.gerarHash("123456");

        assertFalse(PasswordUtil.verificar("senha-errada", hash));
    }
}
