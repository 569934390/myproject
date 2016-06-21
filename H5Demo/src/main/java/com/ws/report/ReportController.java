package com.ws.report;

import java.beans.PropertyDescriptor;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztesoft.cache.ReportCache;
import com.ztesoft.dto.HostMonitoringDto;
import com.ztesoft.dto.NmsLinkDto;
import com.ztesoft.dto.NodeElementDto;
import com.ztesoft.dto.Page;
import com.ztesoft.dto.Quartz;
import com.ztesoft.dto.ReportIOPDto;
import com.ztesoft.dto.ReportRequest;
import com.ztesoft.dto.ReportTaskDto;
import com.ztesoft.dto.TemplateParameter;
import com.ztesoft.model.AlarmData;
import com.ztesoft.model.DcSql;
import com.ztesoft.model.KpiCodeData;
import com.ztesoft.model.NmsTopoSymbol;
import com.ztesoft.model.ReportTask;
import com.ztesoft.report.ExportExcelFactory;
import com.ztesoft.report.ExportExcelFactory.EXPORT_TYPE;
import com.ztesoft.report.Exporter;
import com.ztesoft.service.alarm.AlarmDataService;
import com.ztesoft.service.report.CopyLibraryService;
import com.ztesoft.service.report.ReportService;
import com.ztesoft.service.resource.NodeElementService;
import com.ztesoft.service.solr.SolrQueryService;
import com.ztesoft.util.JsonUtils;
import com.ztesoft.util.QuartzUtil;
import com.ztesoft.util.ReportUtils;
import com.ztesoft.util.ReportUtils.REPORT_TYPE;
import com.ztesoft.util.StringUtils;

@Controller
@RequestMapping("report")
public class ReportController {
	@Autowired
	AlarmDataService alarmDataService;
	@Autowired
	ReportService reportService;
	@Autowired
	NodeElementService nodeElementService;
	@Autowired
	CopyLibraryService copyLibraryService;
	@Autowired
	SolrQueryService solrQueryService;
	@Autowired
	private SchedulerFactoryBean jobScheduler;

	/**
	 * 获取事件监控指标
	 */
	@RequestMapping("loadAffairMonitorGrid.do")
	@ResponseBody
	public Page<Map<String, Object>> loadAffairMonitorGrid(HttpServletRequest request,Page<Map<String, Object>> page){
		return reportService.loadAffairMonitorGrid(request,page);
	}
	
	
	@RequestMapping("loadAffairChartData.do")
	@ResponseBody
	public List<Map<String,Object>> loadAffairChartData(HttpServletRequest request){
		return  reportService.loadAffairChartData(request);
	}

	@RequestMapping("cacheAffairList.do")
	@ResponseBody
	public String cacheAffairList(HttpServletRequest request, HttpServletResponse response) {
		String suffix = request.getParameter("suffix");
		String affairtList = request.getParameter("affairtList");
		String columnHander = request.getParameter("columnHander");
		String columnIndex = request.getParameter("columnIndex");
		String fileName = request.getParameter("fileName");
		String sheetName = request.getParameter("sheetName");

		ReportCache reportCache=ReportCache.getInstance();
		reportCache.addCache("suffix", suffix);
		reportCache.addCache("affairtList", affairtList);
		reportCache.addCache("columnHander", columnHander);
		reportCache.addCache("columnIndex", columnIndex);
		reportCache.addCache("fileName", fileName);
		reportCache.addCache("sheetName", sheetName);
		return "success";
	}
	@RequestMapping("exportAffairList.do")
	@ResponseBody
	public void exportAffairList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReportCache reportCache=ReportCache.getInstance();

		Map<String,Object> affairMap = new HashMap<String,Object>();
		String suffix=!StringUtils.isEmpty(reportCache.getCache("suffix"))?reportCache.getCache("suffix").toString():"xls";
		String affairtList=reportCache.getCache("affairtList").toString();
		String columnHander=reportCache.getCache("columnHander").toString();
		String columnIndex=reportCache.getCache("columnIndex").toString();
		String fName=reportCache.getCache("fileName").toString();
		String sheetName=!StringUtils.isEmpty(reportCache.getCache("sheetName"))?reportCache.getCache("sheetName").toString():"DATA";
		affairMap.put("suffix", suffix);
		affairMap.put("affairtList", affairtList);
		affairMap.put("columnHander", columnHander);
		affairMap.put("columnIndex", columnIndex);
		affairMap.put("sheetName", sheetName);
		
		List<List<String>> exportList = reportService.exportAffairList(affairMap);
		Exporter ex = ExportExcelFactory.create(EXPORT_TYPE.GRID_TO_EXCEL);
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMdd");
		String filename = fName + sdf.format(new Date());
		filename = URLEncoder.encode(fName + sdf.format(new Date()),"UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="+ filename + ".xls");// 设定输出文件头
		response.setContentType("application/msexcel");// 定义输出类型
		ex.export(response.getOutputStream(), exportList,affairMap);
		reportCache=null;
	}
	@RequestMapping("loadAffairStockData.do")
	@ResponseBody
	public List<Map<String,Object>> loadAffairStockData(HttpServletRequest request,KpiCodeData kpiCodeData) throws Exception {
		return reportService.loadAffairStockData(kpiCodeData);
	}
	
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
		// alarmData.setAlarmState("1");
		// alarmData.setOprtState("30");
		// alarmData.setOprtState("00A");
		// List<ReportResultDto>
		// resultList=reportService.selectReportResultList(new ReportResult());
		// List<Map<String,Object>>
		// dataSet=ReportUtils.convertReportData(resultList);
		List<AlarmData> dataSet = alarmDataService.loadAlarmDataList(alarmData);
		// List<Map<String,Object>> dataSet=JsonUtils.toMapList(alarmDataList);
		PropertyDescriptor[] descriptors = BeanUtils
				.getPropertyDescriptors(AlarmData.class);
		for (int i = 0; i < descriptors.length; i++) {
			String fieldName = descriptors[i].getName();
			if (i > 5 && !fieldName.equals("regionId")
					&& !fieldName.equals("generateTime"))
				continue;
			if (fieldName.equals("class"))
				continue;
			// if(!StringUtils.isEmptyForObject(dataSet.get(0).get(fieldName))){
			columnList.add(fieldName);
			reportParameterMap.put(fieldName, fieldName);
			fieldMap.put(fieldName, descriptors[i].getPropertyType().getName()
					.toString());
			// }
		}
		String templateName = "table.jrxml.tpl";
		String reportName = "测试";

		// columnList.add("id");
		// columnList.add("name");
		// columnList.add("desc");
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

	@RequestMapping("exportNodeElement.do")
	private void exportNodeElement(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "params", required = false) String params,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> reportParameterMap = new HashMap<String, Object>();
		Map<String, Object> templateParameterMap = new HashMap<String, Object>();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		LinkedHashMap<String, Object> headerMap = new LinkedHashMap<String, Object>();
		headerMap.put("nodeName", "网元名称");
		headerMap.put("ipAddress", "IP地址");
		headerMap.put("regionName", "区域名称");
		headerMap.put("nodeTypeName", "网元类型");
		headerMap.put("pollEnabledName", "网元状态");
		TemplateParameter templateParameter = new TemplateParameter();
		List<String> columnList = new ArrayList<String>();
		List<String> parameterList = new ArrayList<String>();
		if (StringUtils.isEmpty(type)) {
			type = "";
		}
		Enum<REPORT_TYPE> fileType = ReportUtils.getReportType(type);
		NodeElementDto nodeElementDto = JsonUtils.toBean(params,
				NodeElementDto.class);
		// alarmData.setAlarmState("1");
		// alarmData.setOprtState("30");
		// alarmData.setOprtState("00A");
		// List<ReportResultDto>
		// resultList=reportService.selectReportResultList(new ReportResult());
		// List<Map<String,Object>>
		// dataSet=ReportUtils.convertReportData(resultList);
		List<NodeElementDto> dataSet = nodeElementService
				.selectViewList(nodeElementDto);
		// List<Map<String,Object>> dataSet=JsonUtils.toMapList(alarmDataList);
		// PropertyDescriptor[]
		// descriptors=BeanUtils.getPropertyDescriptors(AlarmData.class);
		PropertyDescriptor[] descriptors = BeanUtils
				.getPropertyDescriptors(NodeElementDto.class);
		for (int i = 0; i < descriptors.length; i++) {
			String fieldName = descriptors[i].getName();
			if (fieldName.equals("class") || !headerMap.containsKey(fieldName))
				continue;
			columnList.add(fieldName);
			reportParameterMap.put(fieldName, headerMap.get(fieldName)
					.toString());
			fieldMap.put(fieldName, descriptors[i].getPropertyType().getName()
					.toString());
		}
		String templateName = "table.jrxml.tpl";
		String reportName = "网元数据";

		// columnList.add("id");
		// columnList.add("name");
		// columnList.add("desc");
		templateParameter.setFieldMap(fieldMap);
		templateParameter.setGroupField(nodeElementDto.getGroupField());
		parameterList.add("title");
		templateParameterMap.put("title", "java.lang.String");
		reportParameterMap.put("title", "网元数据");
		templateParameter.setParameterMap(templateParameterMap);
		ReportUtils.setHeaders(templateParameter, headerMap);
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

	@RequestMapping("exportNmsLink.do")
	private void exportNmsLink(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "params", required = false) String params,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> reportParameterMap = new HashMap<String, Object>();
		Map<String, Object> templateParameterMap = new HashMap<String, Object>();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		LinkedHashMap<String, Object> headerMap = new LinkedHashMap<String, Object>();
		headerMap.put("srcRegionName", "源端所属区域");
		headerMap.put("srcNodeName", "源端网元");
		headerMap.put("srcIpAddress", "源端IP");
		headerMap.put("srcPortIpAddress", "源端端口IP");
		headerMap.put("desNodeName", "宿端网元");
		headerMap.put("desIpAddress", "宿端IP");
		headerMap.put("desPortIpAddress", "宿端端口IP");
		TemplateParameter templateParameter = new TemplateParameter();
		List<String> parameterList = new ArrayList<String>();
		if (StringUtils.isEmpty(type)) {
			type = "";
		}
		Enum<REPORT_TYPE> fileType = ReportUtils.getReportType(type);
		List<String> groupField = new ArrayList<String>();
		groupField.add("srcRegionName");
		NmsLinkDto nmsLinkDto = JsonUtils.toBean(params, NmsLinkDto.class);
		nmsLinkDto.setGroupField(groupField);
		// alarmData.setAlarmState("1");
		// alarmData.setOprtState("30");
		// alarmData.setOprtState("00A");
		// List<ReportResultDto>
		// resultList=reportService.selectReportResultList(new ReportResult());
		// List<Map<String,Object>>
		// dataSet=ReportUtils.convertReportData(resultList);
		List<NmsLinkDto> dataSet = reportService.selectViewList(nmsLinkDto);
		// List<Map<String,Object>> dataSet=JsonUtils.toMapList(alarmDataList);
		// PropertyDescriptor[]
		// descriptors=BeanUtils.getPropertyDescriptors(AlarmData.class);
		PropertyDescriptor[] descriptors = BeanUtils
				.getPropertyDescriptors(NmsLinkDto.class);
		for (int i = 0; i < descriptors.length; i++) {
			String fieldName = descriptors[i].getName();
			if (fieldName.equals("class") || !headerMap.containsKey(fieldName))
				continue;
			reportParameterMap.put(fieldName, headerMap.get(fieldName)
					.toString());
			fieldMap.put(fieldName, descriptors[i].getPropertyType().getName()
					.toString());
		}
		String templateName = "table.jrxml.tpl";
		String reportName = "链路数据";

		// columnList.add("id");
		// columnList.add("name");
		// columnList.add("desc");
		templateParameter.setFieldMap(fieldMap);
		templateParameter.setGroupField(nmsLinkDto.getGroupField());
		parameterList.add("title");
		templateParameterMap.put("title", "java.lang.String");
		reportParameterMap.put("title", reportName);
		templateParameter.setParameterMap(templateParameterMap);
		ReportUtils.setHeaders(templateParameter, headerMap);
		// templateParameter.setHeaders(columnList);
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

	@RequestMapping("saveOrUpdateReportTask")
	@ResponseBody
	public int saveOrUpdateReportTask(String action, ReportTask reportTask,
			Quartz quartz) {
		String cron = QuartzUtil.convertToCron(quartz,
				reportTask.getSamplTimeType());
		reportTask.setSamplTimeRule(cron);
		if (action.equals("add")) {
			return reportService.saveReportTask(reportTask);
		} else {
			return reportService.updateReportTask(reportTask);
		}
	}

	@RequestMapping("deleteReportTask")
	@ResponseBody
	public int deleteReportTask(ReportTask reportTask) {
		return reportService.deleteReportTask(reportTask);
	}

	@RequestMapping("findReportTaskById")
	@ResponseBody
	public ReportTaskDto findReportTaskById(ReportTask reportTask) {
		ReportTask task = reportService.findReportTaskById(reportTask);
		ReportTaskDto dto = new ReportTaskDto();
		BeanUtils.copyProperties(task, dto);
		Quartz quartz = QuartzUtil.convertToQuartz(dto.getSamplTimeRule());
		dto.setQuartz(quartz);
		return dto;
	}

	// 江苏电信副本库运营监控
	@RequestMapping("hostMonitoring")
	@ResponseBody
	public Page<HostMonitoringDto> findTopoSymbolPage(
			NmsTopoSymbol nmsTopoSymbol, Page<HostMonitoringDto> page) {
		List<HostMonitoringDto> topoList = copyLibraryService
				.findHostMonitoring();
		System.out.println("集合是：" + topoList);
		page.setResult(topoList);
		return page;
	}

	// 数据库监控
	@RequestMapping("dataMonitoring")
	@ResponseBody
	public Page<HostMonitoringDto> findDataMonitoring(
			NmsTopoSymbol nmsTopoSymbol, Page<HostMonitoringDto> page) {
		List<HostMonitoringDto> topoList = copyLibraryService
				.findDatabaseMonitoring();
		System.out.println("集合是：" + topoList);
		page.setResult(topoList);
		return page;
	}

	// 数据库同步
	@RequestMapping("dataSynchronization")
	@ResponseBody
	public Page<HostMonitoringDto> findDataSynchronization(
			Page<HostMonitoringDto> page) {
		// List<HostMonitoringDto> topoList =
		// copyLibraryService.findDatabaseMonitoring();
		List<HostMonitoringDto> topoList = new ArrayList<HostMonitoringDto>();
		topoList.add(new HostMonitoringDto());
		System.out.println("集合是：" + topoList);
		page.setResult(topoList);
		return page;
	}

	// 江苏电信副本库运营监控excel导出功能

	@RequestMapping("exportCopyLibrary.do")
	private void exportCopyLibrary(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<List<String>> list=copyLibraryService.exportCopyLibrary();
		Exporter ex = ExportExcelFactory.create(EXPORT_TYPE.GRID_TO_EXCEL);
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMdd");
		String filename = "江苏电信副本库运营监控日报" + sdf.format(new Date());
		filename = URLEncoder.encode("江苏电信副本库运营监控日报" + sdf.format(new Date()),
				"UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ filename + ".xls");// 设定输出文件头
		response.setContentType("application/msexcel");// 定义输出类型
		SimpleDateFormat namesdf = new SimpleDateFormat("(yyyy年MM月dd日)");
		ex.export(response.getOutputStream(), list,
				"江苏电信副本库运营监控日报" + namesdf.format(new Date()));
		ReportCache reportCache  = ReportCache.getInstance();
		reportCache = null;
	}

	@RequestMapping("saveOIPData")
	@ResponseBody
	public void saveOIPData(
			@RequestParam(value = "sys", required = false) String sys,
			@RequestParam(value = "run", required = false) String run,
			@RequestParam(value = "problem", required = false) String problem,
			@RequestParam(value = "work", required = false) String work,
			HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("------------传送数据--------------");
		ReportCache reportCache = ReportCache.getInstance();
		reportCache.addCache("sys", sys);
		reportCache.addCache("run", run);
		reportCache.addCache("problem", problem);
		reportCache.addCache("work", work);
	}

	@RequestMapping("exportReportOIP")
	public void exportReportOIP(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReportCache reportCache = ReportCache.getInstance();
		// System.out.println("-----------------导出数据-------------------");
		// System.out.println(reportCache.getCache("sys"));
		// System.out.println(reportCache.getCache("run"));
		// System.out.println(reportCache.getCache("problem"));
		// System.out.println(reportCache.getCache("work"));

		List<List<String>> list = new ArrayList<List<String>>();
		List<String> listz = new ArrayList<String>();
		listz.add("系统情况检查");
		list.add(listz);
		List<String> listT = new ArrayList<String>();
		listT.add("序号");
		listT.add("检查项");
		listT.add("状态");
		listT.add("责任人");
		listT.add("说明");
		listT.add("达标率");
		list.add(listT);
		String system = (String) reportCache.getCache("sys");
		List<ReportIOPDto> systemData = JsonUtils.toList(system,
				ReportIOPDto.class);
		for (Iterator<ReportIOPDto> iterator = systemData.iterator(); iterator
				.hasNext();) {
			ReportIOPDto report = (ReportIOPDto) iterator.next();
			List<String> listVal = new ArrayList<String>();
			if (!"".equals(report.getId()) && report.getId() != null) {
				listVal.add(report.getId().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getCheckItem())
					&& report.getCheckItem() != null) {
				listVal.add(report.getCheckItem().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getStatus()) && report.getStatus() != null) {
				listVal.add(report.getStatus().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getResponsible())
					&& report.getResponsible() != null) {
				listVal.add(report.getResponsible().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getExplanation())
					&& report.getExplanation() != null) {
				listVal.add(report.getExplanation().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getRate()) && report.getRate() != null) {
				listVal.add(report.getRate().toString());
			} else {
				listVal.add("");
			}
			listVal.add("sys");
			list.add(listVal);
		}
		// 运行指标监控
		List<String> runTitle = new ArrayList<String>();
		runTitle.add("运行指标监控");
		list.add(runTitle);
		List<String> runProperty = new ArrayList<String>();
		runProperty.add("序号");
		runProperty.add("监控指标");
		runProperty.add("监控对象");
		runProperty.add("结果");
		runProperty.add("责任人");
		runProperty.add("说明");
		runProperty.add("达标率");
		list.add(runProperty);
		String run = (String) reportCache.getCache("run");
		List<ReportIOPDto> runData = JsonUtils.toList(run, ReportIOPDto.class);
		for (Iterator<ReportIOPDto> iterator = runData.iterator(); iterator
				.hasNext();) {
			ReportIOPDto report = (ReportIOPDto) iterator.next();
			List<String> listVal = new ArrayList<String>();
			if (!"".equals(report.getId()) && report.getId() != null) {
				listVal.add(report.getId().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getIndex()) && report.getIndex() != null) {
				listVal.add(report.getIndex().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getObject()) && report.getObject() != null) {
				listVal.add(report.getObject().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getResult()) && report.getResult() != null) {
				listVal.add(report.getResult().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getResponsible())
					&& report.getResponsible() != null) {
				listVal.add(report.getResponsible().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getExplanation())
					&& report.getExplanation() != null) {
				listVal.add(report.getExplanation().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getRate()) && report.getRate() != null) {
				listVal.add(report.getRate().toString());
			} else {
				listVal.add("");
			}
			listVal.add("run");
			list.add(listVal);
		}
		// 问题跟踪
		List<String> problemTitle = new ArrayList<String>();
		problemTitle.add("问题跟踪");
		list.add(problemTitle);
		List<String> problemProperty = new ArrayList<String>();
		problemProperty.add("序号");
		problemProperty.add("问题名称");
		problemProperty.add("创建时间");
		problemProperty.add("状态");
		problemProperty.add("责任人");
		problemProperty.add("问题现象");
		problemProperty.add("解决情况");
		list.add(problemProperty);
		String problem = (String) reportCache.getCache("problem");
		List<ReportIOPDto> problemDate = JsonUtils.toList(problem,
				ReportIOPDto.class);
		for (Iterator<ReportIOPDto> iterator = problemDate.iterator(); iterator
				.hasNext();) {
			ReportIOPDto report = (ReportIOPDto) iterator.next();
			List<String> listVal = new ArrayList<String>();
			if (!"".equals(report.getId()) && report.getId() != null) {
				listVal.add(report.getId().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getName()) && report.getName() != null) {
				listVal.add(report.getName().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getCreateDate())
					&& report.getCreateDate() != null) {
				listVal.add(report.getCreateDate().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getStatus()) && report.getStatus() != null) {
				listVal.add(report.getStatus().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getResponsible())
					&& report.getResponsible() != null) {
				listVal.add(report.getResponsible().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getPhenomenon())
					&& report.getPhenomenon() != null) {
				listVal.add(report.getPhenomenon().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getSituation())
					&& report.getSituation() != null) {
				listVal.add(report.getSituation().toString());
			} else {
				listVal.add("");
			}
			listVal.add("problem");
			list.add(listVal);
		}

		// 专项工作
		List<String> workTitle = new ArrayList<String>();
		workTitle.add("专项工作");
		list.add(workTitle);
		List<String> workProperty = new ArrayList<String>();
		workProperty.add("序号");
		workProperty.add("工作项");
		workProperty.add("工作计划");
		workProperty.add("责任人");
		workProperty.add("进度");
		workProperty.add("说明");
		list.add(workProperty);
		String work = (String) reportCache.getCache("work");
		List<ReportIOPDto> workData = JsonUtils
				.toList(work, ReportIOPDto.class);
		for (Iterator<ReportIOPDto> iterator = workData.iterator(); iterator
				.hasNext();) {
			ReportIOPDto report = (ReportIOPDto) iterator.next();
			List<String> listVal = new ArrayList<String>();
			if (!"".equals(report.getId()) && report.getId() != null) {
				listVal.add(report.getId().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getWork()) && report.getWork() != null) {
				listVal.add(report.getWork().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getPlan()) && report.getPlan() != null) {
				listVal.add(report.getPlan().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getResponsible())
					&& report.getResponsible() != null) {
				listVal.add(report.getResponsible().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getPlanRate())
					&& report.getPlanRate() != null) {
				listVal.add(report.getPlanRate().toString());
			} else {
				listVal.add("");
			}
			if (!"".equals(report.getExplanation())
					&& report.getExplanation() != null) {
				listVal.add(report.getExplanation().toString());
			} else {
				listVal.add("");
			}
			listVal.add("work");
			list.add(listVal);
		}
		Exporter ex = ExportExcelFactory.create(EXPORT_TYPE.GRID_TO_EXCEL);
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMdd");
		String filename = "江苏OIP运行日报" + sdf.format(new Date());
		filename = URLEncoder.encode("江苏OIP运行日报" + sdf.format(new Date()),
				"UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ filename + ".xls");// 设定输出文件头
		response.setContentType("application/msexcel");// 定义输出类型
		SimpleDateFormat namesdf = new SimpleDateFormat("(yyyy年MM月dd日)");
		ex.exportOIP(response.getOutputStream(), list, "江苏电信OIP系统运维工作日报"
				+ namesdf.format(new Date()));
		// reportCache.removeCache();
		reportCache = null;
	}

	//卡资源报表
	@RequestMapping("saveCard")
	@ResponseBody
	public void saveCard(
			@RequestParam(value = "maintain", required = false) String maintain,
			@RequestParam(value = "groupCard", required = false) String groupCard,
			@RequestParam(value = "resale", required = false) String resale,
			@RequestParam(value = "three", required = false) String three,
			@RequestParam(value = "four", required = false) String four,
			HttpServletRequest request, HttpServletResponse response) {

		ReportCache reportCache = ReportCache.getInstance();
		reportCache.addCache("maintain", maintain);
		reportCache.addCache("groupCard", groupCard);
		reportCache.addCache("resale", resale);
		reportCache.addCache("three", three);
		reportCache.addCache("four", four);
	}
	
	@RequestMapping("exportCard")
	public void exportCard(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		List<List<String>> list=copyLibraryService.dealCard();
//		List<List<Object>> list1=copyLibraryService.dealFJ();
		Exporter ex = ExportExcelFactory.create(EXPORT_TYPE.GRID_TO_EXCEL);
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMdd");
		String filename = "江苏电信UIM卡系统工作日报" + sdf.format(new Date());
		filename = URLEncoder.encode("江苏电信UIM卡系统工作日报" + sdf.format(new Date()),
				"UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ filename + ".xls");// 设定输出文件头
		response.setContentType("application/msexcel");// 定义输出类型
		SimpleDateFormat namesdf = new SimpleDateFormat("(yyyy年MM月dd日)");
		List<List<Object>> list1=copyLibraryService.dealFJ("_10_");
		List<List<Object>> list2=copyLibraryService.dealwfd("-3G-");
		List<List<Object>> list3=copyLibraryService.dealFJ("_Z");
		ex.exportCard(response.getOutputStream(), list,list1,list2,list3, "江苏电信UIM卡系统工作日报"
				+ namesdf.format(new Date()));
		// reportCache.removeCache();
		ReportCache reportCache  = ReportCache.getInstance();
		reportCache = null;
	}

	@RequestMapping("findHostHeader")
	@ResponseBody
	public Map<String,Object> findHostHeader(HttpServletRequest request,KpiCodeData kpiCodeData){
		return copyLibraryService.findHostHeader(kpiCodeData);
	}
	@RequestMapping("findDBHeader")
	@ResponseBody
	public Map<String,Object> findDBHeader(HttpServletRequest request,KpiCodeData kpiCodeData){
		return copyLibraryService.findDBHeader(kpiCodeData);
	}
	@RequestMapping("findDBSHeader")
	@ResponseBody
	public Map<String,Object> findDBSHeader(HttpServletRequest request,KpiCodeData kpiCodeData){
		return copyLibraryService.findDBSHeader(kpiCodeData);
	}
	
	@RequestMapping("findHostData.do")
	@ResponseBody
	public Page<Map<String, Object>> findHostData(HttpServletRequest request,Page<Map<String, Object>> page){
		return copyLibraryService.findHostData(request, page);
	}
	
	@RequestMapping("findDBData.do")
	@ResponseBody
	public Page<Map<String, Object>> findDBData(HttpServletRequest request,Page<Map<String, Object>> page){
		return copyLibraryService.findDBData(request, page);
	}
	
	@RequestMapping("findSynData.do")
	@ResponseBody
	public Page<Map<String, Object>> findSynData(HttpServletRequest request,Page<Map<String, Object>> page){
		return copyLibraryService.findSynData(request, page);
	}
	//副本库
	@RequestMapping("saveLibrary")
	@ResponseBody
	public void saveLibrary(
			@RequestParam(value = "host", required = false) String host,
			@RequestParam(value = "hs", required = false) String hs,
			@RequestParam(value = "dbm", required = false) String dbm,
			@RequestParam(value = "ds", required = false) String ds,
			@RequestParam(value = "dbms", required = false) String dbms,
			@RequestParam(value = "dss", required = false) String dss,
			@RequestParam(value = "hostIndex", required = false) String hostIndex,
			@RequestParam(value = "hostHeader", required = false) String hostHeader,
			@RequestParam(value = "dbIndex", required = false) String dbIndex,
			@RequestParam(value = "dbHeader", required = false) String dbHeader,
			@RequestParam(value = "synIndex", required = false) String synIndex,
			@RequestParam(value = "synHeader", required = false) String synHeader,
			HttpServletRequest request, HttpServletResponse response) {

		ReportCache reportCache = ReportCache.getInstance();
		reportCache.addCache("host", host);
		reportCache.addCache("hs", hs);
		reportCache.addCache("dbm", dbm);
		reportCache.addCache("ds", ds);
		reportCache.addCache("dbms", dbms);
		reportCache.addCache("dss", dss);
		reportCache.addCache("hostIndex", hostIndex);
		reportCache.addCache("hostHeader", hostHeader);
		reportCache.addCache("dbIndex", dbIndex);
		reportCache.addCache("dbHeader", dbHeader);
		reportCache.addCache("synIndex", synIndex);
		reportCache.addCache("synHeader", synHeader);
	}
	
	//oip系统检查数据
	@RequestMapping("findSystemData.do")
	@ResponseBody
	public Page<ReportIOPDto> findSystemData( Page<ReportIOPDto> page) {
		List<ReportIOPDto> list = copyLibraryService.findSystemData();
		System.out.println("集合是：" + list);
		copyLibraryService.findCard("_10_");
		page.setResult(list);
		return page;
	}
	
	//oip運行情況数据
	@RequestMapping("findRunMonitoringData.do")
	@ResponseBody
	public Page<ReportIOPDto> findRunMonitoringData( Page<ReportIOPDto> page) {
		List<ReportIOPDto> list = copyLibraryService.findRunMonitoting();
		System.out.println("集合是：" + list);
		page.setResult(list);
		return page;
	}
	
	//创建报表工具下拉框选项
	@RequestMapping("addDcSql.do")
	@ResponseBody
	public String addDcSql(DcSql dcSql){
		String success=null;
		if(copyLibraryService.addDcSql(dcSql)==true){
			success="{success:true,msg:'true'}";
		}else{
			success="{success:false,msg:'false'}";
		}
		return success;
	}
	@RequestMapping("updateDcSql.do")
	@ResponseBody
	public String updateDcSql(DcSql dcSql){
		String success=null;
		if(copyLibraryService.updateDcSql(dcSql)==true){
			success="{success:true,msg:'true'}";
		}else{
			success="{success:false,msg:'false'}";
		}
		return success;
	}
	@RequestMapping("deleteDcSql.do")
	@ResponseBody
	public String deleteDcSql(String id){
		String success=null;
		if(copyLibraryService.deleteDcSql(id)==true){
			success="{success:true,msg:'true'}";
		}else{
			success="{success:false,msg:'false'}";
		}
		return success;
	}
	@RequestMapping("findDcSql.do")
	@ResponseBody
	public List<DcSql> findDcSql(){
		return copyLibraryService.findDcSql();
	}
	
	@RequestMapping("findCard.do")
	@ResponseBody
	public Page<Map<String, Object>> findCard(@RequestParam(value = "search", required = false) String search,Page<Map<String, Object>> page){
		List<Map<String, Object>> list=copyLibraryService.findCard(search);
		page.setResult(list);
		page.setTotalCount(list.size());
		return page;			
	}
	@RequestMapping("findAreaCard.do")
	@ResponseBody
	public Page<Map<String, Object>> findAreaCard(@RequestParam(value = "search", required = false) String search,String type,Page<Map<String, Object>> page){
		List<Map<String, Object>> list=copyLibraryService.findAreaCard(search, type);
		page.setResult(list);
		page.setTotalCount(list.size());
		return page;			
	}
}
