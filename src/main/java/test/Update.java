package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/updatebooks")
public class Update extends HttpServlet {
	Connection con=null;
		@Override
		public void init() throws ServletException {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8","root","mysql123");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw=resp.getWriter();
			int id=Integer.parseInt(req.getParameter("bookid"));
			String name=req.getParameter("bookname");
			double price=Double.parseDouble(req.getParameter("bookprice"));
			String author=req.getParameter("author");
			
			PreparedStatement pstmt=null;
			
			String query="update book_data set book_name=?,book_price=?,book_author=? where book_id=?";
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(4, id);
				pstmt.setString(1, name);
				pstmt.setDouble(2, price);
				pstmt.setString(3, author);
				int count=pstmt.executeUpdate();
				pw.print("<h1>"+count+"  record updated  </h1>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
