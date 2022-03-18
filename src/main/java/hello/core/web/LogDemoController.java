package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //final 키워드가 붙은 인스턴스를 매개변수로 받는 생성자를 자동으로 만들어줌
public class LogDemoController {

    private final LogDemoService logDemoService;
    //MyLogger를 찾을 수 있는(DL할 수 있는) 애가 주입이 됨.
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody //보여지는 화면(view)이 없고, 문자만 반환할 때
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        //HTTP 요청이 여러 개 들어와도 각각 수행할 수 있다.
        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
