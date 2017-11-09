package pds.esibank.proto.protoweb.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pds.esibank.proto.protoweb.services.LdapSvc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

}
