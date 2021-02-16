
import java.util.Vector;

class Student extends User {
    int marks;

    Student(String n, int marks) {
        this.marks = marks;
        name = n;
    }

    static Vector<Student> createStudent(String name1, int marks, Vector<Student> v, Student m1) {

        v.add(m1);

        return v;

    }

    static void review(MCQ mcqs[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.println("\t\tQuestion " + (i + 1) + ". " + mcqs[i].question);
            for (int j = 0; j < 4; ++j) {
                System.out.println("\t\t" + (j + 1) + ". " + mcqs[i].options[j]);
            }
            System.out.println("\t\tYour Answer : " + mcqs[i].studentAnswer);
            System.out.println("\t\tCorrect Answer : " + mcqs[i].answer);
            if (mcqs[i].answer == mcqs[i].studentAnswer) {
                System.out.println("\t\tmarks allotted = 1");
            } else {
                System.out.println("\t\tmarks allotted = 0");
            }
            System.out.println(
                    "\t\t----------------------------------------------------------------------------------------------------------- ");
            pause();
        }
    }

}
