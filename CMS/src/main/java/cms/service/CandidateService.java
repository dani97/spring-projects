package cms.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cms.bean.CandidateBean;
import cms.dao.CandidateDao;
import cms.util.WrongDataException;

public class CandidateService {
	private CandidateDao dao;
	CandidateService(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		// TODO Auto-generated method stub
		this.dao = (CandidateDao)context.getBean("dao");
		System.out.println("[+]Service Started.");
	}
	
	public String addCandidate(CandidateBean bean) throws WrongDataException{
		try{
		if(bean==null||bean.getName()==""||bean.getName().length()<2||bean.getM1()<0||bean.getM1()>100||bean.getM2()<0||bean.getM2()>100||bean.getM3()<0||bean.getM3()>100){
			throw new WrongDataException();
		}
		}
		catch(WrongDataException e)
		{
			return "DATA INCORRECT";
		}
		String id = bean.getId();
		
		int total = bean.getM1()+bean.getM1()+bean.getM3();
		if(total>=240)
		{
			bean.setResult("PASS");
			bean.setGrade("DISTINCTION");
		}
		else if(total>=180)
		{
			bean.setGrade("FIRST CLASS");
			bean.setResult("PASS");
		}
		else if(total>=150){
			bean.setGrade("SECOND CLASS");
			bean.setResult("PASS");
		}
		else if(total>=105)
		{
			bean.setGrade("THIRD CLASS");
			bean.setResult("PASS");
		}
		else{
			bean.setGrade("NO GRADE");
			bean.setResult("FAIL");
		}
		dao.addCandidate(bean);
		String result;
		if(total>=105)
			result = "PASS";
		else
			result = "FAIL";
		return id+":"+result;
		
	}
	
	
	public ArrayList<CandidateBean> displayAll(String criteria)
	{
		ArrayList<CandidateBean> res = new ArrayList<CandidateBean>();
		if(criteria.equals("PASS")||criteria.equals("FAIL")||criteria.equals("ALL"))
		{
			
			res = (ArrayList<CandidateBean>) dao.getByResult(criteria);
		}
		else
		{
			try{
			throw new WrongDataException();
			}
			catch(WrongDataException e)
			{
				return null;
			}
		}
		return res;
	}

	public static void main(String[] args) throws WrongDataException {
		
		CandidateService candidateMain = new CandidateService();
		String result ="";
		
			CandidateBean bean = new CandidateBean();
			bean.setId("S004");
			bean.setName("somu");
			bean.setM1(50);
			bean.setM2(55);
			bean.setM3(50);
			result = candidateMain.addCandidate(bean);
			ArrayList<CandidateBean> al = candidateMain.displayAll("PASS");
			Iterator<CandidateBean> i =al.iterator();
			System.out.println(al.size());
			while(i.hasNext())
			{
				CandidateBean bean1 = i.next();
				System.out.println(bean1.getId()+" "+bean1.getName()+" "+bean1.getResult()+" "+bean1.getGrade());
				
			}
		System.out.println(result);

	}

}
