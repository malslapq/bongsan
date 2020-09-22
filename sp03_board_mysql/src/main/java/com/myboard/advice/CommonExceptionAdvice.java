package com.myboard.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public String common(Exception e, Model model) {
		System.out.println("예외발생");
		model.addAttribute("exception",e);
		e.printStackTrace();
		return "error_common";
	}
}
