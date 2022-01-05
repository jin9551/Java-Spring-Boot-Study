package com.example.study2.demo.controller;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class HelloWorldControllerTest {

    @Autowired
    private HelloWorldController helloWorldController;
    
    // 모의 http request와 response를 생성하여 테스트
    private MockMvc mockMvc;


    @Test
    void helloWorld(){
//        System.out.println("test");
        System.out.println(helloWorldController.helloWorld());

        assertThat(helloWorldController.helloWorld()).isEqualTo("Hello World");
    }

    @Test
    void mockMvcTest() throws Exception {
        
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();
        
        // 테스트 실행 
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/helloWorld")
        ).andDo(MockMvcResultHandlers.print()) //자세한 내용 출력
         .andExpect(MockMvcResultMatchers.status().isOk())// 200이 떨어지는지
                .andExpect(MockMvcResultMatchers.content().string("Hello World")); //hello world인지 체크
    }
}