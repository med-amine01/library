package de.tekup.library.service;

import de.tekup.library.dto.request.RoleRequest;
import de.tekup.library.dto.response.RoleResponse;
import de.tekup.library.exception.RoleServiceException;

import java.util.List;

public interface RoleService {
    RoleResponse saveRole(RoleRequest role) throws RoleServiceException;
    List<RoleResponse> findRoles();
}
