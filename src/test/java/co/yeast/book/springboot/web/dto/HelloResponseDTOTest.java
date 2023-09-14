package co.yeast.book.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDTOTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 5000;

        //when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);                  // assertj라는 테스트 검증 라이브러리의 검증 메소드
        assertThat(dto.getAmount()).isEqualTo(amount);              // 메소드 체이닝 지원
    }
}
