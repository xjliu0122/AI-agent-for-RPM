package ravensproject;

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

public class Calculator33 {
	private HashMap<String, HashMap<String, Integer>> pix = new HashMap<String, HashMap<String, Integer>>();
	private double pro = 0.05;

	public int process(HashMap<String, String> fileNameMap) {
		// Load all image first.
		for (String key : fileNameMap.keySet()) {
			String path = fileNameMap.get(key);
			File img = new File(path);
			HashMap<String, Integer> pixImg = ReadImage(path);
			pix.put(key, pixImg);
		}
		
		int answer;
		answer = pattern5();

		if (answer != 0) {
			return answer;
		}
		answer = pattern6();

		if (answer != 0) {
			return answer;
		}
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

		answer = pattern7();

		if (answer != 0) {
			return answer;
		}
		answer = pattern9();

		if (answer != 0) {
			return answer;
		}
		answer = pattern10();
		
		if (answer != 0) {
			return answer;
		}
		answer = pattern8();
		
		if (answer != 0) {
			return answer;
		}
		
		return -1;
	}

	// Pattern 1: all same.
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

			if (AssertEqual(numA, numB) && AssertEqual(numA, numC)
					&& AssertEqual(numD, numE) && AssertEqual(numD, numF)
					&& AssertEqual(numG, numH)) {
				qualifyH = true;
			}
			if (AssertEqual(numA, numD) && AssertEqual(numA, numG)
					&& AssertEqual(numB, numE) && AssertEqual(numB, numH)
					&& AssertEqual(numC, numF)) {
				qualifyV = true;
			}

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(numH, GetTotalNum(String.valueOf(i + 1)))) {
						return i + 1;
					}
				}
			}
			if (qualifyV) {
				for (int i = 0; i < 8; i++) {
					if (AssertEqual(numF, GetTotalNum(String.valueOf(i + 1)))) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{
		}
		return 0;
	}

	// Pattern 2: always increase.
	@SuppressWarnings("unused")
	private int pattern2() {
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

			if (numA < numB && numB < numC && numD < numE && numE < numF
					&& numG < numH) {
				qualifyH = true;
			}
			if (numA < numD && numD < numG && numB < numE && numE < numH
					&& numC < numF) {
				qualifyV = true;
			}
			if (!(qualifyH && qualifyV))
				return 0;
			float lowest = 99999;
			int lowAns = 0;

			for (int i = 0; i < 8; i++) {
				float num = GetTotalNum(String.valueOf(i + 1));
				if (AssertEqual(numB / numA, numE / numD)) // 倍数关系
				{
					if (Math.abs(numC / numA - num / numG) < lowest) {
						lowest = Math.abs(numC / numA - num / numG);
						lowAns = i + 1;
					}
				}
			}
			if (lowAns != 0)
				return lowAns;

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {

					float num = GetTotalNum(String.valueOf(i + 1));

					if (num > numH
							&& Math.abs((num - numF) - (numF - numE)) < lowest) {
						lowest = num / numH;
						lowAns = i + 1;
					}

				}
			}
			if (qualifyV) {
				for (int i = 0; i < 8; i++) {
					float num = GetTotalNum(String.valueOf(i + 1));
					if (num > numF
							&& Math.abs((num - numF) - (numH - numE)) < lowest) {
						lowest = num / numH;
						lowAns = i + 1;
					}
				}
			}
			return lowAns;
		} else // 2*2
		{
		
		}
		return 0;
	}

	// Pattern 3: total number.
	@SuppressWarnings("unused")
	private int pattern3() {
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

			if (AssertEqual(numA + numB + numC, numD + numE + numF)) {
				qualifyH = true;
			}
			if (AssertEqual(numA + numD + numG, numB + numE + numH)) {
				qualifyV = true;
			}
			
			float lowest = 99999;
			int lowAns = 0;

			
			if (qualifyH) {
				for (int i = 0; i < 8; i++) {

					float num = GetTotalNum(String.valueOf(i + 1));
					if (AssertEqual(numA + numB + numC, numG + numH + num)) {
						return i + 1;
					}
				}
			}
			if (qualifyV) {
				for (int i = 0; i < 8; i++) {
					float num = GetTotalNum(String.valueOf(i + 1));
					if (AssertEqual(numA + numD + numG, numC + numF + num)) {
						return i + 1;
					}
				}
			}
			return lowAns;
		} else // 2*2
		{

		}
		return 0;
	}

	// Pattern 4: add a same number.
	@SuppressWarnings("unused")
	private int pattern4() {
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

			if (AssertEqual(numB - numA, numC - numB)
					&& AssertEqual(numE - numD, numF - numE)) {
				qualifyH = true;
			}
			if (AssertEqual(numA - numD, numD - numG)
					&& AssertEqual(numB - numE, numE - numH)) {
				qualifyV = true;
			}
			if (!(qualifyH && qualifyV))
				return 0;
			float lowest = 99999;
			int lowAns = 0;

			if (qualifyH) {
				for (int i = 0; i < 8; i++) {

					float num = GetTotalNum(String.valueOf(i + 1));
					if (AssertEqual(num - numH, numH - numG)) {
						// add a postion compare.
						if ((AssertEqual(GetUpNum(String.valueOf(i + 1)),
								GetUpNum("H")))
								|| (AssertEqual(
										GetBottomNum(String.valueOf(i + 1)),
										GetBottomNum("H"))))
							return i + 1;
					}
				}
			}

			return lowAns;
		} else // 2*2
		{

		}
		return 0;
	}

	// Pattern 5: reflection.
	@SuppressWarnings("unused")
	private int pattern5() {
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
			if (AssertEqual(GetLeftNum("A"), GetRightNum("C"))
					&& AssertEqual(numB, numH)
					&& AssertEqual(GetLeftNum("D"), GetRightNum("F"))) {
				for (int i = 0; i < 8; i++) {

					if (AssertEqual(GetRightNum(String.valueOf(i + 1)),
							GetLeftNum("G"))) {
						return i + 1;
					}
				}
			}

		} else // 2*2
		{

		}
		return 0;
	}

	// Pattern 6: 平移.
	@SuppressWarnings("unused")
	private int pattern6() {
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
			if (AssertEqual(GetLeftNum("A"), GetRightNum("C"))
					&& (numD / numE < 1.2) // use 1.2 to get a rough same idea.
					&& AssertEqual(GetLeftNum("D"), GetRightNum("F"))) {
				for (int i = 0; i < 8; i++) {

					if (AssertEqual(pix.get("G").get("UPLEFT"),
							pix.get(String.valueOf(i + 1)).get("UPRIGHT")))

					{
						return i + 1;
					}
				}
			}

		} else // 2*2
		{

		}
		return 0;
	}

	// Pattern 7: like problem 8
	@SuppressWarnings("unused")
	private int pattern7() {
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
			if (AssertEqual(numB, numD) && AssertEqual(numC, numG)
					&& AssertEqual(numF, numH)) {
				for (int i = 0; i < 8; i++) {
					float expected = GetBottomNum("F") + GetRightNum("H");
					if (AssertEqual(expected,
							GetTotalNum(String.valueOf(i + 1))))

					{
						return i + 1;
					}
				}
			}

		} else // 2*2
		{

		}
		return 0;
	}
	
	// Pattern 8
		@SuppressWarnings("unused")
		private int pattern8() {
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
				
				
				if ((numH-numA)/numF>1.1 && (numC-numE)/numG >1.1) {	
				for (int i = 0; i < 8; i++) {
					
					if ( 
							(numD-GetTotalNum(String.valueOf(i + 1)))/numB >1.1)

						return i + 1;
				}
			}}

			else // 2*2
			{
			}
			return 0;
		}
 
	// Pattern 9:   平行差值一致 D-05
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

		
				if (AssertEqual(numA - numD, numB - numE)
						&& AssertEqual(numB - numE, numC - numF)) {
					qualifyV = true;
				}

				float lowest = 99999;
				int lowAns = 0;

				if (qualifyV) {
					for (int i = 0; i < 8; i++) {

						float num = GetTotalNum(String.valueOf(i + 1));
						if (AssertEqual(num - numF, numH - numE)) {
							lowAns = i + 1; 
						}
					}
				}

				return lowAns;
			} else // 2*2
			{

			}
			return 0;
		}
		// Pattern 10:   各倍数 D-12
		@SuppressWarnings("unused")
		private int pattern10() {
		
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

		
				if (AssertEqual(numA / 3 , numF / 4)
						&& AssertEqual(numB / 4, numD / 5)) {
					qualifyV = true;
				}

				float lowest = 99999;
				int lowAns = 0;

				if (qualifyV) {
					for (int i = 0; i < 8; i++) {

						float num = GetTotalNum(String.valueOf(i + 1));
						if (AssertEqual(numD / 5, num / 3)) {
							lowAns = i + 1; 
							break;
						}
					}
				}

				return lowAns;
			} else // 2*2
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

		} catch (Exception ex) {
		}
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
				int thisPixel = figureAImage.getRGB(i, j);
				if (thisPixel != -1) {
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

}
