package com.ws.dto;

import java.util.List;
import java.util.Map;

public class TemplateParameter {
	private Map<String, Object> fieldMap;
	private Map<String, Object> parameterMap;
	private List<String> headers;
	private List<String> groupField;
	private Integer columnWidth;
	private Integer lastColumnWidth;
	private Integer totalCount;
	private String reportName="data";
	
	public Map<String, Object> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, Object> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public Integer getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(Integer columnWidth) {
		this.columnWidth = columnWidth;
	}

	public Integer getLastColumnWidth() {
		return lastColumnWidth;
	}

	public void setLastColumnWidth(Integer lastColumnWidth) {
		this.lastColumnWidth = lastColumnWidth;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<String> getGroupField() {
		return groupField;
	}

	public void setGroupField(List<String> groupField) {
		this.groupField = groupField;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
}
