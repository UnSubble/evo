package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

public record IdentifierNode(String name) implements AstNode {
}