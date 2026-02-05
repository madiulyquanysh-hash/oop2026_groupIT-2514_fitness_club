package repositories.Repositories;

import entities.MembershipType;
import java.sql.SQLException;
import java.util.List;

public interface MembershipTypeRepository {
    List<MembershipType> getAllTypes() throws SQLException;
}