package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import forms.LoginForm;
import forms.SignupForm;
import models.Profile;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by lubuntu on 8/21/16.
 */
public class Application extends Controller{

    @Inject
    FormFactory formFactory;

    @Inject
    ObjectMapper objectMapper;
    public Result signUp() {

        Form<SignupForm> signupForm = formFactory.form(SignupForm.class).bindFromRequest();
        if(signupForm.hasErrors()) {
            return ok(signupForm.errorsAsJson());
        }

        Profile profile = new Profile(signupForm.data().get("firstName"),signupForm.data().get("lastName"));
        profile.db().save(profile);

        User user = new User(signupForm.data().get("email"),signupForm.data().get("password"));
        user.profile = profile;
        User.db().save(user);

        return ok((JsonNode) objectMapper.valueToTree(user));
    }

    public Result logIn() {
        Form<LoginForm> logInForm = formFactory.form(LoginForm.class).bindFromRequest();
        if(logInForm.hasErrors()) {
            return ok(logInForm.errorsAsJson());
        }

        return ok();
    }
}
