package Expressions;

//COPYRIGHT MohammadKazemzadeh
public class PriorityExp2 {

	public static double PowerCalc(double Given, double Given_2) {
		double res = 1.0;
		boolean flag = false;
		if (Given_2 < 0) {
			flag = true;
			Given_2 *= -1;
		}
		for (int i = 1; i <= Given_2; i += 1) {
			res *= Given;
		}

		if (flag)
			res = 1 / res;

		return res;
	}

	public static int Check_availability(char[] oP, int C_op, char ch) {
		int Res = -1;
		for (int i = 0; i < C_op; i += 1) {
			if (oP[i] == ch) {
				Res = 1;
				break;
			}
		}
		return Res;
	}

	public static double PriorityExp2_(String Given) {
		char[] op = new char[100];
		int C_op = 0;
		String[] Numbs = new String[100];
		int cN = 0;
		int i = 0;
		int z = 0;
		int HandsUpPow = -1;
		int HandsUpMult = -1;
		int HandsUpDiv = -1;
		boolean yeahPow = false;
		boolean yeahMult = false;
		boolean yeahDiv = false;
		// checking for -+ | +- | Args while handling
		// don't know how to handle *- | *+ | /- | /+ |
		while (z < Given.length() - 1) {
			if (Given.charAt(z) == '-' || Given.charAt(z) == '+' || Given.charAt(z) == '*' || Given.charAt(z) == '^'
					|| Given.charAt(z) == '/')
				if (Given.charAt(z + 1) == '-' || Given.charAt(z + 1) == '+' || Given.charAt(z + 1) == '*'
						|| Given.charAt(z + 1) == '^' || Given.charAt(z + 1) == '/') {
					if (Given.charAt(z) == '-' && Given.charAt(z + 1) == '-')
						Given = Given.substring(0, z) + '+' + Given.substring(z + 2, Given.length());

					else if (Given.charAt(z) == '-' && Given.charAt(z + 1) == '+')
						Given = Given.substring(0, z) + '-' + Given.substring(z + 2, Given.length());

					else if (Given.charAt(z) == '+' && Given.charAt(z + 1) == '-')
						Given = Given.substring(0, z) + '-' + Given.substring(z + 2, Given.length());
					else if (Given.charAt(z) == '^' && Given.charAt(z + 1) == '-')
						HandsUpPow = z + 1;
					else if (Given.charAt(z) == '*' && Given.charAt(z + 1) == '-')
						HandsUpMult = z + 1;
					else if (Given.charAt(z) == '/' && Given.charAt(z + 1) == '-')
						HandsUpDiv = z + 1;
				}
			z++;

		}
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
				if (i == HandsUpDiv) {
					yeahPow = true;
					i++;
				} else if (i == HandsUpPow) {
					yeahPow = true;
					i++;
				} else if (i == HandsUpMult) {
					yeahMult = true;
					i++;
				} else if (i != HandsUpPow && i != HandsUpMult && i != HandsUpDiv)
					op[C_op++] = Given.charAt(i++);
			}
			while (i < Given.length() && Given.charAt(i) != '+' && Given.charAt(i) != '-' && Given.charAt(i) != '^'
					&& Given.charAt(i) != '/' && Given.charAt(i) != '*') {

				if (i == HandsUpDiv + 1 && yeahDiv == true) {
					Temp += '-';
					Temp += Given.charAt(i++);
				}
				if (i == HandsUpMult + 1 && yeahMult == true) {
					Temp += '-';
					Temp += Given.charAt(i++);
				}
				if (i == HandsUpPow + 1 && yeahPow == true) {
					Temp += '-';
					Temp += Given.charAt(i++);
				} else
					Temp += Given.charAt(i++);

			}
			if (Temp.length() != 0) {
				Numbs[cN++] = Temp;
			}
			Temp = "";
			// to eliminate the first negative sign from the char array and give pass it's
			// value
			// to the first number

		}
		if (F_Not_Nega == false) {
			Numbs[0] = 0 - Double.parseDouble(Numbs[0]) + "";

		}
		while (cN >= 1 && C_op > 0) {
			// by priority
			// power
			int loo = 0;
			while (Check_availability(op, C_op, '^') != -1) {
				while (op[loo] != '^' && loo < C_op) {
					loo++;
				}
				if (op[loo] == '^') {
					Numbs[loo] = PowerCalc(Double.parseDouble(Numbs[loo]), Double.parseDouble(Numbs[loo + 1])) + "";
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

			// multiplication and division
			while (Check_availability(op, C_op, '*') != -1 || Check_availability(op, C_op, '/') != -1) {
				loo = 0;
				while ((op[loo] != '*' && op[loo] != '/') && loo < C_op) {
					loo++;
				}
				if (op[loo] == '*') {
					Numbs[loo] = (Double.parseDouble(Numbs[loo])) * (Double.parseDouble(Numbs[loo + 1])) + "";
					for (int y = loo + 1; y <= cN - 2; y += 1) {
						Numbs[y] = Numbs[y + 1];
					}
					cN--;
					for (int y = loo; y <= C_op - 2; y += 1) {
						op[y] = op[y + 1];
					}
					C_op--;
				} else if (op[loo] == '/') {
					Numbs[loo] = (Double.parseDouble(Numbs[loo])) / (Double.parseDouble(Numbs[loo + 1])) + "";
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
			// summation and subtraction
			loo = 0;
			while (Check_availability(op, C_op, '+') != -1 || Check_availability(op, C_op, '-') != -1) {
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
		}
		double res = Double.parseDouble(Numbs[0]);
		return res;

	}

	public static void main(String[] args) {
		// 43+12.23234432^4-3.23*234.1/1000.32
		// -438.001078-0.2021+2.3101003^3+4243.2323/45.23
		// ((-(2^-2)*(4)))
		String Given = "2+5-3.2*-2";
		System.out.println(PriorityExp2_(Given));
		// System.out.println(PowerCalc(2, -2));

	}

}
