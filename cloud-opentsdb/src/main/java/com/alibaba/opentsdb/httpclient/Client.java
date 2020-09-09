package com.alibaba.opentsdb.httpclient;

import java.io.IOException;

import com.alibaba.opentsdb.buidler.MetricBuilder;
import com.alibaba.opentsdb.request.QueryBuilder;
import com.alibaba.opentsdb.response.Response;
import com.alibaba.opentsdb.response.SimpleHttpResponse;

public interface Client {
    
	String PUT_POST_API = "/api/put";
	String QUERY_POST_API = "/api/query";
	
	Response pushMetrics(MetricBuilder builder) throws IOException;
	
	SimpleHttpResponse pushQueries(QueryBuilder builder)throws IOException;
}
