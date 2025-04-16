package com.unsubble.evo.ast.node;

import com.unsubble.evo.ast.AstNode;
import com.unsubble.evo.common.Twin;

import java.util.Map;

public record TypeDefNode(String name, Map<String, Twin<Integer>> fields, int totalSize) implements AstNode {
}
