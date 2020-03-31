package com.spring.boot.rocks.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.boot.rocks.model.AppPaginationModel;
import com.spring.boot.rocks.model.AppRole;
import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.model.GenerateCSVReport;
import com.spring.boot.rocks.model.GenerateExcelReport;
import com.spring.boot.rocks.model.GeneratePdfReport;
import com.spring.boot.rocks.repository.AppRoleJPARepository;
import com.spring.boot.rocks.repository.AppUserJPARepository;
import com.spring.boot.rocks.service.AppUserService;
import com.spring.boot.rocks.validator.AppUserAddValidator;
import com.spring.boot.rocks.validator.AppUserEditValidator;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

@Controller
@RequestMapping("/")
@SessionAttributes({ "roles", "programareas" })
public class AppUserController {
	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppRoleJPARepository appRoleJPARepository;

	@Autowired
	private AppUserJPARepository appUserJPARepository;

	@Autowired
	private AppUserAddValidator userAddValidator;

	@Autowired
	private AppUserEditValidator userEditValidator;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String root(Model model) {
//		return "redirect:userlist";
		return "home";
	}
	
	
	@RequestMapping(value = { "home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = { "userlist" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<AppUser> users = appUserService.findAllUsers();
		model.addAttribute("users", users);
		return "userlist";
	}
	
	@RequestMapping(value = { "usersearch" }, method = RequestMethod.GET)
	public String findByUsernameLike(@RequestParam("userName") String username, ModelMap model) {
		List<AppUser> users = appUserJPARepository.findByUsernameLike(username);
		model.addAttribute("users", users);
		return "userlist";
	}

	@RequestMapping(value = { "userlist2" }, method = RequestMethod.GET, produces = "text/html")
	public String showuserList(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {

		int BUTTONS_TO_SHOW = 9;
		int INITIAL_PAGE = 0;
		int INITIAL_PAGE_SIZE = 5;
		int[] PAGE_SIZES = { 1, 5, 10, 15, 20, 25, 50, 100 };
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

//		System.out.println("AppUser repo : " + appusersRepository.findAll());
		Page<AppUser> users = appUserJPARepository
				.findAll(PageRequest.of(evalPage, evalPageSize, Sort.by(Order.asc("id"))));
//		System.out.println("AppUser list get total pages : " + appuserlist.getTotalPages() + "appuser list get number "	+ appuserlist.getNumber());
		AppPaginationModel pager = new AppPaginationModel(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("users", users);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);
		return "userlist2";
	}

	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("addnewuser", true);
		model.addAttribute("userForm", new AppUser());
		return "userregistration";
	}

	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") AppUser userForm, BindingResult bindingResult, Model model) {
		model.addAttribute("addnewuser", true);
		userAddValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "userregistration";
		}
		appUserService.save(userForm);
		model.addAttribute("addusersuccess", true);
//		model.addAttribute("success", "User created successfully:<br><hr/>" + "ID: "+ userForm.getId() + "<br>ID: "+userForm.getUsername() + "<br>ID: "+userForm.getUseremail()
//				+ "<br>ID: "+userForm.getUserfirstname() + "<br>ID: "+userForm.getUserlastname());
		model.addAttribute("addsuccess", "<div class=\"jumbotron jumbo\">\n" +
//				"ID: " + userForm.getId() + "<br>Username: "
//				+ userForm.getUsername() + "<br>EmailID: " + userForm.getUseremail() + "<br>FirstName: "
//				+ userForm.getUserfirstname() + "<br>LastName: " + userForm.getUserlastname() + "<br>Address: "
//				+ userForm.getUseraddress() + "<br>Roles: " + userForm.getRoles()
//				
//				+
				"<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "	ID: </label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"id\">" + userForm.getId() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"username\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "Username :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"username\">" + userForm.getUsername() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useremail\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "EmailID :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useremail\">" + userForm.getUseremail() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "FirstName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userfirstname\">" + userForm.getUserfirstname()
				+ "</p>\n" + "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userlastname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "LastName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userlastname\">" + userForm.getUserlastname() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useraddress\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Address :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useraddress\">" + userForm.getUseraddress() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"roles\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Roles :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"roles\">"
				+ userForm.getRoles().toString().replace("AppRole [id=", "").replace("1, name=", "")
						.replace("2, name=", "").replace("3, name=", "").replace("[", "").replace("]", "")
						.replace("]]", "")
				+ "</p>\n" + "						</div>"

				+ "</p>\n</div><hr/>");
		return "success";

	}

	@RequestMapping(value = { "edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, Model model) {
		AppUser user = appUserService.findByUsername(username);
		model.addAttribute("userForm", user);
		model.addAttribute("edit", true);
		model.addAttribute("editexistinguser", true);
		return "userregistration";
	}

	@RequestMapping(value = { "edit-user-{username}" }, method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userForm") @Valid AppUser userForm, BindingResult bindingResult,
			Model model, @PathVariable String username) {
		model.addAttribute("editexistinguser", true);
		userEditValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "userregistration";
		}

		appUserService.updateUser(userForm);
		model.addAttribute("editusersuccess", true);
		model.addAttribute("editsuccess", "<div class=\"jumbotron jumbo\">\n" +
//				"ID: " + userForm.getId() + "<br>Username: "
//				+ userForm.getUsername() + "<br>EmailID: " + userForm.getUseremail() + "<br>FirstName: "
//				+ userForm.getUserfirstname() + "<br>LastName: " + userForm.getUserlastname() + "<br>Address: "
//				+ userForm.getUseraddress() + "<br>Roles: " + userForm.getRoles()
//				
//				+
				"<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "	ID: </label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"id\">" + userForm.getId() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"username\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "Username :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"username\">" + userForm.getUsername() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useremail\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "EmailID :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useremail\">" + userForm.getUseremail() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "FirstName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userfirstname\">" + userForm.getUserfirstname()
				+ "</p>\n" + "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userlastname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "LastName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userlastname\">" + userForm.getUserlastname() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useraddress\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Address :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useraddress\">" + userForm.getUseraddress() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"roles\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Roles :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"roles\">"
				+ userForm.getRoles().toString().replace("AppRole [id=", "").replace("1, name=", "")
						.replace("2, name=", "").replace("3, name=", "").replace("[", "").replace("]", "")
						.replace("]]", "")+ "</p>\n" + "						</div>"

				+ "</p>\n</div><hr/>");
		return "success";
	}

	@RequestMapping(value = { "view-user-{username}" }, method = RequestMethod.GET)
	public String viewUser(@PathVariable String username, Model model) {
		AppUser user = appUserService.findByUsername(username);
		model.addAttribute("userForm", user);
		return "userview";
	}

	@RequestMapping(value = { "delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username) {
		appUserService.deleteUserByUsername(username);
		return "redirect:userlist";
	}

	@ModelAttribute("roles")
	public List<AppRole> initializeRoles() {
		return (List<AppRole>) appRoleJPARepository.findAll();
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:login?logout";
	}

	@RequestMapping(value = "Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "useraccessDenied";
	}

	@RequestMapping(value = "/alluserreportPDF", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> allusersReport() throws IOException {

		List<AppUser> users = (List<AppUser>) appUserService.findAllUsers();

		ByteArrayInputStream bis = GeneratePdfReport.userReport(users);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=UsersReport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping(value = { "export-user-pdf-{username}" }, method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> exportUser(@PathVariable String username, Model model) {
		AppUser user = appUserService.findByUsername(username);
		model.addAttribute("userForm", user);
		ByteArrayInputStream bis = GeneratePdfReport.oneuserReport(user);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=" + username + ".pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@RequestMapping(value = "/alluserreportCSV", method = RequestMethod.GET)
	public void csvUsers(HttpServletResponse response) throws IOException {
		List<AppUser> users = (List<AppUser>) appUserService.findAllUsers();
		GenerateCSVReport.writeUsers(response.getWriter(), users);
		response.setHeader("Content-Disposition", "attachment; filename=AllUsersCSVReport.csv");
	}

	@RequestMapping(value = "/export-user-csv-{username}", method = RequestMethod.GET)
	public void usercsvReport(@PathVariable String username, HttpServletResponse response) throws IOException {
//    	 HttpHeaders headers = new HttpHeaders();
//	        headers.add("Content-Disposition", "inline; filename=" +username+".csv");
		response.setHeader("Content-Disposition", "attachment; filename=" + username + "CSVReport.csv");
		AppUser user = appUserService.findByUsername(username);
		GenerateCSVReport.writeUser(response.getWriter(), user);
	}

	@RequestMapping(value = "/export-user-xml-{username}", method = RequestMethod.GET)
	public @ResponseBody AppUser getUser(@PathVariable String username) {
		AppUser user = appUserService.findByUsername(username); // or set your own fields
		// user.setId(userid);
		// user.setUsername(username);
		// and so on....

		return user;
	}

	@GetMapping(value = "/alluserreportExcel")
	public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
		List<AppUser> users = (List<AppUser>) appUserService.findAllUsers();
		ByteArrayInputStream in = GenerateExcelReport.usersToExcel(users);
		// return IO ByteArray(in);
		HttpHeaders headers = new HttpHeaders();
		// set filename in header
		headers.add("Content-Disposition", "attachment; filename=users.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@RequestMapping("/alluserreportJSON")
	public @ResponseBody String getusersJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		List<AppUser> userlist = null;
		@SuppressWarnings("unused")
		String exception = null;
		String arrayToJson = null;
		try {
			userlist = appUserService.findAllUsers();
			arrayToJson = objectMapper.writeValueAsString(userlist);
		} catch (Exception ex) {
			ex.printStackTrace();
			exception = ex.getMessage();
		}
		return arrayToJson;
	}

	@RequestMapping("/export-user-json-{username}")
	public @ResponseBody String getuserJSON(@PathVariable String username, HttpServletResponse response) {

		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		@SuppressWarnings("unused")
		String exception = null;
		String arrayToJson = null;
		try {
			AppUser user = appUserService.findByUsername(username);
			arrayToJson = objectMapper.writeValueAsString(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			exception = ex.getMessage();
		}
		return arrayToJson;
	}

	@RequestMapping(value = "jasper-HTMLEXPORT-report", method = RequestMethod.GET)
	public void report(HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(appUserService.jasperhtmlreport());
		InputStream inputStream = this.getClass().getResourceAsStream("/reports/jasperreport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public String getTimeStamp() {
		TimeZone mytimeZone = TimeZone.getTimeZone("EST");
		Calendar calendar = Calendar.getInstance(mytimeZone);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		simpleDateFormat.setTimeZone(mytimeZone);
		String setTimeStamp = simpleDateFormat.format(calendar.getTime());
		return setTimeStamp;
	}

}
