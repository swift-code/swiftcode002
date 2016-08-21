package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import models.Profile;
import models.User;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lubuntu on 8/21/16.
 */
public class HomeController extends Controller {

    @Inject FormFactory formFactory;
    @Inject ObjectMapper objectMapper;

    public Result getProfile(Long userId) {

        User user = User.find.byId(userId);
        Profile profile = Profile.find.byId(user.profile.id);
        ObjectNode data = objectMapper.createObjectNode();

        List<Long> connectedUserIds = user.connections.stream().map(x->x.id).collect(Collectors.toList());
        List<Long> connectionRequestSendUserIds = user.connectionRequestsSent.stream().map(x->x.receiver.id).collect(Collectors.toList());


        data.set("suggestion",null);
        return ok();
    }

}
