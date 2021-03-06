package com.elfefe.fonctionne.mathengine.parser.operators;

import com.elfefe.fonctionne.mathengine.parser.EvaluationContext;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeConstant;

public abstract class UnaryOperator extends Operator {

    protected abstract NodeConstant toResult(NodeConstant arg1);

    public NodeConstant toResult(EvaluationContext context, NodeConstant arg1) {
        this.evaluationContext = context;
        return toResult(arg1);
    }
}
