
import java.io.*;

public class User {
	String name;
	int noOfQuestions;

	static void pause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	static void display_mcqs(int n, MCQ mcqs[]) throws IOException {
		File file = new File("QuestionBank.txt");
		FileWriter z = new FileWriter(file);
		z.flush();
		for (int i = 0; i < n; i++) {
			System.out.println();
			z.write("\r\n");
			System.out.println("\t\tQuestion " + (i + 1) + ". " + mcqs[i].question);
			z.write("\t\tQuestion " + (i + 1) + ". " + mcqs[i].question);
			z.write("\r\n");
			for (int j = 0; j < 4; ++j) {
				System.out.println("\t\t" + (j + 1) + ". " + mcqs[i].options[j]);
				z.write("\t\t" + (j + 1) + ". " + mcqs[i].options[j]);
				z.write("\r\n");
			}
			System.out.println("\t\tcorrect answer: " + mcqs[i].answer);
			z.write("\t\tcorrect answer: " + mcqs[i].answer);
			z.write("\r\n");
			System.out.println(
					"\t\t----------------------------------------------------------------------------------------------------------- ");
			z.write("\t\t----------------------------------------------------------------------------------------------------------- ");
			z.write("\r\n");
			pause();

		}
		z.close();

	}

}
