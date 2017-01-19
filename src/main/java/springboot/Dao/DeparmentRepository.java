package springboot.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.model.Deparment;

/**
 * Created by Administrator on 2017/1/18.
 */
@Repository
public interface DeparmentRepository extends JpaRepository<Deparment,Long>{
}
