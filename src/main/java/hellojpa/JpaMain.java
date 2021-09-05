package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = "+ findMember.getId());
            System.out.println("findMember.name = "+ findMember.getName());

            findMember.setName("HelloJpa");


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
