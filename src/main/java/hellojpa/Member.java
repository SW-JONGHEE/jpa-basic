package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name", nullable = false) //컬럼 매핑
    private String username;

    private Integer age;

    // EnumType
    /*
        기본 ORDINAL 사용xxx, 숫자로 저장되게 되는데 enum 추가시 값의 의미 모호
        String 필수!!
    */
    @Enumerated(EnumType.STRING) // enum타입 매핑
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)   // 날짜 타입 매핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 요즘엔
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob    // VARCHAR 을 넘어서는 큰 뭔가를 넣을때! BLOB(나머지 예 byte), CLOB(String) 매핑
    private String description;

    @Transient // 테이블 컬럼 매핑 없이 메모리에서만 쓰고 싶을 경우!
    private  int temp;

    public Member() {

    }

}
