package com.khq.ch2;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://khq.com/wsdl")
public interface IUserService
{
	    //��ѯ��ɫ������
		@WebMethod
		public String  queryRoleData();
		
		// �ҳ�ѧ�������ְ���ѧ��������ְ������
		@WebMethod
		public String  queryGroupByRoleCount();
		
		//-- **ѧ����ѧ�γ̵�����
		public String  queryStuAndkmCount(String name);
		
		//����¼
		@WebMethod
		public String checkUserLogin(String cname,String cphon);
		//���ݲ˵�
		@WebMethod
		public String queryGirdMenuData();
}
