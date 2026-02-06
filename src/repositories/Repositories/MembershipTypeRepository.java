package repositories.Repositories;

import entities.MembershipType;
import java.sql.SQLException;
import java.util.List;

public interface MembershipTypeRepository extends CrudRepository<MembershipType> {
    List<MembershipType> getAllTypes() throws SQLException;
}