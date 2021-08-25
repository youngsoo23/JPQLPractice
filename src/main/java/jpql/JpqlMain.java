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

//            Member member1 = em.createQuery("select m from Member m where m.username =:username", Member.class)
//                    .setParameter("username", "member1")
//                            .getSingleResult();
//
//            System.out.println("member1 = " + member1.getUsername());


//            //Object[] 타입으로 조회
//            List rl= em.createQuery("select m.username, m.age from Member m").getResultList();
//
//            Object o = rl.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);

        // new 명령어로 조회
            //단순 값을 DTO로 바로 조회 
            //SELECT new jpabook.jpql.UserDTO(m.username, m.age) FROM Member m
            List<MemberDto> resultList =
                    em.createQuery("select new jpql.MemberDto(m.username,m.age) from Member m", MemberDto.class).getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
