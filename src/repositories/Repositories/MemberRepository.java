package repositories.Repositories;

import entities.Member;
import java.sql.SQLException;
import java.util.List;

public interface MemberRepository {
    void addMember(Member member) throws SQLException;
    Member getMemberByEmail(String email) throws SQLException;
    List<Member> getAllMembers() throws SQLException;
    List<Member> searchByName(String name) throws SQLException;
}//new
