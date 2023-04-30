package hello.springmvc.basic;

import lombok.Data;

@Data //Getter, Setter, toString, Equals 등등 모든 기능 제공
public class HelloData {
    private String username; //변수 명이 파라미터 명과 같아야 Binding이 가능하다
    private int age;
}
