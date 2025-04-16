package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

public record BinaryOpNode(String operator, AstNode left, AstNode right) implements AstNode {
}
