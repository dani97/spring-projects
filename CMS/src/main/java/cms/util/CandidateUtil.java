package cms.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import cms.bean.CandidateBean;

public class CandidateUtil implements RowMapper<CandidateBean> {

	@Override
	public CandidateBean mapRow(ResultSet resultSet, int count) throws SQLException {
		// TODO Auto-generated method stub
		CandidateBean bean = new CandidateBean();
		bean.setGrade(resultSet.getString("grade"));
		bean.setId(resultSet.getString("id"));
		bean.setM1(resultSet.getInt("m1"));
		bean.setM2(resultSet.getInt("m2"));
		bean.setM3(resultSet.getInt("m3"));
		bean.setName(resultSet.getString("name"));
		bean.setResult(resultSet.getString("result"));
		return bean;
	}
	
	public static SqlParameterSource getParamResource(CandidateBean bean) {
		
		return new MapSqlParameterSource("id",bean.getId()).addValue("name", bean.getName())
				.addValue("m1", bean.getM1()).addValue("m2", bean.getM2()).addValue("m3", bean.getM3())
				.addValue("grade", bean.getGrade()).addValue("result", bean.getResult());
		
	}

}

