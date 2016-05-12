package controllers.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vo.Notice;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dw on 2016. 5. 10..
 */
@Controller
public class Test {
    private static final Logger log = LoggerFactory.getLogger(Test.class);
    @RequestMapping(value = "222.htm", method = RequestMethod.POST)
    public String test(Notice n, HttpServletRequest request) {
        System.out.println(request.getServletContext().getRealPath("/customer/upload"));


        System.out.println(n.getFile());
        System.out.println(n.getTitle());
        System.out.println(n.getContent());
        System.out.println(n.getFileSrc());


        n.setFileSrc(n.getFileSrc());
        return "test.jsp";
    }
}
