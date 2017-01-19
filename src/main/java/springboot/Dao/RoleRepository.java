package springboot.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.model.Role;

/**
 *
 * Created by xy on 2017/1/18.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
