<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="${reportName}" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="30" bottomMargin="30" isFloatColumnFooter="true" uuid="1d8b126a-7036-4e8b-81a6-ce194583465c">
	<property name="net.sf.jasperreports.export.pdf.tagged" value="true"/>
	<property name="net.sf.jasperreports.export.pdf.tag.language" value="ZH-CN"/>
	<property name="ireport.zoom" value="1.0"/>
	<property name="ireport.x" value="0"/>
	<property name="ireport.y" value="0"/>
	<#list parameterMap?keys as key>
			<parameter name="${key}" class="java.lang.String"/>
	</#list>
	<#list fieldMap?keys as key>
			<parameter name="${key}" class="java.lang.String"/>
	</#list>
	<#list fieldMap?keys as key>
			<field name="${key}" class="${fieldMap[key]}"/>
	</#list>
	<!--
	<variable name="REGION_ID_AVERAGE" class="java.math.BigDecimal" resetType="Group" resetGroup="regionGroup" calculation="Average">
		<variableExpression><![CDATA[$F{regionId}]]></variableExpression>
	</variable>
	<variable name="REGION_ID_COUNT" class="java.math.BigDecimal" resetType="Group" resetGroup="regionGroup" calculation="Count">
		<variableExpression><![CDATA[$F{regionId}+"-"+$F{alarmCount}]]></variableExpression>
	</variable>
	<variable name="TOTAL_COUNT" class="java.math.BigDecimal" resetType="Group" resetGroup="SummaryDummyGroup" calculation="Count">
		<variableExpression><![CDATA[$V{PAGE_NUMBER}]]></variableExpression>
	</variable>
	-->
	<#if groupField?exists>
		<variable name="GROUP_COUNT" class="java.math.BigDecimal" resetType="Group" resetGroup="reportGroup" calculation="Count">
		<variableExpression><![CDATA[<#list groupField as group><#if group_index!=(groupField?size-1)>$F{${group}}+"-"+</#if><#if group_index==(groupField?size-1)>$F{${group}}</#if></#list>]]></variableExpression>
		</variable>
		<group name="SummaryDummyGroup">
			<groupFooter>
				<band height="60">
					<textField isStretchWithOverflow="true">
						<reportElement x="0" y="20" width="555" height="15" uuid="7490a565-68a0-4e68-a404-ba1ff86995f0"/>
						<textElement textAlignment="Center" verticalAlignment="Middle">
							<font size="14" pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
						</textElement>
						<textFieldExpression><![CDATA["共" + 
						String.valueOf($V{REPORT_COUNT}) + 
						"条记录"]]></textFieldExpression>
					</textField>
				</band>
			</groupFooter>
		</group>
	</#if>
	<#if groupField?exists>
	<group name="reportGroup">
		<groupExpression><![CDATA[<#list groupField as group><#if group_index!=(groupField?size-1)>$F{${group}}+"-"+</#if><#if group_index==(groupField?size-1)>$F{${group}}</#if></#list>]]></groupExpression>
		<groupHeader>
			<band height="25">
				<textField evaluationTime="Group" evaluationGroup="reportGroup">
					<reportElement x="0" y="0" width="555" height="25" uuid="4fc2aa88-efe0-4243-a789-7146270cff4c"/>
					<box>
						<leftPen lineWidth="1.0"/>
						<bottomPen lineWidth="1.0"/>
						<rightPen lineWidth="1.0"/>
					</box>
					<textElement textAlignment="Left" verticalAlignment="Middle">
						<font pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
					</textElement>
					<textFieldExpression><![CDATA[
					<#list groupField as group>
						<#if group_index!=(groupField?size-1)>$P{${group}}+":"+$F{${group}}+","+</#if>
						<#if group_index==(groupField?size-1)>$P{${group}}+":"+$F{${group}}</#if>
					</#list>
					+" 共" + String.valueOf($V{GROUP_COUNT}) + "条记录"
					]]></textFieldExpression>
				</textField>
			</band>
		</groupHeader>
	</group>
	</#if>
	<title>
		<band height="50">
			<textField>
				<reportElement x="0" y="0" width="555" height="40" uuid="7891f99b-c027-430c-9616-b6debbf080ec">
					<property name="net.sf.jasperreports.export.pdf.tag.h1" value="full"/>
				</reportElement>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="22" pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{title}]]></textFieldExpression>
			</textField>
		</band>
	</title>
	<pageHeader>
		<band height="20">
			<#list headers as header>
			<textField>
				<#if header_index!=(headers?size-1)>
				<reportElement mode="Opaque" x="${header_index*columnWidth}" y="0" width="${columnWidth}" height="20" backcolor="#808080" uuid="dae559e2-5a66-4beb-a4cc-ea0abd1cd47b">
				</#if>
				<#if header_index==(headers?size-1)>
				<reportElement mode="Opaque" x="${header_index*columnWidth}" y="0" width="${lastColumnWidth}" height="20" backcolor="#808080" uuid="dae559e2-5a66-4beb-a4cc-ea0abd1cd47b">
				</#if>
					<#if header_index==0>
					<property name="net.sf.jasperreports.export.pdf.tag.table" value="start"/>
					<property name="net.sf.jasperreports.export.pdf.tag.tr" value="start"/>
					</#if>
					<#if header_index==(headers?size-1)>
					<property name="net.sf.jasperreports.export.pdf.tag.table" value="end"/>
					<property name="net.sf.jasperreports.export.pdf.tag.tr" value="end"/>
					</#if>
					<property name="net.sf.jasperreports.export.pdf.tag.th" value="full"/>
				</reportElement>
				<box>
					<pen lineColor="#000000"/>
					<topPen lineWidth="1.0"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<#if header_index==(headers?size-1)>
					<rightPen lineWidth="1.0" />
					</#if>
				</box>
				<textElement  textAlignment="Center" verticalAlignment="Middle">
					<font size="12" pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{${header}}]]></textFieldExpression>
			</textField>
			</#list>
		</band>
	</pageHeader>
	<detail>
		<band height="15" splitType="Prevent">
			<frame>
				<reportElement x="0" y="0" width="555" height="15" uuid="069970d2-09de-4d2d-b6c5-84ea52e6fd90">
					<property name="net.sf.jasperreports.export.pdf.tag.tr" value="full"/>
				</reportElement>
				<#list headers as header>
				<textField isBlankWhenNull="true" isStretchWithOverflow="true">
				<#if header_index!=(headers?size-1)>
					<reportElement stretchType="RelativeToBandHeight" x="${header_index*columnWidth}" y="0" width="${columnWidth}" height="15" uuid="8aecb1e3-2ca2-4efd-997b-68af11558793">
				</#if>
				<#if header_index==(headers?size-1)>
					<reportElement stretchType="RelativeToBandHeight" x="${header_index*columnWidth}" y="0" width="${lastColumnWidth}" height="15" uuid="8aecb1e3-2ca2-4efd-997b-68af11558793">
				</#if>
						<property name="net.sf.jasperreports.export.pdf.tag.td" value="full"/>
					</reportElement>
					<box>
						<leftPen lineWidth="1.0"/>
						<bottomPen lineWidth="0.5"/>
						<#if header_index==(headers?size-1)>
						<rightPen lineWidth="1.0" />
						</#if>
					</box>
					<textElement textAlignment="Center" verticalAlignment="Middle">
							<font size="10" pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
					</textElement>
					<#if fieldMap[header]=="java.util.Date">
					<textFieldExpression><![CDATA[new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒").format($F{${header}})]]></textFieldExpression>
					</#if>
					<#if fieldMap[header]!="java.util.Date">
					<textFieldExpression><![CDATA[$F{${header}}]]></textFieldExpression>
					</#if>
				</textField>
				</#list>
			</frame>
		</band>
	</detail>
	<pageFooter>
		<band height="40">
			<textField evaluationTime="Now">
				<reportElement x="0" y="10" width="277" height="30" uuid="7891f99b-c027-430c-9616-b6debbf080ec">
					<property name="net.sf.jasperreports.export.pdf.tag.h1" value="full"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="14" pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA["第"+String.valueOf($V{PAGE_NUMBER})+"页/共"]]></textFieldExpression>
			</textField>
			<textField evaluationTime="Report" >
				<reportElement x="277" y="10" width="278" height="30" uuid="7891f99b-c027-430c-9616-b6debbf080ec">
					<property name="net.sf.jasperreports.export.pdf.tag.h1" value="full"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="14" pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA[String.valueOf($V{PAGE_NUMBER})+"页"]]></textFieldExpression>
			</textField>
		</band>
	</pageFooter>
</jasperReport>
