

import com.shenyang.bean.Student;
import com.shenyang.commDao.daoImpl.StudentDaoImpl;


public class Test {
	public static void main(String[] args) {
		try {
			StudentDaoImpl daoImpl = new StudentDaoImpl();
			Student stu = new Student();
			stu.setName("张三");
			daoImpl.delete(stu);
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
class A{
	protected int a = 5;
}
class B extends A{
	protected int b = 6;
}