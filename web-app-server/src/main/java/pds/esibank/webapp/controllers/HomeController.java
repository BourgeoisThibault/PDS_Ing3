package pds.esibank.webapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.models.notification.PushNotificationModel;
import pds.esibank.webapp.services.LdapSvc;

import javax.ws.rs.FormParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = GET)
    public String GetHome() {
        return "home/index";
    }

    @RequestMapping(value = "/team", method = GET)
    public ModelAndView GetTeam() {
        ModelAndView modelAndView = new ModelAndView("home/team");
        modelAndView.addObject("listLdapMembers", LdapSvc.getAllLdapUser("teammembers"));
        return modelAndView;
    }

    @RequestMapping(value = "/about", method = GET)
    public String GetAbout() {
        return "home/about";
    }

    @RequestMapping(value = "/member", method = GET)
    public ModelAndView GetOnMember(@RequestParam("uid") String uid) {
        ModelAndView model = new ModelAndView("home/teammember");
        model.addObject("member", LdapSvc.getOneLdapUser(uid,"teammembers"));
        return model;
    }

    @RequestMapping(value="/getldapimageuser", method=GET)
    public ResponseEntity<byte[]> getImage(@RequestParam("uid") String uid) {
        byte[] content = LdapSvc.getUserImage(uid,"teammembers");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/notification", method = GET)
    public ModelAndView GetSendNotif() {
        ModelAndView modelAndView = new ModelAndView("notification/sendNotif");
        modelAndView.addObject("accept", false);
        modelAndView.addObject("returnmessage", "");
        return modelAndView;
    }

    @RequestMapping(value = "/notification", method = POST)
    public ModelAndView PostSendNotif(@RequestParam String uid,
                                @RequestParam String title,
                                @RequestParam String message) {

        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setTitle(title);
        notificationModel.setMessage(message);

        String myUri = "http://notification.esibank.inside.esiag.info/send/" + uid;
        //String myUri = "http://localhost:1234/send/" + uid;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = restTemplate.postForEntity(myUri,notificationModel,String.class);

        ModelAndView modelAndView = new ModelAndView("notification/sendNotif");
        modelAndView.addObject("accept", true);
        modelAndView.addObject("returnmessage", result.getBody());
        return modelAndView;
    }


}
