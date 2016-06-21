package com.ws.report;

import com.ws.dto.ReportRequest;
import com.ws.dto.TemplateParameter;
import com.ws.model.AlarmData;
import com.ws.utils.ReportUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("report")
public class ReportController {


	
	@RequestMapping("export")
	private void export(
			@RequestParam(value = "type", required = false) String type,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> reportParameterMap = new HashMap<String, Object>();
		Map<String, Object> templateParameterMap = new HashMap<String, Object>();
		Map<String, Object> fieldMap = new HashMap<String, Object>();

		TemplateParameter templateParameter = new TemplateParameter();
		List<String> columnList = new ArrayList<String>();
		List<String> parameterList = new ArrayList<String>();
		if (StringUtils.isEmpty(type)) {
			type = "";
		}
		Enum<ReportUtils.REPORT_TYPE> fileType = ReportUtils.REPORT_TYPE.PDF;
		if (type.equals("pdf")) {
			fileType = ReportUtils.REPORT_TYPE.PDF;
		} else if (type.equals("html")) {
			fileType = ReportUtils.REPORT_TYPE.HTML;

		} else if (type.equals("excel")) {
			fileType = ReportUtils.REPORT_TYPE.EXCEL;

		} else if (type.equals("rft")) {
			fileType = ReportUtils.REPORT_TYPE.RTF;

		}
		AlarmData alarmData = new AlarmData();
		 alarmData.setAlarmId(1);
		 alarmData.setAlarmSource("厦门电力");
		alarmData.setAlarmType("网元");
		 alarmData.setCreateDate(new Date());
		List<AlarmData> dataSet=new ArrayList<>();
		for (int i = 0; i <50 ; i++) {
			dataSet.add(alarmData);
		}

		PropertyDescriptor[] descriptors = BeanUtils
				.getPropertyDescriptors(AlarmData.class);
		for (int i = 0; i < descriptors.length; i++) {
			String fieldName = descriptors[i].getName();
			if (fieldName.equals("class"))
				continue;
			// if(!StringUtils.isEmptyForObject(dataSet.get(0).get(fieldName))){
			columnList.add(fieldName);
			reportParameterMap.put(fieldName, fieldName);
			fieldMap.put(fieldName, descriptors[i].getPropertyType().getName()
					.toString());
			// }
		}
		reportParameterMap.put("alarmId","报警标识");
		reportParameterMap.put("alarmType","报警类型");
		reportParameterMap.put("alarmSource","报警来源");
		reportParameterMap.put("createDate","创建时间");

		String templateName = "table.jrxml.tpl";
		String reportName = "测试";

//		 columnList.add("id");
//		 columnList.add("name");
//		 columnList.add("desc");
		templateParameter.setFieldMap(fieldMap);
		parameterList.add("title");
		templateParameterMap.put("title", "java.lang.String");
		reportParameterMap.put("title", "测试");
		templateParameter.setParameterMap(templateParameterMap);
		templateParameter.setHeaders(columnList);
		// parameterMap.put("id", "id");
		// parameterMap.put("name", "name");
		// parameterMap.put("desc", "desc");
		// List<Map<String,Object>> dataSet=new ArrayList<Map<String,Object>>();
		// for (int i = 0; i < 50; i++) {
		// Map<String,Object> map=new HashMap<String, Object>();
		// for (String string : columnList) {
		// map.put(string, string+i);
		// }
		// dataSet.add(map);
		// };
		ReportRequest reportRequest = new ReportRequest(reportParameterMap,
				dataSet, fileType, templateName, templateParameter, reportName);
		ReportUtils.exportReport(request, response, reportRequest);

	}

}
