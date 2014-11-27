package com.ht.solr;


import org.apache.solr.client.solrj.impl.HttpSolrServer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SolrHttpServerProvide {

    private static class SolrHttpServerProvideInstance {
        private static final SolrHttpServerProvide instance = new SolrHttpServerProvide();
    }

    public static SolrHttpServerProvide getInstance() {
        return SolrHttpServerProvideInstance.instance;
    }

    private SolrHttpServerProvide() {

    }

    private Lock lock = new ReentrantLock();
    private ConcurrentHashMap<String,HttpSolrServer> httpSolrServerMap = new ConcurrentHashMap<String, HttpSolrServer>();

    public void createSolrHttpServer(String coreName) throws Exception{
        try{
            lock.lock();
            String url = SystemCondition.ROOT_URL_PATH+"/"+coreName;
            HttpSolrServer  server = new HttpSolrServer(url);
            server.setMaxRetries(1);
            server.setConnectionTimeout(3*1000);
            server.setSoTimeout(5*1000);
            server.setDefaultMaxConnectionsPerHost(100);
            server.setMaxTotalConnections(100);
            server.setFollowRedirects(false);
            server.setAllowCompression(true);
            httpSolrServerMap.put(coreName,server);
        }finally {
            lock.unlock();
        }

    }

    public HttpSolrServer getSolrHttpServer(String coreName) throws Exception{

        if(!httpSolrServerMap.containsKey(coreName)){
            createSolrHttpServer(coreName);
        }
        return httpSolrServerMap.get(coreName);
    }

}
