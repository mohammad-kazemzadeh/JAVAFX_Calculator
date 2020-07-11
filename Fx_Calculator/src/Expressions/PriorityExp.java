package Expressions;

//COPYRIGHT MohammadKazemzadeh
public class PriorityExp {

	public static double PriorityExp_(String Given) {
		char[] op = new char[100];
		int C_op = 0;
		String[] Numbs = new String[100];
		int cN = 0;
		int i = 0;

		if (Given.charAt(0) == '-' || Given.charAt(0) == '+') {
			i += 1;
		}
		boolean F_Not_Nega = true;
		if (Given.charAt(0) == '-') {
			F_Not_Nega = false;
		}
		while (i < Given.length()) {
			String Temp = "";
			if (Given.charAt(i) == '-' || Given.charAt(i) == '+' || Given.charAt(i) == '*' || Given.charAt(i) == '^'
					|| Given.charAt(i) == '/') {
				op[C_op++] = Given.charAt(i++);
			}
			while (i < Given.length() && Given.charAt(i) != '+' && Given.charAt(i) != '-' && Given.charAt(i) != '^'
					&& Given.charAt(i) != '/' && Given.charAt(i) != '*') {
				Temp += Given.charAt(i++);

			}
			Numbs[cN++] = Temp;
			Temp = "";
		}
		// if their is a '-' sign on the beginning of the Given String we dont add it to
		// char array
		// and pass the Value to the first number
		if (F_Not_Nega == false) {
			Numbs[0] = 0 - Double.parseDouble(Numbs[0]) + "";
		}
		while (cN >= 1 && C_op > 0) {
			int loo = 0;
			while ((op[loo] != '+' && op[loo] != '-') && loo < C_op) {
				loo++;
			}
			if (op[loo] == '+') {
				Numbs[loo] = (Double.parseDouble(Numbs[loo])) + (Double.parseDouble(Numbs[loo + 1])) + "";
				for (int y = loo + 1; y <= cN - 2; y += 1) {
					Numbs[y] = Numbs[y + 1];
				}
				cN--;
				for (int y = loo; y <= C_op - 2; y += 1) {
					op[y] = op[y + 1];
				}
				C_op--;
			} else if (op[loo] == '-') {
				Numbs[loo] = (Double.parseDouble(Numbs[loo])) - (Double.parseDouble(Numbs[loo + 1])) + "";
				for (int y = loo + 1; y <= cN - 2; y += 1) {
					Numbs[y] = Numbs[y + 1];
				}
				cN--;
				for (int y = loo; y <= C_op - 2; y += 1) {
					op[y] = op[y + 1];
				}
				C_op--;
			}
		}
		double res = Double.parseDouble(Numbs[0]);
		return res;
	}

	public static void main(String[] args) {
		String n = "438.001078-0.2021+2.3101003+4243.2323";
		System.out.println(PriorityExp_(n));

	}

}
