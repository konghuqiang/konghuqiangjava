package com.khq.ch4;

import java.util.List;

import javax.jws.WebService;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.khq.ch2.IUserService;
import com.khq.ch3.DB;
import com.khq.ch5.Menu;
import com.khq.ch5.Role;
import com.khq.ch5.StuAndRole;

@WebService(portName="userservice",
serviceName="UserServiceImpl",
targetNamespace="http://khq.com/wsdl",
endpointInterface="com.khq.ch2.IUserService")
public class UserServiceImpl implements IUserService
{
	@Override
	public String queryRoleData()
	{
		System.out.println("UserServiceImpl is queryRoleData start...");
		DB db =new DB();
		List<Role> lists=db.queryRoleData();
		System.out.println("---->"+lists.size());
		JSONArray array=new JSONArray();
		for(Role role:lists)
		{
			JSONObject obj=new JSONObject();
			obj.put("eid",role.getEid());
			obj.put("ename",role.getEname());
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());
		return array.toString();
	}
	@Override
	public String queryGroupByRoleCount() {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGroupByRoleCount  start...  ");
		
		DB db = new DB();
		
		List<StuAndRole> lists = db.queryRoleGroupCount();

		System.out.println("--->" + lists.size());
		JSONArray array = new JSONArray();
		for (StuAndRole crole : lists) {

			JSONObject obj = new JSONObject();
			obj.put("rname", crole.getRname());
			obj.put("rcount", crole.getRcount());
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());

		return array.toString();
	}

	@Override
	public String queryStuAndkmCount(String name) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGroupByRoleCount  start...  ");
		
		DB db = new DB();
		
		String  data=db.queryStuAndkmCount(name);
		
		System.out.println("data-->"+data);
		System.out.println("data-->" + data);
		return data;
	}
	
	@Override
	public String checkUserLogin(String cname,String cphon)
	{
		// TODO Auto-generated method stub
		DB db=new DB();
		System.out.println("UserServiceImpl  is checkUserLogin  start...  ");
		int count=db.checkUserLogin(cname,cphon);
		if(count>0)
		{
			return "µÇÂ¼³É¹¦";
		}
		return "µÇÂ¼Ê§°Ü";
	}
	@Override
	public String queryGirdMenuData()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGirdMenuData  start...  ");
		DB db=new DB();
		List<Menu>lists=db.queryGirdMenuData();
		String strJson=com.alibaba.fastjson.JSONArray.toJSONString(lists);
		return strJson;
	}
}
