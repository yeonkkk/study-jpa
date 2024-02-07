package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // xml 파일에 설정한 persistence unit-name
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try {
            Member member1 = new Member(150L, "A");
            member1.setRoleType(RoleType.USER);

            em.persist(member1);
            tx.commit(); // 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear(); // 커넥션을 물고 있기 때문에, 사용을 다하면 닫아 줘야 한다.
        }
        emf.close(); // 애플리케이션 종료 시 factory도 닫아 줘야 한다.
    }
}
