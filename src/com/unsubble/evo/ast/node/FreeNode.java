package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;

public record FreeNode(AstNode target) implements AstNode {
}