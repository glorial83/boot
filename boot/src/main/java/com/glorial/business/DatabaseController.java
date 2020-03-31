package com.glorial.business;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DatabaseController {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseController.class);

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@RequestMapping("selecttest")
	@ResponseBody
	public List<Map<String, String>> selecttest() {
		logger.error("selecttest 시작");
		//return sqlSessionTemplate.selectList("test.selecttest");

		Map<String, Object> empInfo = new HashMap<>();
		empInfo.put("EMP_ID", "사용자ID1");
		empInfo.put("EMP_NM", "홍길동1");
		empInfo.put("EMP_NO", 123456);

		empInfo.put("RECORD_TYPE", "TAKEIT.EMP_MAP");
		empInfo.put("TABLE_TYPE", "TAKEIT.EMP_LIST");

		Map<String, Object> params = new HashMap<>();
		params.put("dsInput", Arrays.asList(new Object[] {empInfo, empInfo}));
		params.put("result", "");
		params.put("TEST_ID", "정하ㅣㅓㅣㅁㄴ엏");

		List<Map<String, String>> result = sqlSessionTemplate.selectList("test.EMP_DATE_INSERT", params);

		System.out.println(params.get("result"));

		return result;
	}
}
