package cms.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cms.bean.CandidateBean;
import cms.util.CandidateUtil;

public class CandidateDao{
	
	NamedParameterJdbcTemplate template;
	CandidateDao(DataSource dataSource){
		this.template= new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	public CandidateDao() {
		// TODO Auto-generated constructor stub
	}

	public String addCandidate(CandidateBean bean)
	{
		System.out.println("success");
		String sql = "insert into candidate_tbl (id,name,m1,m2,m3,result,grade)values(:id,:name,:m1,:m2,:m3,:result,:grade)";
		int numRowsAffected = template.update(sql, CandidateUtil.getParamResource(bean));
		if(numRowsAffected>0) {
			return "SUCCESS";
		}else {
			return "FAILURE";
		}
	
	}


	public List<CandidateBean>getByResult(String criteria)
	{
		
			if(criteria.equals("ALL"))
			{
				String sql = "select * from candidate_tbl";
				return this.template.query(sql, CandidateUtil.getParamResource(new CandidateBean()),new CandidateUtil());
			}
			else
			{
				String sql = "select * from candidate_tbl where result=:result ";
				SqlParameterSource sq = new MapSqlParameterSource("result",criteria);
				return this.template.query(sql, CandidateUtil.getParamResource(new CandidateBean(criteria)) ,new CandidateUtil());

			}

	}
}
