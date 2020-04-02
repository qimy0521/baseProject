package com.gcx.api.fdd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fadada.sdk.client.FddClientBase;
import com.fadada.sdk.client.FddClientExtra;
import com.fadada.sdk.client.authForfadada.*;
import com.fadada.sdk.client.authForfadada.model.AgentInfoINO;
import com.fadada.sdk.client.authForfadada.model.BankInfoINO;
import com.fadada.sdk.client.authForfadada.model.CompanyInfoINO;
import com.fadada.sdk.client.authForfadada.model.LegalInfoINO;
import com.fadada.sdk.client.authForplatform.ApplyClientNumCert;
import com.fadada.sdk.client.common.ReturnBaseT;
import com.fadada.sdk.client.request.ExtsignReq;
import com.fadada.sdk.test.util.ConfigUtil;
import com.fadada.sdk.util.http.HttpsUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URLDecoder;

public class auth4fadada {
    private static String APP_ID = "402848";
    private static String APP_SECRET = "5lLLFWnPy5zktVFrf7UpZdNO";
    private static String V = "2.0";
    private static String HOST = "https://testapi.fadada.com:8443/api/";

    private String customer_name="齐曼玉";
    private String id_card = "41138119950521741X";// 身份证号码;
    private String ident_type = "0";// 身份证类型 ;
    private String mobile="15537815712";
    private String contract_id="12345678";
    private String template_id="xmxc001";
    private String transaction_id="123456";
    private String email="qimy0521@gmail.com";
    private String openUser_id;
    private File file;
    private StringBuffer response = new StringBuffer("==================Welcome ^_^ ==================");

    @Before
    public void before() {
        String timeStamp = HttpsUtil.getTimeStamp();

        customer_name = "齐曼玉";
        contract_id = "CONT" + timeStamp;// 直接上传接口合同编号
        template_id = "TEMP" + timeStamp;// 模板编号
        transaction_id = "TRAN_" + timeStamp;// 手动签交易号
        openUser_id = "OPEN_" + timeStamp;// 用户在我们系统的ID
        email = "qimy0521@gmail.com";// 个人邮箱
        id_card = "41138119950521741X";// 身份证号码;
        mobile = "15537815712";// 手机号;
        file = new File(ConfigUtil.getFilePath());

        response.append("\n").append("名字:").append(customer_name);
        response.append("\n").append("身份证号码:").append(id_card);
        response.append("\n").append("证件类型:").append(ident_type);
        response.append("\n").append("手机号:").append(mobile);
        response.append("\n").append("邮箱:").append(email);
        response.append("\n").append("上传合同编号:").append(contract_id);
        response.append("\n").append("上传模板编号:").append(template_id);
        response.append("\n").append("手动签交易号:").append(transaction_id);
    }





    /*=============法大大实名测试=============*/
    @Test
    public void Test1(){
//        regAccount();//1注册账号
//        getAuthCompanyurl();//2获取企业实名认证地址
//        getAuthPersonurl();//3获取个人实名认证地址
//        findPersonCertInfo();//17 查询个人实名认证信息
        findCompanyCertInfo();//18 查询企业实名认证信息
//        GetFileForUUID();//19 通过uuid下载文件
//        ApplyCert();//4实名证书申请
//        ApplyNumCert();//5编号证书申请
//        addSignature1();//6印章上传  4690011
//        addSignature2();//6印章上传
//        customSignature();//7自定义印章

    }
    @Test
    public void Test2(){
//        uploadContract();//8合同上传
//        uploadTemplate();//9模板上传
        GenerateContract();//10模板填充
    }

    @Test
    public void Test3(){
//        uploadContract();//8合同上传
//        extsign();//12手动签署接口
//        extsignAuto();//11自动签署
        viewContract();//13合同查看
//        downloadContract();//14合同下载
//        contractFiling();//15合同归档
    }

    /*=============注册账号=============*/
    @Test
    public void regAccount(){
        response.append("\n").append("注册账号:");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
        String open_id = openUser_id;
        //类型 1 个人 2 企业
        String account_type = "2";
        String result =base.invokeregisterAccount(open_id,account_type);
        Object parse = JSONObject.parse(result);


        response.append("\n").append(result);
    }

    /*=============获取企业实名认证地址=============*/
    @Test
    public void getAuthCompanyurl(){
        response.append("\n").append("获取企业实名认证地址:");
        GetCompanyVerifyUrl comverify = new GetCompanyVerifyUrl(APP_ID,APP_SECRET,V,HOST);
        CompanyInfoINO companyInfo = new CompanyInfoINO();
        companyInfo.setCompany_name("深圳市茶小点有点公司");
        companyInfo.setCredit_no("41978211");
        companyInfo.setCredit_image_path("");

        BankInfoINO bankInfo = new BankInfoINO();
        bankInfo.setBank_name("招商银行");
        bankInfo.setBank_id("127683928347");
        bankInfo.setSubbranch_name("中关村支行");

        LegalInfoINO legalInfo = new LegalInfoINO();
        legalInfo.setLegal_name("");
        legalInfo.setLegal_id("");
        legalInfo.setLegal_mobile("");
        legalInfo.setLegal_id_front_path("");

        AgentInfoINO agentInfo = new AgentInfoINO();
        agentInfo.setAgent_name("");
        agentInfo.setAgent_id("");
        agentInfo.setAgent_mobile("");
        agentInfo.setAgent_id_front_path("");


        //基本参数
        String customer_id = "8906684F306ABA8A4FC24F5B1B3FA545";//必填
        /**
         * 实名认证套餐类型
         * 0：标准方案（对公打款+纸质审核）默认0；
         * 1：对公打款；
         * 2：纸质审核',
         */
        String verifyed_way = "0";
        String page_modify = "1";//必填
		String m_verified_way = "0";//实名认证套餐类型

        String company_principal_type = "1";//1.法人，2代理人
        String notify_url = "http://www.baidu.com";//必填
        String return_url = "http://www.baidu.com";//可填
       String result = comverify.invokeCompanyVerifyUrl(companyInfo,bankInfo,legalInfo
                ,agentInfo, customer_id,verifyed_way,m_verified_way,page_modify,
                company_principal_type,return_url,notify_url,"1","1");
        response.append("\n").append(result);
        String data = JSON.parseObject(result).getString("data");
        if (null !=data){
            String url = JSON.parseObject(data).getString("url");
            url = decode(url);
            System.out.println(url);
        }
    }
    /*=============获取个人实名认证地址=============*/
    public void getAuthPersonurl(){
        response.append("\n").append("获取个人实名认证地址:");
        GetPersonVerifyUrl personverify = new GetPersonVerifyUrl(APP_ID,APP_SECRET,V,HOST);
        String customer_id = "2048DF02C91AAAD81F402C67CDBCC37E";
        String verifyed_way = "0";
        String page_modify = "1";
        String notify_url = "https://www.baidu.com";
        String return_url= "https://www.baidu.com";
        String customer_name = "";
        String customer_ident_type = "0";
        String customer_ident_no ="";
        String mobile ="";
        String ident_front_path ="";
        String result = personverify.invokePersonVerifyUrl(customer_id,verifyed_way,
                page_modify,notify_url,return_url,customer_name,customer_ident_type,
                customer_ident_no,mobile,ident_front_path,"1","0");
        response.append("\n").append(result);
        String data = JSON.parseObject(result).getString("data");
         if (null !=data){
             String url = JSON.parseObject(data).getString("url");
             url = decode(url);
            System.out.println(url);
        }
    }

    /*=============查询个人实名认证信息=============*/
    public void findPersonCertInfo(){
        response.append("\n").append("查询个人实名认证信息:");
        FindCertInfo personCertInfo = new FindCertInfo(APP_ID,APP_SECRET,V,HOST);
        String verified_serialno = "52a2e2acdde14a19b2bab0595191d74b";
        String result = personCertInfo.invokeFindPersonCert(verified_serialno,
                "1");
        response.append("\n").append(result);
    }
    /*=============查询企业实名认证信息=============*/
    @Test
    public void findCompanyCertInfo(){
        response.append("\n").append("查询企业实名认证信息:");
        FindCertInfo personCertInfo = new FindCertInfo(APP_ID,APP_SECRET,V,HOST);
        String verified_serialno = "1ade878428754efaba8666c27ebb3082";
        String result = personCertInfo.invokeFindPersonCert(verified_serialno,
                "2");
        response.append("\n").append(result);
    }

    public void ApplyCert(){
        response.append("\n").append("申请实名证书:");
        ApplyCert applyCert = new ApplyCert(APP_ID,APP_SECRET,V,HOST);
        String customer_id = "2048DF02C91AAAD81F402C67CDBCC37E";
        String verified_serialno= "52a2e2acdde14a19b2bab0595191d74b";
        String result = applyCert.invokeApplyCert(customer_id,verified_serialno);
        response.append("\n").append(result);
    }

    public void ApplyNumCert(){
        response.append("\n").append("编号证书申请:");
        ApplyNumCert applyNumCert = new ApplyNumCert(APP_ID,APP_SECRET,V,HOST);
        String customer_id = "";
        String verified_serialno= "";
        String result = applyNumCert.invokeapplyNumcert(customer_id,verified_serialno);
        response.append("\n").append(result);
    }

    public void GetFileForUUID(){
        response.append("\n").append("通过uuid下载文件地址:");
        GetFileForUUID getFileForUUID = new GetFileForUUID(APP_ID,APP_SECRET,V,HOST);
        String uuid = "";
        String result = getFileForUUID.invokeFileForUUID(uuid);
        response.append("\n").append(result);
    }

    @Test
    public void addSignature1(){
        response.append("\n").append("印章上传:");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
        File imgfile = new File("/Users/octopus/Desktop/签章.png");
        String result = base.invokeaddSignature("8906684F306ABA8A4FC24F5B1B3FA545",imgfile,"");
        response.append("\n").append(result);
    }
    public void addSignature2(){
        response.append("\n").append("印章上传:");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
        String signature_img_base64 = "";
        String result = base.invokeaddSignature("",signature_img_base64);
        response.append("\n").append(result);
    }
    public void customSignature(){
        response.append("\n").append("自定义印章:");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
        String customer_id ="";
        String content ="content";
        String result = base.invokecustomSignature(customer_id,content);
        response.append("\n").append(result);
    }

    public void uploadContract() {
        response.append("\n").append("上传合同接口");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
        String result = base.invokeUploadDocs(contract_id, "合同标题", null, "", ".pdf");
        response.append("\n").append(result);
    }
    public void uploadTemplate() {
        response.append("\n").append("上传模板");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
        String doc_url ="https://dev-xiongmaoxingchu-food.oss-cn-beijing.aliyuncs.com/%E6%8E%88%E6%9D%83%E6%A8%A1%E7%89%88%E6%96%87%E6%9C%AC.pdf";
        String result = base.invokeUploadTemplate(template_id, null, doc_url);
        response.append("\n").append(result);
    }

    @Test
    public void GenerateContract() {
        try {
            response.append("\n").append("合同生成");
            FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
            String font_size = "12";
            String font_type = "";
            String paramter = "";
            paramter = getparamter();
            String dynamic_tables = "";
            dynamic_tables = getdynamic_tables();
            String result = base.invokeGenerateContract("TEMP20200106143712", "CONT202001061234", "熊猫星厨", font_size, font_type, paramter, dynamic_tables);
            response.append("\n").append(result);
            String viewpdf_url = JSON.parseObject(result).getString("viewpdf_url");
//            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + viewpdf_url);
            System.out.print("viewpdf_url");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extsign(){
        response.append("\n").append("手动签署接口:");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
        ExtsignReq req = new ExtsignReq();
        req.setCustomer_id("8906684F306ABA8A4FC24F5B1B3FA545");
        req.setTransaction_id(transaction_id);
        req.setContract_id("CONT202001061234");
        req.setReturn_url("http://www.baidu.com");
        req.setDoc_title("熊猫星厨和齐曼玉的合同");
        req.setSign_keyword("委托方签字或盖章");
        req.setKeyword_strategy("2");
        req.setReturn_url("http://www.baidu.com");
        String result = base.invokeExtSign(req);
        response.append("\n").append(result);
    }

    public void extsignAuto() {
        try {
            response.append("\n").append("自动签");
            FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
            ExtsignReq req = new ExtsignReq();
            req.setCustomer_id("");
            req.setTransaction_id(transaction_id);
            req.setContract_id("");
            req.setClient_role("1");
            req.setSign_keyword("");
            req.setDoc_title("1");
            String result = base.invokeExtSignAuto(req);
            response.append("\n").append(result);
            String viewpdf_url = JSON.parseObject(result).getString("viewpdf_url");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + viewpdf_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewContract() {
        try {
            response.append("\n").append("合同查看");
            FddClientExtra extra = new FddClientExtra(APP_ID,APP_SECRET,V,HOST);
            String contract_id = "CONT20200106152645";
            String result = extra.invokeViewPdfURL(contract_id);
            response.append("\n").append(result);
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void downloadContract() {
        response.append("\n").append("合同下载");
        String contract_id = "";
        FddClientExtra extra = new FddClientExtra(APP_ID,APP_SECRET,V,HOST);
        String result = extra.invokeDownloadPdf(contract_id);
        response.append("\n").append(result);
    }
    @Test
    public void contractFiling() {
        response.append("\n").append("合同归档");
        FddClientBase base = new FddClientBase(APP_ID,APP_SECRET,V,HOST);
		String contract_id = "CONT202001061234";
        String result = base.invokeContractFilling(contract_id);
        response.append("\n").append(result);
    }


    @After
    public void after() {
        System.out.println(response);
    }


    private String decode(String bizContent) {
        try {
            bizContent = URLDecoder.decode(bizContent, "utf-8");
            bizContent = new String(Base64.decodeBase64(bizContent.getBytes()));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return bizContent;
    }

    private String getparamter() {
        JSONObject paramter = new JSONObject();
		 paramter.put("homeUrl","http://www.xiongmaoxingchu.net");
        paramter.put("borrower","深圳法大大");
//        paramter.put("platformName","测试环境");
        return paramter.toString();
    }
    private String getdynamic_tables() {
        JSONArray dynamic_tables = new JSONArray();
        JSONObject dynamic2 = new JSONObject();
        dynamic2.put("insertWay", 1);
        dynamic2.put("keyword", "Xxxx");
		dynamic2.put("pageBegin", "1");
        dynamic2.put("cellHeight", "16.0");

        dynamic2.put("colWidthPercent", new int[] { 3, 4, 4, 4});
        dynamic2.put("theFirstHeader", "附二");
        dynamic2.put("cellHorizontalAlignment", "1");
        dynamic2.put("cellVerticalAlignment", "5");
        dynamic2.put("headers", new String[] { "序号", "借款人", "贷款人", "金额" });
        String row1[] = new String[] { "1", "小网", "小易", "1000" };
        String row2[] = new String[] { "2", "小云", "小音", "2000" };
        String row3[] = new String[] { "3", "小乐", "天马", "3000" };
        dynamic2.put("datas", new String[][] { row1, row2, row3 });
        dynamic2.put("headersAlignment", "1");
        dynamic2.put("tableWidthPercentage", 80);
        dynamic_tables.add(dynamic2);
        System.out.println(dynamic_tables.toString());
        return dynamic_tables.toString();
    }
}
