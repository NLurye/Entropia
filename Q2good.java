package test;

import java.util.*;

public class Q2good {
	List<Double> X,Y;
	HashMap<Double,HashMap<Double,Integer>>p_map = new HashMap<>();

	public Q2good(List<Double> X, List<Double> Y) {
		this.X=X;
		this.Y=Y;
	}

	public double conditionalEntropy() {
		double sum=0;
		for(int i=0;i<X.size();i++) {
			double x = X.get(i);
			double y = Y.get(i);
			if (p_map.containsKey(y))
				if (p_map.get(y).containsKey(x))
					p_map.get(y).put(x, p_map.get(y).get(x + 1)); //y exist, x exist
				else p_map.get(y).put(x, 1);//y exist, new x
			else
				p_map.put(y, new HashMap<Double,Integer>() {{
					put(x, 1);
				}});//new y new x
		}
//		sum+=Pxy(x,Y.get(j))*Math.log10(Pxy(x,Y.get(j))/Py(Y.get(j)))/Math.log10(2);
		for(HashMap<Double,Integer> x_map: p_map.values()) {//for each unique y
			double sum_x = 0;
			for(double x_count: x_map.values()) { //for each unique x
				sum_x += x_count*Math.log10(x_count)/Math.log10(2);
			}
			sum+=sum_x/(x_map.size()/Y.size());

		}
		return -sum;
	}
}


