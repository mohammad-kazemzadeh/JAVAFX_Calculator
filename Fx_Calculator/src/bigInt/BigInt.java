package bigInt;

public class BigInt {

	

	
	private String Num;

	
	public BigInt() {
		Num = "0";
	}

	public BigInt(String Given) {
		this.Num = StrFix(Given);
	}

	public BigInt(int Given) {
		this.Num = Given + "";
	}

	// setters
	public void Set(String Given) {
		this.Num = StrFix(Given);
	}

	// Getter
	public String Get() {
		return this.Num;
	}

	// Operations
	@Override
	public String toString() {
		return this.Num;
	}

	public int StrToRevArr(int[] Arr) {
		int cArr = this.Num.length();
		for (int i = 0; i < cArr; i += 1) {
			Arr[i] = this.Num.charAt(cArr - i - 1) - '0';
		}
		return cArr;
	}

	public String StrFix(String Given) {
		String Fix = "";
		int StartPoint = 0;
		if (Given.charAt(0) == '-' || Given.charAt(0) == '+') {
			Fix = Given.charAt(0) + "";
			StartPoint = 1;
		}

		for (int i = StartPoint; i < Given.length(); i += 1) {
			if (Given.charAt(i) >= '0' && Given.charAt(i) <= '9')
				Fix += Given.charAt(i) + "";
		}

		if (Fix.length() > 0)
			return Fix;
		else
			return "0";
	}

	public void Sum(BigInt Given, BigInt Given_2) {
		int[] First = new int[100000000];
		int[] Second = new int[100000000];
		int[] Res = new int[100000000];
		int cFirst = Given.StrToRevArr(First);
		int cSec = Given_2.StrToRevArr(Second);
		int max = cFirst;
		if (cFirst > cSec)
			for (int x = cSec; x < cFirst; x++) {

				Second[x] = 0;
				max = cFirst;

			}
		if (cSec > cFirst)
			for (int x = cFirst; x < cSec; x++) {
				First[x] = 0;
				max = cSec;

			}
		int q = 0;/// naqli
		for (int x = 0; x < max; x++) {
			Res[x] = (First[x] + Second[x] + q) % 10;
			q = (First[x] + Second[x] + q) / 10;

		}

		int cRes = max;
		while (q > 0) {
			Res[cRes++] = q % 10;
			q /= 10;
		}

		String FinalRes = "";

		for (int x = cRes - 1; x >= 0; x--)
			FinalRes += Res[x];

		Set(FinalRes);

	}

	public void Minus(BigInt Given, BigInt Given_2) {
		String FinalRes = "";
		int[] First = new int[100000000];
		int[] Second = new int[100000000];
		int[] Res = new int[100000000];

		int cC = 0;
		int cA = Given.StrToRevArr(First);
		int cB = Given_2.StrToRevArr(Second);

		int CompRes = Given.BigCompare(Given_2);
		int max = cA;
		if (cA > cB)
			for (int x = cB; x < cA; x++) {

				Second[x] = 0;
				max = cA;

			}
		if (cB > cA)
			for (int x = cA; x < cB; x++) {
				First[x] = 0;
				max = cB;

			}
		if (CompRes > 0) {
			int quotient = 1;
			for (int i = 0; i < max; i += 1) {
				int temp = 0;
				if (First[i] - Second[i] < 0) {
					temp = Second[i] - First[i];
					Res[i] = 10 - temp;
					cC += 1;
					Second[i + 1] += quotient;
				} else if (First[i] - Second[i] >= 0) {
					Res[i] = First[i] - Second[i];
					cC += 1;
				}

			}

		} else if (CompRes == 0) {
			FinalRes = "0";
			this.Set(FinalRes);
			return;
		} else {
			FinalRes = "0";
			this.Set(FinalRes);
			return;
		}
		String SemFinalRes = "";
		for (int i = cC - 1; i >= 0; i -= 1) {
			SemFinalRes += Res[i];
		}
		int u = 0;
		try {
			while (SemFinalRes.charAt(u) == '0') {
				u++;
			}
		} catch (Exception e) { // just to avoid program from breaking }

			if (u != SemFinalRes.length()) {
				for (int x = u; x < SemFinalRes.length(); x++)
					FinalRes += SemFinalRes.charAt(x);
			} else
				FinalRes = "0";

		}
		if (u != SemFinalRes.length()) {
			for (int x = u; x < SemFinalRes.length(); x++)
				FinalRes += SemFinalRes.charAt(x);
		} else
			FinalRes = "0";

		this.Set(FinalRes);

	}

	public void Div(BigInt Given_2) {

	}

	private String ElOfLeadingZeros(String SemFinalRes) {
		String FinalRes = "";
		int u = 0;
		try {
			while (SemFinalRes.charAt(u) == '0') {
				u++;
			}
		} catch (Exception e) { // just to avoid program from breaking }

			if (u != SemFinalRes.length()) {
				for (int x = u; x < SemFinalRes.length(); x++)
					FinalRes += SemFinalRes.charAt(x);
			} else
				FinalRes = "0";

		}
		if (u != SemFinalRes.length()) {
			for (int x = u; x < SemFinalRes.length(); x++)
				FinalRes += SemFinalRes.charAt(x);
		} else
			FinalRes = "0";
		return FinalRes;
	}

	public void Multiply(BigInt Given, BigInt Given_2) {
		String Result = "";
		int[] First = new int[100000000];
		int[] Second = new int[100000000];
		int[] Res = new int[100000000];

		int cRes = 0;
		int cA = Given.StrToRevArr(First);
		int cB = Given_2.StrToRevArr(Second);
		for (int y = 0; y < cB; y++) {
			cRes = y;
			int p = 0; // naqli +
			int q = 0; // naqli *
			for (int x = 0; x < cA; x++) {

				int t = (Second[y] * First[x] + q);

				int t2 = (t % 10 + Res[cRes] + p);
				Res[cRes++] = t2 % 10;
				q = t / 10;

				p = t2 / 10;
			}

			q = q + p;
			if (q > 0) {
				Res[cRes] += q;

			}

		}

		for (int i = cRes; i >= 0; i -= 1) {
			Result += Res[i];

		}
		Result = ElOfLeadingZeros(Result);
		this.Set(Result);
	}

	public void Factorial(long Given) {
		String result = "";
		int[] arr = new int[100000];
		int cArr = 1;
		arr[0] = 1;
		for (int i = 1; i <= Given; i += 1) {
			int car = 0;
			for (int Index = 0; Index < cArr; Index += 1) {
				int Temp = arr[Index] * i + car;
				arr[Index] = Temp % 10;
				car = Temp / 10;
			}
			while (car > 0) {
				arr[cArr++] = car % 10;
				car /= 10;
			}

		}
		for (int i = cArr - 1; i >= 0; i -= 1) {
			result += arr[i];
		}
		this.Set(result);

	}

	public int BigCompare(BigInt Given_2) {
		String NGiven = "";
		String NGiven_2 = "";
		int res = 0;

		// looking for leading zeros
		int u = 0;
		try {

			while (this.Num.charAt(u) == '0') {
				u++;
			}
		} catch (Exception e) {
			u--;
		}

		for (int x = u; x < this.Num.length(); x++)
			NGiven += this.Num.charAt(x);

		int u2 = 0;
		try {
			while (Given_2.Get().charAt(u2) == '0') {
				u2++;
			}
		} catch (Exception e) {
			u2--;
		}

		for (int x = u2; x < Given_2.Get().length(); x++)
			NGiven_2 += Given_2.Get().charAt(x);

		if ((this.Num.charAt(0) >= '0' && this.Num.charAt(0) <= '9') && Given_2.Get().charAt(0) == '-') {
			res = 1;
		} else if (NGiven.length() > NGiven_2.length()) {
			res = 1;
		}

		else if (NGiven.length() == NGiven_2.length()) {
			boolean Are_same = true;
			for (int i = 0; i < NGiven.length(); i += 1) {
				if (NGiven.charAt(i) - '0' != NGiven_2.charAt(i) - '0') {
					Are_same = false;
				}
			}
			if (Are_same) {
				res = 0;
			} else if (NGiven.charAt(0) - '0' < NGiven_2.charAt(0) - '0') {
				res = -1;
			} else if (NGiven.charAt(0) - '0' == NGiven_2.charAt(0) - '0') {
				for (int i = 1; i < NGiven.length(); i += 1) {
					if (NGiven.charAt(i) - '0' > NGiven_2.charAt(i) - '0') {
						res = 1;
						break;
					}

					else if (NGiven.charAt(i) - '0' == NGiven_2.charAt(i) - '0') {
						continue;
					} else if (NGiven.charAt(i) - '0' < NGiven_2.charAt(i) - '0') {
						res = -1;
						break;
					}
				}
			} else if (NGiven.charAt(0) - '0' > NGiven_2.charAt(0) - '0') {
				res = 1;
			}
		} else if (NGiven.length() < NGiven_2.length()) {
			res = -1;
		}
		return res;

	}
}
