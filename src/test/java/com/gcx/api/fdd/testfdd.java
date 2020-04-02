package com.gcx.api.fdd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fadada.sdk.util.crypt.FddEncryptTool;
import com.fadada.sdk.util.http.HttpsUtil;
import com.gcx.api.common.wechatutils.HttpClientPool;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class testfdd {


    @Test
    public void test() throws Exception {





        try {

/*
            String url=HOST+"before_authsign.api?"+"app_id="+APP_ID+"&timestamp="+timeStamp+"&v="+V+"&msg_digest="
                    +msgDigest+"&transaction_id="+transaction_id+"&auth_type=1"+"&contract_id="+contract_id+"&customer_id="+customer_id
                    +"&return_url="+return_url;
*/

            String timeStamp = HttpsUtil.getTimeStamp();
            String sha1 = FddEncryptTool.sha1(402848 + FddEncryptTool.md5Digest("67385472989184"+timeStamp) +
                    FddEncryptTool.sha1("5lLLFWnPy5zktVFrf7UpZdNO" + "6738547298918467385472989184"));
            String msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));

            List<NameValuePair> params=new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("transaction_id", "67385472989184"));
            params.add(new BasicNameValuePair("contract_id", "67385472989184"));
            params.add(new BasicNameValuePair("customer_id", "8906684F306ABA8A4FC24F5B1B3FA545"));
            params.add(new BasicNameValuePair("return_url", "www.baidu.com"));
            params.add(new BasicNameValuePair("auth_type", "1"));
            params.add(new BasicNameValuePair("app_id", "402848"));
            params.add(new BasicNameValuePair("timestamp", timeStamp));
            params.add(new BasicNameValuePair("v", "2.0"));
            params.add(new BasicNameValuePair("msg_digest", msgDigest));
            params.add(new BasicNameValuePair("notify_url", "www.baidu.com"));

            StringBuilder sb = new StringBuilder();



            params.forEach(m->{
                try {
                    sb.append(m.getName()).append("=").append(URLEncoder.encode(m.getValue(),"UTF-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
            sb.delete(sb.length() - 1, sb.length());
            CloseableHttpClient httpClient = HttpClientPool.getHttpClient();
            String url= "https://testapi.fadada.com:8443/api/before_authsign.api?"+sb.toString();
            System.out.println(url);
        }catch (Exception e){
        e.printStackTrace();}
    }


}
