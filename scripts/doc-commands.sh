#!/bin/sh
# 003 AC-2: runs every ```bash block of README.md. Commands that need the
# network or a display belong in ```text blocks.
set -eu
tmp=$(mktemp -d)
trap 'rm -rf "$tmp"' EXIT
blocks=$(awk '/^```bash$/{b=1; n++; next} /^```$/{b=0} b{print > ("'"$tmp"'/block" n ".sh")} END{print n+0}' README.md)
i=1
while [ "$i" -le "$blocks" ]; do
	echo "== README bloco bash $i"
	sh -eu "$tmp/block$i.sh"
	i=$((i + 1))
done
echo "doc-commands: $blocks bloco(s) ok"
