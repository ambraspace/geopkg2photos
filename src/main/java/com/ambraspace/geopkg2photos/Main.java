package com.ambraspace.geopkg2photos;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main
{
	
	
	public static void main(String[] args)
	{
		for (String s:args)
		{
			System.out.println("Processing file " + s);
			connectAndExtract(s);
		}
	}
	
	
	private static void connectAndExtract(String fileName)
	{
		
		Connection c = null;

		try {

			Class.forName("org.sqlite.JDBC");
	
			c = DriverManager.getConnection("jdbc:sqlite:" + fileName);

			Statement query = c.createStatement();
			ResultSet rs = query.executeQuery("SELECT * FROM Photos");
			String imName;
			byte[] im;
			while (rs.next())
			{
				imName = rs.getString("UUID");
				im = rs.getBytes("photo");
				if (im == null || im.length==0)
						continue;
				System.out.println("Saving " + imName + ".jpg");
				FileOutputStream fos = new FileOutputStream(imName + ".jpg");
				fos.write(im);
				fos.close();
			}
			rs.close();
			query.close();
			c.close();
			
		} catch ( Exception e ) {

			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	
			return;

		}
		

	}

}
