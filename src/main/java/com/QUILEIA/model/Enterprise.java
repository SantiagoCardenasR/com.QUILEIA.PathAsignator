package com.QUILEIA.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.Driver;

public class Enterprise 
{
	private ArrayList<Officer> officers;
	private ArrayList<Path> paths;
	
	public Enterprise()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathfinder_data","root","");
			Statement stmt = con.createStatement();
			ResultSet officerCountRs = stmt.executeQuery("SELECT COUNT(*) FROM Officer");
			officerCountRs.next();
			int officerCount = officerCountRs.getInt(1);
			if(officerCount > 0)
			{
				officers = new ArrayList<Officer>();
				ResultSet officersRs = stmt.executeQuery("SELECT * from Officer");
				while(officersRs.next())
				{
					Officer newOfficer = new Officer(officersRs.getString(1),officersRs.getString(2),officersRs.getInt(3),officersRs.getString(4),officersRs.getInt(5));
					officers.add(newOfficer);
				}
			}
			else
			{
				officers = new ArrayList<Officer>();
			}
			
		} 
		catch (Exception e) 
		{
		}
	}

	public ArrayList<Officer> getOfficers() 
	{
		return officers;
	}

	public void setOfficers(ArrayList<Officer> officers) 
	{
		this.officers = officers;
	}

	public ArrayList<Path> getPaths() 
	{
		return paths;
	}

	public void setPaths(ArrayList<Path> paths) 
	{
		this.paths = paths;
	}
	
	

}
