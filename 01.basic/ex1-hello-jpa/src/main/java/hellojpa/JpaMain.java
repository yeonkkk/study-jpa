package hellojpa;

import java.util.List;
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
            // 1. member 생성
//            Member member = new Member();
//        member.setId(1L);
//        member.setName("홍길동");
//            member.setId(2L);
//            member.setName("김길동");
//            em.persist(member);

            // 2. member 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            // 3. 삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

            // 4. 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("최길동");

            // JPQL 실습
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit(); // 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear(); // 커넥션을 물고 있기 때문에, 사용을 다하면 닫아 줘야 한다.
        }
        emf.close(); // 애플리케이션 종료 시 factory도 닫아 줘야 한다.
    }
}
