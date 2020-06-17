package jdbc;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;

public class PropertiesChange {
	public static void readProperties() throws IOException {
		InputStream insss =PropertiesChange.class.getResourceAsStream("/mybatis/jdbc.properties");
		Properties pss = new Properties();
		pss.load(insss);
		System.out.println(pss.getProperty("jdbc.username"));
	}
	/**
	 * 添加配置文件信息
	 * 
	 * @param filePath 
	 * @param content
	 */
	public static void addMessageFile(String filePath, String content) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filePath, "rw");
			raf.seek(raf.length());
			raf.write(content.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 
	}
	public static void changeProperties(String url,String userName,String passwd) throws IOException {
		Properties pro = new Properties();
		InputStream in = null;
		try{
	        //in = new BufferedInputStream(new FileInputStream("src/mybatis/jdbc.properties"));
			in =PropertiesChange.class.getResourceAsStream("/mybatis/jdbc.properties");
			pro.load(in);
	        FileOutputStream file = new FileOutputStream("G:\\gitproject\\Auto-TestCase-generate-Tool\\ATG\\Auto-TestCase-generate-tool\\src\\mybatis\\jdbc.properties");
	        pro.clear();
	        pro.put("jdbc.password",passwd);
	        pro.put("jdbc.username",userName);
	        pro.put("jdbc.url","jdbc:mysql://"+url+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
	        pro.put("jdbc.driver","com.mysql.jdbc.Driver");
	        //pro.setProperty("jdbc.url","jdbc:mysql://"+url+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
	        pro.store(file,"这次更新的备注");
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	    	in.close();
	    }
	}
}
