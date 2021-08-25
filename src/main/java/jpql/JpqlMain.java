package jpql;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            //TypeQuery 예제
//            TypedQuery<String> query1 = em.createQuery("select m.username from Member m", String.class);
//            TypedQuery<Member> query2 = em.createQuery("select m from Member m", Member.class);
//            List<Member> members = query2.getResultList();
            
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username =:username", Member.class);
//            query.setParameter("username", "member1");
//            Member sr = query.getSingleResult();

            Member member1 = em.createQuery("select m from Member m where m.username =:username", Member.class)
                    .setParameter("username", "member1")
                            .getSingleResult();

            System.out.println("member1 = " + member1.getUsername());

            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
