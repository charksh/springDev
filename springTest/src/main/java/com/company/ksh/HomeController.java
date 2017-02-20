package com.company.ksh;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home")
	public String home(Locale locale, ModelMap model) { 
		logger.info("Welcome home @@@@@@");
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		
		HashMap<String,String> hash = new HashMap<>();
		hash.put("cc", "ccc");
		hash.put("dd", "ddd");
		
		// 모델은 아래처럼 화면 url 로 return 시킬경우 EL 로 접근하여 화면에서 바로 사용할수 있다.
		model.addAttribute("list", list);
		model.addAttribute("hash", hash);
		model.addAttribute("serverTime", formattedDate );

		return "/home";
	}
	
	@RequestMapping(value = "/house")
	public @ResponseBody List<String> house(@RequestBody List<Map<String,Object>> str, Model model, SessionStatus status) {
		logger.info("Welcome house : {}", str);	

		System.out.printf("%s", str.get(0).get("aa"));
		
		List<String> list = new ArrayList<>(Arrays.asList("AAA","BBB"));
		
		HashMap<String,String> hash = new HashMap<>();
		hash.put("CC", "CCCCC");
		hash.put("DD", "DDDDD");
		
		// 화면에서 ajax 로 접근했을시 @ResponseBody 사용하여 List 로 넘기면 json 으로 사용가능 
		// model 로 담아서 넘기는 것은 못받음 주석처리
		//model.addAttribute("list", list);
		//model.addAttribute("hash", hash);
		
		//status.setComplete();
		
		return list;
		//return "forward:/home.do";
	}
	
	@RequestMapping(value = "/pension")
	public String pension(Model model, SessionStatus status) {
		logger.info("Welcome pension !!!!!!!!");	
		
		status.setComplete();
		
		return "forward:/home.do";
	}
	
	@RequestMapping(value = "/excelUpload")
	public String excelUpload(@RequestParam(value="excelFile") MultipartFile multiFile, HttpServletRequest request, Model model) {
		logger.info("Welcome excelUpload !!!!!!!!");	

		//MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		//MultipartFile multiFile = multipartRequest.getFile("excelFile");
		
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		System.out.printf("%d", multiFile.getSize());
		System.out.println(""); 
		System.out.printf("%s", multiFile.getOriginalFilename());
		
		// C:\\JavaTools\\sts-bundle\\pivotal-tc-server-developer-3.1.3.SR1\\base-instance\\wtpwebapps\\springTest\\resources\\upload
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String attachPath = "resources/upload/";
		String fileName = multiFile.getOriginalFilename();
	
		try {
			multiFile.transferTo(new File(realPath + attachPath + fileName));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/excelDownload")
	public String excelDownload(HttpServletResponse response, Model model) {
		logger.info("Welcome excelDownload !!!!!!!!");
		
		String excelFileName = "codetest.xlsx";
		
		logger.info("File Name : {}", Paths.get(excelFileName).toString());

		model.addAttribute("excelList", new ArrayList<String>(Arrays.asList("Kim Seok Hyun", "1234", "Korea Seoul")));
		
		return "excelView";
	}
	
}
