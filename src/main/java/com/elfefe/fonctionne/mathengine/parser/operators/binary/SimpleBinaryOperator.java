package com.elfefe.fonctionne.mathengine.parser.operators.binary;

import com.elfefe.fonctionne.mathengine.parser.nodes.NodeConstant;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeMatrix;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeNumber;
import com.elfefe.fonctionne.mathengine.parser.operators.BinaryOperator;

import java.util.function.BiFunction;

public abstract class SimpleBinaryOperator extends BinaryOperator {

    @Override
    public NodeConstant toResult(NodeConstant arg1, NodeConstant arg2) {
        BiFunction<NodeNumber, NodeNumber, NodeConstant> biFunc = getBiFunc();

        if (arg2 instanceof NodeNumber) {
            return arg1.applyUniFunc(elem -> biFunc.apply(elem.getTransformer().toNodeNumber(),
                    arg2.getTransformer().toNodeNumber()));
        } else if (arg2 instanceof NodeMatrix) {
            return arg1.getTransformer().toNodeMatrix()
                    .applyBiFunc(arg2, biFunc);
        } else {
            return arg1.getTransformer().toNodeVector()
                    .applyBiFunc(arg2, biFunc);
        }
    }

    protected abstract BiFunction<NodeNumber, NodeNumber, NodeConstant> getBiFunc();
}
