package com.redteamobile.servicezuul;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * zuul网关是支持回退的
 * zuul性能优化 如果 容器部署 选择 undertow  https://www.jianshu.com/p/10ac931202e3
 */
@Component
public class MyFallbackProvider implements FallbackProvider {

    /**
     * 单个指定服务名 为 单个回退
     * 如果要为所有路由提供默认回退，
     * 可以创建FallbackProvider类型的bean并使getRoute方法返回*或null。
     * @return
     */
    @Override
    public String getRoute() {
        return "service-ribbon";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        private ClientHttpResponse response(final HttpStatus status) {
            return new ClientHttpResponse() {
                @Override
                public HttpStatus getStatusCode() throws IOException {
                    return status;
                }

                @Override
                public int getRawStatusCode() throws IOException {
                    return status.value();
                }

                @Override
                public String getStatusText() throws IOException {
                    return status.getReasonPhrase();
                }

                @Override
                public void close() {
                }

                @Override
                public InputStream getBody() throws IOException {
                    return new ByteArrayInputStream("fallback".getBytes());
                }

                @Override
                public HttpHeaders getHeaders() {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    return headers;
                }
            };
        }

    }