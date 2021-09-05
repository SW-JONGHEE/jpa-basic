package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //쓰레드간에 공유x(사용하고 버려야한다)
        EntityManager em = emf.createEntityManager();

        //jpa의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try{
            // create
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("Hello");
//            em.persist(member);

            // update
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = "+ findMember.getId());
//            System.out.println("findMember.name = "+ findMember.getName());
//
//            findMember.setName("HelloJpa");

/*
***          jpql!
*             jpql 테이블을 대상이 아닌 객체를 대상으로 가져온다
*             jpql의 메리트? 객체 지향 쿼리로 각 디비에 맞게 변역을 해준다..
*             jpa를 사용하면 엔티티 객체를 중심으로 개발하게 된다. 여기서 문제점은 검색 쿼리!
*             검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
*             모든 db 데이터를 객체로 변환해서 검색하는 것은 불가능
*             애플리케이션이 필요한 데이터만 db에서 불러오려면 결국 검색 조건이 포함된 sql이 필요 --> 관계형에 종속적일 수 밖에 없다.
*             그래서! sql을 추상화한 jpql이라는 객체 지향 쿼리 언어를 제공
*/
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for(Member member: result) {
                System.out.println("member.name = " + member.getName());
            }

            //em.flush -- 쓰기 지연 sql 저장소에 쌓여있는 db sql 적용(commit 은 따로 필요)
            //em.detach -- 준영속(영속석 컨텍스트에서 빠지는 형태)


            tx.commit();
        }catch (Exception e) {
            tx.rollback();

        } finally {
            em.close();
        }

        //전체 application 이 끝나면 entity manager factory 닫아주기
        emf.close();

    }
}
