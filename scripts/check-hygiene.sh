#!/bin/sh
# 003 AC-1: nada de .idea/, Main.java de exemplo nem imports devtools.vNNN.
set -eu
fail=0
if git ls-files | grep -E '^\.idea/|/Main\.java$'; then
	echo "arquivo de IDE ou Main.java de exemplo versionado" >&2
	fail=1
fi
if git ls-files '*.java' | xargs grep -n 'import org\.openqa\.selenium\.devtools\.v[0-9]' 2>/dev/null; then
	echo "import de devtools.vNNN: quebra na próxima versão do Selenium" >&2
	fail=1
fi
[ "$fail" -eq 0 ] && echo "check-hygiene: ok"
exit "$fail"
