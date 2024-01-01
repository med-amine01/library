package de.tekup.library.service;

import de.tekup.library.entity.Authority;
import java.util.List;
import java.util.Set;

public interface AuthorityService {

    List<String> findNonExistentAuthorities(Set<String> authorityNames);

    Authority createDefaultAuthority();
}