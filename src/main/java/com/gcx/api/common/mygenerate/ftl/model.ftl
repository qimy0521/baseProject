package com.gcx.${projectName}.model;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ${table.javaName} {

	<#list table.columns as column>
	<#if column.javaTypeSimple =='Date'&&column.javaName!='timeAdd'&&column.javaName!='timeAudit'> 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	</#if>
	@JsonProperty("${column.javaName}")
	private ${column.javaTypeSimple} ${column.javaName};

	</#list> 

	<#list table.columns as column>
	public ${column.javaTypeSimple} get${column.javaName2}() {
		return ${column.javaName};
	}

	<#if column.javaTypeSimple =='String'>  
	public void set${column.javaName2}(${column.javaTypeSimple} ${column.javaName}) {
		this.${column.javaName} = ${column.javaName} == null ? null : ${column.javaName}.trim();
	}	

	<#else>
	public void set${column.javaName2}(${column.javaTypeSimple} ${column.javaName}) {
		this.${column.javaName} = ${column.javaName};
	}	

	</#if>
	</#list>



}