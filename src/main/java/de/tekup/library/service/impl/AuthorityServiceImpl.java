package de.tekup.library.service.impl;

import de.tekup.library.entity.Authority;
import de.tekup.library.enums.Authorities;
import de.tekup.library.repository.AuthorityRepository;
import de.tekup.library.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public List<String> findNonExistentAuthorities(Set<String> authorityNames) {
        return authorityRepository.findNonExistentAuthorities(authorityNames);
    }

    @Override
    public Authority createDefaultAuthority() {
        Authority defaultAuthority = new Authority();
        defaultAuthority.setName(Authorities.READ.name());
        return defaultAuthority;
    }
}