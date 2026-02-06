package repositories.Repositories;

import entities.Member;
import java.sql.SQLException;
import java.util.List;

public interface MemberRepository extends CrudRepository<Member> {
    Member getMemberByEmail(String email) throws SQLException;
    List<Member> getActiveMembers() throws SQLException;
    List<Member> searchByName(String name) throws SQLException;
    Member findByEmail(String email) throws SQLException;
}