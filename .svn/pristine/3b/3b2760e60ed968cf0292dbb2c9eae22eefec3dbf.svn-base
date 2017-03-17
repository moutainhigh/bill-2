package com.herongtech.console.web.busicontroller.print;

import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;


public class testljt {

	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pre = null;
		ResultSet result = null;
		//IDB con = DBFactory.getDB();
		try {
			 SAXReader reader = new SAXReader();  
		        //通过当前线程的类加载器，获得文件的相对路径，将xml文件读入到输入流  
		     InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db-config.xml");  
		     Document doc = reader.read(in);
		     //获得节点对象，取得jdbc相关的配置信息  
		     Element root1 = doc.getRootElement();
		     String a=root1.elementText("datasource");
		     Element b = root1.element("datasource");
		     String name = b.elementText("jdbcusername").trim();
		     String password = b.elementText("jdbcpassword").trim();
		     String url = b.elementText("jdbcurl").trim();
		     String driver = b.elementText("jdbcdriver").trim();
		    //通过JDBC获得数据库连接  
		       Class.forName(driver);  
		       Connection con = DriverManager.getConnection(url, name, password);  
		       
		       con.setAutoCommit(false);  
		       Statement st =  con.createStatement();  
		       //插入一个空对象empty_clob()  
		       st.executeUpdate("insert into LJTTEST (NAME,STR) values ('www',empty_clob())");  
		       //锁定数据行进行更新，注意“for update”语句    最终是把这4000多个字符 从1位置替换empty_clob()
		       ResultSet rs = st.executeQuery("select STR from LJTTEST where NAME='www' for update");  
		       if (rs.next())  
		       {  
		           //得到java.sql.Clob对象后强制转换为oracle.sql.CLOB  
		           oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob("STR");  
		           Writer outStream = clob.getCharacterOutputStream();  
		           //data是传入的字符串，定义：String data  
		          // char[] c = "超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1超过4000的字符串：qqqor(Ce.s.sqtClob(ets = (ol.CLOB clob = (oracle.sql.CLOB) rs.getClob(1".toCharArray();  
		           char[] c = "123".toCharArray();
		           outStream.write(c, 0, c.length);  
		           outStream.flush();
		           outStream.close();
		       }  	       
		       con.commit();  
		       con.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
