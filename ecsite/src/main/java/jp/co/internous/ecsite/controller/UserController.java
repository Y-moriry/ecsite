package jp.co.internous.ecsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.EntryRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.UserForm;

@Controller
@RequestMapping("/ecsite/user")
public class UserController {
	
	@Autowired
	private EntryRepository entryRepos;
	
	@Autowired
	private UserRepository userRepos;
	
	@RequestMapping("/")
	public String userIndex() {
	
		return "entry";
	}
	
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm f) {
		int count = userRepos.findCountByUserName(f.getUserName());
		return count > 0;
	}

	
	@PostMapping("addUser")
	public String userWelcome(UserForm userForm, Model model) {
		
		model.addAttribute("userName", userForm.getUserName());
		model.addAttribute("password", userForm.getPassword());
		
		User user = new User();
		user.setUserName(userForm.getUserName());
		user.setPassword(userForm.getPassword());
		user.setFullName(userForm.getFullName());
		entryRepos.saveAndFlush(user);
		
		return "forward:/ecsite/";
		
	}
	
}
