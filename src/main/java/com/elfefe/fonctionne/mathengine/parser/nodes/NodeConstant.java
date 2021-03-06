package com.elfefe.fonctionne.mathengine.parser.nodes;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class NodeConstant extends Node implements Comparable<NodeConstant> {

    public abstract NodeConstant applyUniFunc(Function<NodeNumber, NodeConstant> func);

    public abstract NodeConstant applyBiFunc(NodeConstant b, BiFunction<NodeNumber, NodeNumber, NodeConstant> func);

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract String toString();

}
