package jdbc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import views.DataBaseDisplay;

import java.awt.Robot;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class DBTools {
    public static SqlSessionFactory sessionFactory;

    static{
        try {
            //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�
        	//InputStream fis2 = DBTools.class.getResourceAsStream("\\mybatis\\mybatis.cfg.xml");
            Reader reader = Resources.getResourceAsReader("\\mybatis\\mybatis.cfg.xml");
            //����sqlSession�Ĺ���
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //������ִ��ӳ���ļ���sql��sqlSession
    public static SqlSession getSession(){
        return sessionFactory.openSession();
    }
}
