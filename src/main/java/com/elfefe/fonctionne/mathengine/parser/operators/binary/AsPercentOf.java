package com.elfefe.fonctionne.mathengine.parser.operators.binary;

import com.elfefe.fonctionne.mathengine.parser.nodes.NodeConstant;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeFactory;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeNumber;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodePercent;
import com.elfefe.fonctionne.mathengine.parser.operators.BinaryOperator;

public class AsPercentOf extends BinaryOperator
{
	@Override
	public String[] getAliases()
	{
		return new String[] { "as%of", "aspercentof", "aspercentageof", "asa%of", "asapercentof",
				"asapercentageof" };
	}

	@Override
	public int getPrecedence()
	{
		return 3;
	}

	@Override
	public String toLongString()
	{
		return "as a percentage of";
	}

	@Override
	public NodeConstant toResult(NodeConstant arg1, NodeConstant arg2)
	{
		NodeNumber a = arg1.getTransformer().toNodeNumber();
		NodeNumber b = arg2.getTransformer().toNodeNumber();
		NodeNumber hundred = NodeFactory.createNodeNumberFrom(100.0);

		return new NodePercent(a.divide(b).multiply(hundred).getTransformer().toNodeNumber().doubleValue());
	}

	@Override
	public String toString()
	{
		return "as % of";
	}

}
