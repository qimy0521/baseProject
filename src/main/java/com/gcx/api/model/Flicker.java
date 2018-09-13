package com.gcx.api.model;

import com.gcx.api.common.elasticSerachs.ElasticSerachUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
@Data
@Document(indexName = ElasticSerachUtil.NAME_INDEX , type = ElasticSerachUtil.NAME_TYPE)
public class Flicker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Field(type = FieldType.Text,  store = true)
    private String ID;//ID
    @Field(type = FieldType.Integer,   store = true)
    private Integer classify;//分类(1表扬2激励)
    @Field(type = FieldType.Text,  store = true)
    private String county;//区县
    @Field(type = FieldType.Text,   store = true)
    private String villages;//乡镇
    @Field(type = FieldType.Text,   store = true)
    private String organizationName;//机构名称
    @Field(type = FieldType.Text,   store = true)
    private String departmentName;//部门名称
    @Field(type = FieldType.Text,   store = true)
    private String incident;//事件
    @Field(type = FieldType.Text,   store = true)
    private String cityCode;//城市类型 1济南
    @Field(type = FieldType.Text,   store = true)
    private String createT;//创建时间
    @Field(type = FieldType.Text,   store = true)
    private String createUid;//创建人ID
    @Field(type = FieldType.Text,   store = true)
    private String updateT;//更新时间
    @Field(type = FieldType.Text,   store = true)
    private String updateUid;//更新人ID
    @Field(type = FieldType.Text,   store = true)
    private String publishUid;//发布人ID
    @Field(type = FieldType.Text,   store = true)
    private String publishT;//发布时间

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classify=").append(classify);
        sb.append(", county=").append(county);
        sb.append(", villages=").append(villages);
        sb.append(", organizationName=").append(organizationName);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", incident=").append(incident);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", createT=").append(createT);
        sb.append(", createUid=").append(createUid);
        sb.append(", updateT=").append(updateT);
        sb.append(", updateUid=").append(updateUid);
        sb.append(", publishUid=").append(publishUid);
        sb.append(", publishT=").append(publishT);
        sb.append("]");
        return sb.toString();
    }
}