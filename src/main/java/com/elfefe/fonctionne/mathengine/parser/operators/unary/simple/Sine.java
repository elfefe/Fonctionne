package com.elfefe.fonctionne.mathengine.parser.operators.unary.simple;

import com.elfefe.fonctionne.mathengine.parser.nodes.NodeConstant;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeFactory;
import com.elfefe.fonctionne.mathengine.parser.operators.unary.TrigOperator;

public class Sine extends TrigOperator {

    @Override
    public String[] getAliases() {
        return new String[]{"sin", "sine"};
    }

    @Override
    public NodeConstant getResult(double num) {
        double result = Math.sin(num);
        return NodeFactory.createNodeNumberFrom(result);
    }

    @Override
    public String toLongString() {
        return "sine";
    }

    @Override
    public String toString() {
        return "sin";
    }
}
