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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getName() = " + m.getName());
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
