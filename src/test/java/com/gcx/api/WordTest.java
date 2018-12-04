package com.gcx.api;

import com.alibaba.fastjson.JSONObject;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Text;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther : root
 * @date :  2018/11/9 15:28
 * @description :
 */
public class WordTest {

    public static void main(String[] args) throws Exception {
        List<Wordbean> infoList=new ArrayList();
        File file = new File("C:\\Users\\root\\Desktop\\60个简历 11月16日");
        boolean directory = file.isDirectory();
        if(directory){
            File[] docs = file.listFiles();
            for (File doc:docs){
                Wordbean wordbean=new Wordbean();
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(doc);
                MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
                String textNodesXPath = "//w:t";
                List<Object> textNodes= mainDocumentPart.getJAXBNodesViaXPath(textNodesXPath, true);
                for(int i=0;i<textNodes.size();i++){
                    Object obj = textNodes.get(i);
                    Text text = (Text) ((JAXBElement) obj).getValue();
                    switch (i){
                        case 4 :wordbean.setName(text.getValue());
                        case 6:wordbean.setSex(text.getValue());
                        case 8:wordbean.setPhone(text.getValue());
                        case 14:wordbean.setXl(text.getValue());
                    }
                }
                infoList.add(wordbean);
            }
            Object o = JSONObject.toJSON(infoList);
            System.out.println(o);


        }

    }
}
