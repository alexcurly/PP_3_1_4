package PP_3_1_4.service;

import PP_3_1_4.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllUser();

    void save(Role role);

    Role showUserById(Long id);

    Role findByRoleName(String roleName);

    Role findById(Long id);
}
