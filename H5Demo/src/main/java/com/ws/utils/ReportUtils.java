package com.ws.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.ztesoft.dto.ReportRequest;
import com.ztesoft.dto.ReportResultDto;
import com.ztesoft.dto.TemplateParameter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ReportUtils {
	
    private static JRAbstractExporter exporter;
    private static Configuration cfg = new Configuration();
    static {
    	cfg.setClassForTemplateLoading(ReportUtils.class, "/template/report");
    	cfg.setEncoding(Locale.CHINA, "UTF-8");
    }

	public static enum REPORT_TYPE {
		PDF, HTML, EXCEL, RTF
	}
	public static String FILE_TYPE_PDF=".pdf";
	public static String FILE_TYPE_HTML=".html";
	public static String FILE_TYPE_EXCEL=".xls";
	public static String FILE_TYPE_RTF=".rtf";
    
    public static void exportReport(HttpServletRequest request,HttpServletResponse response,ReportRequest reportRequest) {
    	response.setCharacterEncoding("UTF-8");
    	Set<String> fieldSet=reportRequest.getTemplateParameter().getFieldMap().keySet();
    	Integer columnWidth=555/fieldSet.size();
    	reportRequest.getTemplateParameter().setColumnWidth(columnWidth);
    	reportRequest.getTemplateParameter().setLastColumnWidth(555-columnWidth*fieldSet.size()+columnWidth);
    	reportRequest.getTemplateParameter().setTotalCount(reportRequest.getDataSet().size());
    	if (REPORT_TYPE.PDF.equals(reportRequest.getFileType())) {
    		response.setContentType("application/pdf");
    		exporter=new JRPdfExporter();
        } else if (REPORT_TYPE.HTML.equals(reportRequest.getFileType())) {
        	response.setContentType("text/html");
        	exporter = new JRHtmlExporter();
            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        } else if (REPORT_TYPE.EXCEL.equals(reportRequest.getFileType())) {
    		response.setContentType("application/vnd.ms-excel");
            exporter = new JRXlsExporter();
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{reportRequest.getReportName()});
        } else {
    		response.setContentType("application/rtf");
            exporter = new JRRtfExporter();
        }
    	List<?> dataSet=reportRequest.getDataSet();
    	JRDataSource dataSource = new JRBeanCollectionDataSource(dataSet);
		Template template;
		try {
			template = cfg.getTemplate(reportRequest.getTemplateName());
			StringWriter writer=new StringWriter();
			template.process(reportRequest.getTemplateParameter(), writer);
			String content=writer.toString();
			writer.close();
			System.out.println("=============报表模版");
			System.out.println(content);
			System.out.println("=============报表模版");
			JasperDesign design = JRXmlLoader.load(new ByteArrayInputStream(content.getBytes("UTF-8")));
			JasperReport report = JasperCompileManager.compileReport(design);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, reportRequest.getReportParameterMap(),dataSource);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();
			response.setContentLength(baos.size());
			response.setHeader("Content-Disposition", "inline;filename="+URLEncoder.encode(reportRequest.getReportName(),"UTF-8"));
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			out.close();
		}catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    /*******************************************************************************
	 * 方法名称:exportCustomFile  说明：导出自定义文件
	 * @param HttpServletRequest,HttpServletResponse
	 * @ANTHOR GUO.WEI UR(633632) 20150120
	 ******************************************************************************/
    public static void exportCustomFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	response.setCharacterEncoding("UTF-8");
    	//纯文件输出
        try {
        	String fileName = request.getAttribute("fileName").toString();
//        	new String(request.getAttribute("fileName").toString().getBytes("iso-8859-1"),"utf-8");
        	String fileData = request.getAttribute("fileData").toString();
//        	new String(request.getAttribute("fileData").toString().getBytes("iso-8859-1"),"utf-8");
        	response.reset(); 
        	response.setContentType("");
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
			ServletOutputStream outStream = response.getOutputStream();
			outStream.write(fileData.getBytes("UTF-8"));
			outStream = response.getOutputStream();
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    /** 
     * 将指定文件夹打包成zip 
     * @param folder 
     * @throws IOException  
     */  
//    public static void zipFile(String folder) throws IOException {  
//        File zipFile = new File(folder + ".zip");  
//        if (zipFile.exists()) {  
//            zipFile.delete();  
//        } 
//        ZipOutputStream zipout = null;
//        FileInputStream fileInputStream = null;
//        BufferedInputStream origin = null;//刚增加
//        try{
//	        zipout = new ZipOutputStream(new FileOutputStream(zipFile));  
//	        File dir = new File(folder);  
//	        File[] fs = dir.listFiles();  
//	        byte[] buf = null;  
//	        if(fs!=null){  
//	            for (File f : fs) {  
//	                zipout.putNextEntry(new ZipEntry(f.getName()));  
//	                fileInputStream = new FileInputStream(f);  
//	                buf = new byte[2048];  
//	                origin = new BufferedInputStream(fileInputStream,2048);  
//	                int len;  
//	                while ((len = origin.read(buf,0,2048))!=-1) {  
//	                    zipout.write(buf,0,len);  
//	                }  
//	            }
//	                zipout.flush();  
//	                origin.close(); //刚增加
//	                fileInputStream.close();
//	            zipout.close(); 
//	        }  
//        }
//        catch(IOException e){
//        	e.printStackTrace();
//        	throw e; 
//        }finally{
//        	if(zipout!=null){
//        		zipout.flush();  
//                zipout.close(); 
//        	}
//        	if(origin!=null){ //刚增加
//        		origin.close(); 
//        	}	
//        	if(fileInputStream!=null){
//        		fileInputStream.close();  
//        	}
//        }
//      
//    }  
//    
//    public class FileTest {
//    	public static void main(String[] args)   {
//    	 File  file=new File("c:\\test.txt");
//    	 BufferedReader read=null;
//    	 BufferedWriter writer=null;
//    	 try {
//    	   writer=new BufferedWriter(new  FileWriter("c:\\zwm.txt"));
//    	 } catch (IOException e1) {
//    	  e1.printStackTrace();
//    	 }
//    	 try {
//    	   read=new BufferedReader(new  FileReader(file));
//    	   String tempString = null;
//    	   while((tempString=read.readLine())!=null){
//    	    writer.append(tempString);
//    	    writer.newLine();//换行
//    	    writer.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
//    	   }
//    	   read.close();
//    	   writer.close();
//    	   System.out.println("文件写入完成...");
//    	 } catch (IOException e) {
//    	  e.printStackTrace();
//    	 }
//
//    	}
//    	}
    public static Enum<REPORT_TYPE> getReportType(String type){
    	Enum<REPORT_TYPE> fileType= REPORT_TYPE.PDF;
    	if(type.equals("pdf")){
			fileType= REPORT_TYPE.PDF;
		}else if(type.equals("html")){
			fileType= REPORT_TYPE.HTML;

		}else if(type.equals("xls")){
			fileType= REPORT_TYPE.EXCEL;

		}else if(type.equals("rft")){
			fileType= REPORT_TYPE.RTF;
		}
    	return fileType;
    }
    public static void setHeaders(TemplateParameter templateParameter,LinkedHashMap<String, Object> headerMap){
    	Iterator<String> it=headerMap.keySet().iterator();
    	List<String> headers=new ArrayList<String>();
		while(it.hasNext()){
			String key=it.next();
			headers.add(key);
		}
		templateParameter.setHeaders(headers);
    }
    public static List<Map<String,Object>> convertReportData(List<ReportResultDto> resultList){
    	List<Map<String,Object>> dataSet=new ArrayList<Map<String,Object>>();
    	short previousResultSeq=0;
    	short resultSeq=1;
    	short maxResultSeq=0;
		Map<String,Object> record=new HashMap<String, Object>();
		for (int i = 0; i < resultList.size(); i++) {
			if(i==0){
				resultSeq=resultList.get(i).getResultSeq();
				continue;
			}else{
				previousResultSeq=resultSeq;
				resultSeq=resultList.get(i).getResultSeq();
			}
			if(previousResultSeq==resultSeq){
				maxResultSeq=1;
				break;
			}
			if(previousResultSeq>resultSeq){
				maxResultSeq=previousResultSeq;
				break;
			}
		}
    	for (ReportResultDto reportResultDto : resultList) {
			record.put(reportResultDto.getColumnName(), reportResultDto.getColumnValue());
    		if(reportResultDto.getResultSeq()==maxResultSeq){
    			dataSet.add(record);
				record=new HashMap<String, Object>();
    		}
		}
    	return dataSet;
    }    
}

