import java.util.Scanner;

public class Grade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("������ɼ���");
		int grade = Integer.parseInt(sc.next());
		if (grade < 0 || grade > 100) {
			System.out.println("��������ȷ�ĳɼ���");			
		} else if (grade < 60) {
			System.out.println("���ź���������");
		} else {
			System.out.println("��ϲ���ɼ�����");
		}
	}

}
