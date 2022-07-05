import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

public class ConnectDB {
	private Connection connect;
	private String inputUser;
	private String inputPassword;
	//
	private String IDemployee;
	ResultSet resultSet;
	Scanner scanner = new Scanner(System.in);
	public void Connect() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			if (connect != null) {
				System.out.println("Connect Success.");
			} else {
				System.out.println("Connect Failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

		public void function () {
			System.out.print("ลงเวลาเช้าพิม 1"+ "\nลงเวลาออกพิมพ์ 2 " + "\nFunction ที่เลือก : ");
			
			
			int inputFunction = scanner.nextInt() ;
			if (inputFunction == 1) {
				timeIn();
			}else if (inputFunction == 2 ){
				timeOut();
			}
			
		}
	public void disConnect() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void login() {
		try {

			PreparedStatement ppstm = connect.prepareStatement(
					"SELECT * FROM aboutemployee INNER JOIN employeeacount ON aboutemployee.EmployeeID = employeeacount.EmployeeID WHERE EmployeeUser = ? AND  EmployeePassword = ?");

			
			System.out.print("UserName : ");
			inputUser = scanner.next();

			System.out.print("Password : ");
			inputPassword = scanner.next();

			ppstm.setString(1, inputUser);
			ppstm.setString(2, inputPassword);
			resultSet = ppstm.executeQuery();

			if (resultSet.next()) {
				IDemployee = resultSet.getString("EmployeeID");
				
				System.out.println("รหัสพนักงาน : "+IDemployee +"\nชื่อ : "+resultSet.getString("EmployeeName")+ "\n Correct");
				//System.out.println(resultSet.getString("EmployeeName"));
					function();
			} else {
				System.out.println("UserName or Password Not Correct");
			}
//			System.out.println(resultSet.getString("EmployeeName"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void timeIn() {
		try {
			Date date = new Date();
			PreparedStatement ppstm = connect
					.prepareStatement("UPDATE aboutemployee SET TimeIn = ?   WHERE  EmployeeID = ?");
			ppstm.setObject(1, date);
			ppstm.setString(2, IDemployee);
			ppstm.executeUpdate();
			
			System.out.println("ลงเวลาเข้า " + date);
			
			System.out.println("ลงเวลาเข้าสำเร็จ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void timeOut() {
		try {
			Date date = new Date();
			PreparedStatement ppstm = connect
					.prepareStatement("UPDATE aboutemployee SET TimeOut = ?   WHERE  EmployeeID = ?");
			ppstm.setObject(1, date);
			ppstm.setString(2, IDemployee);
			ppstm.executeUpdate();
			System.out.println("เวลาออก "+date);
			//System.out.println(resultSet.getString("TimeOut"));
			System.out.println("ลงเวลาออกสำเร็จ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
