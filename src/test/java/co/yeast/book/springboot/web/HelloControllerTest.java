package co.yeast.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @RunWith(SpringRunner.class) in Junit4
// 스프링 부트 테스트와 Junit 사이에 연결자 역할, Junit 테스트 실행방법 지정
@ExtendWith(SpringExtension.class)
// 여러 스프링 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션
// @Controller, @ControllerAdvice, @Service, @Component, @Repository 등 사용불가
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired          // 필요한 의존 객체 "타입"에 해당하는 빈을 찾아 주입
    private MockMvc mvc;

    @Test               // 단위 테스트 수행할 메소드 지정
    public void hello가_리턴된다() throws Exception{         // @warning 테스트 코드 한글 인식 오류 해결 후 File > Invalidate Caches
        String hello = "hello";

        mvc.perform(get("/hello"))            // /hello 주소로 HTTP GET 요청, 체이닝 지원으로 여러 검증 기능 이어서 선언 가능
                .andExpect(status().isOk())              // mvc.perform의 결과 검증, HTTP Header의 Status 검증(200, 404. 500 등),
                .andExpect(content().string(hello));     // mvc.perform의 결과 검증, 컨드롤러에서 "hello"리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDTO가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)                            // 값은 String만 허용, 이외 문자열로 변환 필요
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)));            // jsonPath는 JSON 응답값을 필드별로 검증하는 메소드
    }

}
