package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member { // jpa가 관리하는 entity

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 id 생성해 주는 것
    private Long id; // 데이터를 구분하기 위해서 시스템이 저장하는 임의의 값
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
