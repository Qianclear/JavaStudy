import java.util.Scanner;

public class Grade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入成绩：");
		int grade = Integer.parseInt(sc.next());
		if (grade < 0 || grade > 100) {
			System.out.println("请输入正确的成绩！");			
		} else if (grade < 60) {
			System.out.println("很遗憾，不及格！");
		} else {
			System.out.println("恭喜！成绩及格！");
		}
	}

}
