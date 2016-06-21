<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="demo" language="groovy" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20">
	<property name="ireport.zoom" value="1.0"/>
	<property name="ireport.x" value="0"/>
	<property name="ireport.y" value="33"/>
	<subDataset name="dataset1"/>
	<parameter name="title" class="java.lang.String"/>
	<#list headers as header>
			<parameter name="${header}" class="java.lang.String"/>
	</#list>
	<queryString language="json">
		<![CDATA[]]>
	</queryString>
	<#list headers as header>
			<field name="${header}" class="java.lang.String"/>
	</#list>
	<background>
		<band splitType="Stretch"/>
	</background>
	<title>
		<band height="79" splitType="Stretch">
			<textField>
				<reportElement x="227" y="29" width="100" height="20"/>
				<textElement>
					<font pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{title}]]></textFieldExpression>
			</textField>
		</band>
	</title>
	<pageHeader>
		<band height="35" splitType="Stretch">
		<#list headers as header>
			<textField>
				<reportElement x="${header_index*200}" y="15" width="100" height="20"/>
				<textElement>
					<font pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$P{${header}}]]></textFieldExpression>
			</textField>
		</#list>
		</band>
		
	</pageHeader>
	<columnHeader>
		<band height="61" splitType="Stretch"/>
	</columnHeader>
	<detail>
		<band height="125" splitType="Stretch">
			<#list headers as header>
			<textField>
				<reportElement x="${header_index*200}" y="15" width="100" height="20"/>
				<textElement>
					<font pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{${header}}]]></textFieldExpression>
			</textField>
		</#list>
		</band>
	</detail>
	<columnFooter>
		<band height="45" splitType="Stretch"/>
	</columnFooter>
	<pageFooter>
		<band height="54" splitType="Stretch">
			<textField evaluationTime="Report">
				<reportElement x="227" y="17" width="100" height="20"/>
				<textElement>
					<font pdfFontName="STSong-Light" pdfEncoding="UniGB-UCS2-H" isPdfEmbedded="true"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{PAGE_NUMBER}]]></textFieldExpression>
			</textField>
		</band>
	</pageFooter>
	<summary>
		<band height="42" splitType="Stretch"/>
	</summary>
</jasperReport>
