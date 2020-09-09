package com.alibaba.opentsdb.httpclient;


import java.io.IOException;

import com.alibaba.opentsdb.buidler.MetricBuilder;
import com.alibaba.opentsdb.request.QueryBuilder;
import com.alibaba.opentsdb.response.Response;
import com.alibaba.opentsdb.response.SimpleHttpResponse;


public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
			ExpectResponse exceptResponse) throws IOException;

	public SimpleHttpResponse pushQueries(QueryBuilder builder,
                                          ExpectResponse exceptResponse) throws IOException;
}
