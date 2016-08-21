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
import java.util.Objects;
import java.util.stream.Collector;
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


        data.set("suggestion",objectMapper.valueToTree(
                User.find.all().stream()
                        .filter(x-> !connectedUserIds.contains(x.id) && !connectionRequestSendUserIds.contains(x.id) &&
                                !Objects.equals(x.id,userId))
                        .map(x -> {
                                    ObjectNode jsonNodes = objectMapper.createObjectNode();
                                    Profile profile1 = Profile.find.byId(x.profile.id);
                                    jsonNodes.put("firstName", profile1.firstName);
                                    jsonNodes.put("lastName", profile1.lastName);
                                    jsonNodes.put("id", x.id);
                                    jsonNodes.put("emailId", x.email);
                                    return jsonNodes;
                                }
                        ).collect(Collectors.toList()))
        );

        return ok();
    }

}
