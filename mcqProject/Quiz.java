import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import UserDefinedExceptions.*;

public class Quiz {
	static Scanner sc = new Scanner(System.in);
	static MCQ mcqs[] = new MCQ[20]; // array to store info about max 20 mcqs
	static int n = 0; // number of mcqs teacher wants
	static Vector<Student> v = new Vector<Student>();
	static Vector<Query> qQ = new Vector<Query>();
	static int noOfQuesPerStudent;
	static int students = 0;

	public static void main(String[] args) throws Exception {
		File f = new File("Querys.txt");
		FileWriter z = new FileWriter(f);
		noOfQuesPerStudent = 0;
		boolean flagquery = false;
		for (int i = 0; i < 20; i++) {
			mcqs[i] = new MCQ();
		}
		int choice = 0, t1 = 0;
		System.out.println("\t\t----------------------------------------------------------");
		System.out.println("\t\t**********************************************************");
		System.out.println("\t\t----------------------------------------------------------");
		System.out.println("\t\t\t\tWelcome Welcome Welcome !");
		System.out.println("\t\t----------------------------------------------------------");
		System.out.println("\n\n\t\t\t\t****  MCQ Quiz System ****\n\n");
		System.out.println("\t\t----------------------------------------------------------");
		System.out.println("\t\t**********************************************************");
		System.out.println("\t\t----------------------------------------------------------\n\n");

		do {
			System.out.println("\t\t----------------------------------------------------------");
			System.out.println("\t\t**********************************************************");
			System.out.println("\t\t----------------------------------------------------------");
			System.out.print("\t\t1.Student \n\t\t2.Teacher \n\t\t3.Exit \n\t\tchoice :  ");
			int except = 0;

			while (except != 1) {
				try {
					try {
						choice = sc.nextInt();
						System.out.println("\n");
						except = 1;
						except = 1;
						break;
					} catch (Exception e) {

						throw new NotIntegerException("enter choice which is 1 or 2 or 3");

					}
				} catch (NotIntegerException e) {

					System.out.println(e);
					System.out.print("\t\tPlease enter your choice again :");
					String num = sc.next();

				}
			}
			int t = 0;
			System.out.println("\t\t----------------------------------------------------------");
			System.out.println("\t\t**********************************************************");
			System.out.println("\t\t----------------------------------------------------------");

			// if teacher then ask to make questions
			switch (choice) {
				case 1:
					if (noQuestion()) {
						System.out.println("\t\tTell your teacher to make questions");
					} else {
						String name1 = "";
						t = 0;
						while (t != 1) {
							try {
								System.out.print("\t\tPlease enter your name :");
								name1 = sc.next();
								for (int i = 0; i < name1.length(); i++) {
									if (AlphanumericException.isSpecialcharacter(name1.charAt(i)))
										throw new AlphanumericException(
												"Name should not contain any special character \n\t\t-----------------------------------------\n");
								}
								t = 1;
							} catch (AlphanumericException e) {
								System.out.println(e);
							}
						}

						System.out.println("\t\tWelcome " + name1 + " Your test will start in 5 seconds");
						testStudent(noOfQuesPerStudent);
						int marks = evaluate();

						Student s = new Student(name1, marks);
						v = Student.createStudent(name1, marks, v, s);
						students++;
						System.out.println("\t\tCongrats you got " + marks + "/" + noOfQuesPerStudent);
						System.out.println("\t\tThanks for taking the test");

						System.out.println("\t\tPlease press R to review and any other character to continue");
						String review = sc.next();
						if (review.equals("R")) {
							Student.review(mcqs, n);
						} else {
							continue;
						}
						System.out.print(
								"\t\tEnter Q to raise a query on any question. Enter any other character if you dont have any query.");
						char Q;
						Q = sc.next().charAt(0);
						if (Q == 'Q') {
							flagquery = true;
							Query qq = new Query();
							System.out.print("\t\tEnter the question number");
							qq.qno = sc.nextInt();
							System.out.print("\t\tEnter the response which you think is correct");
							qq.newAns = sc.nextInt();
							System.out.print("\t\tEnter your justification");
							qq.justif = sc.nextLine();
							qq.justif = sc.nextLine();
							qQ.add(qq);
						}
					}
					break;
				case 2:
					String name = "";
					System.out.println(
							"----------------------------------------------------------------------------------------------------------- ");
					if (t1 == 0) {
						t = 0;
						while (t != 1) {
							try {
								System.out.print("\t\t Please enter your name : ");
								name = sc.next();
								for (int i = 0; i < name.length(); i++) {
									if (AlphanumericException.isSpecialcharacter(name.charAt(i)))
										throw new AlphanumericException(
												"Name should not contain any special character \n\t\t-----------------------------------------\n");
								}
								t = 1;
							} catch (AlphanumericException e) {
								System.out.println(e);
							}
						}
						t1++;

					}

					System.out.println();
					Teacher T = new Teacher(name);

					// Console cnsl = System.console();
					// char[] pass1= cnsl.readPassword( "Enter password : ");
					//
					// String pass = "";
					// for (int i = 0; i < pass1.length; ++i) {
					// pass += pass1[i];
					// }
					// System.out.println();

					final String passwd;
					final String message = "Enter password";
					if (System.console() == null) {
						final JPasswordField pf = new JPasswordField();
						passwd = JOptionPane.showConfirmDialog(null, pf, message, JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ? new String(pf.getPassword())
										: "";
					} else
						passwd = new String(System.console().readPassword("%s> ", message));
					int entry = 0;
					while (entry != 1) {
						if (passwd.equals(T.password)) {

							System.out.println(
									"\t\tpress 3 to take 10 java questions from internet\n\t\tpress 2 to view student queries.\n\t\tpress 1 to display marks\n\t\tpress 0 to frame questions");
							System.out.print("\t\tYour choice : ");
							int q = sc.nextInt();
							if (q == 1) {
								z.flush();
								System.out.println("\t\t\t Teacher name :" + T.name);
								Teacher.displayMarks(v, students, noOfQuesPerStudent);
							} else if (q == 0) {
								System.out.print("\t\tEnter how many questions you want to make : ");
								n = sc.nextInt();

								mcqs = Teacher.makeQuestions(n, mcqs);
								z.flush();
								noOfQuesPerStudent = sc.nextInt();
								while (noOfQuesPerStudent > n) {
									System.out.println(
											"\t\tThe number of questions per student cannot be more than the number of "
													+ "questions you have made.");
									noOfQuesPerStudent = sc.nextInt();
								}
								System.out.print("\n\t\tDisplaying your MCQS ");
								// waiting for 1 sec
								pause();
								System.out.print(".");
								pause();
								System.out.print(".");
								pause();
								System.out.println(".");
								Teacher.display_mcqs(n, mcqs);
							} else if (q == 2) // q==2
							{
								if (flagquery) {
									for (int i = 0; i < qQ.size(); ++i) {
										System.out.println("\t\tQuestion " + (qQ.get(i).qno) + ". "
												+ mcqs[qQ.get(i).qno - 1].question);
										z.write("\t\tQuestion " + (qQ.get(i).qno) + ". "
												+ mcqs[qQ.get(i).qno - 1].question + "\r\n");

										for (int j = 0; j < 4; ++j) {
											System.out.println(
													"\t\t" + (j + 1) + ". " + mcqs[qQ.get(i).qno - 1].options[j]);
											z.write("\t\t" + (j + 1) + ". " + mcqs[qQ.get(i).qno - 1].options[j]
													+ "\r\n");
										}
										System.out.println(
												"\t\tCorrect option set by you:" + mcqs[qQ.get(i).qno - 1].answer);
										z.write("\t\tCorrect option set by you:" + mcqs[qQ.get(i).qno - 1].answer
												+ "\r\n");
										System.out.println("\t\tOption suggested by student:" + qQ.get(i).newAns);
										z.write("\t\tOption suggested by student:" + qQ.get(i).newAns + "\r\n");
										System.out.println("\t\tStudent's justification:" + qQ.get(i).justif + "\n");
										z.write("\t\tStudent's justification:" + qQ.get(i).justif + "\r\n\r\n\r\n");
									}
								} else {
									System.out.println("\t\t Students have not raised any queries\n");
									z.write("\t\t Students have not raised any queries");
								}
							} else { // q == 3
								mcqs = Teacher.netQuestions();
								noOfQuesPerStudent = 10;
								n = 10;
							}
							System.out.println("\t\tplease enter 1 to exit and any other key to continue");
							String ent = sc.next();

							if (ent.equals("1")) {
								entry = 1;
							}
						} else {
							System.out.println("\t\t $$$$$$$$$$$ Please enter correct password $$$$$$$$$$$");
							break;
						}

					}
					break;
				case 3:
					System.out.print("\t\tExiting");
					pause();
					System.out.print(".");
					pause();
					System.out.print(".");
					pause();
					System.out.println(".");
					System.out.println("\t\tThank you for using our system");
					break;
				default:
					System.out.println("\t\tInvalid Choice");
			}
		} while (choice != 3);
		z.close();

	}

	static boolean noQuestion() {
		if (n == 0)
			return true;
		return false;
	}

	static int evaluate() {
		int marks = 0;
		for (int i = 0; i < n; i++) {
			if (mcqs[i].answer == mcqs[i].studentAnswer)
				marks += 1;
		}
		return marks;
	}

	static void intro() {
		System.out.println("\t\tGet Ready for the test !\n\t\tTest Begins in : ");
		for (int i = 5; i >= 1; i--) {
			System.out.println("\t\t" + i);
			// waiting for 1 sec
			pause();
		}
	}

	static void pause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	static void testStudent(int noOfQuesPerStudent) {
		intro();
		int randNos = noOfQuesPerStudent;
		int a[] = new int[n];
		int temp;
		while (randNos != 0) {
			temp = (int) (ThreadLocalRandom.current().nextDouble() * n);
			if (a[temp] != 1) {
				a[temp] = 1;
				randNos--;
			}
		}
		int randFourNos[] = new int[4];
		for (int j = 0; j < 4; ++j) {
			randFourNos[j] = -1;
		}
		int temp1 = 0;
		int randNo = 0;
		while (temp1 != 4) {
			randNo = (int) (ThreadLocalRandom.current().nextDouble() * 4);
			// System.out.println(randNo);
			int flag = 0; // flag indicates if the option has already been assigned in the random array
			for (int j = 0; j < 4; j++)
				if (randFourNos[j] == randNo)
					flag = 1;
			if (flag == 1)
				continue;
			else {
				randFourNos[temp1] = randNo;
				// System.out.println(temp1+" "+randFourNos[temp1]);
				temp1++;
			}
		}

		String temp_options[] = new String[4];
		int k = 0;
		for (int i = 0; i < n; i++) {
			mcqs[i].studentAnswer = 0;
			if (a[i] == 1) {
				System.out.println();
				System.out.println("\t\tQuestion " + (k + 1) + ". " + mcqs[i].question);

				for (int j = 0; j < 4; ++j) {
					// System.out.println((j + 1) + ". " + mcqs[i].options[j]);
					System.out.println("\t\t" + (j + 1) + ". " + mcqs[i].options[randFourNos[j]]);
					temp_options[j] = mcqs[i].options[randFourNos[j]];
				}
				System.out.println(
						"----------------------------------------------------------------------------------------------------------- ");
				int t = 0, choice = 0;
				while (t != 1) {
					System.out.print("\t\tYour choice : ");
					choice = sc.nextInt();
					if (choice > 4)
						System.out.println("\t\tINVALID OPTION");
					else
						t = 1;
				}

				choice = choice - 1;
				int j;
				for (j = 0; j <= 3; j++)
					if (mcqs[i].options[j].equals(temp_options[choice])) {
						break;
					}

				mcqs[i].studentAnswer = j + 1;
				System.out.println(
						"\t\t----------------------------------------------------------------------------------------------------------- ");

				k++;
			}
		}
	}
}
