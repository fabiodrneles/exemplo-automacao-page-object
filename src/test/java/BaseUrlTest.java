import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseUrlTest {

    // 001 AC-4: com -DbaseUrl, os testes usam a URL informada (o site real)
    // e a fixture não é iniciada.
    @Test
    void baseUrlPropertyWins() throws Exception {
        String real = "https://automacao.testerglobal.com/";
        assertEquals(real, TestaPaginas.resolveBaseUrl(real, () -> {
            throw new AssertionError("a fixture não deveria ser iniciada");
        }));
    }

    @Test
    void fixtureIsTheDefault() throws Exception {
        assertEquals("http://127.0.0.1:1/", TestaPaginas.resolveBaseUrl(null, () -> "http://127.0.0.1:1/"));
        assertEquals("http://127.0.0.1:1/", TestaPaginas.resolveBaseUrl(" ", () -> "http://127.0.0.1:1/"));
    }
}
