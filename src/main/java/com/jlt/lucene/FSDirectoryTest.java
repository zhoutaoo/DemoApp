package com.jlt.lucene;
import org.apache.lucene.analysis.standard.StandardAnalyzer;   
import org.apache.lucene.document.Document;   
import org.apache.lucene.document.Field;   
import org.apache.lucene.index.IndexWriter;   
import org.apache.lucene.queryParser.QueryParser;   
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;   
import org.apache.lucene.search.Query;   
import org.apache.lucene.store.FSDirectory;   
  

@SuppressWarnings("deprecation")
public class FSDirectoryTest {   
  
    //建立索引的路径   
    public static final String path = "index";   
	public static void main(String[] args) throws Exception {   
        Document doc1 = new Document();   
        doc1.add( new Field("name", "lighter javaeye com zhoutao test",Field.Store.YES,Field.Index.TOKENIZED));   
  
        Document doc2 = new Document();   
        doc2.add(new Field("name", "lighter blog test",Field.Store.YES,Field.Index.TOKENIZED));   
  
        IndexWriter writer = new IndexWriter(FSDirectory.getDirectory(path, true), new StandardAnalyzer(), true);   
        writer.setMaxFieldLength(3);   
        writer.addDocument(doc1);   
        writer.setMaxFieldLength(3);   
        writer.addDocument(doc2);   
        writer.close();   
  
        IndexSearcher searcher = new IndexSearcher(path);   
        Hits hits = null;   
        Query query = null;   
        QueryParser qp = new QueryParser("name",new StandardAnalyzer());   
           
        query = qp.parse("lighter");   
        hits = searcher.search(query);   
        System.out.println("查找\"lighter\" 共" + hits.length() + "个结果");   
  
        query = qp.parse("javaeye");   
        hits = searcher.search(query);   
        System.out.println("查找\"javaeye\" 共" + hits.length() + "个结果");   
        
        query = qp.parse("zhoutao");   
        hits = searcher.search(query);   
        System.out.println("查找\"zhoutao\" 共" + hits.length() + "个结果");   
  
    }   
  

}