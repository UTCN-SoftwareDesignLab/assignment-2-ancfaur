package bookstoreApp.service.role;

import bookstoreApp.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
