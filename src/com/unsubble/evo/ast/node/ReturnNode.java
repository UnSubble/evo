package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

public record ReturnNode(AstNode value) implements AstNode {
}