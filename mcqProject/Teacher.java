
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Teacher extends User {
    String password = "1234";

    Teacher(String n) {
        name = n;
    }

    static MCQ[] makeQuestions(int n, MCQ mcqs[]) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 20; i++) {
            mcqs[i] = new MCQ();
        }
        while (n > 20) {
            System.out.println("\t\tOnly 20 questions are allowed");
            n = sc.nextInt();
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------------------- ");
        for (int i = 0; i < n; i++) {
            mcqs[i].qno = i + 1;
            System.out.print("\t\tEnter question " + (i + 1) + ": ");
            mcqs[i].question = sc.next();
            mcqs[i].question += sc.nextLine();
            System.out.println("\t\tEnter options for question " + (i + 1));
            for (int j = 0; j < 4; j++) {
                System.out.printf("\t\t%d. ", j + 1);
                mcqs[i].options[j] = sc.next();
                mcqs[i].options[j] += sc.nextLine();
            }
            System.out.print("\t\tEnter option number of answer for question " + (i + 1) + ": ");
            mcqs[i].answer = sc.nextInt();
            System.out.println("\t\t---------------------------------------------------------------------");
        }
        //
        System.out.print("\t\tEnter the number of questions each student has to answer : ");

        return mcqs;
    }

    static void displayMarks(Vector<Student> v, int students, int noOfQuesPerStudent) throws IOException {

        File f = new File("testReport.txt");
        FileWriter w = new FileWriter(f);
        w.flush();

        System.out.print("\t\tsr.no.\tName\tMarks\tpercentage\t\n");
        w.write("\t\tsr.no.\tName\tMarks\tpercentage\t\r\n");

        System.out.println("\t\t------------------------------------------- ");
        w.write("\t\t------------------------------------------- \r\n");
        int i = 1;
        int ninety = 0;
        int seventy = 0;
        int forty = 0;
        int fortyless = 0;
        for (int j = 0; j < students; j++) {
            System.out.print("\t\t");

            System.out.print(i);
            w.write("\t\t" + i);
            // w.write(i);
            i++;
            int marks;
            marks = v.elementAt(j).marks;
            double percent = (marks * 100 / noOfQuesPerStudent);

            if (percent >= 90) {
                ninety++;
            } else if (percent >= 70) {
                seventy++;
            } else if (percent >= 40) {
                forty++;
            } else {
                fortyless++;
            }

            System.out.print("\t" + v.elementAt(j).name);
            w.write("\t" + v.elementAt(j).name);
            System.out.print("\t" + v.elementAt(j).marks);
            w.write("\t" + v.elementAt(j).marks);
            System.out.print("\t" + percent);
            w.write("\t" + percent);
            System.out.println("\n");
            w.write("\r\n");
            System.out.println("\t\t------------------------------------------ ");
            w.write("\t\t------------------------------------------ \r\n");

        }
        System.out.println("\n\n\t\tnumber of students above 90% is " + ninety);
        w.write("\r\n\r\n\t\tnumber of students above 90% is " + ninety);
        System.out.println("\n");
        w.write("\r\n\r\n");
        System.out.println("\t\tnumber of students above 70% is " + seventy);
        w.write("\t\tnumber of students above 70% is " + seventy);
        System.out.println("\n");
        w.write("\r\n\r\n");
        System.out.println("\t\tnumber of students above 40% is " + forty);
        w.write("\t\tnumber of students above 40% is " + forty);
        System.out.println("\n");
        w.write("\r\n\r\n");
        System.out.println("\t\tnumber of students less than 40% is " + fortyless);
        w.write("\t\tnumber of students less than 40% is " + fortyless);
        System.out.println("\n");
        w.write("\r\n\r\n");

        w.close();

    }

    static MCQ[] netQuestions() throws IOException {
        Document doc = Jsoup.connect("https://www.tutorialspoint.com/java/java_online_quiz.htm").get();
        Elements elements = doc.select("div.QA");
        int i = 1;
        MCQ[] v = new MCQ[10];
        String extra_option = "ONLY THE OTHER OPTIONS CAN BE SELECTED";
        for (Element element : elements) {

            Element element2 = element.select("div.QA").select("div.Q").first();

            if (element2 != null) {

                MCQ m = new MCQ();
                Elements paras = element2.getElementsByTag("p");
                Vector<String> question_details = new Vector<String>();
                int correct_ans = 0;
                for (Element para : paras) {

                    question_details.add(para.text());
                    String s = para.getElementsByTag("a").attr("class");

                    if (s.equals("true") == true)

                    {
                        correct_ans = (int) (para.text().charAt(0) - 'A') + 1;

                    }
                    m.qno = i;

                    m.answer = correct_ans;
                    m.question = question_details.get(0).substring(6);
                    for (int ii = 1; ii < 5; ii++) {
                        try {
                            m.options[ii - 1] = question_details.get(ii).substring(4);

                        } catch (Exception e) {
                            m.options[ii - 1] = extra_option;
                        }
                    }

                }

                v[i - 1] = m;
                i = i + 1;
            }

        }
        File file = new File("QuestionBank.txt");
        FileWriter z = new FileWriter(file);
        z.flush();
        for (int j = 0; j < v.length; j++) {
            System.out.println("\t\t" + v[j].qno + ". " + v[j].question);
            z.write("\t\t" + v[j].qno + ". " + v[j].question);
            z.write("\r\n");
            System.out.println("\t\t1. " + v[j].options[0]);
            z.write("\t\t1. " + v[j].options[0]);
            z.write("\r\n");
            System.out.println("\t\t2. " + v[j].options[1]);
            z.write("\t\t2. " + v[j].options[1]);
            z.write("\r\n");
            System.out.println("\t\t3. " + v[j].options[2]);
            z.write("\t\t3. " + v[j].options[2]);
            z.write("\r\n");
            System.out.println("\t\t4. " + v[j].options[3]);
            z.write("\t\t4. " + v[j].options[3]);
            z.write("\r\n");
            System.out.println("\t\tCorrect option no:" + v[j].answer);
            z.write("\t\tCorrect option no:" + v[j].answer);
            z.write("\r\n");
            System.out.println("\n");
            z.write("\r\n");
            User.pause();

        }
        z.close();
        return v;
    }

}