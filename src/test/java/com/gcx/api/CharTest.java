package com.gcx.api;

/**
 * @auther : root
 * @date :  2018/11/20 11:17
 * @description :
 */
public class CharTest {
    public boolean isSearchParam(String param){
        if(param!=null&&!param.equals("")){
            char[] temp = param.toCharArray();
            for(char c:temp){
                if(c!='_'){
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {

        CharTest charTest=new CharTest();
        boolean searchParam = charTest.isSearchParam("测试");



    }
}
