package com.elfefe.fonctionne.mathengine.parser.operators.unary;

import com.elfefe.fonctionne.mathengine.parser.nodes.Node;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeConstant;
import com.elfefe.fonctionne.mathengine.parser.nodes.NodeVector;

import java.util.Arrays;
import java.util.Collections;

public class Reverse extends VectorOperator
{
	@Override
	protected NodeConstant calculateResultFromVector(NodeVector arg1)
	{
		Node[] nodes = arg1.getValues().clone();
		Collections.reverse(Arrays.asList(nodes));
		return new NodeVector(nodes);
	}
	
	@Override
	protected void fillAcceptedArguments()
	{
		acceptedArgumentLengths.add(INFINITE_ARGUMENT_LENGTH);
	}

	@Override
	public String[] getAliases()
	{
		return new String[] { "rev", "reverse", "opposite", "backward",
				"backwards", "inverse", "invert" };
	}
	
	@Override
	protected String getExpectedArgumentsString()
	{
		return INFINITE_ARG_LENGTH_EXPECTED_USAGE;
	}

	@Override
	public int getPrecedence()
	{
		return 2;
	}

	@Override
	public String toLongString()
	{
		return toString();
	}

	@Override
	public String toString()
	{
		return "reverse";
	}

}
