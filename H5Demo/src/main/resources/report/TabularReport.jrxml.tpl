<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="TabularReport" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="30" bottomMargin="30" isFloatColumnFooter="true" uuid="1d8b126a-7036-4e8b-81a6-ce194583465c">
	<property name="net.sf.jasperreports.export.pdf.tagged" value="true"/>
	<property name="net.sf.jasperreports.export.pdf.tag.language" value="EN-US"/>
	<property name="ireport.zoom" value="1.0"/>
	<property name="ireport.x" value="0"/>
	<property name="ireport.y" value="0"/>
	<title>
		<band height="50">
			<staticText>
				<reportElement x="180" y="5" width="375" height="40" uuid="7891f99b-c027-430c-9616-b6debbf080ec">
					<property name="net.sf.jasperreports.export.pdf.tag.h1" value="full"/>
				</reportElement>
				<textElement textAlignment="Right">
					<font size="22"/>
				</textElement>
				<text><![CDATA[Tabular Report]]></text>
			</staticText>
		</band>
	</title>
	<pageHeader>
		<band height="30">
			<staticText>
				<reportElement mode="Opaque" x="0" y="0" width="100" height="30" backcolor="#808080" uuid="dae559e2-5a66-4beb-a4cc-ea0abd1cd47b">
					<property name="net.sf.jasperreports.export.pdf.tag.table" value="start"/>
					<property name="net.sf.jasperreports.export.pdf.tag.tr" value="start"/>
					<property name="net.sf.jasperreports.export.pdf.tag.th" value="full"/>
					<property name="net.sf.jasperreports.export.pdf.tag.rowspan" value="2"/>
				</reportElement>
				<box leftPadding="10">
					<pen lineColor="#000000"/>
					<topPen lineWidth="1.0"/>
					<leftPen lineWidth="1.0"/>
					<bottomPen lineWidth="0.5"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="12" isBold="true"/>
				</textElement>
				<text><![CDATA[Header 1]]></text>
			</staticText>
			<staticText>
				<reportElement mode="Opaque" x="100" y="0" width="100" height="30" backcolor="#808080" uuid="deeeefe7-4688-45e0-82ab-dd8a3d2a0eee">
					<property name="net.sf.jasperreports.export.pdf.tag.th" value="full"/>
					<property name="net.sf.jasperreports.export.pdf.tag.rowspan" value="2"/>
				</reportElement>
				<box leftPadding="10">
					<pen lineColor="#000000"/>
					<topPen lineWidth="1.0"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="12" isBold="true"/>
				</textElement>
				<text><![CDATA[Header 2]]></text>
			</staticText>
			<staticText>
				<reportElement mode="Opaque" x="200" y="0" width="100" height="30" backcolor="#808080" uuid="e66a84b5-572c-4689-9652-f2a066d0a1d3">
					<property name="net.sf.jasperreports.export.pdf.tag.th" value="full"/>
					<property name="net.sf.jasperreports.export.pdf.tag.rowspan" value="2"/>
				</reportElement>
				<box leftPadding="10">
					<pen lineColor="#000000"/>
					<topPen lineWidth="1.0"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="12" isBold="true"/>
				</textElement>
				<text><![CDATA[Header 3]]></text>
			</staticText>
		</band>
	</pageHeader>
	<detail>
		<band height="15" splitType="Prevent">
			<frame>
				<reportElement x="0" y="0" width="300" height="15" uuid="069970d2-09de-4d2d-b6c5-84ea52e6fd90">
					<property name="net.sf.jasperreports.export.pdf.tag.tr" value="full"/>
				</reportElement>
				<textField isStretchWithOverflow="true">
					<reportElement stretchType="RelativeToTallestObject" x="0" y="0" width="100" height="15" uuid="8aecb1e3-2ca2-4efd-997b-68af11558793">
						<property name="net.sf.jasperreports.export.pdf.tag.td" value="full"/>
					</reportElement>
					<box leftPadding="10">
						<leftPen lineWidth="1.0"/>
						<bottomPen lineWidth="0.5"/>
					</box>
					<textFieldExpression><![CDATA["multi\nline text"]]></textFieldExpression>
				</textField>
				<textField>
					<reportElement stretchType="RelativeToTallestObject" x="100" y="0" width="100" height="15" uuid="c0ee6146-6501-4cf8-95f4-0a9e1c2a8181">
						<property name="net.sf.jasperreports.export.pdf.tag.td" value="full"/>
					</reportElement>
					<box leftPadding="10">
						<leftPen lineWidth="0.5"/>
						<bottomPen lineWidth="0.5"/>
					</box>
					<textFieldExpression><![CDATA["text two"]]></textFieldExpression>
				</textField>
				<textField>
					<reportElement stretchType="RelativeToTallestObject" x="200" y="0" width="100" height="15" uuid="07892ada-8d4e-4527-9d81-c46c0432ff82">
						<property name="net.sf.jasperreports.export.pdf.tag.td" value="full"/>
					</reportElement>
					<box leftPadding="10">
						<leftPen lineWidth="0.5"/>
						<bottomPen lineWidth="0.5"/>
						<rightPen lineWidth="0.5"/>
					</box>
					<textFieldExpression><![CDATA["text three"]]></textFieldExpression>
				</textField>
			</frame>
		</band>
	</detail>
</jasperReport>
