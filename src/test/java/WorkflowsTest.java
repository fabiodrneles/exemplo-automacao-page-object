import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkflowsTest {

    private static String read(String path) throws Exception {
        return Files.readString(Path.of(path));
    }

    // 002 AC-1: o CI publica o artefato test-report mesmo quando os testes falham.
    @Test
    void ciUploadsTheTestReport() throws Exception {
        String ci = read(".github/workflows/ci.yml");
        assertTrue(ci.contains("uses: actions/upload-artifact@"), "ci.yml sem upload-artifact");
        assertTrue(ci.contains("name: test-report\n"), "ci.yml sem o artefato test-report");
        assertTrue(ci.contains("if: always()"), "o relatório precisa sair também quando os testes falham");
    }

    // 002 AC-2: o job agendado roda contra o site real.
    @Test
    void realSiteWorkflowUsesTheRealBaseUrl() throws Exception {
        String wf = read(".github/workflows/site-real.yml");
        assertTrue(wf.contains("-DbaseUrl=https://automacao.testerglobal.com/"), "site-real.yml sem -DbaseUrl do site real");
        assertTrue(wf.contains("schedule:") && wf.contains("workflow_dispatch:"), "site-real.yml precisa ser agendado e manual");
    }
}
