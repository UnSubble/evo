package com.unsubble.evo.parser;

import com.unsubble.evo.ast.AstNode;

public record ParseResult(AstNode node, int nextIndex) {
}
