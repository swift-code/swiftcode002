package forms;

import play.data.validation.Constraints;

/**
 * Created by lubuntu on 8/20/16.
 */
public class LoginForm {


    @Constraints.Required
    public String email;

    @Constraints.Required
    public String password;


}
