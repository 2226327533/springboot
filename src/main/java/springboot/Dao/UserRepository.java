package springboot.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.model.User;

/**
 * Created by Administrator on 2017/1/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
