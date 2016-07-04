package com.jlt.lucene;
import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class StandardAnalyzerTest{
    //构造函数，   
    public StandardAnalyzerTest()   
    {   
    }   
    @SuppressWarnings("deprecation")
	public static void main(String[] args)    
    {   
        //生成一个StandardAnalyzer对象   
        Analyzer aAnalyzer = new StandardAnalyzer();   
        //测试字符串   
        StringReader sr = new StringReader("zhoutao javaeye woekspace com is the are on");   
        //生成TokenStream对象   
        TokenStream ts = aAnalyzer.tokenStream("name", sr);    
        try {   
            int i=0;   
            Token t = ts.next();   
            while(t!=null)   
            {   
                //辅助输出时显示行号   
                i++;   
                //输出处理后的字符   
                System.out.println("第"+i+"行:"+t.termText());   
                //取得下一个字符   
                t=ts.next();   
            }   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   
} 
