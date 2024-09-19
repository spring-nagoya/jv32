package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myBean.UserBean;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

@WebServlet(urlPatterns = {"/r06"})
public class R06Servlet 
	extends HttpServlet
{
	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
	throws ServletException, IOException{
		
		funcServlet(req, resp , "get");
		
	}
	
	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
	throws ServletException, IOException{
		
		funcServlet(req, resp , "post");
		
	}
	
	protected void funcServlet(
			HttpServletRequest req,
			HttpServletResponse resp,
			String strKind)
	throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//遷移先のファイル名
		String strMovePath = "/jsp/r06_result.jsp";
		
		/***処理の開始********/
			//DB連携の変数定義
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			//連想配列(HashMap)を要素とした配列
			//	→ArrayListクラス(動的配列)
			//		→<E>:Element(要素)
			ArrayList<HashMap<String, String>> ary = new ArrayList<HashMap<String,String>>();
			
			//	↓	HashMapからBeansに変更
			ArrayList<UserBean> aryBean = new ArrayList<UserBean>();
			
			try {
				
				//①Driverの読み込み
				Class.forName("com.mysql.jdbc.Driver");
	
				//②DB接続
				con = DriverManager.getConnection("jdbc:mysql://localhost/2024jv32?useSSL=false", "root", "Passw0rd");
				//SQL:alter user 'root'@' localhost' identified with mysql_native_password by 'Passw0rd';
	
				//③SQL実行変数の作成
				stmt = con.createStatement();
	
				//④SQL文の作成
				String strSQL = " select * from t_user ";
	
				//⑤SQL
				//stmt.executeQuery : select文
				//stmt.executeUpdate : select文以外
				rs = stmt.executeQuery(strSQL);
	
				while(rs.next()){
					System.out.println(rs.getString("f_name"));
					System.out.println("<br />");
					
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("f_id", rs.getString("f_id"));
					map.put("f_name", rs.getString("f_name"));
					map.put("f_age", rs.getString("f_age"));
					map.put("f_password", rs.getString("f_password"));
					
					ary.add(map);
					//	↓HashMapからBeansに変更
					UserBean bean1 = new UserBean();
					bean1.setId  (rs.getString("f_id")      );
					bean1.setName(rs.getString("f_name")    );
					bean1.setAge (rs.getInt("f_age")        );
					bean1.setPass(rs.getString("f_password"));
					aryBean.add(bean1);
					bean1 = null;
			}


		}catch(ClassNotFoundException e) {
			
		}catch(SQLException e) {
			
		}catch (Exception e) {
			// TODO: handle exception
			
		}finally {
			//rsの解放
			try{rs.close();}catch(Exception e) {}
			//stmtの解放
			try{stmt.close();}catch(Exception e) {}
			//DB切断
			try{con.close();}catch(Exception e) {}
			
		}
			
		//ServletからJSPへ値渡し
		//Attribute(属性)
		req.setAttribute("dataRows", ary);
		req.setAttribute("beanAry", aryBean);
		
		

		/***処理の終了********/
		
		PrintWriter out = resp.getWriter();
		out.println("servelet("+strKind+")");
		
		//ServletからJSPへ遷移
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher(strMovePath);
		dispatcher.forward(req, resp);
		
		//String strData = (String)req.getAttribute("dataRows");
		
		
		
		
	}
}
