package wxq.fallbackProvider;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wzm
 * @description 熔断器
 * @date 2020/10/20
 */
public class ServiceFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        // 这里只针对"springbootService"服务进行熔断
        // 如果需要针对所有服务熔断，则return "*"
        return "springbootService";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        System.out.println("route:"+route);

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "ok";
            }

            @Override
            public void close() {

            }

            @Override
            // 发生熔断式，返回的信息
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("Sorry, the service is unavailable now.".getBytes());
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
