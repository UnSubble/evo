package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

public record BinaryOpNode(AstNode left, String operator, AstNode right) implements AstNode {
}
