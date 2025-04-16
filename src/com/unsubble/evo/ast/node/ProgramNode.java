package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

import java.util.List;

public record ProgramNode(List<AstNode> statements) implements AstNode {
}
