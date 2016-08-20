package forms;

import com.typesafe.config.ConfigException;
import models.User;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubuntu on 8/20/16.
 */
public class SignupForm {

    @Constraints.Required
    public String email;

    @Constraints.Required
    public String password;

    @Constraints.Required
    public String firstName;

    @Constraints.Required
    public String lastName;

    public List<ValidationError> validate()
    {
        List<ValidationError> errors = new ArrayList<>();
        User user = User.find.where().eq("email",email).findUnique();
        if(user!= null)
        {
            errors.add(new ValidationError("message","Email already exists"));
            errors.add(new ValidationError("error","true"));
        }
        return errors;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
