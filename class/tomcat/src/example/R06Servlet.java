package myServlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

// import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


@WebServlet(urlPatterns = {"/r06"})
public class R06Servlet 
	extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		funcServlet(request,response, "get");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		funcServlet(request,response, "post");
	}

	@Override
	public void funcServlet(HttpServletRequest request, HttpServletResponse response, String method) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String strMovePath = "/jsp/r06.jsp";

		PrintWriter out = response.getWriter();

  //スクリプトレット
  //  Javaプログラムを書くところ
//DB連携の変数定義
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

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
				System.out.println("<br/>");

				HashMap<String, String> map = new HashMap<String, String>();

				map.put("f_id",rs.getString("f_id"));
				map.put("f_name",rs.getString("f_name"));
				map.put("f_age",rs.getString("f_age"));
				map.put("f_pass",rs.getString("f_pass"));

				list.add(map);
			}

			//rsの解放
			rs.close();
			//stmtの解放
			stmt.close();
			//DB切断
			con.close();
		}catch (ClassNotFoundException err) {
			err.printStackTrace();
		}catch (SQLException err) {
			err.printStackTrace();
		}finally {
			try {rs.close();}catch (SQLException err) {err.printStackTrace();}
			try {stmt.close();}catch (SQLException err) {err.printStackTrace();}
			try {con.close();}catch (SQLException err) {err.printStackTrace()}
		}

		out.println("Servlet から実行("+method+")");
		out.println("test");

		RequestDispatcher dispatcher = request.getRequestDispatcher(strMovePath);
		dispatcher.forward(request, response);

	}
}
