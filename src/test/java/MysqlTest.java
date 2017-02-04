
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import org.springframework.util.Assert;
import springboot.dao.DeparmentRepository;
import springboot.dao.RoleRepository;
import springboot.dao.UserRepository;
import springboot.model.Deparment;
import springboot.model.Role;
import springboot.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by xy on 2017/1/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {
    private static Logger logger= LoggerFactory.getLogger(MysqlTest.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeparmentRepository deparmentRepository;
    @Autowired
    RoleRepository roleRepository;
    @Before
    public void initData(){
        System.out.println("545421210214541553031024154652");
       userRepository.deleteAll();
        roleRepository.deleteAll();
        deparmentRepository.deleteAll();
        Deparment deparment=new Deparment();
        deparment.setName("开发部");
        deparmentRepository.save(deparment);
        Assert.notNull(deparment.getId());
        Role role=new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId());
        User user=new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDeparment(deparment);
        List<Role> roles=roleRepository.findAll();
        Assert.notNull(roles);
        user.setRoles(roles);
        userRepository.save(user);
        Assert.notNull(user.getId());
    }
    @Test
    public void findPages(){
        System.out.print("12233154541515485452152445945");
        Pageable pageable=new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        Page<User> page=userRepository.findAll(pageable);
        Assert.notNull(page);
        for (User user:page.getContent()){
            logger.info("====user====username:{},departname:{}.role name:{}",user.getName(),user.getDeparment().getName(),user.getRoles().get(0).getName());

        }
    }

}
