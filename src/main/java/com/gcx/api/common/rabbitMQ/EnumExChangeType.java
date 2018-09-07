package com.gcx.api.common.rabbitMQ;

/**
 * @Auther: root
 * @Date: 2018/9/7 14:54
 * @Description: rabbitMQ交换机的几种类型
 */
public enum EnumExChangeType {
    DIRECT("direct"),//直连交换机
    TOPIC("topic"),//主题交换机
    FANOUT("fanout");//扇形交换机
    private String type;
    EnumExChangeType(String type){
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
