package auth.permission;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.*;

import java.util.*;

import security.entity.*;
import security.business.*;

/**
 * Controller responsável por gerir a troca de
 * senha do usuário através de serviço REST
 * 
 * @author Techne
 *
 */
@RestController
@RequestMapping(value = "/changePassword")
@PreAuthorize("hasRole('Logged')")
public class ChangePassword {
  
  @Autowired
  @Qualifier("UserBusiness")
  private UserBusiness userBusiness;
  
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public User post(final String oldPassword, final String newPassword, final String newPasswordConfirmation)
          throws Exception {
    
    if(!newPassword.equals(newPasswordConfirmation))
      throw new RuntimeException("Senha de confirmação diferente");
    
    org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User)SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
    
    List<User> users = userBusiness.findByLogin(userDetails.getUsername(), new PageRequest(0, 100)).getContent();
    if(users.size() > 0) {
      User user = users.get(0);
      
      if(!passwordEncoder.matches(oldPassword, user.getPassword()))
        throw new RuntimeException("Senha anterior não confere!");
      
      user.setPassword( passwordEncoder.encode(newPassword) );
      userBusiness.put(user);
      return user;
    }
    
    throw new RuntimeException("Usuario não encontrado!");
  }
}