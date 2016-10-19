package auth.permission;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import security.dao.PermissionDAO;
import security.dao.RoleDAO;
import security.dao.UserDAO;
import security.dao.UserRoleDAO;
import security.entity.Permission;
import security.entity.Role;
import security.entity.User;
import security.entity.UserRole;

@Component
public class AuthenticationConfigurer implements AuthenticationProvider {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(AuthenticationConfigurer.class);

  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private UserDAO userRepository;

  @Autowired
  private UserRoleDAO userRoleRepository;

  @Autowired
  private RoleDAO roleRepository;

  @Autowired
  private PermissionDAO permissionRepository;

  public final String ROLE_ADMIN_ID = "00000000-0000-0000-0000-000000000000";
  public final String ROLE_LOGGED_ID = "11111111-1111-1111-1111-111111111111";
  private static Role ROLE_LOGGED = null;
  public static final String ROLE_ADMIN_NAME = "Administrators";

  public void createDatabase() {

    LOGGER.info("Creating database");

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    User userAdmin = new User();
    userAdmin.setName("admin").setLogin("admin")
        .setPassword(passwordEncoder.encode("admin"));

    Role roleAdmin = new Role();
    roleAdmin.setId(ROLE_ADMIN_ID).setName(ROLE_ADMIN_NAME);

    Permission permissionAdmin = new Permission();
    permissionAdmin.setPath("/views/admin/**").setVerb("ALL")
        .setRole(roleAdmin).setPriority(1).setEnabled(true);
    Permission permissionAdminRest = new Permission();
    permissionAdminRest.setPath("/api/rest/security/**").setVerb("ALL")
        .setRole(roleAdmin).setPriority(1).setEnabled(true);

    Role roleLogged = new Role();
    roleLogged.setId(ROLE_LOGGED_ID).setName("Logged");

    Permission permissionLogged = new Permission();
    permissionLogged.setPath("/views/logged/**").setVerb("ALL")
        .setRole(roleLogged).setPriority(1).setEnabled(true);

    Permission changePasswordLogged = new Permission();
    changePasswordLogged.setPath("/changePassword").setVerb("POST")
        .setRole(roleLogged).setPriority(1).setEnabled(true);

    Permission permissionLoggedRest = new Permission();
    permissionLoggedRest.setPath("/api/rest/**").setVerb("ALL")
        .setRole(roleLogged).setPriority(1).setEnabled(true);

    UserRole userRoleAdmin = new UserRole();
    userRoleAdmin.setRole(roleAdmin).setUser(userAdmin);

    User userOrdinary = new User();
    userOrdinary.setName("techne").setLogin("techne")
        .setPassword(passwordEncoder.encode("techne"));

    userRepository.save(userOrdinary);
    userRepository.save(userAdmin);
    roleRepository.save(roleAdmin);
    roleRepository.save(roleLogged);
    permissionRepository.save(permissionAdmin);
    permissionRepository.save(permissionLogged);
    permissionRepository.save(changePasswordLogged);
    permissionRepository.save(permissionAdminRest);
    permissionRepository.save(permissionLoggedRest);
    userRoleRepository.save(userRoleAdmin);
  }

  private UsernamePasswordAuthenticationToken authenticateDataBase(
      Authentication authentication) throws AuthenticationException {
    String name = authentication.getName();
    String rawPassword = authentication.getCredentials().toString();
    List<User> users = userRepository.findByLogin(name, new PageRequest(0,
        100)).getContent();

    if (users.isEmpty())
      throw new UsernameNotFoundException("Usuário não encontrado!");

    User user = users.get(0);
    if (passwordEncoder.matches(rawPassword, user.getPassword())) {
      Set<GrantedAuthority> roles = getAuthorities(user);
      org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
          user.getName(), user.getPassword(), false, false, false,
          false, roles);
      UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
          userDetails, user.getPassword(), roles);
      userToken.setDetails(userDetails);
      return userToken;
    } else {
      throw new BadCredentialsException("Usuário ou senha incorreta!");
    }
  }

  private UsernamePasswordAuthenticationToken authenticateLocal(
      Authentication authentication) throws AuthenticationException {

    User user = new User().setLogin("local").setName("local")
        .setPassword("local");

    String formLogin = authentication.getName();
    String formPassword = authentication.getCredentials().toString();

    if (!user.getLogin().equals(formLogin)) {
      throw new BadCredentialsException("Usuário não encontrado!");
    } else {
      if (!user.getPassword().equals(formPassword))
        throw new BadCredentialsException("Usuário ou senha incorreta!");
    }

    Set<GrantedAuthority> roles = new HashSet<>();
    roles.add(new SimpleGrantedAuthority("Administrators"));
    roles.add(new SimpleGrantedAuthority("Logged"));

    org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
        user.getName(), user.getPassword(), false, false, false, false,
        roles);

    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
        userDetails, user.getPassword(), roles);

    return userToken;
  }

  @Override
  public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
    return authenticateDataBase(authentication);
    //return authenticateLocal(authentication);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

  private Set<GrantedAuthority> getAuthorities(User user) {
    Set<GrantedAuthority> authorities = new HashSet<>();

    Pageable pageable = new PageRequest(0, 100);
    List<UserRole> roles = userRoleRepository.findByLogin(user.getLogin(),
        pageable).getContent();
    for (UserRole userRole : roles) {
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
          userRole.getRole().getName());
      authorities.add(grantedAuthority);
    }

    if (ROLE_LOGGED == null)
      ROLE_LOGGED = roleRepository.findOne(ROLE_LOGGED_ID);

    // Virtual Role Logged
    authorities.add(new SimpleGrantedAuthority(ROLE_LOGGED.getName()));

    LOGGER.debug("user authorities are " + authorities.toString());
    return authorities;
  }

}