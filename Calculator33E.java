package ravensproject;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator33E {
	private HashMap<String, HashMap<String, Integer>> pix = new HashMap<String, HashMap<String, Integer>>();
	private double pro = 0.09;

	public int process(HashMap<String, String> fileNameMap) {
		// Load all image first.
		for (String key : fileNameMap.keySet()) {
			String path = fileNameMap.get(key);
			File img = new File(path);
			HashMap<String, Integer> pixImg = ReadImage(path);
			pix.put(key, pixImg);
		}

		int answer;
		answer = pattern1();

		if (answer != 0) {
			return answer;
		}
		answer = pattern2();

		if (answer != 0) {
			return answer;
		}

		answer = pattern3();

		if (answer != 0) {
			return answer;
		}

		answer = pattern4();

		if (answer != 0) {
			return answer;
		}

		answer = pattern5();

		if (answer != 0) {
			return answer;
		}

		answer = pattern6();

		if (answer != 0) {
			return answer;
		}
		answer = pattern7();

		if (answer != 0) {
			return answer;
		}
		answer = pattern8();
		if (answer != 0) {
			return answer;
		}
		answer = pattern9();

		if (answer != 0) {
			return answer;
		}
		return -1;
	}

	// Pattern 1: A+B = C
	@SuppressWarnings("unused")
	private int pattern1() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");

			if (AssertEqual(numA + numB, numC)
					&& AssertEqual(numD + numE, numF)) {
				qualifyH = true;
			}

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(numH + numG,
							GetTotalNum(String.valueOf(i + 1)))) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern 2: E-03 E-10
	@SuppressWarnings("unused")
	private int pattern2() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");

			if (AssertEqual(numA, numB) && AssertEqual(numA, numD)
					&& AssertEqual(numA, numE) && AssertEqual(numC, numF)
					&& AssertEqual(numG - numD, numH - numE)

			) {
				qualifyH = true;
			}
			int low = 999999;
			int lowans = 0;
			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					int diff = Math.abs(GetTotalNum(String.valueOf(i + 1))
							- numF - (numH - numE));
					if (diff < low) {
						low = diff;
						lowans = i + 1;
					}
				}
			}
			return lowans;

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern3 : E-04 (A-b = c) & non refelction
	@SuppressWarnings("unused")
	private int pattern3() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");

			if (AssertEqual(numA - numB, numC)
					&& AssertEqual(numD - numE, numF)

			) {
				qualifyH = true;
			}

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(numG - numH,
							GetTotalNum(String.valueOf(i + 1)))
							&& (!AssertEqual(GetLeftNum(String.valueOf(i + 1)),
									GetRightNum(String.valueOf(i + 1))))

					) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern4 : E-05 (A-b = c) & refelction
	@SuppressWarnings("unused")
	private int pattern4() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");

			if (AssertEqual(numA - numB, numC)
					&& AssertEqual(numD - numE, numF)

			) {
				qualifyH = true;
			}

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(numG - numH,
							GetTotalNum(String.valueOf(i + 1)))
							&& (AssertEqual(GetLeftNum(String.valueOf(i + 1)),
									GetRightNum(String.valueOf(i + 1))))

					) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern5 : E-06
	@SuppressWarnings("unused")
	private int pattern5() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");
			int numCent = 0;
			if (AssertEqual((numC - (numB - numA)), (numF - (numE - numD)))) {
				qualifyH = true;
				numCent = numC - (numB - numA);

			}

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(numC - (numB - numA),
							GetTotalNum(String.valueOf(i + 1)) - (numH - numG))

					) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern6 : E-07
	@SuppressWarnings("unused")
	private int pattern6() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");
			int numCent = 0;
			numCent = (numC + numB - numA) / 2;

			if (AssertEqual(numCent, (numF + numE - numD) / 2)) {
				qualifyH = true;

			}

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(numE + numF - 2 * numCent,
							GetTotalNum(String.valueOf(i + 1)))

					) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern7 : E-09
	@SuppressWarnings("unused")
	private int pattern7() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");

			if (AssertEqual(this.GetUpNum("A"), GetUpNum("C"))
					&& AssertEqual(this.GetUpNum("D"), GetUpNum("F"))
					&& AssertEqual(this.GetUpNum("B"), GetUpNum("H"))
					&& AssertEqual(this.GetUpNum("A"), GetUpNum("G"))
					&& AssertEqual(this.GetBottomNum("B"), GetBottomNum("C"))
					&& AssertEqual(this.GetBottomNum("D"), GetBottomNum("G"))
					&& AssertEqual(this.GetBottomNum("E"), GetBottomNum("F"))
					&& AssertEqual(this.GetBottomNum("E"), GetBottomNum("H"))) {
				qualifyH = true;

			}

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(this.GetUpNum("A"),
							GetUpNum(String.valueOf(i + 1)))
							&& AssertEqual(
									this.GetBottomNum(String.valueOf(i + 1)),
									GetBottomNum("F"))) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern8 : E-11 ( A+B ) / 2 > C
	@SuppressWarnings("unused")
	private int pattern8() {
		boolean qualifyH = false;
		boolean qualifyV = false;
		if (pix.get("D") != null) // 3*3
		{
			int numA = GetTotalNum("A");
			int numB = GetTotalNum("B");
			int numC = GetTotalNum("C");
			int numD = GetTotalNum("D");
			int numE = GetTotalNum("E");
			int numF = GetTotalNum("F");
			int numG = GetTotalNum("G");
			int numH = GetTotalNum("H");

			for (int i = 0; i < 8; i++) {
				if ((GetTotalNum("F") + GetTotalNum("H")) / 2 > GetTotalNum(String
						.valueOf(i + 1)))

					return i + 1;
			}
		}

		else // 2*2
		{
		}
		return 0;
	}
	// Pattern 10
			@SuppressWarnings("unused")
			private int pattern9() {
				boolean qualifyH = false;
				boolean qualifyV = false;
				if (pix.get("D") != null) // 3*3
				{
					float numA = GetTotalNum("A");
					float numB = GetTotalNum("B");
					float numC = GetTotalNum("C");
					float numD = GetTotalNum("D");
					float numE = GetTotalNum("E");
					float numF = GetTotalNum("F");
					float numG = GetTotalNum("G");
					float numH = GetTotalNum("H");
					float row1 = numA+numB-numC;
					float row2 = numD+numE-numF;
					float c1 = numA+numD-numG;
					float c2 = numB+numE-numH;
					
					
					if (AssertEqual(row1, row2)
							&& AssertEqual(c1, c2) && AssertEqual(row1, c1) && numA+numB>numC
							&& numA+numD>numG && AssertEqual(numC-numB,numF-numE)) {	
					for (int i = 0; i < 8; i++) {
						
						if (
								numG+numH-GetTotalNum(String.valueOf(i + 1))>row1 && numC+numF-GetTotalNum(String.valueOf(i + 1))>c1)

							return i + 1;
					}
				}}

				else // 2*2
				{
				}
				return 0;
			}			

	boolean AssertEqual(float a, float b) {
		if (a == b)
			return true;
		float f = a / b;

		return (Math.abs(f - 1) < pro);
	}

	int GetTotalNum(String id) {
		return pix.get(id).get("UPLEFT") + pix.get(id).get("UPRIGHT")
				+ pix.get(id).get("BOTTOMLEFT")
				+ pix.get(id).get("BOTTOMRIGHT");

	}

	int GetUPLEFT(String id) {
		return pix.get(id).get("UPLEFT");

	}

	int GetUPRIGHT(String id) {
		return pix.get(id).get("UPRIGHT");

	}

	int GetBOTTOMLEFT(String id) {
		return pix.get(id).get("BOTTOMLEFT");

	}

	int GetBOTTOMRIGHT(String id) {
		return pix.get(id).get("BOTTOMRIGHT");

	}

	int GetUpNum(String id) {
		return pix.get(id).get("UPLEFT") + pix.get(id).get("UPRIGHT");

	}

	int GetBottomNum(String id) {
		return pix.get(id).get("BOTTOMLEFT") + pix.get(id).get("BOTTOMRIGHT");

	}

	int GetLeftNum(String id) {
		return pix.get(id).get("UPLEFT") + pix.get(id).get("BOTTOMLEFT");

	}

	int GetRightNum(String id) {
		return pix.get(id).get("UPRIGHT") + pix.get(id).get("BOTTOMRIGHT");

	}

	private HashMap<String, Integer> ReadImage(String path) {
		BufferedImage figureAImage = null;
		try {
			figureAImage = ImageIO.read(new File(path));
			figureAImage = erosion(figureAImage);
		} catch (Exception ex) {
		}

		int red;
		int newPixel;
		int threshold = 50;

		int upleft = 0;
		int upRight = 0;
		int bottomLeft = 0;
		int bottomRight = 0;

		int width = figureAImage.getWidth();
		int height = figureAImage.getHeight();
		int halfWidth = width / 2;
		int halfHeight = height / 2;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				int pix = figureAImage.getRGB(i, j);

				if (pix != -1) {
					if ((i <= halfWidth) && (j <= halfHeight)) {
						upleft++;
					}
					if ((i > halfWidth) && (j <= halfHeight)) {
						upRight++;
					}
					if ((i <= halfWidth) && (j > halfHeight)) {
						bottomLeft++;
					}
					if ((i > halfWidth) && (j > halfHeight)) {
						bottomRight++;
					}

				}
			}
		}

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("UPLEFT", upleft);
		map.put("UPRIGHT", upRight);
		map.put("BOTTOMLEFT", bottomLeft);
		map.put("BOTTOMRIGHT", bottomRight);
		return map;
	}

	public BufferedImage erosion(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage erosionImage = new BufferedImage(w, h,
				BufferedImage.TYPE_BYTE_BINARY);
		for (int X = 0; X < w; X++)
			for (int Y = 0; Y < h; Y++) {

				int value = -16777216;

				int pixel = image.getRGB(X, Y);
				if ((pixel & 0x00FFFFFF) != 0)
					value = -1;

				erosionImage.setRGB(X, Y, value);

			}
		return erosionImage;
	}

}
