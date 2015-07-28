/**
 * Copyright 2010 Tim Azzopardi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.log4jdbc;

import java.util.List;


import org.slf4j.Logger;

public class ResultSetCollectorPrinter {

	private Logger log;

	public ResultSetCollectorPrinter(Logger log) {
		this.log = log;
	}

	public void printResultSet(ResultSetCollector resultSetCollector) {

		int columnCount = resultSetCollector.getColumnCount();
		int rowIndex=0;
		int totalColumnLength=0;
		int maxLength[] = new int[columnCount];

		for (int column = 1; column <= columnCount; column++) {
			maxLength[column - 1] = resultSetCollector.getColumnName(column)
					.length();
		}
		if (resultSetCollector.getRows() != null) {
			rowIndex=0;
			for (List<Object> printRow : resultSetCollector.getRows()) {
				int colIndex = 0;
				for (Object v : printRow) {
					if (v != null) {
						int length = v.toString().length()+Utilities.getChineseSize(v.toString());
						if (length > maxLength[colIndex]) {
							maxLength[colIndex] = length;
						}
					}
					colIndex++;
				}
				rowIndex++;
				if(resultSetCollector.getMaxRow()!=null&&rowIndex>=resultSetCollector.getMaxRow()){
					break;
				}
			}
		}
		for (int column = 1; column <= columnCount; column++) {
			maxLength[column - 1] = maxLength[column - 1] + 1;
			if(resultSetCollector.getMaxColumn()!=null&&maxLength[column - 1]>resultSetCollector.getMaxColumn()){
				maxLength[column - 1]=resultSetCollector.getMaxColumn();
			}
			totalColumnLength+=maxLength[column - 1];
		}
		totalColumnLength+=columnCount;

		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight("-", maxLength[column - 1],resultSetCollector.getMaxColumn()).replaceAll(" ", "-")
					+ "|");
		}
		println();
		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight(resultSetCollector.getColumnName(column),
					maxLength[column - 1],resultSetCollector.getMaxColumn())
					+ "|");
		}
		println();
		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight("-", maxLength[column - 1],resultSetCollector.getMaxColumn()).replaceAll(" ", "-")
					+ "|");
		}
		println();
		if (resultSetCollector.getRows() != null) {
			rowIndex=0;
			for (List<Object> printRow : resultSetCollector.getRows()) {
				int colIndex = 0;
				print("|");
				for (Object v : printRow) {
					print(padRight(v == null ? "null" : v.toString(),
							maxLength[colIndex] - Utilities.getChineseSize(v.toString()),resultSetCollector.getMaxColumn())
							+ "|");
					colIndex++;
				}
				rowIndex++;
				println();
				if(resultSetCollector.getMaxRow()!=null&&rowIndex>=resultSetCollector.getMaxRow()){
					break;
				}
			}
		}
		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight("-", maxLength[column - 1],resultSetCollector.getMaxColumn()).replaceAll(" ", "-")
					+ "|");
		}
		println();
		if(resultSetCollector.getMaxRow()!=null&&rowIndex>=resultSetCollector.getMaxRow()){
			String str="it's has "+resultSetCollector.getRows().size()+" records,the other records please query db or modify the maxRow params to see more";
			print("|");
			print(padRight(str, totalColumnLength-1,resultSetCollector.getMaxColumn())+ "|");
			println();
			print("|");
			for (int column = 1; column <= columnCount; column++) {
				print(padRight("-", maxLength[column - 1],resultSetCollector.getMaxColumn()).replaceAll(" ", "-")
						+ "|");
			}
			println();
		}
		resultSetCollector.reset();
	}

	public static String padRight(String s, int n,Integer maxColumn) {
		if(maxColumn!=null&&s.length()>n){
			s=s.substring(0,maxColumn/2);
			return String.format("%1$-" + (maxColumn-Utilities.getChineseSize(s)) + "s", s);
		}
		return String.format("%1$-" + n + "s", s);
	}

	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}

	void println() {
		log.info(sb.toString());
		sb.setLength(0);
	}

	private StringBuffer sb = new StringBuffer();

	void print(String s) {
		sb.append(s);
	}

}
