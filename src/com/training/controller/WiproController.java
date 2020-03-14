package com.training.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.model.User;

/**
 * Servlet implementation class WiproController
 */
@WebServlet("/WiproController")
public class WiproController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WiproController() {
    	
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		String jdbcURL = "jdbc:mysql://remotemysql.com:3306/VA3dcIm46p";
        String dbUser = "VA3dcIm46p";
        String dbPassword = "NsECKINSXe";
 
        Connection connection = null;
        try {
        	
        	
        	Class.forName("com.mysql.jdbc.Driver");
        	connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        	System.out.println("Connection Successful");
            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
            System.out.println(email +":" +password);
            PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			
			
			statement.setString(2, password);
			 
	        ResultSet result = statement.executeQuery();
	        
	        System.out.println(result);
	        User user = null;
	        
	        
	        if (result.next()) {
	            user = new User();
	            String nameReturned = result.getString(1);
	            user.setFullname(nameReturned);
	            user.setEmail(email);
	            response.getWriter().append("Hello ").append(user.getFullname());
	        }
	        else {
	        	response.getWriter().append("Sorry ").append("You are not authorized to use Resource");
	        	
	        }
	        
	        connection.close();
	        
	        

	        
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
 
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
