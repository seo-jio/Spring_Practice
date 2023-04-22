package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message : " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    // 초기화 콜백
    // 외부 라이브러리 사용 시에는 @Bean(initMehtod, distroyMehtod)를 사용
    // 그게 아니라면 자바에서 제공하는 기능인 @PostConstruct, @PreDestroy를 사용
    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    // 소멸 전 콜백
    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
