package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

public record AssignmentNode(AstNode target, AstNode value) implements AstNode {
}
