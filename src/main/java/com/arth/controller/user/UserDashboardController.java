package com.arth.controller.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.arth.entity.UserEntity;
import com.arth.repository.ExpenseRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserDashboardController {

	@Autowired
	ExpenseRepository expRepo;
	
	@GetMapping("/userdashboard")
	public String userDashboard(HttpSession session,Model model) {
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		
		LocalDate l = LocalDate.now(); 
		Integer month = l.getMonth().getValue();
		Integer year = l.getYear();

		Integer currentMonthExp = expRepo.getCurrentMonthExpAmountByUserId(month, user.getUserId());
		model.addAttribute("currentMonthExp",currentMonthExp);
		
		Integer currentYearExp = expRepo.getCurrentYearExpAmountByUserId(year, user.getUserId());
		model.addAttribute("currentYearExp",currentYearExp);
		
		
		
		
		return "UserDashboard";
	}

	@GetMapping("/myprofile")
	public String myProfile() {
		return "MyProfile";
	}
	
	@GetMapping("/editprofile")
	public String editProfile() {
		return "EditMyProfile";
	}
	


}
