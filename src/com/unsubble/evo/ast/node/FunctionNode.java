package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

import java.util.List;

public record FunctionNode(String name, List<String> parameters, List<AstNode> body) implements AstNode {
}
