package jdbc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import views.DataBaseDisplay;

import java.awt.Robot;
import java.io.Reader;

public class DBTools {
    public static SqlSessionFactory sessionFactory;

    static{
        try {
            //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�
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
