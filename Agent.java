package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Uncomment these lines to access image processing.
//import java.awt.Image;
//import java.io.File;
//import javax.imageio.ImageIO;

/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this
 * file.
 * 
 * You may also create and submit new files in addition to modifying this file.
 * 
 * Make sure your file retains methods with the signatures: public Agent()
 * public char Solve(RavensProblem problem)
 * 
 * These methods will be necessary for the project's main method to run.
 * 
 */
public class Agent {
	/**
	 * The default constructor for your Agent. Make sure to execute any
	 * processing necessary before your Agent starts solving problems here.
	 * 
	 * Do not add any variables to this signature; they will not be used by
	 * main().
	 * 
	 */
	ArrayList<Calculator33> sourceTransformations;

	public Agent() {
		sourceTransformations = new ArrayList<Calculator33>();
	}

	/**
	 * The primary method for solving incoming Raven's Progressive Matrices. For
	 * each problem, your Agent's Solve() method will be called. At the
	 * conclusion of Solve(), your Agent should return a String representing its
	 * answer to the question: "1", "2", "3", "4", "5", or "6". These Strings
	 * are also the Names of the individual RavensFigures, obtained through
	 * RavensFigure.getName().
	 * 
	 * In addition to returning your answer at the end of the method, your Agent
	 * may also call problem.checkAnswer(String givenAnswer). The parameter
	 * passed to checkAnswer should be your Agent's current guess for the
	 * problem; checkAnswer will return the correct answer to the problem. This
	 * allows your Agent to check its answer. Note, however, that after your
	 * agent has called checkAnswer, it will *not* be able to change its answer.
	 * checkAnswer is used to allow your Agent to learn from its incorrect
	 * answers; however, your Agent cannot change the answer to a question it
	 * has already answered.
	 * 
	 * If your Agent calls checkAnswer during execution of Solve, the answer it
	 * returns will be ignored; otherwise, the answer returned at the end of
	 * Solve will be taken as your Agent's answer to this problem.
	 * 
	 * @param problem
	 *            the RavensProblem your agent should solve
	 * @return your Agent's answer to this problem
	 */
	public int Solve(RavensProblem problem) {

		RavensFigure D2 = problem.getFigures().get("D");
		if (D2 != null) {
			Long Start = System.nanoTime();
			Calculator33E calce = new Calculator33E();
			Calculator33 calc = new Calculator33();
			
			// Figure out transformation between A and B first
			RavensFigure A = problem.getFigures().get("A");
			RavensFigure B = problem.getFigures().get("B");
			RavensFigure C = problem.getFigures().get("C");
			RavensFigure D = problem.getFigures().get("D");
			RavensFigure E = problem.getFigures().get("E");
			RavensFigure F = problem.getFigures().get("F");
			RavensFigure G = problem.getFigures().get("G");
			RavensFigure H = problem.getFigures().get("H");

			HashMap<String, String> fileNameMap = new HashMap<String, String>();
			if (A != null)
				fileNameMap.put("A", A.getVisual());
			if (B != null)
				fileNameMap.put("B", B.getVisual());
			if (C != null)
				fileNameMap.put("C", C.getVisual());
			if (D != null)
				fileNameMap.put("D", D.getVisual());
			if (E != null)
				fileNameMap.put("E", E.getVisual());
			if (F != null)
				fileNameMap.put("F", F.getVisual());
			if (G != null)
				fileNameMap.put("G", G.getVisual());
			if (H != null)
				fileNameMap.put("H", H.getVisual());
			for (int i = 0; i < 8; i++) {
				RavensFigure fig = problem.getFigures().get(
						String.valueOf((i + 1)));
				if (fig != null)
					fileNameMap.put(String.valueOf((i + 1)), fig.getVisual());
			}
			
			int ans = 0;
			
			if(problem.getName().contains(" E"))
			{
				ans = calce.process(fileNameMap);
			}
			else
			{
				ans = calc.process(fileNameMap);
			}
			
			
			Long End = System.nanoTime();

			float duration = (End - Start) / 1000;
			System.out.println(problem.getName() + ": " + "Time Spent : "
					+ duration + " microseconds");
			return ans;
		} else // 2*2
		{
			Calculator calc = new Calculator();
			// Figure out transformation between A and B first
			RavensFigure A = problem.getFigures().get("A");
			RavensFigure B = problem.getFigures().get("B");
			RavensFigure C = problem.getFigures().get("C");

			HashMap<Integer, Integer> distancesHori = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> distancesVerti = new HashMap<Integer, Integer>();

			int ScoreA2B = 9999;
			int ScoreA2C = 9999;
			int ScoreToUse = 9999;
			Long Start = System.nanoTime();
			ScoreA2B = CalculateDistance(A, B);
			ScoreA2C = CalculateDistance(A, C);

			System.out.println(problem.getName() + ": " + "A -> B distance : "
					+ ScoreA2B);
			for (int i = 0; i < 6; i++) {
				RavensFigure fig = GetFigure(problem, String.valueOf(i + 1));
				distancesHori.put(i + 1,
						Math.abs(CalculateDistance(C, fig) - ScoreA2B));
				System.out.println(problem.getName() + ": " + "C -> "
						+ String.valueOf(i + 1) + ":  distance Hori: "
						+ distancesHori.get(i + 1));
				distancesVerti.put(i + 1,
						Math.abs(CalculateDistance(B, fig) - ScoreA2C));
				System.out.println(problem.getName() + ": " + "B -> "
						+ String.valueOf(i + 1) + ":  distance Verti: "
						+ distancesVerti.get(i + 1));
			}

			// Pick the closest one.
			int answerChose = 0;
			int answerScore = 9999;
			if (ScoreA2B < ScoreA2C) {
				System.out.println(problem.getName() + ": " + "Chose A->B");
				ScoreToUse = ScoreA2B;
				// pick from horiental.
				for (int i = 0; i < 6; i++) {
					if (distancesHori.get(i + 1) < answerScore) {
						answerChose = i + 1;
						answerScore = distancesHori.get(i + 1);
					}
				}

			} else {
				ScoreToUse = ScoreA2C;
				System.out.println(problem.getName() + ": " + "Chose A->C");
				// pick from distancesVerti.
				for (int i = 0; i < 6; i++) {
					if (distancesVerti.get(i + 1) < answerScore) {
						answerChose = i + 1;
						answerScore = distancesVerti.get(i + 1);
					}
				}
			}

			// Now, process special cases.

			// 1st case. I like pacman
			if (getObjectShapeFromObj(A.getObjects().get("a"))
					.equals("pac-man")
					|| (getObjectShapeFromObj(A.getObjects().get("a"))
							.contains("triangle") && A.getObjects().size() == 2)) {
				int diff = GetAngleFromFig(B) - GetAngleFromFig(A);

				for (int i = 0; i < 6; i++) {
					RavensFigure fig = GetFigure(problem, String.valueOf(i + 1));
					for (String key : fig.getObjects().keySet()) {
						int diff2 = GetAngleFromFig(fig) - GetAngleFromFig(C);
						if (diff2 < 0) {
							diff2 = diff2 + 180;
						}
						if (diff == diff2) {
							System.out.println(problem.getName() + ": "
									+ "used angle solution ");
							answerChose = i + 1;
						}
					}
				}
			}

			// 2nd case. what is octagon
			if (getObjectShapeFromObj(A.getObjects().get("a"))
					.equals("octagon")) {
				String fillA = GetFillFromFig(A);
				String fillB = GetFillFromFig(B);
				String fillC = GetFillFromFig(C);
				String fill = null;
				String shape = null;

				if (fillA.equals(fillB)) {
					fill = fillC;
				}

				if (fillA.equals(fillC)) {
					fill = fillB;
				}

				if (getObjectShapeFromObj(A.getObjects().get("a")).equals(
						getObjectShapeFromObj(B.getObjects().get("b")))) {
					shape = getObjectShapeFromObj(C.getObjects().get("c"));
				} else {
					shape = getObjectShapeFromObj(B.getObjects().get("b"));
				}

				for (int i = 0; i < 6; i++) {
					RavensFigure fig = GetFigure(problem, String.valueOf(i + 1));
					for (String key : fig.getObjects().keySet()) {
						String fillAns = GetFillFromFig(fig);
						String shapeAns = GetShapeFromFig(fig);
						if (fillAns.equals(fill)&&shapeAns.equals(shape)) {
							System.out.println(problem.getName() + ": "
									+ "used fill solution ");
							answerChose = i + 1;
						}
					}
				}
			}
			System.out.println(problem.getName() + ": " + "Answered : "
					+ answerChose);
			Long End = System.nanoTime();

			float duration = (End - Start) / 1000;
			System.out.println(problem.getName() + ": " + "Time Spent : "
					+ duration + " microseconds");
			return answerChose;
		}
	}

	private int GetAngleFromFig(RavensFigure b) {
		// TODO Auto-generated method stub
		for (String key : b.getObjects().keySet()) {
			for (String keya : b.getObjects().get(key).getAttributes().keySet()) {
				if (keya.equals("angle")) {
					return Integer.parseInt(b.getObjects().get(key)
							.getAttributes().get(keya));
				}

			}

		}
		return -1;
	}

	private String GetFillFromFig(RavensFigure b) {
		// TODO Auto-generated method stub
		for (String key : b.getObjects().keySet()) {
			for (String keya : b.getObjects().get(key).getAttributes().keySet()) {
				if (keya.equals("fill")) {
					return b.getObjects().get(key).getAttributes().get(keya);
				}

			}

		}
		return null;
	}
	private String GetShapeFromFig(RavensFigure b) {
		// TODO Auto-generated method stub
		for (String key : b.getObjects().keySet()) {
			for (String keya : b.getObjects().get(key).getAttributes().keySet()) {
				if (keya.equals("shape")) {
					return b.getObjects().get(key).getAttributes().get(keya);
				}

			}

		}
		return null;
	}
	private int CalculateDistance(RavensFigure c, RavensFigure fig) {
		// For now, we use object in order, and add missing objects as
		// difference of the No. of the attribute of the object we compare to.
		// and we ignore inside relation ship in this match.
		ArrayList<String> keyTarUsed = new ArrayList<String>();
		ArrayList<String> keySourUsed = new ArrayList<String>();
		Calculator calc = new Calculator();
		int scoreTotal = 0;
		for (String key : c.getObjects().keySet()) {
			// get 1st object of same shape.
			RavensObject tarChose = null;
			for (String keyTar : fig.getObjects().keySet()) {
				if (keyTarUsed.contains(keyTar))
					continue;

				if (getObjectShapeFromObj(fig.getObjects().get(keyTar)).equals(
						getObjectShapeFromObj(c.getObjects().get(key)))) {
					tarChose = fig.getObjects().get(keyTar);
					keyTarUsed.add(keyTar);
					break;
				}
			}
			if (tarChose != null) // found a same shape
			{
				keySourUsed.add(key);
				calc.setSource(c.getObjects().get(key));
				calc.setTarget(tarChose);
				ArrayList<RavImageAttribute> result = calc.getDifferences();
				int score = calc.GetWeight(result);
				scoreTotal = scoreTotal + score;
			}

		}
		// now loop the source object for not matched shape try to map with an
		// unused tar.

		for (String key : c.getObjects().keySet()) {
			if (keySourUsed.contains(key))
				continue;

			RavensObject tarChose = null;
			for (String keyTar : fig.getObjects().keySet()) {
				if (keyTarUsed.contains(keyTar))
					continue;
				tarChose = fig.getObjects().get(keyTar);
				keyTarUsed.add(keyTar);
				break;
			}

			if (tarChose != null) // found a shape in target objects but with
									// different shape.
			{
				keySourUsed.add(key);
				calc.setSource(c.getObjects().get(key));
				calc.setTarget(tarChose);
				ArrayList<RavImageAttribute> result = calc.getDifferences();
				int score = calc.GetWeight(result);
				scoreTotal = scoreTotal + score;
			}

		}

		// now loop the source object for not matched shape to see who remain
		// unmapped
		// give a fixed score.
		for (String key : c.getObjects().keySet()) {
			if (keySourUsed.contains(key))
				continue;
			int score = 4; // use a fixed value to represent an avrage score for
							// missing an object.
			scoreTotal = scoreTotal + score;

		}

		return scoreTotal;
	}

	private String getObjectShapeFromObj(RavensObject obj) {
		return obj.getAttributes().get("shape");
	}

	private RavensFigure GetFigure(RavensProblem problem, String figureName) {
		Map<String, RavensFigure> figures = problem.getFigures();

		for (String key : figures.keySet()) {
			if (key.equals(figureName))
				return figures.get(key);

		}
		return null;
	}

}
